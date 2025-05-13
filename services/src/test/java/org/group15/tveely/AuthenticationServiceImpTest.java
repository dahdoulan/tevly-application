package org.group15.tveely;

import org.group15.tveely.DTOs.AuthenticationRequest;
import org.group15.tveely.DTOs.AuthenticationResponse;
import org.group15.tveely.DTOs.RegistrationRequest;
import org.group15.tveely.dao.RoleDao;
import org.group15.tveely.dao.TokenDao;
import org.group15.tveely.dao.UserDao;
import org.group15.tveely.spi.EmailService;
import org.group15.tveely.spi.JwtService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.Authentication;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;



@ExtendWith(MockitoExtension.class)
class AuthenticationServiceImpTest {

    @Mock
    private UserDao userDao;
    @Mock
    private RoleDao roleDao;
    @Mock
    private JwtService jwtService;
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private EmailService emailService;
    @Mock
    private TokenDao tokenDao;

    @InjectMocks
    private AuthenticationServiceImp authenticationService;

    private UserEntity createTestUser() {
        UserEntity user = new UserEntity();
        user.setId(1L);
        user.setEmail("test@example.com");
        user.setPassword("encodedPassword");
        user.setEnabled(false);
        user.setDateOfBirth(LocalDate.now().minusYears(25));
        return user;
    }

    private RegistrationRequest createRegistrationRequest() {
        return new RegistrationRequest(
                "John",
                "Doe",
                "john.doe@example.com",
                "password123",
                LocalDate.now().minusYears(20) // Correct parameter order and type
        );
    }

    @Test
    void registerUser_SavesUserWithUserRole() throws Exception {
        RoleEntity userRole = new RoleEntity();
        userRole.setName("USER");
        when(roleDao.findByName("USER")).thenReturn(Optional.of(userRole));
        when(passwordEncoder.encode(any())).thenReturn("encodedPassword");

        authenticationService.registerUser(createRegistrationRequest());

        verify(userDao).save(argThat(user ->
                user.getEmail().equals("john.doe@example.com") &&
                        user.getDateOfBirth().equals(LocalDate.now().minusYears(20))
        ));
        verify(emailService).sendEmail(any(), any(), any(), any(), any(), any());
    }

    @Test
    void authenticate_ReturnsValidJwtWithRoles() throws Exception {
        AuthenticationRequest request = new AuthenticationRequest(
                "test@example.com",
                "password123"
        );

        UserEntity user = createTestUser();
        RoleEntity role = new RoleEntity();
        role.setName("USER");
        user.setRoles(List.of(role));

        Authentication auth = mock(Authentication.class);
        when(auth.getPrincipal()).thenReturn(user);
        when(authenticationManager.authenticate(any())).thenReturn(auth);
        when(jwtService.generateToken(any(), any())).thenReturn("jwt.token.here");

        AuthenticationResponse response = authenticationService.authenticate(request);

        assertThat(response.getToken()).isEqualTo("jwt.token.here");
        assertThat(response.getRole()).isEqualTo("USER");
    }

    @Test
    void activateAccount_ValidToken_ActivatesUser() throws Exception {
        TokenEntity validToken = new TokenEntity();
        validToken.setExpiresAt(LocalDateTime.now().plusHours(1));

        UserEntity user = createTestUser();
        validToken.setUser(user);

        when(tokenDao.findByToken("valid-token")).thenReturn(Optional.of(validToken));
        when(userDao.findById(1L)).thenReturn(Optional.of(user));

        authenticationService.activateAccount("valid-token");

        verify(userDao).save(argThat(u -> u.isEnabled()));
        verify(tokenDao).save(argThat(t -> t.getValidatedAt() != null));
    }

    // Other test methods remain the same with proper LocalDate usage
}