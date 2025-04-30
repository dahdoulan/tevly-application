package org.group15.tveely.repository;

import org.group15.tveely.RatingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RatingRepository extends JpaRepository<RatingEntity, Long> {

 Optional<RatingEntity> findByUser_IdAndVideo_Id(Long userId, Long videoId);

}
