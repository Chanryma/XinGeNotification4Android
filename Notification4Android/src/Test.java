
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONObject;

import com.ecity.notification.android.NotificationService4Android;
import com.ecity.notification.exception.InvalidConfigurationException;
import com.ecity.notification.exception.NotificationException;

public class Test {

    public static void main(String[] args) {
        send2JM("user1");
    }

    private static void send2XingeDemo(String username) {
        NotificationService4Android service = NotificationService4Android.getInstance();
        try {
            service.init(2100238303, "66a9c9ffe9ac24b4b151058a11801ab5");
            JSONObject json = createNotificationContent();
            service.push2User(json, username);
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        } catch (NotificationException e) {
            e.printStackTrace();
        }
    }

    private static void send2JM(String username) {
        NotificationService4Android service = NotificationService4Android.getInstance();
        try {
            service.init(2100208342, "eb175c03e21335304fcd347bcbe12d0a");
            JSONObject json = createNotificationContent();
            service.push2User(json, username);
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
        json.put("sendtime", "2013-05-12");
        json.put("geterid", "suyubin");
        json.put("geter", "苏余斌");
        json.put("type", "wo_new");
        json.put("funid", "30");
        json.put("msg", "测试消息，请忽略。" + getDateTime());

        return json;
    }

    private static String getDateTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(new Date());
    }
}
