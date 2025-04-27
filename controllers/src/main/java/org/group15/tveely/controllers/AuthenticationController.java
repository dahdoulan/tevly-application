package org.group15.tveely.controllers;


import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


import org.group15.tveely.DTOs.AuthenticationRequest;
import org.group15.tveely.DTOs.AuthenticationResponse;
import org.group15.tveely.DTOs.RegistrationRequest;
import org.group15.tveely.spi.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
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
        try {
            return ResponseEntity.ok(service.authenticate(request));
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/activate-account")
    public void confirm(
            @RequestParam("token") String token
    ) throws MessagingException {
        service.activateAccount(token);
    }


}