package org.group15.tveely.dao;

import lombok.AllArgsConstructor;
import org.group15.tveely.DTOs.ThumbnailProjection;
import org.group15.tveely.DTOs.videometadata.VideoMetadata;
import org.group15.tveely.VideoEntity;
import org.group15.tveely.mappers.VideoEntityToVideo;
import org.group15.tveely.mappers.VideoToVideoEntity;
import org.group15.tveely.dto.VideoDto;
import org.group15.tveely.repository.VideoRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class VideoAdapterDaoImpl implements VideoDao<VideoDto>{

    private final VideoRepository videoRepository;

    @Override
    public List<VideoDto> findVideoByStatus(String status) {
        VideoEntityToVideo mapper = new VideoEntityToVideo();
        List<VideoEntity> entity = videoRepository.findVideoEntitiesByStatus(status);
        return entity.stream().map(mapper::map).toList();
    }

    @Override
    public void uploadVideo(VideoDto video) {

    }

    @Override
    public List<VideoMetadata> findByStatus(String status) {
        return List.of();
    }

    @Override
    public Optional<ThumbnailProjection> findThumbnailById(Long id) {
        return Optional.empty();
    }

    @Override
    public void updateVideoStatus(VideoDto video, String status) {
        videoRepository.updateStatusById(video.getId(), status);
    }

    @Override
    public VideoEntity findVideoEntityById(Long id){
        return videoRepository.findVideoEntityById(id);
    }

    @Override
    public void updateAverageRatingById(Long id, int averageRating){
        videoRepository.updateAverageRatingById(id,averageRating);
    }

    @Override
    public List<VideoEntity> findVideoEntityByStatus(String status) {
        return List.of();
    }

    @Override
    public void updateVideo(VideoDto video) {
        VideoToVideoEntity mapper = new VideoToVideoEntity(null, null);
        videoRepository.save(mapper.map(video));
    }

    @Override
    public void updateVideoStatus(Long id, String status) {
        videoRepository.updateStatusById(id, status);
    }
}
