package org.group15.tveely.mappers;

import org.group15.tveely.entities.VideoEntity;
import org.group15.tveely.models.Video;
import org.group15.tveely.models.VideoAdapter;
import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.stereotype.Component;

@Component
public class VideoToVideoEntity {
    public VideoEntity map(Video video) {
        VideoEntity videoEntity = new VideoEntity();
        videoEntity.setVideoUrl(video.getVideoUrl());
        videoEntity.setTitle(video.getTitle());
        videoEntity.setDescription(video.getDescription());
        videoEntity.setStatus(video.getStatus());
        videoEntity.setUploadDate(video.getUploadDate());
        videoEntity.setProcessingPath(video.getProcessingPath());
        videoEntity.setUpdatedAt(video.getUploadDate());
        videoEntity.setCreatedAt(video.getUploadDate());
        videoEntity.setContent(video.getContent());
        return videoEntity;
    }

    public VideoEntity map(VideoAdapter video) {
        VideoEntity videoEntity = new VideoEntity();
        videoEntity.setId(video.getId());
        videoEntity.setVideoUrl(video.getVideoUrl());
        videoEntity.setTitle(video.getTitle());
        videoEntity.setDescription(video.getDescription());
        videoEntity.setStatus(video.getStatus());
        videoEntity.setUploadDate(video.getUploadDate());
        videoEntity.setUpdatedAt(video.getUploadDate());
        videoEntity.setCreatedAt(video.getUploadDate());
        videoEntity.setProcessingPath(video.getProcessingPath());
        videoEntity.setContent(video.getContent());
        return videoEntity;
    }
}
