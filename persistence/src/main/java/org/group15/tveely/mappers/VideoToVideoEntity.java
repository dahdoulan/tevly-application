package org.group15.tveely.mappers;

import lombok.RequiredArgsConstructor;
import org.group15.tveely.VideoEntity;
import org.group15.tveely.Video;
import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VideoToVideoEntity {

    private final CategoryToCategoryEntity categoryToCategoryEntity;

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
        videoEntity.setThumbnail(BlobProxy.generateProxy(video.getThumbnail()));
        videoEntity.setThumbnailUrl(video.getThumbnailUrl());
        videoEntity.setCategoryEntity(categoryToCategoryEntity.map(video.getCategory())
                .orElseThrow(() -> new IllegalArgumentException("Category not found")));
        return videoEntity;
    }



}
