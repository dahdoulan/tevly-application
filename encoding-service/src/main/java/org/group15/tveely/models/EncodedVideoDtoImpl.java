package org.group15.tveely.models;

import org.group15.tveely.dto.EncodedVideoDto;
import org.group15.tveely.dto.VideoDto;

public class EncodedVideoDtoImpl implements EncodedVideoDto {
    private Long id;
    private VideoDto videoDto;
    private String url;
    private String title;

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public void setVideo(VideoDto video) {
        this.videoDto = video;
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
    public VideoDto getVideo() {
        return this.videoDto;
    }

    @Override
    public String getUrl() {
        return this.url;
    }
}
