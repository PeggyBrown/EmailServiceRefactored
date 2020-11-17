package com.st;

public class
MainExecutor {
    public ExecutionStatus execute() {
        MessageBuilder messageBuilder = new MessageBuilder();
        MailPropertiesLoader propertiesLoader = new MailPropertiesLoader();
        MessageValidator messageValidator = new MessageValidator();
        Transporter transporter = new Transporter();
        EmailSender sender = new EmailSender(messageBuilder, propertiesLoader, messageValidator, transporter);
        return sender.sendEmail("your@email.com", "System test", "Hello world!");
    }
}
