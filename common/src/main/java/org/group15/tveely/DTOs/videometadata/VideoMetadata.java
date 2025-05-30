package org.group15.tveely.DTOs.videometadata;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.group15.tveely.CategoryEntity;
import org.group15.tveely.DTOs.comment.CommentResponseDTO;
import org.group15.tveely.UserEntity;

import java.util.List;

public interface VideoMetadata {
    Long getId();
    String getTitle();
    String getDescription();
    String getVideoUrl();
    @JsonIgnore
    CategoryEntity getCategoryEntity();
    List<CommentResponseDTO> getComments();
    int getAverageRating();
    int getUserRating();
    @JsonIgnore
    UserEntity getFilmmaker();


    default String getThumbnailUrl() {
       return "/videos/"+getId()+"/thumbnail";
   }
    default String getMoviemaker(){ return this.getFilmmaker().getFullName();}

    default String getCategory() {
        return getCategoryEntity().getCategory();
    }
}
