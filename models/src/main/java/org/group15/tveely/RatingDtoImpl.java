package org.group15.tveely;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.group15.tveely.dto.RatingDto;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RatingDtoImpl implements RatingDto {
    private Long userId;
    private Long videoId;
    private int rating;
}
