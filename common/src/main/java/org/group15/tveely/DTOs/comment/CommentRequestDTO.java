package org.group15.tveely.DTOs.comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentRequestDTO {

    private String email;
    private Long videoId;
    private String comment;
}