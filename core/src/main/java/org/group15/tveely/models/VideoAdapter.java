package org.group15.tveely.models;

import java.time.LocalDateTime;

public interface VideoAdapter {
    Long getId();
    String getTitle();
    LocalDateTime getUploadDate();
    String getStatus();
    String getDescription();
    String getThumbnailUrl();
    String getVideoUrl();
    String getProcessingPath();
    byte[] getContent();

    void setId(Long id);
    void setTitle(String title);
    void setUploadDate(LocalDateTime uploadDate);
    void setStatus(String status);
    void setDescription(String description);
    void setThumbnailUrl(String thumbnailUrl);
    void setVideoUrl(String videoUrl);
    void setContent(byte[] content);
    void setProcessingPath(String processingPath);
}
