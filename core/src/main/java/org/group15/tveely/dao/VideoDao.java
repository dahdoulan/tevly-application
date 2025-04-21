package org.group15.tveely.dao;

import org.group15.tveely.DTOs.ThumbnailProjection;
import org.group15.tveely.DTOs.VideoMetadata;

import java.util.List;
import java.util.Optional;

public interface VideoDao<T>{
    void uploadVideo(T video);
    List<VideoMetadata> findByStatus(String status);
    Optional<ThumbnailProjection> findThumbnailById(Long id);
}
