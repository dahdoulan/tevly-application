package org.group15.tveely.spi;


import jakarta.mail.MessagingException;
import org.group15.tveely.DTOs.AuthenticationRequest;
import org.group15.tveely.DTOs.AuthenticationResponse;
import org.group15.tveely.DTOs.RegistrationRequest;

public interface AuthenticationService {

     void registerUser(RegistrationRequest request) throws MessagingException;

     void registerAdmin(RegistrationRequest request) throws MessagingException;

     void registerFilmmaker(RegistrationRequest request) throws MessagingException;

     AuthenticationResponse authenticate(AuthenticationRequest request) throws MessagingException;

     void activateAccount(String token) throws MessagingException;


}
