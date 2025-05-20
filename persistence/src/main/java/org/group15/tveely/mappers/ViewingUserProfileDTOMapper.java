package org.group15.tveely.mappers;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.group15.tveely.DTOs.ViewingUserProfileDTO;
import org.group15.tveely.UserEntity;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class ViewingUserProfileDTOMapper {

    public ViewingUserProfileDTO UserEntityToViewingUserProfileDTO(UserEntity userEntity) {

        ViewingUserProfileDTO viewingUserProfileDTO = new ViewingUserProfileDTO();
        viewingUserProfileDTO.setFullName(userEntity.getFullName());
        viewingUserProfileDTO.setEmail(userEntity.getEmail());
        viewingUserProfileDTO.setBirthDate(userEntity.getDateOfBirth());
        return viewingUserProfileDTO;
    }
    }
