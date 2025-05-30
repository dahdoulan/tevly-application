package org.group15.tveely.dao;

import org.group15.tveely.dto.EncodedVideoDto;

import java.util.List;

public interface EncodedVideoDao {
    void saveVideo(EncodedVideoDto encodedVideo);
    List<EncodedVideoDto> getVideosById(Long id);
}
