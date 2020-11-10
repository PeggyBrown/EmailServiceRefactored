package com.st;

public class MessageValidator {
    public MessageValidator() {
    }

    void validateMessage(MsgInfo msgInfo) {
        if (msgInfo.hasEmptySubject() || msgInfo.hasEmptyRecipient()) {
            throw new InvalidEmailException("Empty fields not allowed");
        }
        if (emailAddressIsIncorrect(msgInfo)) {
            throw new InvalidEmailException("Email address of recipient invalid");
        }
    }

    private boolean emailAddressIsIncorrect(MsgInfo msgInfo) {
        return !msgInfo.getTo().contains("@");
    }
}