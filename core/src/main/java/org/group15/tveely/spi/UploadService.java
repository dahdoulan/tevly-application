package org.group15.tveely.spi;

@FunctionalInterface
public interface UploadService <T>{
    void uploadVideo(T video);
}
