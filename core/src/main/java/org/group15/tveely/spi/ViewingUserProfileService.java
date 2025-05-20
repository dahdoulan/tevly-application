package org.group15.tveely.spi;

import org.group15.tveely.DTOs.ViewingUserProfileDTO;

public interface ViewingUserProfileService {

    ViewingUserProfileDTO fetchingUserProfileServiceImpl(String email);

}
