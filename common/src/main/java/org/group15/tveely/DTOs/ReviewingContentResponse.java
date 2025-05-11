package org.group15.tveely.DTOs;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ReviewingContentResponse {
    private Long id;
    private String title;
    private String description;
    private String videoUrl;
    private String filmmakerEmail;
    private String filmmakerFullName;
    private String thumbnailUrl;
    private String category;
}
