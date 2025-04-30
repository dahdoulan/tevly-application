package org.group15.tveely.DTOs.rating;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RatingRequest {
    private String email;
    private Long videoId;
    private Long rating;
}
