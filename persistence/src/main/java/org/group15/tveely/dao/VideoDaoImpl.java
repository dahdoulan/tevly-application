package org.group15.tveely.dao;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.group15.tveely.DTOs.ThumbnailProjection;
import org.group15.tveely.DTOs.videometadata.VideoMetadata;
import org.group15.tveely.Video;
import org.group15.tveely.VideoEntity;
import org.group15.tveely.mappers.VideoEntityToVideo;
import org.group15.tveely.mappers.VideoToVideoEntity;
import org.group15.tveely.persistence.FileSystem;
import org.group15.tveely.dto.VideoDto;
import org.group15.tveely.repository.UploadRepository;
import org.group15.tveely.repository.VideoRepository;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class VideoDaoImpl implements VideoDao<Video> {

    private final VideoRepository videoRepository;
    private final VideoToVideoEntity videoToVideoEntity;
    private final FileSystem fileSystem;
    private final UploadRepository uploadRepository;

    @Override
    public void uploadVideo(Video videoEntity) {
        String path = videoEntity.getTitle();
        String directory = System.getenv("VIDEO_PROCESSING_DIRECTORY");
        try {
            Path videoPath = fileSystem.resolvePath(directory, path);
            fileSystem.storeVideo(videoEntity, videoPath);
            videoEntity.setProcessingPath(videoPath.toString());
            uploadRepository.save(videoToVideoEntity.map(videoEntity));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<VideoDto> findVideoByStatus(String status) {
        VideoEntityToVideo mapper = new VideoEntityToVideo();
        List<VideoEntity> entity = videoRepository.findVideoEntitiesByStatus(status);
        return entity.stream().map(mapper::map).toList();
    }
    @Transactional
    @Override
    public List<VideoEntity> findVideoEntityByStatus(String status) {

        return videoRepository.findVideoEntitiesByStatus(status);
    }


    @Override
    public void updateVideoStatus(VideoDto video, String status) {
        videoRepository.updateStatusById(video.getId(), status);
    }

    @Override
    public List<VideoMetadata> findByStatus(String status) {
        return videoRepository.findByStatus(status);
    }

    @Override
    public Optional<ThumbnailProjection> findThumbnailById(Long id) {
        return videoRepository.findThumbnailById(id);
    }

    @Transactional
    @Override
    public void updateVideo(VideoDto video) {
        VideoToVideoEntity mapper = new VideoToVideoEntity(null, null);
        videoRepository.save(mapper.map(video));
    }

    @Override
    public VideoEntity findVideoEntityById(Long id) {
        return videoRepository.findVideoEntityById(id);
    }

    @Override
    public void updateAverageRatingById(Long id, int averageRating) {
        videoRepository.updateAverageRatingById(id, averageRating);
    }

    @Override
    public void updateVideoStatus(Long id, String status) {
        videoRepository.updateStatusById(id, status);
    }
}
