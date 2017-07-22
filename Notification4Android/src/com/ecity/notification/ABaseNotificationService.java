package com.ecity.notification;

import java.util.List;

import org.json.JSONObject;

import com.ecity.notification.exception.NotificationException;

/**
 * Base class of notification service.
 * @author Ma Qianli
 *
 */
public abstract class ABaseNotificationService {

    /**
     * Send a notification to a single user.
     * @param content Notification content, for example, title, description.
     * @param userId id of the recipient.
     * @throws NotificationException
     */
    public void push2User(JSONObject content, String userId) throws NotificationException {
        push2UserWithLog(content, userId);
    }

    /**
     * Send a notification to multiple users.
     * @param content Notification content, for example, title, description.
     * @param userId ids of the recipients.
     * @throws NotificationException
     */
    public void push2Users(JSONObject content, List<String> userIds) throws NotificationException {
        push2UsersWithLog(content, userIds);
    }

    /**
     * Send a notification to all users.
     * @param content Notification content, for example, title, description.
     * @throws NotificationException
     */
    public void push2AllUsers(JSONObject content) throws NotificationException {
        push2AllUsersWithLog(content);
    }

    /**
     * Send a notification to a single user.
     * @param content Notification content, for example, title, description.
     * @param userId id of the recipient.
     * @throws NotificationException
     */
    protected abstract void push2UserImpl(JSONObject content, String userId) throws NotificationException;

    /**
     * Send a notification to multiple users.
     * @param content Notification content, for example, title, description.
     * @param userId ids of the recipients.
     * @throws NotificationException
     */
    protected abstract void push2UsersImpl(JSONObject content, List<String> userIds) throws NotificationException;

    /**
     * Send a notification to all users.
     * @param content Notification content, for example, title, description.
     * @throws NotificationException
     */
    protected abstract void push2AllUsersImpl(JSONObject content) throws NotificationException;

    private void push2UserWithLog(JSONObject content, String userId) throws NotificationException {
        LogUtil.debug("Sending message to '" + userId + "'. content=" + content);
        push2UserImpl(content, userId);
        LogUtil.debug("Sent message to '" + userId + "' successfully.");
    }

    private void push2UsersWithLog(JSONObject content, List<String> userIds) throws NotificationException {
        LogUtil.debug("Sending message to '" + GsonUtil.toJson(userIds) + "'. content=" + content);
        push2UsersImpl(content, userIds);
        LogUtil.debug("Sent message to '" + GsonUtil.toJson(userIds) + "' successfully.");
    }

    private void push2AllUsersWithLog(JSONObject content) throws NotificationException {
        LogUtil.debug("Sending message to all users'. content=" + content);
        push2AllUsersImpl(content);
        LogUtil.debug("Sent message to all users successfully.");
    }
}