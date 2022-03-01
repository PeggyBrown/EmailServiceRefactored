package com.st;


import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class EmailSenderTest {

    String toEmail = "to@email.com";
    String subject = "Subject";
    String text = "Text";

    EmailSender sender;

    MessageBuilder messageBuilder;

    MessageValidator messageValidator;

    PropertiesLoader propertiesLoader;

    @Mock
    Transporter transporter;


    @BeforeEach
    void setUp() {
        initMocks(this);
        messageValidator = new MessageValidator();
        messageBuilder = new MessageBuilder();
        propertiesLoader = new FakePropertiesLoader();
        sender = new EmailSender(
                messageBuilder,
                propertiesLoader,
                messageValidator,
                transporter);
    }


    @Test
    void shouldSendEmail() throws MessagingException {
        //when
        sender.sendEmail(toEmail, subject, text);

        //then
        ArgumentCaptor<MimeMessage> argument = ArgumentCaptor.forClass(MimeMessage.class);
        verify(transporter).sendMessage(argument.capture(), anyString(), anyString());
        String result = argument.getValue().getSubject();
        assertThat(result).isEqualTo(this.subject);
    }

    @Test
    void shouldNotSendMessageWhenTransporterThrowsException() throws MessagingException {
        doThrow(new MessagingException("Something went wrong"))
                .when(transporter).sendMessage(any(), anyString(), anyString());
        ExecutionStatus status = sender.sendEmail(toEmail, subject, text);
        assertThat(status).isEqualTo(ExecutionStatus.FAILURE);
    }

    @Test
    void shouldNotSendMessageWhenTransporterThrowsNPException() throws MessagingException {
        doThrow(new NullPointerException("Something went wrong"))
                .when(transporter).sendMessage(any(), anyString(), anyString());
        ExecutionStatus status = sender.sendEmail(toEmail, subject, text);
        assertThat(status).isEqualTo(ExecutionStatus.FAILURE);
    }

    @Test
    void shouldNotSendEmailWhenAddressIncorrect() {
        //then
        assertThatExceptionOfType(InvalidEmailException.class).isThrownBy(() ->
                sender.sendEmail("toEmail.com", subject, text))
                .withMessage("Email address of recipient invalid");
    }

    @Test
    void shouldNotSendEmailWhenSubjectEmpty() {
        //then
        assertThatExceptionOfType(InvalidEmailException.class).isThrownBy(() ->
            sender.sendEmail(toEmail, "", text)
        ).withMessage("Empty fields not allowed");
    }
}