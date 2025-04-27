package org.group15.tveely.dao;

import lombok.AllArgsConstructor;
import org.group15.tveely.filesystem.FileSystem;
import org.group15.tveely.mappers.VideoToVideoEntity;
import org.group15.tveely.Video;
import org.group15.tveely.repository.UploadRepository;
import org.springframework.stereotype.Component;

import java.nio.file.Path;

@Component
@AllArgsConstructor
public class UploadDaoImpl implements UploadDao {

    private final UploadRepository uploadRepository;
    private final VideoToVideoEntity videoToVideoEntity;
    private final FileSystem fileSystem;

    @Override
    public void uploadVideo(Video videoEntity) {
        String path = videoEntity.getTitle();
        String directory = "./resources/users/";
        try{
            Path videoPath = fileSystem.resolvePath(directory, path);
            fileSystem.storeVideo(videoEntity, videoPath);
            videoEntity.setProcessingPath(videoPath.toString());
            uploadRepository.save(videoToVideoEntity.map(videoEntity));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
