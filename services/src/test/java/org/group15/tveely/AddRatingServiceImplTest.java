package org.group15.tveely;
import org.group15.tveely.dao.RatingDao;
import org.group15.tveely.dao.VideoDao;
import org.group15.tveely.mappers.RatingMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AddRatingServiceImplTest {

    @Mock
    private RatingDao ratingDao;

    @Mock
    private RatingMapper ratingMapper;

    @Mock
    private VideoDao<Video> videoDao;

    @InjectMocks
    private AddRatingServiceImpl service;

    @Test
    void addRating_NewRating_SavesNewEntityAndUpdatesAverage() {
        // Given
        Rating rating = new Rating(1L, 1L, 5);
        RatingEntity newEntity = new RatingEntity();

        UserEntity user = new UserEntity();
        user.setId(1L);
        newEntity.setUser(user);

        VideoEntity video = new VideoEntity();
        video.setId(1L);
        newEntity.setVideo(video);

        newEntity.setRating(5);

        when(ratingDao.findByUserIdAndVideoId(1L, 1L)).thenReturn(Optional.empty());
        when(ratingMapper.ratingToEntity(rating)).thenReturn(newEntity);
        when(ratingDao.findByVideo_Id(1L)).thenReturn(Optional.of(List.of(newEntity)));

        // When
        service.addRating(rating);

        // Then
        verify(ratingDao).save(newEntity);
        verify(videoDao).updateAverageRatingById(1L, 5);
    }

    @Test
    void addRating_ExistingRating_UpdatesEntityAndUpdatesAverage() {
        // Given
        Rating rating = new Rating(1L, 1L, 5);
        RatingEntity existingEntity = new RatingEntity();
        UserEntity user = new UserEntity();
        user.setId(1L);

        VideoEntity video = new VideoEntity();
        video.setId(1L);

        existingEntity.setUser(user);
        existingEntity.setVideo(video);
        existingEntity.setRating(3);

        when(ratingDao.findByUserIdAndVideoId(1L, 1L)).thenReturn(Optional.of(existingEntity));
        when(ratingDao.findByVideo_Id(1L)).thenReturn(Optional.of(List.of(existingEntity)));

        // When
        service.addRating(rating);

        // Then
        assertThat(existingEntity.getRating()).isEqualTo(5);
        verify(ratingDao).save(existingEntity);
        verify(videoDao).updateAverageRatingById(1L, 5);
    }

    @Test
    void addRating_MultipleRatings_CalculatesCorrectAverage() {
        // Given
        Rating rating = new Rating(1L, 1L, 5);
        RatingEntity existing1 = new RatingEntity();
        existing1.setRating(4);
        RatingEntity existing2 = new RatingEntity();
        existing2.setRating(3);
        List<RatingEntity> existingRatings = List.of(existing1, existing2);

        when(ratingDao.findByUserIdAndVideoId(1L, 1L)).thenReturn(Optional.empty());
        RatingEntity newEntity = new RatingEntity();
        newEntity.setRating(5);
        when(ratingMapper.ratingToEntity(rating)).thenReturn(newEntity);
        when(ratingDao.findByVideo_Id(1L)).thenReturn(Optional.of(List.of(existing1, existing2, newEntity)));

        // When
        service.addRating(rating);

        // Then
        verify(videoDao).updateAverageRatingById(1L, 4); // (4+3+5)/3 = 4.0
    }

    @Test
    void addRating_AverageRoundsCorrectly() {
        // Given
        Rating rating = new Rating(1L, 1L, 3);
        RatingEntity existing1 = new RatingEntity();
        existing1.setRating(4);
        RatingEntity existing2 = new RatingEntity();
        existing2.setRating(3);
        List<RatingEntity> existingRatings = List.of(existing1, existing2);

        when(ratingDao.findByUserIdAndVideoId(1L, 1L)).thenReturn(Optional.empty());
        RatingEntity newEntity = new RatingEntity();
        newEntity.setRating(3);
        when(ratingMapper.ratingToEntity(rating)).thenReturn(newEntity);
        when(ratingDao.findByVideo_Id(1L)).thenReturn(Optional.of(List.of(existing1, existing2, newEntity)));

        // When
        service.addRating(rating);

        // Then
        verify(videoDao).updateAverageRatingById(1L, 3); // (4+3+3)/3 ≈ 3.33 → rounds to 3
    }

    @Test
    void addRating_UpdateSingleRating_UpdatesAverage() {
        // Given
        Rating rating = new Rating(1L, 1L, 5);
        RatingEntity existingEntity = new RatingEntity();

        UserEntity user = new UserEntity();
        user.setId(1L);
        VideoEntity video = new VideoEntity();
        video.setId(1L);

        existingEntity.setUser(user);
        existingEntity.setVideo(video);
        existingEntity.setRating(3);

        when(ratingDao.findByUserIdAndVideoId(1L, 1L)).thenReturn(Optional.of(existingEntity));
        when(ratingDao.findByVideo_Id(1L)).thenReturn(Optional.of(List.of(existingEntity)));

        // When
        service.addRating(rating);

        // Then
        verify(videoDao).updateAverageRatingById(1L, 5);
    }
}