package org.group15.tveely.mappers;

import org.group15.tveely.entities.EncodedVideoEntity;
import org.group15.tveely.models.EncodedVideoAdapter;
import org.group15.tveely.models.EncodedVideoAdapterImpl;

public class EncodedVideoEntityToEncodedVideo {

    public static EncodedVideoAdapter map(EncodedVideoEntity videoEntity) {
        VideoEntityToVideo mapper = new VideoEntityToVideo();
        EncodedVideoAdapterImpl adapter = new EncodedVideoAdapterImpl();
        adapter.setId(videoEntity.getId());
        adapter.setVideo(mapper.map(videoEntity.getVideo()));
        adapter.setUrl(videoEntity.getEncodedVideoUrl());
        adapter.setTitle(videoEntity.getEncodedVideoTitle());
        return adapter;
    }
}
