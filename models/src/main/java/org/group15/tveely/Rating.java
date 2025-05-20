package org.group15.tveely;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.group15.tveely.models.RatingAdapter;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rating implements RatingAdapter {
    private Long userId;
    private Long videoId;
    private int rating;
}
