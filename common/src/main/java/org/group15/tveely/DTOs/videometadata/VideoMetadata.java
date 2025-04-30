package org.group15.tveely.DTOs.videometadata;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.group15.tveely.CategoryEntity;
import org.group15.tveely.DTOs.comment.CommentResponseDTO;

import java.util.List;

public interface VideoMetadata {
    Long getId();
    String getTitle();
    String getDescription();
    String getVideoUrl();
    @JsonIgnore
    CategoryEntity getCategoryEntity();
    List<CommentResponseDTO> getComments();

   default String getThumbnailUrl() {
       return "/videos/"+getId()+"/thumbnail";
   }

    default String getCategory() {
        return getCategoryEntity().getCategory();
    }
}
