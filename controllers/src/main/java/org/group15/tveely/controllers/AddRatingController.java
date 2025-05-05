package org.group15.tveely.controllers;


import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.group15.tveely.DTOs.rating.RatingRequest;
import org.group15.tveely.mappers.RatingMapper;
import org.group15.tveely.spi.AddRatingService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@AllArgsConstructor
@RestController
public class AddRatingController {

    private final AddRatingService addRatingService;
    private final RatingMapper ratingMapper;

    @PostMapping("/api/video/Rating")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<?> addRating(@RequestBody @Valid RatingRequest request)throws MessagingException {

        String authenticatedEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        log.info("Email: {}", authenticatedEmail);

        request.setEmail(authenticatedEmail);

        addRatingService.addRating(ratingMapper.ratingRequestToModel(request));
        return ResponseEntity.accepted().build();
    }
}
