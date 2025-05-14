package org.group15.tveely.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.group15.tveely.DTOs.ViewingUserProfileDTO;
import org.group15.tveely.spi.ViewingUserProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@AllArgsConstructor
@RestController
public class ViewingUserProfileController {
    private ViewingUserProfileService viewingUserProfileService;
    @GetMapping("/api/user/profile")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<ViewingUserProfileDTO> fetchingUserProfile() {
        String authenticatedEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        log.info("Email: {}", authenticatedEmail);
        ViewingUserProfileDTO userProfile = viewingUserProfileService.fetchingUserProfileServiceImpl(authenticatedEmail);
        return ResponseEntity.ok(userProfile);
    }
}
