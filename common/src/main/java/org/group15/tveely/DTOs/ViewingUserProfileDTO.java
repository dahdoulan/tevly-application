package org.group15.tveely.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViewingUserProfileDTO {

    private String fullName;
    private String email;
    private LocalDate birthDate;
}
