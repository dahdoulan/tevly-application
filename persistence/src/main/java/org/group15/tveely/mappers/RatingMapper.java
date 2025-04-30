package org.group15.tveely.mappers;

import lombok.RequiredArgsConstructor;
import org.group15.tveely.DTOs.rating.RatingRequest;
import org.group15.tveely.Rating;
import org.group15.tveely.RatingEntity;
import org.group15.tveely.UserEntity;
import org.group15.tveely.VideoEntity;
import org.group15.tveely.dao.UserDao;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class RatingMapper {

    private final UserDao userDao;


    public Rating ratingRequestToModel(RatingRequest ratingRequest) {
        if (ratingRequest == null) {
            return null;
        }

        UserEntity userEntity = userDao.findByEmail(ratingRequest.getEmail())
                .orElse(null); // Optional: throw if null

        if (userEntity == null) {
            throw new RuntimeException("User not found with email: " + ratingRequest.getEmail());
        }

        return new Rating(
                userEntity.getId(),
                ratingRequest.getVideoId(),
                ratingRequest.getRating()
        );
    }

    public RatingEntity ratingToEntity(Rating rating) {
        if (rating == null) {
            return null;
        }

        RatingEntity entity = new RatingEntity();

        entity.setUser(userDao.findById(rating.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found")));

        VideoEntity videoEntity = new VideoEntity();
        videoEntity.setId(rating.getVideoId());
        entity.setVideo(videoEntity);

        entity.setRating(rating.getRating());

        return entity;
    }



}
