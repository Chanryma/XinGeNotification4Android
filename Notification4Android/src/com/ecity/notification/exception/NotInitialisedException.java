package com.ecity.notification.exception;

public class NotInitialisedException extends NotificationException {
    private static final long serialVersionUID = -2391187098910490506L;

    public NotInitialisedException(Exception e) {
        super(e);
    }

    public NotInitialisedException(String msg) {
        super(msg);
    }
}
