package org.group15.tveely.dao;


import org.group15.tveely.entities.RoleEntity;

import java.util.Optional;

public interface RoleDao {

    Optional<RoleEntity> findByName(String name);
}
