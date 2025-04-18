package org.group15.tveely.dao;

import lombok.AllArgsConstructor;
import org.group15.tveely.entities.UserEntity;
import org.group15.tveely.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class UserDaoImpl implements UserDao {

    private final UserRepository userRepository;

    @Override
    public Optional<UserEntity> findByEmail(String email){
        return userRepository.findByEmail(email);
    };


    @Override
    public void save(UserEntity userEntity) {
         userRepository.save(userEntity);
    }

    @Override
    public Optional<UserEntity> findById(Long id){
        return userRepository.findById(id);
    }

}
