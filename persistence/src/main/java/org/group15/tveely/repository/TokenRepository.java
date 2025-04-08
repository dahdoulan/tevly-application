package org.group15.tveely.repository;

import org.antlr.v4.runtime.Token;
import org.group15.tveely.entities.TokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<TokenEntity,Integer> {

    Optional<TokenEntity> findByToken(String token);
}