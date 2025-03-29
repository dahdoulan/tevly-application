package org.group15.tveely.dao;

@FunctionalInterface
public interface UploadDao <T>{
    void uploadVideo(T video);
}
