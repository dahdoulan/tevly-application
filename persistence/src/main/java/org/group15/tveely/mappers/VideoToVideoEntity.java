package org.group15.tveely.mappers;

import org.group15.tveely.entities.VideoEntity;
import org.group15.tveely.models.Video;
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
        videoEntity.setUpdatedAt(video.getUploadDate());
        videoEntity.setCreatedAt(video.getUploadDate());
        videoEntity.setContent(BlobProxy.generateProxy(video.getContent()));
        return videoEntity;
    }
}
