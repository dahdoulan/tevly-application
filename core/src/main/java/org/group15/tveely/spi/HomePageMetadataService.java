package org.group15.tveely.spi;

import org.group15.tveely.DTOs.VideoMetadata;

import java.util.List;

public interface HomePageMetadataService {

    List<VideoMetadata> getHomePageMetadata() ;
    byte[] getThumbnailBytes(Long videoId);
}
