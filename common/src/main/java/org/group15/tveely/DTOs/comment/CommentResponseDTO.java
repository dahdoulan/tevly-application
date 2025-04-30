package org.group15.tveely.DTOs.comment;

import lombok.Data;

import java.time.LocalDate;
@Data
public class CommentResponseDTO {

    String comment;
    String fullName;
    LocalDate date;

    public CommentResponseDTO(String comment, String fullName, LocalDate date) {
        this.comment = comment;
        this.fullName = fullName;
        this.date = date;
    }
}
