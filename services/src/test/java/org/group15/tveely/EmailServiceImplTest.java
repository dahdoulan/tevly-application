package org.group15.tveely;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.thymeleaf.context.Context;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.javamail.JavaMailSender;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class EmailServiceImplTest {

    @Mock
    private JavaMailSender mailSender;

    @Mock
    private SpringTemplateEngine templateEngine;

    @InjectMocks
    private EmailServiceImpl emailService;

    // Use the actual enum from production code
    private enum TestEmailTemplateName {
        ACTIVATE_ACCOUNT
    }

    @Test
    void sendEmail_WithTemplateName_UsesCorrectTemplate() throws MessagingException {
        // Arrange
        MimeMessage mimeMessage = mock(MimeMessage.class);
        when(mailSender.createMimeMessage()).thenReturn(mimeMessage);
        when(templateEngine.process(eq("ACTIVATE_ACCOUNT"), any(Context.class)))
                .thenReturn("email-content");

        // Act
        emailService.sendEmail(
                "test@example.com",
                "John Doe",
                EmailTemplateName.ACTIVATE_ACCOUNT,
                "http://activation",
                "123456",
                "Account Activation"
        );

        // Assert
        verify(templateEngine).process(eq("ACTIVATE_ACCOUNT"), any(Context.class));
        verify(mailSender).send(mimeMessage);
    }

    @Test
    void sendEmail_WithNullTemplate_UsesDefaultTemplate() throws MessagingException {
        // Arrange
        MimeMessage mimeMessage = mock(MimeMessage.class);
        when(mailSender.createMimeMessage()).thenReturn(mimeMessage);
        when(templateEngine.process(eq("confirm-email"), any(Context.class)))
                .thenReturn("email-content");

        // Act
        emailService.sendEmail(
                "test@example.com",
                "Jane Smith",
                null,
                "http://confirmation",
                "654321",
                "Confirm Email"
        );

        // Assert
        verify(templateEngine).process(eq("confirm-email"), any(Context.class));
    }

    @Test
    void sendEmail_PassesCorrectContextVariables() throws MessagingException {
        // Arrange
        MimeMessage mimeMessage = mock(MimeMessage.class);
        when(mailSender.createMimeMessage()).thenReturn(mimeMessage);
        ArgumentCaptor<Context> contextCaptor = ArgumentCaptor.forClass(Context.class);
        when(templateEngine.process(eq("ACTIVATE_ACCOUNT"), contextCaptor.capture()))
                .thenReturn("email-content");

        // Act
        emailService.sendEmail(
                "user@test.com",
                "Alice Wonder",
                EmailTemplateName.ACTIVATE_ACCOUNT,
                "http://reset",
                "987654",
                "Password Reset"
        );

        // Assert
        Context context = contextCaptor.getValue();
        Map<String, Object> variables = new HashMap<>();

        // Properly retrieve variables using Thymeleaf's API
        for (String varName : context.getVariableNames()) {
            variables.put(varName, context.getVariable(varName));
        }

        assertThat(variables)
                .containsEntry("username", "Alice Wonder")
                .containsEntry("confirmationUrl", "http://reset")
                .containsEntry("activation_code", "987654");
    }

    @Test
    void sendEmail_HandlesMessagingException() throws MessagingException {
        // Arrange
        when(mailSender.createMimeMessage()).thenThrow(new MessagingException("Test exception"));

        // Act & Assert
        assertThatThrownBy(() -> emailService.sendEmail(
                "test@fail.com",
                "Error User",
                null,
                "http://error",
                "000000",
                "Failing Subject"
        )).isInstanceOf(MessagingException.class);
    }
}