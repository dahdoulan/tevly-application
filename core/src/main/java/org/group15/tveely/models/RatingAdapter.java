package org.group15.tveely.models;

public interface RatingAdapter {
    Long getUserId();
    void setUserId(Long userId);

    Long getVideoId();
    void setVideoId(Long videoId);

    int getRating();
    void setRating(int rating);
}
