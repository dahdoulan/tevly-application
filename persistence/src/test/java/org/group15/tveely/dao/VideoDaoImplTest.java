package org.group15.tveely.dao;

import org.group15.tveely.VideoEntity;
import org.group15.tveely.mappers.CategoryToCategoryEntity;
import org.group15.tveely.mappers.VideoToVideoEntity;
import org.group15.tveely.Video;
import org.group15.tveely.repository.VideoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class VideoDaoImplTest {

    private VideoDaoImpl dao;
    private VideoRepository repo;
    private VideoToVideoEntity mapper;
    private CategoryToCategoryEntity categoryMapper;

    @BeforeEach
    void setUp() {
        repo = mock(VideoRepository.class);
        CategoryDao categoryDao = mock(CategoryDao.class);
        categoryMapper = new CategoryToCategoryEntity(categoryDao);
        mapper = new VideoToVideoEntity(categoryMapper);
        dao = new VideoDaoImpl(repo, mapper);
    }

    @Test
    void whenUploadVideo_thenRepoShouldBeCalled() {
        Video video = createVideo();
        when(repo.save(any())).thenReturn(new VideoEntity());
        dao.uploadVideo(video);
        verify(repo, times(1)).save(any(VideoEntity.class));
    }

    private Video createVideo() {
        Video video = new Video();
        video.setTitle("title");
        video.setDescription("description");
        video.setVideoUrl("videoUrl");
        video.setStatus("RAW");
        video.setThumbnailUrl("thumbnailUrl");
        video.setContent(new byte[]{});
        video.setThumbnail(new byte[]{});
        video.setCategory("Action");
        return video;
    }
}
