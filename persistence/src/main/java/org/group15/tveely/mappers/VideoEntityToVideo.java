package org.group15.tveely.mappers;

import org.group15.tveely.VideoEntity;
import org.group15.tveely.models.EncodedVideo;
import org.group15.tveely.models.VideoAdapter;

public class VideoEntityToVideo {

    public VideoAdapter map(VideoEntity videoEntity)  {
        EncodedVideo video = new EncodedVideo();
        video.setId(videoEntity.getId());
        video.setVideoUrl(videoEntity.getVideoUrl());
        video.setTitle(videoEntity.getTitle());
        video.setDescription(videoEntity.getDescription());
        video.setStatus(videoEntity.getStatus());
        video.setUploadDate(videoEntity.getUploadDate());
        video.setProcessingPath(videoEntity.getProcessingPath());
        video.setContent(videoEntity.getContent());
        return video;
    }
}
