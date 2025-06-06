package org.group15.tveely.spi;

import org.group15.tveely.DTOs.videometadata.VideoMetadataDTO;

import java.util.List;

public interface HomePageMetadataService {

    List<VideoMetadataDTO> getHomePageMetadata(String email) ;
    byte[] getThumbnailBytes(Long videoId);
}
