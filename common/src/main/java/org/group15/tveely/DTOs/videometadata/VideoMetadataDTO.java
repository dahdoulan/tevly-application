package org.group15.tveely.DTOs.videometadata;

import org.group15.tveely.CategoryEntity;
import org.group15.tveely.DTOs.comment.CommentResponseDTO;
import org.group15.tveely.UserEntity;

import java.util.List;

public class VideoMetadataDTO implements VideoMetadata {
    private final Long id;
    private final String title;
    private final String description;
    private final String videoUrl;
    private final CategoryEntity categoryEntity;
    private final List<CommentResponseDTO> comments;
    private final int averageRating;
    private final int userRating;
    private final UserEntity filmmaker;
    public VideoMetadataDTO(Long id, String title, String description, String videoUrl,
                            CategoryEntity categoryEntity, List<CommentResponseDTO> comments,int averageRating, int userRating, UserEntity filmmaker) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.videoUrl = videoUrl;
        this.categoryEntity = categoryEntity;
        this.comments = comments;
        this.averageRating = averageRating;
        this.userRating = userRating;
        this.filmmaker = filmmaker;
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

    @Override
    public int getAverageRating() { return averageRating; }
    @Override
    public int getUserRating() { return userRating; }

    @Override
    public UserEntity getFilmmaker() { return filmmaker; }
}