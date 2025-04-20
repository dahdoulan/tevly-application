package org.group15.tveely.spi;

import jakarta.mail.MessagingException;
import org.group15.tveely.EmailTemplateName;

public interface EmailService {

     void sendEmail(String to, String username, EmailTemplateName emailTemplate, String confirmationUrl, String activationCode, String subject) throws MessagingException;
}
