package org.group15.tveely.models;

import java.io.IOException;
import java.nio.file.Path;

public interface FileSystem <T>{
    Path resolvePath(String directory, String path) throws IOException;
    void storeVideo(T video, Path path);
    void removeVideo(String path);
}
