package org.group15.tveely.mappers;

import org.group15.tveely.VideoEntity;
import org.group15.tveely.Video;
import org.group15.tveely.dao.UserDao;
import org.group15.tveely.models.VideoAdapter;
import lombok.RequiredArgsConstructor;
import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VideoToVideoEntity {

    private final CategoryToCategoryEntity categoryToCategoryEntity;
    private final UserDao userDao;

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
        videoEntity.setThumbnail(BlobProxy.generateProxy(video.getThumbnail()));
        videoEntity.setThumbnailUrl(video.getThumbnailUrl());
        videoEntity.setCategoryEntity(categoryToCategoryEntity.map(video.getCategory())
                .orElseThrow(() -> new IllegalArgumentException("Category not found")));
        videoEntity.setFilmmaker(userDao.findByEmail(video.getFilmmakerEmail())
                .orElseThrow(() -> new IllegalArgumentException("User not found")));

        videoEntity.setProcessingPath(video.getProcessingPath());
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
