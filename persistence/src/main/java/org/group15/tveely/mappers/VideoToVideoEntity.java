package org.group15.tveely.mappers;

import lombok.RequiredArgsConstructor;
import org.group15.tveely.*;
import org.group15.tveely.dao.UserDao;
import org.group15.tveely.dto.VideoDto;
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
        videoEntity.setThumbnail(video.getThumbnail());
        videoEntity.setThumbnailUrl(video.getThumbnailUrl());
        videoEntity.setCategoryEntity(categoryToCategoryEntity.map(video.getCategory())
                .orElseThrow(() -> new IllegalArgumentException("Category not found")));
        videoEntity.setFilmmaker(userDao.findByEmail(video.getFilmmakerEmail())
                .orElseThrow(() -> new IllegalArgumentException("User not found")));
        videoEntity.setProcessingPath(video.getProcessingPath());
        return videoEntity;
    }

    public VideoEntity map(VideoDto video) {
        UserMapper userMapper = new UserMapper();
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
        videoEntity.setThumbnail(video.getThumbnail());
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setCategory(video.getCategory().getCategory());
        categoryEntity.setId(video.getCategory().getId());
        videoEntity.setFilmmaker(userMapper.toEntity(video.getFilmmaker()));
        videoEntity.setCategoryEntity(categoryEntity);
        return videoEntity;
    }

}
