package org.group15.tveely.mappers;

import org.group15.tveely.entities.VideoEntity;
import org.group15.tveely.models.EncodedVideo;
import org.group15.tveely.models.VideoAdapter;

public class VideoEntityToVideo {

    public VideoAdapter map(VideoEntity videoEntity) {
        EncodedVideo video = new EncodedVideo();
        video.setVideoUrl(videoEntity.getVideoUrl());
        video.setTitle(videoEntity.getTitle());
        video.setDescription(videoEntity.getDescription());
        video.setStatus(videoEntity.getStatus());
        video.setUploadDate(videoEntity.getUploadDate());
        video.setContent(video.getContent());
        return video;
    }
}
