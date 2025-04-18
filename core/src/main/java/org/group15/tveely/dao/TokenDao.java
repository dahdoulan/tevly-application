package org.group15.tveely.dao;

import org.group15.tveely.entities.TokenEntity;

import java.util.Optional;

public interface TokenDao {

     Optional<TokenEntity> findByToken(String token);

     void save(TokenEntity tokenEntity);
}
