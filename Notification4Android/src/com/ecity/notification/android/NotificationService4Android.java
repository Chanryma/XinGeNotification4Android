package com.ecity.notification.android;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;

import com.ecity.notification.ABaseNotificationService;
import com.ecity.notification.exception.InvalidConfigurationException;
import com.ecity.notification.exception.NotInitialisedException;
import com.ecity.notification.exception.NotificationException;
import com.tencent.xinge.Message;
import com.tencent.xinge.XingeApp;

/**
 * Send notifications to Android users.
 * 
 * @author Ma Qianli
 * 
 */
public class NotificationService4Android extends ABaseNotificationService {
    private static final int MSG_EXPIRE_TIME = 24 * 60 * 60; // One day.
    private static NotificationService4Android instance;
    private static XingeApp xinGeApp;
    private long accessId;
    private String secreteKey;

    private NotificationService4Android() {
    }

    public static NotificationService4Android getInstance() {
        if (instance != null) {
            return instance;
        }

        instance = new NotificationService4Android();

        return instance;
    }

    /**
     * @param accessId AccessId of our APP, provided by XinGe.
     * @param secreteKey SecreteKey of our APP, provided by XinGe.
     * @throws InvalidConfigurationException
     */
    public void init(long accessId, String secreteKey) throws InvalidConfigurationException {
        if (accessId <= 0) {
            throw new InvalidConfigurationException("Invalid value '" + accessId + "' of property 'accessId'.");
        }

        if (StringUtils.isBlank(secreteKey)) {
            throw new InvalidConfigurationException("Invalid value '" + secreteKey + "' of property 'secreteKey'.");
        }

        this.accessId = accessId;
        this.secreteKey = secreteKey;
        xinGeApp = new XingeApp(this.accessId, this.secreteKey);
    }

    @Override
    protected void push2UserImpl(JSONObject content, String userId) throws NotificationException {
        checkReadiness();

        Message message = createMessage4Android(content.toString(), Message.TYPE_MESSAGE);
        JSONObject result = xinGeApp.pushSingleAccount(0, userId, message);
        validateSendMessageResult(result);
    }

    @Override
    protected void push2UsersImpl(JSONObject content, List<String> userIds) throws NotificationException {
        checkReadiness();

        Message message = createMessage4Android(content.toString(), Message.TYPE_MESSAGE);
        JSONObject result = xinGeApp.createMultipush(message);
        validateCreateMessageResult(result);
        result = xinGeApp.pushAccountListMultiple(result.getJSONObject("result").getInt("push_id"), userIds);
        validateSendMessageResult(result);
    }

    private Message createMessage4Android(String content, int msgType) {
        Message message = new Message();
        message.setExpireTime(MSG_EXPIRE_TIME);
        message.setContent(content);
        message.setType(msgType);

        return message;
    }

    private void validateCreateMessageResult(JSONObject result) throws NotificationException {
        if (result.getInt("ret_code") != 0) {
            throw new NotificationException("Failed to create XinGe message. Detail info from XinGe SDK: " + result);
        }
    }

    private void validateSendMessageResult(JSONObject result) throws NotificationException {
        if (result.getInt("ret_code") != 0) {
            throw new NotificationException("Failed to send XinGe message. Detail response from server: " + result);
        }
    }

    private void checkReadiness() throws NotInitialisedException {
        if (xinGeApp == null) {
            throw new NotInitialisedException("Service has not been initialised.");
        }
    }
}