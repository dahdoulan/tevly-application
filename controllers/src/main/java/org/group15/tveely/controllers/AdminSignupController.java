package org.group15.tveely.controllers;


import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.group15.tveely.entities.RegistrationRequest;
import org.group15.tveely.services.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/signup")
@RequiredArgsConstructor
@Tag(name = "Authentication")
public class AdminSignupController {
    private final AuthenticationService service;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/admin")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> registerAdmin(
            @RequestBody @Valid RegistrationRequest request
    ) throws MessagingException {
        service.registerAdmin(request);
        return ResponseEntity.accepted().build();
    }


}
