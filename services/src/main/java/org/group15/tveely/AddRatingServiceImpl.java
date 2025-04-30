package org.group15.tveely;

import lombok.AllArgsConstructor;
import org.group15.tveely.dao.RatingDao;
import org.group15.tveely.mappers.RatingMapper;
import org.group15.tveely.spi.AddRatingService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AddRatingServiceImpl implements AddRatingService {

    private final RatingDao ratingDao;
    private final RatingMapper ratingMapper;

    @Override
    public void addRating(Rating rating) {
        Optional<RatingEntity> existingRating = ratingDao.findByUserIdAndVideoId(
                rating.getUserId(),
                rating.getVideoId()
        );

        if (existingRating.isPresent()) {
            RatingEntity entity = existingRating.get();
            entity.setRating(rating.getRating());
            ratingDao.save(entity);
        } else {
            ratingDao.save(ratingMapper.ratingToEntity(rating));
        }
    }
}
