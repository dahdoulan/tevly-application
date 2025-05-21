package org.group15.tveely.models;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class EncodedVideo implements Serializable, VideoAdapter {
    private Long id;
    private String title;
    private LocalDateTime uploadDate;
    private String status;
    private String description;
    private String thumbnailUrl;
    private String videoUrl;
    private String processingPath;
    private byte[] content;
    private CategoryAdapter category;
    private UserDetailsAdapter filmmaker;
}
