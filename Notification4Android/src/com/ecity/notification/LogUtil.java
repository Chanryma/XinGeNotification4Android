package com.ecity.notification;

/**
 * Used as a replacement of System.out.println. Discard this class when log functionality is ready.
 * @author Ma Qianli
 *
 */
public class LogUtil {

    public static void debug(Object message) {
        System.out.println(message);
    }

    public static void info(String message) {
        System.out.println(message);
    }

    public static void info(String message, Throwable e) {
        System.out.println(message);
    }

    public static void error(String message) {
        System.out.println(message);
    }

    public static void error(Object message, Throwable e) {
        e.printStackTrace();
    }

    public static void error(Throwable e) {
        e.printStackTrace();
    }
}
