package org.group15.tveely;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.group15.tveely.DTOs.comment.CommentResponseDTO;
import org.group15.tveely.DTOs.videometadata.VideoMetadata;
import org.group15.tveely.DTOs.videometadata.VideoMetadataDTO;
import org.group15.tveely.dao.CommentDao;
import org.group15.tveely.dao.RatingDao;
import org.group15.tveely.dao.UserDao;
import org.group15.tveely.dao.VideoDao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class HomePageMetadataServiceImplTest {

    @Mock
    private VideoDao<Video> videoDao;

    @Mock
    private CommentDao commentDao;

    @Mock
    private RatingDao ratingDao;

    @Mock
    private UserDao userDao;

    @InjectMocks
    private HomePageMetadataServiceImpl service;

    @Test
    void getHomePageMetadata_UserExistsAndHasRating_ReturnsDTOWithUserRating() {
        // Mock VideoMetadata
        VideoMetadata video = mock(VideoMetadata.class);
        when(video.getId()).thenReturn(1L);
        when(video.getTitle()).thenReturn("Test Video");
        when(video.getDescription()).thenReturn("Test Description");
        when(video.getVideoUrl()).thenReturn("http://example.com");
        CategoryEntity category = mock(CategoryEntity.class);
        when(video.getCategoryEntity()).thenReturn(category);
        when(video.getAverageRating()).thenReturn(4);
        when(videoDao.findByStatus("APPROVED")).thenReturn(List.of(video));

        // Mock User
        UserEntity user = new UserEntity();
        user.setId(123L);
        when(userDao.findByEmail("user@example.com")).thenReturn(Optional.of(user));

        // Mock Comments
        CommentEntity comment = new CommentEntity();
        comment.setComment("Great video!");
        UserEntity commentUser = new UserEntity();
        commentUser.setFirstname("Alice");
        commentUser.setLastname("Smith");
        comment.setUser(commentUser);
        comment.setCreateDate(LocalDateTime.now());
        when(commentDao.findByVideoId(1L)).thenReturn(List.of(comment));

        // Mock Rating
        RatingEntity rating = new RatingEntity();
        rating.setRating(5);
        when(ratingDao.findByUserIdAndVideoId(123L, 1L)).thenReturn(Optional.of(rating));

        // Execute
        List<VideoMetadataDTO> result = service.getHomePageMetadata("user@example.com");

        // Verify DTO
        assertThat(result).hasSize(1);
        VideoMetadataDTO dto = result.get(0);
        assertThat(dto.getId()).isEqualTo(1L);
        assertThat(dto.getUserRating()).isEqualTo(5);
        assertThat(dto.getAverageRating()).isEqualTo(4);
        assertThat(dto.getComments()).hasSize(1);

        CommentResponseDTO commentDTO = dto.getComments().get(0);
        assertThat(commentDTO.getComment()).isEqualTo("Great video!");
        assertThat(commentDTO.getFullName()).isEqualTo("Alice Smith");

        // Verify DAO interactions
        verify(videoDao).findByStatus("APPROVED");
        verify(userDao).findByEmail("user@example.com");
        verify(commentDao).findByVideoId(1L);
        verify(ratingDao).findByUserIdAndVideoId(123L, 1L);
    }

    @Test
    void getHomePageMetadata_UserNotFound_ReturnsDTOWithZeroUserRating() {
        VideoMetadata video = mock(VideoMetadata.class);
        when(video.getId()).thenReturn(1L);
        when(videoDao.findByStatus("APPROVED")).thenReturn(List.of(video));

        when(userDao.findByEmail("unknown@example.com")).thenReturn(Optional.empty());
        when(commentDao.findByVideoId(1L)).thenReturn(List.of());

        List<VideoMetadataDTO> result = service.getHomePageMetadata("unknown@example.com");

        assertThat(result.get(0).getUserRating()).isZero();
        verify(ratingDao, never()).findByUserIdAndVideoId(any(), any());
    }

    @Test
    void getHomePageMetadata_UserHasNotRatedVideo_UserRatingIsZero() {
        VideoMetadata video = mock(VideoMetadata.class);
        when(video.getId()).thenReturn(1L);
        when(videoDao.findByStatus("APPROVED")).thenReturn(List.of(video));

        UserEntity user = new UserEntity();
        user.setId(123L);
        when(userDao.findByEmail("user@example.com")).thenReturn(Optional.of(user));
        when(commentDao.findByVideoId(1L)).thenReturn(List.of());
        when(ratingDao.findByUserIdAndVideoId(123L, 1L)).thenReturn(Optional.empty());

        List<VideoMetadataDTO> result = service.getHomePageMetadata("user@example.com");

        assertThat(result.get(0).getUserRating()).isZero();
    }

    @Test
    void getHomePageMetadata_VideoHasNoComments_CommentsListIsEmpty() {
        VideoMetadata video = mock(VideoMetadata.class);
        when(video.getId()).thenReturn(1L);
        when(videoDao.findByStatus("APPROVED")).thenReturn(List.of(video));

        when(userDao.findByEmail("user@example.com")).thenReturn(Optional.empty());
        when(commentDao.findByVideoId(1L)).thenReturn(List.of());

        List<VideoMetadataDTO> result = service.getHomePageMetadata("user@example.com");

        assertThat(result.get(0).getComments()).isEmpty();
    }
}
