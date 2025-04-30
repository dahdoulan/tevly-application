package org.group15.tveely.DTOs.comment;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
public class CommentResponseDTO {

    String comment;
    String fullName;
    LocalDateTime date;

    public CommentResponseDTO(String comment, String fullName, LocalDateTime date) {
        this.comment = comment;
        this.fullName = fullName;
        this.date = date;
    }
}
