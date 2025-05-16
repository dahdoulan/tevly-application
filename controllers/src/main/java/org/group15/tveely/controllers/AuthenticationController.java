package org.group15.tveely.controllers;


import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.group15.tveely.entities.AuthenticationRequest;
import org.group15.tveely.entities.AuthenticationResponse;
import org.group15.tveely.entities.RegistrationRequest;
import org.group15.tveely.services.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication")
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/register/user")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> register(
            @RequestBody @Valid RegistrationRequest request
    ) throws MessagingException {
        service.registerUser(request);
        return ResponseEntity.accepted().build();
    }

    @PostMapping("/register/filmmaker")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> registerFilmmaker(
            @RequestBody @Valid RegistrationRequest request
    ) throws MessagingException {
        service.registerFilmmaker(request);
        return ResponseEntity.accepted().build();
    }


    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }

    @GetMapping("/activate-account")
    public void confirm(
            @RequestParam("token") String token
    ) throws MessagingException {
        service.activateAccount(token);
    }


}