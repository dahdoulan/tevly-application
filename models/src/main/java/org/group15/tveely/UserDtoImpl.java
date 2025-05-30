package org.group15.tveely;


import lombok.*;
import org.group15.tveely.dto.UserDetailsDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDtoImpl implements UserDetailsDto {
    private Long id;
    private String firstname;
    private String lastname;
    private LocalDate dateOfBirth;
    private String email;
    private boolean accountLocked;
    private boolean enabled;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
    private List<String> roles;
}
