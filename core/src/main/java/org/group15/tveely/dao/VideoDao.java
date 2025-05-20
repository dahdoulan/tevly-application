package org.group15.tveely.dao;

import org.group15.tveely.DTOs.ThumbnailProjection;
import org.group15.tveely.DTOs.videometadata.VideoMetadata;
import org.group15.tveely.VideoEntity;
import org.group15.tveely.models.VideoAdapter;

import java.util.List;
import java.util.Optional;

public interface VideoDao <T>{
     List<VideoAdapter> findVideoByStatus(String status);
     void updateVideoStatus(VideoAdapter video, String status);
     void updateVideoStatus(Long id, String status);
    void updateVideo(VideoAdapter video);
    void uploadVideo(T video);
    List<VideoMetadata> findByStatus(String status);
    Optional<ThumbnailProjection> findThumbnailById(Long id);
    VideoEntity findVideoEntityById(Long id);
    void updateAverageRatingById(Long id, int averageRating);
    List<VideoEntity> findVideoEntityByStatus(String status);
}
