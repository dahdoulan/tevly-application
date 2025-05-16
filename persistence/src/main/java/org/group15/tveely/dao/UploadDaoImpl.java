package org.group15.tveely.dao;

import lombok.AllArgsConstructor;
import org.group15.tveely.filesystem.FileSystemImpl;
import org.group15.tveely.mappers.VideoEntityToVideo;
import org.group15.tveely.mappers.VideoToVideoEntity;
import org.group15.tveely.models.FileSystem;
import org.group15.tveely.models.Video;
import org.group15.tveely.models.VideoAdapter;
import org.group15.tveely.repository.UploadRepository;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.UUID;

@Component
@AllArgsConstructor
public class UploadDaoImpl implements UploadDao<Video> {

    private final UploadRepository uploadRepository;
    private final VideoToVideoEntity videoToVideoEntity;
    private final FileSystem<Video> fileSystemImpl;

    @Override
    public void uploadVideo(Video video) {
        String path = video.getTitle().concat("-".concat(UUID.randomUUID().toString()));
        String directory = "./resources/users/";
        try{
            Path videoPath = fileSystemImpl.resolvePath(directory, path);
            fileSystemImpl.storeVideo(video, videoPath);
            video.setProcessingPath(videoPath.toString());
            uploadRepository.save((videoToVideoEntity.map(video)));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public String uploadVideoToObjectStorage(String processingPath, String title) {
        throw new IllegalStateException("Upload Dao Does NOT have an Implementation of uploadVideoToObjectStorage");
    }
}
