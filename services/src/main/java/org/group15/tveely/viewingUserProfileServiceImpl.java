package org.group15.tveely;

import lombok.AllArgsConstructor;
import org.group15.tveely.DTOs.ViewingUserProfileDTO;
import org.group15.tveely.dao.UserDao;
import org.group15.tveely.mappers.ViewingUserProfileDTOMapper;
import org.group15.tveely.spi.ViewingUserProfileService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class viewingUserProfileServiceImpl implements ViewingUserProfileService {
    private final UserDao userDao;
    private final ViewingUserProfileDTOMapper viewingUserProfileDTOMapper;
    @Override
    public ViewingUserProfileDTO fetchingUserProfileServiceImpl(String email) {

        UserEntity user = userDao.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return viewingUserProfileDTOMapper.UserEntityToViewingUserProfileDTO(user);
}
}
