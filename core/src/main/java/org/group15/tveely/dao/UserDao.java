package org.group15.tveely.dao;


import org.group15.tveely.UserEntity;

import java.util.Optional;

public interface UserDao {

    Optional<UserEntity> findByEmail(String email);

    void save(UserEntity userEntity);

    Optional<UserEntity> findById(Long id);
}
