package org.group15.tveely.filesystem;

import org.group15.tveely.Video;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class FileSystem {

    public Path resolvePath(String directory, String path) throws IOException {
        Path userDir = Paths.get(directory);
        if (!Files.exists(userDir)) {
            Files.createDirectories(userDir);
        }
        return userDir.resolve(path.concat(".mp4"));
    }

    public void storeVideo(Video video, Path path){
        try {
            Files.write(path, video.getContent());
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
