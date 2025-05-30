package org.group15.tveely.mappers;

import org.group15.tveely.EncodedVideoEntity;
import org.group15.tveely.dto.EncodedVideoDto;

public class EncodedVideoAdapterToEncodedVideoEntity {
    public EncodedVideoEntity map(EncodedVideoDto encodedVideoDto) {
        VideoToVideoEntity mapper = new VideoToVideoEntity(null, null);
        EncodedVideoEntity encodedVideoEntity = new EncodedVideoEntity();
        encodedVideoEntity.setVideo(mapper.map(encodedVideoDto.getVideo()));
        encodedVideoEntity.setEncodedVideoUrl(encodedVideoDto.getUrl());
        encodedVideoEntity.setEncodedVideoTitle(encodedVideoDto.getTitle());
        return encodedVideoEntity;
    }
}
