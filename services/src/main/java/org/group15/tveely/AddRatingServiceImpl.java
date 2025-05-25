package org.group15.tveely;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.group15.tveely.dao.RatingDao;
import org.group15.tveely.dao.VideoDao;
import org.group15.tveely.mappers.RatingMapper;
import org.group15.tveely.models.RatingAdapter;
import org.group15.tveely.spi.AddRatingService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AddRatingServiceImpl implements AddRatingService {

    private final RatingDao ratingDao;
    private final RatingMapper ratingMapper;
    private final VideoDao<Video> videoDao;
    @Transactional
    @Override
    public void addRating(RatingAdapter rating) {
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

        Optional<List<RatingEntity>> existingRatings = ratingDao.findByVideo_Id(rating.getVideoId());


        if (existingRatings.isPresent() && !existingRatings.get().isEmpty()) {
            List<RatingEntity> ratings = existingRatings.get();

            int sum = ratings.stream().mapToInt(RatingEntity::getRating).sum();
            int count = ratings.size();

            // Round average to nearest integer
            int averageRating = Math.round((float) sum / count);

            videoDao.updateAverageRatingById(rating.getVideoId(), averageRating);
        }


    }
}
