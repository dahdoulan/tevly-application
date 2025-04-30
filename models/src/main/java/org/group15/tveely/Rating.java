package org.group15.tveely;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rating {
    private Long userId;
    private Long videoId;
    private Long rating;
}
