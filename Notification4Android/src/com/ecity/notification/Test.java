package com.ecity.notification;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONObject;

import com.ecity.notification.android.NotificationService4Android;
import com.ecity.notification.exception.InvalidConfigurationException;
import com.ecity.notification.exception.NotificationException;

public class Test {

    public static void main(String[] args) {
        args = new String[] {"2100256277", "a77738f2b6392f05951ac923eca4b0d6", "test"};
        if ((args == null) || (args.length < 3)) {
            System.err.println("\nThree parameters are required. Run the command like:\n\n\tjava -jar NotificationTest.jar access_id secret_key user_account\n");
            return;
        }

        long accessId = 0;
        try {
            accessId = Long.parseLong(args[0]);
        } catch (NumberFormatException e) {
            System.err.println("\nInvalid access_id. access_id should be Long type.");
            return;
        }

        String secretKey = args[1];
        String userAccount = args[2];
        NotificationService4Android service = NotificationService4Android.getInstance();
        try {
            service.init(accessId, secretKey);
            JSONObject json = createNotificationContent();
            System.out.println("Current time: " + getCurrentDateTime());
            service.push2User(json, userAccount);
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        } catch (NotificationException e) {
            e.printStackTrace();
        }
    }

    static JSONObject createNotificationContent() {
        JSONObject json = new JSONObject();
        json.put("proid", "2");
        json.put("projectname", "工程1");
        json.put("sendtime", getCurrentDateTime());
        json.put("geterid", "suyubin");
        json.put("geter", "苏余斌");
        json.put("type", "gx_fankui");
        json.put("type", "hs_faqi");
        json.put("funid", "30");
        json.put("msg", "测试消息");

        return json;
    }
    
    private static String getCurrentDateTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        return format.format(new Date());
    }
}
