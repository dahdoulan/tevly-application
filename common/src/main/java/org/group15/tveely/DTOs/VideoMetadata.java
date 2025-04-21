package org.group15.tveely.DTOs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.group15.tveely.CategoryEntity;

import java.time.LocalDateTime;

public interface VideoMetadata {
    Long getId();
    String getTitle();
    String getDescription();
    String getVideoUrl();
    @JsonIgnore
    CategoryEntity getCategoryEntity();

    default String getCategory() {
        return getCategoryEntity().getCategory();
    }
}
