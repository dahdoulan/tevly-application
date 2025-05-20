package org.group15.tveely.dao;

public interface UploadDao <T>{
    void uploadVideo(T video);
    String uploadVideoToObjectStorage(String processingPath, String title);
}
