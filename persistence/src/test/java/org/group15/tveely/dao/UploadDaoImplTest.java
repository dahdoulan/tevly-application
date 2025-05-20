//package org.group15.tveely.dao;
//
//import org.group15.tveely.VideoEntity;
//import org.group15.tveely.filesystem.FileSystemImpl;
//import org.group15.tveely.mappers.VideoToVideoEntity;
//import org.group15.tveely.models.Video;
//import org.group15.tveely.repository.UploadRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.io.IOException;
//import java.nio.file.Path;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//class UploadDaoImplTest {
//
//    private UploadDaoImpl dao;
//    private UploadRepository repo;
//    private final VideoToVideoEntity mapper = new VideoToVideoEntity();
//    private FileSystemImpl fileSystemImpl;
//
//    @BeforeEach
//    void setUp() {
//        fileSystemImpl = mock(FileSystemImpl.class);
//        repo = mock(UploadRepository.class);
//        dao = new UploadDaoImpl(repo, mapper, fileSystemImpl);
//    }
//
//    @Test
//    void whenUploadVideo_thenRepoShouldBeCalled() throws IOException {
//        Video video = createVideo();
//        when(repo.save(any())).thenReturn(new VideoEntity());
//        doNothing().when(fileSystemImpl).storeVideo(any(), any());
//        when(fileSystemImpl.resolvePath(any(), any())).thenReturn(Path.of("somePath"));
//        dao.uploadVideo(video);
//        verify(repo, times(1)).save(any(VideoEntity.class));
//    }
//
//    private Video createVideo() {
//        Video video = new Video();
//        video.setTitle("title");
//        video.setDescription("description");
//        video.setVideoUrl("videoUrl");
//        video.setStatus("RAW");
//        video.setThumbnailUrl("thumbnailUrl");
//        video.setContent(new byte[]{});
//        return video;
//    }
//}