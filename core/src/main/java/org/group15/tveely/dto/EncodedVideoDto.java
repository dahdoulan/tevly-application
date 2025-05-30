package org.group15.tveely.dto;

public interface EncodedVideoDto {
    void setId(Long id);
    void setVideo(VideoDto video);
    void setUrl(String url);
    void setTitle(String title);

    Long getId();
    VideoDto getVideo();
    String getUrl();
    String getTitle();
}
