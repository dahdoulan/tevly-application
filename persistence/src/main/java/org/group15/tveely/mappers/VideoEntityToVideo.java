package org.group15.tveely.mappers;

import org.group15.tveely.CategoryDtoImpl;
import org.group15.tveely.VideoEntity;
import org.group15.tveely.models.EncodedVideo;
import org.group15.tveely.dto.VideoDto;
import org.springframework.stereotype.Component;

@Component
public class VideoEntityToVideo {
    public VideoDto map(VideoEntity videoEntity){
        UserMapper userMapper = new UserMapper();
        EncodedVideo video = new EncodedVideo();
        video.setId(videoEntity.getId());
        video.setVideoUrl(videoEntity.getVideoUrl());
        video.setTitle(videoEntity.getTitle());
        video.setDescription(videoEntity.getDescription());
        video.setStatus(videoEntity.getStatus());
        video.setUploadDate(videoEntity.getUploadDate());
        video.setProcessingPath(videoEntity.getProcessingPath());
        video.setContent(videoEntity.getContent());
        try{
            video.setThumbnail(videoEntity.getThumbnail());
        }catch (Exception e){
            e.printStackTrace();
        }
        CategoryDtoImpl categoryDtoImpl = new CategoryDtoImpl();
        categoryDtoImpl.setId(videoEntity.getCategoryEntity().getId());
        categoryDtoImpl.setCategory(videoEntity.getCategoryEntity().getCategory());
        video.setFilmmaker(userMapper.toModel(videoEntity.getFilmmaker()));
        video.setCategory(categoryDtoImpl);
        return video;
    }
}
