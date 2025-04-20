package org.group15.tveely;

import org.group15.tveely.dao.UploadDao;
import org.group15.tveely.models.Video;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class UploadServiceImplTest {

    private UploadServiceImpl uploadService;
    private UploadDao uploadDao;
    private Video video;

    @BeforeEach
    void setUp() {
        uploadDao = mock(UploadDao.class);
        uploadService = new UploadServiceImpl(uploadDao);
        video = createVideo();
    }

    @Test
    void whenUploadVideo_thenShouldCallDao(){
        doNothing().when(uploadDao).uploadVideo(video);
        uploadService.uploadVideo(video);
        verify(uploadDao, times(1)).uploadVideo(video);
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