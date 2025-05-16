package org.group15.tveely.models;


import lombok.Data;

import java.sql.Blob;
import java.sql.SQLException;
import java.time.LocalDateTime;

@Data
public class Video{
    private Long id;
    private String title;
    private LocalDateTime uploadDate;
    private String status;
    private String description;
    private String thumbnailUrl;
    private String videoUrl;
    private String processingPath;
    private byte[] content;
}
