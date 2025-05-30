package org.group15.tveely.spi;

import org.group15.tveely.dto.EncodedVideoDto;

import java.util.List;

@FunctionalInterface
public interface EncodedVideoService {
    List<EncodedVideoDto> getEncodedVideo(Long id);
}
