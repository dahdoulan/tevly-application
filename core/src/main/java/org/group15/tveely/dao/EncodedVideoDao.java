package org.group15.tveely.dao;

import org.group15.tveely.models.EncodedVideoAdapter;

import java.util.List;

public interface EncodedVideoDao {
    void saveVideo(EncodedVideoAdapter encodedVideo);
    List<EncodedVideoAdapter> getVideosById(Long id);
}
