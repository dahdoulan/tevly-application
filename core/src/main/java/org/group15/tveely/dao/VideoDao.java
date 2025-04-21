package org.group15.tveely.dao;

import org.group15.tveely.DTOs.VideoMetadata;

import java.util.List;

public interface VideoDao<T>{
    void uploadVideo(T video);
    List<VideoMetadata> findByStatus(String status);
}
