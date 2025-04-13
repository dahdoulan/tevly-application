package org.group15.tveely.models;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EncodedVideo implements VideoAdapter{
    private String title;
    private LocalDateTime uploadDate;
    private String status;
    private String description;
    private String thumbnailUrl;
    private String videoUrl;
    private byte[] content;

}
