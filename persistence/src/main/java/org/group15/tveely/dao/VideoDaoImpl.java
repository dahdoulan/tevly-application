package org.group15.tveely.dao;

import lombok.AllArgsConstructor;
import org.group15.tveely.DTOs.ThumbnailProjection;
import org.group15.tveely.DTOs.VideoMetadata;
import org.group15.tveely.mappers.VideoToVideoEntity;
import org.group15.tveely.Video;
import org.group15.tveely.repository.VideoRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class VideoDaoImpl implements VideoDao<Video> {

    private final VideoRepository videoRepository;
    private final VideoToVideoEntity videoToVideoEntity;

    @Override
    public void uploadVideo(Video videoEntity) {

        videoRepository.save(videoToVideoEntity.map(videoEntity));
    }

    @Override
    public List<VideoMetadata> findByStatus(String status) {
        return videoRepository.findByStatus(status);
    }

    @Override
    public Optional<ThumbnailProjection> findThumbnailById(Long id) {
        return videoRepository.findThumbnailById(id);
    }
}
