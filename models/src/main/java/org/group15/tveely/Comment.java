package org.group15.tveely;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    private Long userId;
    private Long videoId;
    private String comment;
    private LocalDateTime createDate;
}
