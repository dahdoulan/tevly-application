package org.group15.tveely.dao;

import lombok.AllArgsConstructor;
import org.group15.tveely.RatingEntity;
import org.group15.tveely.repository.RatingRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
@AllArgsConstructor
@Component
public class RatingDaoImpl implements RatingDao {
    RatingRepository ratingRepository;

    @Override
    public void save(RatingEntity ratingEntity) {

        ratingRepository.save(ratingEntity);
    }



    @Override
    public Optional<RatingEntity> findByUserIdAndVideoId(Long userId, Long videoId) {
        return ratingRepository.findByUser_IdAndVideo_Id(userId, videoId);
    }


    @Override
    public Optional<List<RatingEntity>> findByVideo_Id(Long videoId){
        return ratingRepository.findByVideo_Id(videoId);
    }

}