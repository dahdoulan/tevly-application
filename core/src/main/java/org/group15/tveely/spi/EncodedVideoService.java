package org.group15.tveely.spi;

import org.group15.tveely.models.EncodedVideoAdapter;

import java.util.List;

@FunctionalInterface
public interface EncodedVideoService {
    List<EncodedVideoAdapter> getEncodedVideo(Long id);
}
