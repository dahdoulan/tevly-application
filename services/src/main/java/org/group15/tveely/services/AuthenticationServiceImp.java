package org.group15.tveely.services;



import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.group15.dtos.authentication.AuthenticationRequest;
import org.group15.dtos.authentication.AuthenticationResponse;
import org.group15.dtos.authentication.RegistrationRequest;
import org.group15.tveely.dao.RoleDao;
import org.group15.tveely.dao.TokenDao;
import org.group15.tveely.dao.UserDao;
import org.group15.tveely.entities.*;


import org.group15.tveely.models.email.EmailTemplateName;

import org.group15.tveely.spi.AuthenticationService;
import org.group15.tveely.spi.EmailService;
import org.group15.tveely.spi.JwtService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImp implements AuthenticationService {

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final RoleDao roleDao;
    private final EmailService emailService;
    private final TokenDao tokenDao;
    @Value("${application.mailing.frontend.activation-url}")
    private String activationUrl;

    @Override
    public void registerUser(RegistrationRequest request) throws MessagingException {
        var userRole = roleDao.findByName("USER")
                .orElseThrow(() -> new IllegalStateException("ROLE USER was not initiated"));
        var user = UserEntity.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .dateOfBirth(request.getDateOfBirth())
                .password(passwordEncoder.encode(request.getPassword()))
                .accountLocked(false)
                .enabled(false)
                .roles(List.of(userRole))
                .build();

        userDao.save(user);
        sendValidationEmail(user);
    }

    @Override
    public void registerFilmmaker(RegistrationRequest request) throws MessagingException {
        var filmmakerRole = roleDao.findByName("FILMMAKER")
                .orElseThrow(() -> new IllegalStateException("ROLE FILMMAKER was not initialized"));
        var user = UserEntity.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .dateOfBirth(request.getDateOfBirth())
                .password(passwordEncoder.encode(request.getPassword()))
                .accountLocked(false)
                .enabled(false)
                .roles(List.of(filmmakerRole)) // Assign FILMMAKER role
                .build();

        userDao.save(user);
        sendValidationEmail(user);
    }

    @Override
    public void registerAdmin(RegistrationRequest request) throws MessagingException {
        var adminRole = roleDao.findByName("ADMIN")
                .orElseThrow(() -> new IllegalStateException("ROLE ADMIN was not initialized"));
        var user = UserEntity.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .dateOfBirth(request.getDateOfBirth())
                .password(passwordEncoder.encode(request.getPassword()))
                .accountLocked(false)
                .enabled(false)
                .roles(List.of(adminRole)) // Assign FILMMAKER role
                .build();

        userDao.save(user);
        sendValidationEmail(user);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) throws MessagingException {
        var auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        var claims = new HashMap<String, Object>();
        var user = ((UserEntity) auth.getPrincipal());
        claims.put("fullName", user.getFullName());

        var jwtToken = jwtService.generateToken(claims, (UserEntity) auth.getPrincipal());
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public void activateAccount(String token) throws MessagingException {
        TokenEntity savedToken = tokenDao.findByToken(token)
                //todo exception has to be defined
                .orElseThrow(() -> new RuntimeException("Invalid token"));
        if (LocalDateTime.now().isAfter(savedToken.getExpiresAt())) {
            sendValidationEmail(savedToken.getUser());
            throw new RuntimeException("Activation token has expired. A new token has been send to the same email address");
        }

        var user = userDao.findById(savedToken.getUser().getId())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        user.setEnabled(true);
        userDao.save(user);

        savedToken.setValidatedAt(LocalDateTime.now());
        tokenDao.save(savedToken);
    }

    private String generateAndSaveActivationToken(UserEntity user) {
        // Generate a token
        String generatedToken = generateActivationCode(6);
        var token = TokenEntity.builder()
                .token(generatedToken)
                .createdAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.now().plusMinutes(15))
                .user(user)
                .build();
        tokenDao.save(token);

        return generatedToken;
    }

    private void sendValidationEmail(UserEntity user) throws MessagingException {
        var newToken = generateAndSaveActivationToken(user);

        emailService.sendEmail(
                user.getEmail(),
                user.getFullName(),
                EmailTemplateName.ACTIVATE_ACCOUNT,
                activationUrl,
                newToken,
                "Account activation"
                );
    }

    private String generateActivationCode(int length) {
        String characters = "0123456789";
        StringBuilder codeBuilder = new StringBuilder();

        SecureRandom secureRandom = new SecureRandom();

        for (int i = 0; i < length; i++) {
            int randomIndex = secureRandom.nextInt(characters.length());
            codeBuilder.append(characters.charAt(randomIndex));
        }

        return codeBuilder.toString();
    }
}
