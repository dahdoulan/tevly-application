package org.group15.tveely.dto;

import java.time.LocalDateTime;

public interface CommentDto {
    Long getUserId();
    void setUserId(Long userId);

    Long getVideoId();
    void setVideoId(Long videoId);

    String getComment();
    void setComment(String comment);

    LocalDateTime getCreateDate();
    void setCreateDate(LocalDateTime createDate);
}
