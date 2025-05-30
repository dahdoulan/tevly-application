package org.group15.tveely.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.group15.tveely.dao.EncodedVideoDao;
import org.group15.tveely.dao.UploadDao;
import org.group15.tveely.dao.VideoDao;
import org.group15.tveely.ffmpeg.FfmpegWrapper;
import org.group15.tveely.dto.EncodedVideoDto;
import org.group15.tveely.models.EncodedVideoDtoImpl;
import org.group15.tveely.persistence.FileSystem;
import org.group15.tveely.dto.VideoDto;
import org.group15.tveely.spi.EncodingService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.group15.tveely.enums.VideoStatus.*;
import static org.group15.tveely.ffmpeg.FfmpegWrapper.BASH;

@Service
@AllArgsConstructor
@Slf4j
public class EncodingServiceImpl implements EncodingService {
    private final FileSystem fileSystem;
    private final VideoDao<VideoDto> videoDao;
    private final EncodedVideoDao encodedVideoDao;
    private final UploadDao<EncodedVideoDto> uploadDao;
    private final ExecutorService executor = Executors.newFixedThreadPool(10);
    private final FfmpegWrapper ffmpeg = FfmpegWrapper.builder()
            .terminal(BASH)
            .expected("-c")
            .outputDir(System.getenv("ENCODING_OUTPUT_DIRECTORY"))
            .build();

    @Transactional
    @Scheduled(fixedRate = 5000)
    @Override
    public void process() {
        videoDao.findVideoByStatus(NEW.name())
                .forEach(video -> executor.submit(() -> {
                    try {
                        videoDao.updateVideoStatus(video, PROCESSING.name());
                        processVideo(video);
                        handleUpload(video);
                    } catch (Exception e) {
                        log.error("ERROR WHILE PROCESSING VIDEO | ERROR {}", e.getMessage());
                        videoDao.updateVideoStatus(video, FAILED.name());
                    }
                }));
    }

    private void processVideo(VideoDto video) throws Exception {
        int exitCode = ffmpeg.encode(video.getProcessingPath());
        if (exitCode != 0) {
            videoDao.updateVideoStatus(video, FAILED.name());
            log.error("ERROR WHILE PROCESSING VIDEO | EXIT CODE: {}", exitCode);
            throw new IllegalStateException("Error while processing video");
        }
    }

    private void handleUpload(VideoDto video) {
        videoDao.updateVideoStatus(video, PROCESSED.name());
        uploadToBlobStorage(video, "720p");
        uploadToBlobStorage(video, "1080p");
        fileSystem.removeVideo(video.getProcessingPath());
        video.setStatus(ENCODED.name());
        videoDao.updateVideo(video);
    }

    private void uploadToBlobStorage(VideoDto video, String resolution) {
        String videoName = resolveEncodedVideoPath(resolveVideoName(video.getProcessingPath()), resolution);
        String title = generateTitle(resolution);
        String videoUrl = uploadDao.uploadVideoToObjectStorage(videoName, title);
        EncodedVideoDto encodedVideoEntity = createEncodedVideo(video, videoUrl, title);
        encodedVideoDao.saveVideo(encodedVideoEntity);
    }

    private EncodedVideoDto createEncodedVideo(VideoDto video, String url, String title) {
        return mapTodEncodedVideoAdapter(video, url, title);
    }

    private String resolveEncodedVideoPath(String videoName, String resolution) {
        return "C:/encoded/".concat(videoName)
                .concat("/" + resolution + "/")
                .concat("stream.mp4");
    }

    private String resolveVideoName(String processingPath) {
        String fullFileName = processingPath.substring(processingPath
                .lastIndexOf("\\") + 1);
        return fullFileName.replaceFirst("[.][^.]+$", "");
    }

    private EncodedVideoDto mapTodEncodedVideoAdapter(VideoDto videoDto, String url, String title) {
        EncodedVideoDtoImpl videoAdapterImpl = new EncodedVideoDtoImpl();
        videoAdapterImpl.setVideo(videoDto);
        videoAdapterImpl.setUrl(url);
        videoAdapterImpl.setTitle(title);
        return videoAdapterImpl;
    }

    private String generateTitle(String resolution) {
        return resolution
                .concat("-".concat(UUID.randomUUID().toString())).concat(".mp4");
    }
}
