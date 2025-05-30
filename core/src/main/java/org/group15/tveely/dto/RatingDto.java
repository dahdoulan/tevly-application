package org.group15.tveely.dto;

public interface RatingDto {
    Long getUserId();
    void setUserId(Long userId);

    Long getVideoId();
    void setVideoId(Long videoId);

    int getRating();
    void setRating(int rating);
}
