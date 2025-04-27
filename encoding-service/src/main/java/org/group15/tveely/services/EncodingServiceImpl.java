package org.group15.tveely.services;

import lombok.AllArgsConstructor;
import org.group15.tveely.Video;
import org.group15.tveely.dao.VideoDao;
import org.group15.tveely.ffmpeg.FfmpegWrapper;
import org.group15.tveely.models.VideoAdapter;
import org.group15.tveely.spi.EncodingService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.group15.tveely.ffmpeg.FfmpegWrapper.BASH;

@Service
@AllArgsConstructor
@Slf4j
public class EncodingServiceImpl implements EncodingService {
    private final VideoDao<VideoAdapter> videoDao;
    private final ExecutorService executor = Executors.newFixedThreadPool(10);
    private final FfmpegWrapper ffmpeg = FfmpegWrapper.builder()
            .scale("1920:1080")
            .terminal(BASH)
            .expected("-c")
            .outputDir("C:\\encoded")
            .build();

    @Scheduled(fixedRate = 5000)
    @Override
    public void process() {
        videoDao.findVideoByStatus("NEW")
                .parallelStream()
                .forEach(video -> executor.submit(() -> {
                    try {
                        videoDao.updateVideoStatus(video, "PROCESSING");
                        int exitCode = ffmpeg.encode(video.getProcessingPath());
                        if(exitCode != 0) {
                            videoDao.updateVideoStatus(video, "ERROR");
                            throw new IllegalStateException("Error while processing video");
                        }
                        videoDao.updateVideoStatus(video, "PROCESSED");
                    } catch (Exception e) {
                        log.error("ERROR WHILE PROCESSING VIDEO | ERROR {}", e);
                    }
                }));
    }
}
