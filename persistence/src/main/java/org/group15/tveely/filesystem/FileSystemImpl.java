package org.group15.tveely.filesystem;

import org.group15.tveely.models.FileSystem;
import org.group15.tveely.Video;
import org.group15.tveely.models.VideoAdapter;
import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;

@Component
public class FileSystemImpl implements FileSystem<Video> {

    @Override
    public Path resolvePath(String directory, String path) throws IOException {
        Path userDir = Paths.get(directory);
        if (!Files.exists(userDir)) {
            Files.createDirectories(userDir);
        }
        return userDir.resolve(path.concat(".mp4"));
    }

    @Override
    public void storeVideo(Video video, Path path){
        try {
            Files.write(path, video.getContent());
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void removeVideo(String path){
        File file = new File(path);
        if (file.delete() == false)
            throw new IllegalStateException("Could not delete file");
    }
}
