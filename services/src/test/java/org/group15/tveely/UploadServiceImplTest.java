package org.group15.tveely;

import org.group15.tveely.dao.VideoDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class UploadServiceImplTest {

    private UploadServiceImpl uploadService;
    private VideoDao videoDao;
    private Video video;

    @BeforeEach
    void setUp() {
        videoDao = mock(VideoDao.class);
        uploadService = new UploadServiceImpl(videoDao);
        video = createVideo();
    }

    @Test
    void whenUploadVideo_thenShouldCallDao(){
        doNothing().when(videoDao).uploadVideo(video);
        uploadService.uploadVideo(video);
        verify(videoDao, times(1)).uploadVideo(video);
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