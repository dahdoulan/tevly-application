package org.group15.tveely.spi;


import jakarta.mail.MessagingException;
import org.group15.dtos.authentication.AuthenticationRequest;
import org.group15.dtos.authentication.AuthenticationResponse;
import org.group15.dtos.authentication.RegistrationRequest;

public interface AuthenticationService {

     void registerUser(RegistrationRequest request) throws MessagingException;

     void registerAdmin(RegistrationRequest request) throws MessagingException;

     void registerFilmmaker(RegistrationRequest request) throws MessagingException;

     AuthenticationResponse authenticate(AuthenticationRequest request) throws MessagingException;

     void activateAccount(String token) throws MessagingException;


}
