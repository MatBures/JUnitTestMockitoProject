package org.firstPF.userService;

public class EmailVerificationServiceException extends RuntimeException {
    public EmailVerificationServiceException(String message) {
        super(message);
    }
}