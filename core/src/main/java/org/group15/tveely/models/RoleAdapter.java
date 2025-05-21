package org.group15.tveely.models;

import java.time.LocalDateTime;
import java.util.List;

public interface RoleAdapter {
    Long getId();
    void setId(Long id);

    String getName();
    void setName(String name);

    List<UserDetailsAdapter> getUsers();
    void setUsers(List<UserDetailsAdapter> users);

    LocalDateTime getCreatedDate();
    void setCreatedDate(LocalDateTime createdDate);

    LocalDateTime getLastModifiedDate();
    void setLastModifiedDate(LocalDateTime lastModifiedDate);
}
