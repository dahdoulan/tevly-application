package org.group15.tveely.models;

public class EncodedVideoAdapterImpl implements EncodedVideoAdapter {
    private Long id;
    private VideoAdapter videoAdapter;
    private String url;
    private String title;

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public void setVideo(VideoAdapter video) {
        this.videoAdapter = video;
    }

    @Override
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public VideoAdapter getVideo() {
        return this.videoAdapter;
    }

    @Override
    public String getUrl() {
        return this.url;
    }
}
