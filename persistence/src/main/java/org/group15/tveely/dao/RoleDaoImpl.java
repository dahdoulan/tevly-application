package org.group15.tveely.dao;

import lombok.AllArgsConstructor;
import org.group15.tveely.RoleEntity;
import org.group15.tveely.repository.RoleRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class RoleDaoImpl implements RoleDao {

    private RoleRepository roleRepository;

    @Override
    public Optional<RoleEntity> findByName(String name){
        return roleRepository.findByName(name);
    }
}
