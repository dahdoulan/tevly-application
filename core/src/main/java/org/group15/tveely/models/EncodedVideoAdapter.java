package org.group15.tveely.models;

public interface EncodedVideoAdapter {
    void setId(Long id);
    void setVideo(VideoAdapter video);
    void setUrl(String url);
    void setTitle(String title);

    Long getId();
    VideoAdapter getVideo();
    String getUrl();
    String getTitle();
}
