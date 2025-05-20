package org.group15.tveely.mappers;

import lombok.RequiredArgsConstructor;
import org.group15.tveely.DTOs.ReviewingContentResponse;
import org.group15.tveely.VideoEntity;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class ReviewingContentMapper {

    public ReviewingContentResponse toDto(VideoEntity videoEntity) {
        return ReviewingContentResponse.builder()
                .id(videoEntity.getId())
                .title(videoEntity.getTitle())
                .description(videoEntity.getDescription())
                .videoUrl(videoEntity.getVideoUrl())
                .category(videoEntity.getCategoryEntity().getCategory())
                .filmmakerEmail(videoEntity.getFilmmaker().getEmail())
                .filmmakerFullName(videoEntity.getFilmmaker().getFullName())
                .thumbnailUrl("/videos/" + videoEntity.getId() + "/thumbnail")
                .build();
    }
}
