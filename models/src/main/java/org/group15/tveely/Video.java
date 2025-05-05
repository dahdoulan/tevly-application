package org.group15.tveely;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Video {
    private String title;
    private LocalDateTime uploadDate;
    private String status;
    private String description;
    private String thumbnailUrl;
    private String videoUrl;
    private String processingPath;
    private byte[] content;
    private byte[] thumbnail;
    private String category;
    private String filmmakerEmail;
}
