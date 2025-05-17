package org.group15.tveely.mappers;

import org.group15.tveely.EncodedVideoEntity;
import org.group15.tveely.models.EncodedVideoAdapter;

public class EncodedVideoAdapterToEncodedVideoEntity {
    public EncodedVideoEntity map(EncodedVideoAdapter encodedVideoAdapter) {
        VideoToVideoEntity mapper = new VideoToVideoEntity();
        EncodedVideoEntity encodedVideoEntity = new EncodedVideoEntity();
        encodedVideoEntity.setVideo(mapper.map(encodedVideoAdapter.getVideo()));
        encodedVideoEntity.setEncodedVideoUrl(encodedVideoAdapter.getUrl());
        encodedVideoEntity.setEncodedVideoTitle(encodedVideoAdapter.getTitle());
        return encodedVideoEntity;
    }
}
