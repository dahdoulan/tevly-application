package org.group15.tveely.dao;

import org.group15.tveely.VideoEntity;
import org.group15.tveely.mappers.VideoToVideoEntity;
import org.group15.tveely.Video;
import org.group15.tveely.repository.UploadRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UploadDaoImplTest {

    private UploadDaoImpl dao;
    private UploadRepository repo;
    private final VideoToVideoEntity mapper = new VideoToVideoEntity();

    @BeforeEach
    void setUp() {
        repo = mock(UploadRepository.class);
        dao = new UploadDaoImpl(repo, mapper);
    }

    @Test
    void whenUploadVideo_thenRepoShouldBeCalled() {
        Video video = createVideo();
        when(repo.save(any())).thenReturn(new VideoEntity());
        dao.uploadVideo(video);
        verify(repo, times(1)).save(any(VideoEntity.class));
    }

    private Video createVideo(){
        Video video = new Video();
        video.setTitle("title");
        video.setDescription("description");
        video.setVideoUrl("videoUrl");
        video.setStatus("RAW");
        video.setThumbnailUrl("thumbnailUrl");
        video.setContent(new byte[]{});
        return video;
    }
}