package com.ecity.notification.exception;

public class InvalidConfigurationException extends NotificationException {
    private static final long serialVersionUID = 1980333395820638850L;

    public InvalidConfigurationException(Exception e) {
        super(e);
    }

    public InvalidConfigurationException(String msg) {
        super(msg);
    }
}
