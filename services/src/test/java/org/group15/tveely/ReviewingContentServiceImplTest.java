package org.group15.tveely;

import org.group15.tveely.DTOs.ReviewingContentResponse;
import org.group15.tveely.dao.VideoDao;
import org.group15.tveely.mappers.ReviewingContentMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReviewingContentServiceImplTest {

    @Mock
    private VideoDao<Video> videoDao;

    @Mock
    private ReviewingContentMapper mapper;

    @InjectMocks
    private ReviewingContentServiceImpl service;

    @Test
    void getAllReviewingContent_ReturnsMappedDtos() {
        // Setup
        VideoEntity video1 = createVideoEntity(1L, "Test Video 1", "user1@example.com");
        VideoEntity video2 = createVideoEntity(2L, "Test Video 2", "user2@example.com");

        ReviewingContentResponse dto1 = createResponseDto(1L, "Test Video 1", "user1@example.com");
        ReviewingContentResponse dto2 = createResponseDto(2L, "Test Video 2", "user2@example.com");

        when(videoDao.findVideoEntityByStatus("ENCODED")).thenReturn(List.of(video1, video2));
        when(mapper.toDto(video1)).thenReturn(dto1);
        when(mapper.toDto(video2)).thenReturn(dto2);

        // Execute
        List<ReviewingContentResponse> result = service.getAllReviewingContent();

        // Verify
        assertThat(result)
                .hasSize(2)
                .extracting(ReviewingContentResponse::getId)
                .containsExactly(1L, 2L);

        verify(videoDao).findVideoEntityByStatus("ENCODED");
    }

    @Test
    void getAllReviewingContent_NoContent_ReturnsEmptyList() {
        when(videoDao.findVideoEntityByStatus("ENCODED")).thenReturn(List.of());
        List<ReviewingContentResponse> result = service.getAllReviewingContent();
        assertThat(result).isEmpty();
    }

    @Test
    void approveContent_UpdatesStatusToApproved() {
        Long contentId = 123L;
        service.approveContent(contentId);
        verify(videoDao).updateVideoStatus(contentId, "APPROVED");
    }

    @Test
    void rejectContent_UpdatesStatusToRejected() {
        Long contentId = 456L;
        service.rejectContent(contentId);
        verify(videoDao).updateVideoStatus(contentId, "REJECTED");
    }

    private VideoEntity createVideoEntity(Long id, String title, String filmmakerEmail) {
        UserEntity filmmaker = new UserEntity();
        filmmaker.setEmail(filmmakerEmail);
        filmmaker.setFirstname("John");
        filmmaker.setLastname("Doe");

        CategoryEntity category = new CategoryEntity();
        category.setCategory("Education");

        VideoEntity video = new VideoEntity();
        video.setId(id);
        video.setTitle(title);
        video.setDescription("Test Description");
        video.setVideoUrl("http://example.com/video.mp4");
        video.setFilmmaker(filmmaker);
        video.setCategoryEntity(category);
        video.setThumbnailUrl("http://example.com/thumbnail.jpg");
        video.setStatus("ENCODED");

        return video;
    }

    private ReviewingContentResponse createResponseDto(Long id, String title, String filmmakerEmail) {
        return ReviewingContentResponse.builder()
                .id(id)
                .title(title)
                .description("Test Description")
                .videoUrl("http://example.com/video.mp4")
                .filmmakerEmail(filmmakerEmail)
                .filmmakerFullName("John Doe")
                .thumbnailUrl("http://example.com/thumbnail.jpg")
                .category("Education")
                .build();
    }
}