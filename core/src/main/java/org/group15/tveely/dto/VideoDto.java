package org.group15.tveely.dto;

import java.sql.SQLException;
import java.time.LocalDateTime;

public interface VideoDto {
    Long getId();
    String getTitle();
    LocalDateTime getUploadDate();
    String getStatus();
    String getDescription();
    String getThumbnailUrl();
    String getVideoUrl();
    String getProcessingPath();
    byte[] getContent();
    byte[] getThumbnail();
    CategoryDto getCategory();
    UserDetailsDto getFilmmaker();

    void setFilmmaker(UserDetailsDto filmmaker);
    void setCategory(CategoryDto category);
    void setId(Long id);
    void setTitle(String title);
    void setUploadDate(LocalDateTime uploadDate);
    void setStatus(String status);
    void setDescription(String description);
    void setThumbnailUrl(String thumbnailUrl);
    void setVideoUrl(String videoUrl);
    void setContent(byte[] content) throws SQLException;
    void setThumbnail(byte[] thumbnail) throws SQLException;
    void setProcessingPath(String processingPath);
}
