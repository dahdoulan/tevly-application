package org.group15.tveely.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface UserDetailsDto {
    Long getId();
    void setId(Long id);

    String getFirstname();
    void setFirstname(String firstname);

    String getLastname();
    void setLastname(String lastname);

    LocalDate getDateOfBirth();
    void setDateOfBirth(LocalDate dateOfBirth);

    String getEmail();
    void setEmail(String email);

    boolean isAccountLocked();
    void setAccountLocked(boolean accountLocked);

    boolean isEnabled();
    void setEnabled(boolean enabled);

    LocalDateTime getCreatedDate();
    void setCreatedDate(LocalDateTime createdDate);

    LocalDateTime getLastModifiedDate();
    void setLastModifiedDate(LocalDateTime lastModifiedDate);

    List<String> getRoles();
    void setRoles(List<String> roles);
}
