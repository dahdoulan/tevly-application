package org.group15.tveely.models;

import lombok.Data;
import org.group15.tveely.dto.CategoryDto;
import org.group15.tveely.dto.UserDetailsDto;
import org.group15.tveely.dto.VideoDto;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class EncodedVideo implements Serializable, VideoDto {
    private Long id;
    private String title;
    private LocalDateTime uploadDate;
    private String status;
    private String description;
    private String thumbnailUrl;
    private String videoUrl;
    private String processingPath;
    private byte[] content;
    private byte[] thumbnail;
    private CategoryDto category;
    private UserDetailsDto filmmaker;
}
