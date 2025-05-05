package org.group15.tveely.dao;

import org.group15.tveely.RatingEntity;

import java.util.List;
import java.util.Optional;

public interface RatingDao {

    void save(RatingEntity ratingEntity);
    Optional<RatingEntity> findByUserIdAndVideoId(Long userId, Long videoId);
    Optional<List<RatingEntity>> findByVideo_Id(Long videoId);
    Optional<RatingEntity> findByUser_Id(Long userId);

}
