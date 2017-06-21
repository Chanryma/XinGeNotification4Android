package com.ecity.notification;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.google.gson.Gson;

public class GsonUtil {
    private static final Gson gson = new Gson();

    public static String toJson(Object obj) {
        try {
            return gson.toJson(obj, obj.getClass());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Convert obj first to a json string, then put the string in a {@link JSONObject}
     * @param obj source
     * @return A JSONObject.
     * @throws JSONException
     */
    public static JSONObject toJSONObject(Object obj) throws JSONException {
        return new JSONObject(GsonUtil.toJson(obj));
    }

    /**
     * Convert obj first to a json string, then put the string in a {@link JSONArray}
     * @param obj source
     * @return A JSONArray.
     * @throws JSONException
     */
    public static JSONArray toJSONArray(Object obj) throws JSONException {
        return new JSONArray(GsonUtil.toJson(obj));
    }

    public static Object tobject(String json, Class<?> cls) {
        try {
            return gson.fromJson(json, cls);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T toObject(String json, Class<T> cls) {
        try {
            return gson.fromJson(json, cls);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
