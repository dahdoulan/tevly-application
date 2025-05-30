package org.group15.tveely.mappers;

import org.group15.tveely.RoleEntity;
import org.group15.tveely.UserEntity;
import org.group15.tveely.UserDtoImpl;
import org.group15.tveely.dto.UserDetailsDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public UserDetailsDto toModel(UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        }

        UserDtoImpl model = new UserDtoImpl();
        model.setId(userEntity.getId());
        model.setFirstname(userEntity.getFirstname());
        model.setLastname(userEntity.getLastname());
        model.setDateOfBirth(userEntity.getDateOfBirth());
        model.setEmail(userEntity.getEmail());
        model.setAccountLocked(userEntity.isAccountLocked());
        model.setEnabled(userEntity.isEnabled());
        model.setCreatedDate(userEntity.getCreatedDate());
        model.setLastModifiedDate(userEntity.getLastModifiedDate());

        if (userEntity.getRoles() != null) {
            model.setRoles(userEntity.getRoles().stream()
                    .map(RoleEntity::getName)
                    .collect(Collectors.toList()));
        }

        return model;
    }

    public UserEntity toEntity(UserDetailsDto model) {
        if (model == null) {
            return null;
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setId(model.getId());
        userEntity.setFirstname(model.getFirstname());
        userEntity.setLastname(model.getLastname());
        userEntity.setDateOfBirth(model.getDateOfBirth());
        userEntity.setEmail(model.getEmail());
        userEntity.setAccountLocked(model.isAccountLocked());
        userEntity.setEnabled(model.isEnabled());
        userEntity.setCreatedDate(model.getCreatedDate());
        userEntity.setLastModifiedDate(model.getLastModifiedDate());

        if (model.getRoles() != null) {
            List<RoleEntity> roles = model.getRoles().stream()
                    .map(roleName -> {
                        RoleEntity role = new RoleEntity();
                        role.setName(roleName);
                        return role;
                    })
                    .collect(Collectors.toList());
            userEntity.setRoles(roles);
        }

        return userEntity;
    }
}
