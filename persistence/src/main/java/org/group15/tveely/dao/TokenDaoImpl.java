package org.group15.tveely.dao;

import lombok.AllArgsConstructor;
import org.group15.tveely.TokenEntity;
import org.group15.tveely.repository.TokenRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class TokenDaoImpl implements TokenDao {


private TokenRepository tokenRepository;


    @Override
    public Optional<TokenEntity> findByToken(String token){

        return tokenRepository.findByToken(token);
    }

    @Override
    public void save(TokenEntity tokenEntity) {
        tokenRepository.save(tokenEntity);
    }
}
