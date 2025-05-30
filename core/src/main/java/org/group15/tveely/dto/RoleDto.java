package org.group15.tveely.dto;

import java.time.LocalDateTime;
import java.util.List;

public interface RoleDto {
    Long getId();
    void setId(Long id);

    String getName();
    void setName(String name);

    List<UserDetailsDto> getUsers();
    void setUsers(List<UserDetailsDto> users);

    LocalDateTime getCreatedDate();
    void setCreatedDate(LocalDateTime createdDate);

    LocalDateTime getLastModifiedDate();
    void setLastModifiedDate(LocalDateTime lastModifiedDate);
}
