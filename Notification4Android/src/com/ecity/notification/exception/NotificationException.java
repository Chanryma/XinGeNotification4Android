package com.ecity.notification.exception;

public class NotificationException extends Exception {
    private static final long serialVersionUID = -8578202277128624514L;

    public NotificationException(Exception e) {
        super(e);
    }

    public NotificationException(String msg) {
        super(msg);
    }
}
