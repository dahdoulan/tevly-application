package org.group15.tveely.mappers;

import org.group15.tveely.EncodedVideoEntity;
import org.group15.tveely.dto.EncodedVideoDto;
import org.group15.tveely.models.EncodedVideoDtoImpl;

public class EncodedVideoEntityToEncodedVideo {

    public static EncodedVideoDto map(EncodedVideoEntity videoEntity) {
        VideoEntityToVideo mapper = new VideoEntityToVideo();
        EncodedVideoDtoImpl adapter = new EncodedVideoDtoImpl();
        adapter.setId(videoEntity.getId());
        adapter.setVideo(mapper.map(videoEntity.getVideo()));
        adapter.setUrl(videoEntity.getEncodedVideoUrl());
        adapter.setTitle(videoEntity.getEncodedVideoTitle());
        return adapter;
    }
}
