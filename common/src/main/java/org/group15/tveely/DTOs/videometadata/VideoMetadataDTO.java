package org.group15.tveely.DTOs.videometadata;

import org.group15.tveely.CategoryEntity;
import org.group15.tveely.DTOs.comment.CommentResponseDTO;

import java.util.List;

public class VideoMetadataDTO implements VideoMetadata {
    private final Long id;
    private final String title;
    private final String description;
    private final String videoUrl;
    private final CategoryEntity categoryEntity;
    private final List<CommentResponseDTO> comments;

    public VideoMetadataDTO(Long id, String title, String description, String videoUrl,
                            CategoryEntity categoryEntity, List<CommentResponseDTO> comments) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.videoUrl = videoUrl;
        this.categoryEntity = categoryEntity;
        this.comments = comments;
    }

    // Implement all interface methods
    @Override
    public Long getId() { return id; }

    @Override
    public String getTitle() { return title; }

    @Override
    public String getDescription() { return description; }

    @Override
    public String getVideoUrl() { return videoUrl; }

    @Override
    public CategoryEntity getCategoryEntity() { return categoryEntity; }

    @Override
    public List<CommentResponseDTO> getComments() { return comments; }
}