package org.cloud.core.utils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import org.cloud.core.base.BaseConstant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonUtils {
    private static Gson gson = null;

    static {
        if (gson == null) {
            gson = new Gson();
        }
    }

    private JsonUtils() {
    }

    /**
     * 对象转成json
     *
     * @param object
     * @return json
     */
    public static String objectToJson(Object object) {
        String json = null;
        if (gson != null) {
            json = gson.toJson(object);
        }
        return json;
    }

    /**
     * Json转成对象
     *
     * @param jsonElement
     * @param cls
     * @return 对象
     */
    public static <T> T jsonToBean(JsonElement jsonElement, Class<T> cls) {
        T t = null;
        if (gson != null) {
            t = gson.fromJson(jsonElement, cls);
        }
        return t;
    }

    /**
     * Json转成对象
     *
     * @param json
     * @param cls
     * @return 对象
     */
    public static <T> T jsonToBean(String json, Class<T> cls) {
        T t = null;
        if (gson != null) {
            t = gson.fromJson(json, cls);
        }
        return t;
    }

    /**
     * json转成list<T>
     *
     * @param jsonArray
     * @param cls
     * @return list<T>
     */
    public static <T> List<T> jsonToList(JsonArray jsonArray, Class<T> cls) {
        List<T> list = new ArrayList<>();
        if (gson != null) {
            for (JsonElement jsonElement : jsonArray) {
                list.add(gson.fromJson(jsonElement, cls)); //cls
            }
        }
        return list;
    }

    /**
     * json转成list<T>
     *
     * @param json
     * @param cls
     * @return list<T>
     */
    public static <T> List<T> jsonToList(String json, Class<T> cls) {
        List<T> list = null;
        if (gson != null) {
            list = gson.fromJson(json, new TypeToken<List<T>>() {
            }.getType());
        }
        return list;
    }

    /**
     * json转成list<T>
     *
     * @param jsonElement
     * @return list<T>
     */
    public static ArrayList<HashMap<String, String>> jsonToList(JsonElement jsonElement) {
        ArrayList<HashMap<String, String>> list = new ArrayList<>();
        if (gson != null) {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            for (Map.Entry<String, JsonElement> elementEntry : jsonObject.entrySet()) {
                HashMap<String, String> hashMap = new HashMap<>();
                String key = elementEntry.getKey();
                String value = jsonObject.get(key).getAsString();
                hashMap.put(BaseConstant.DATA_KEY, key);
                hashMap.put("value", value);
                list.add(hashMap);
            }
        }
        return list;
    }

    /**
     * json转成list中有map的
     *
     * @param json
     * @return List<Map < String, T>>
     */
    public static <T> List<Map<String, T>> jsonToListMaps(String json) {
        List<Map<String, T>> list = null;
        if (gson != null) {
            list = gson.fromJson(json, new TypeToken<List<Map<String, T>>>() {
            }.getType());
        }
        return list;
    }

    /**
     * json转成map的
     *
     * @param json
     * @return Map<String, T>
     */
    public static <T> Map<String, T> jsonToMaps(String json) {
        Map<String, T> map = null;
        if (gson != null) {
            map = gson.fromJson(json, new TypeToken<Map<String, T>>() {
            }.getType());
        }
        return map;
    }

    public static JsonElement parseJsonBody(String jsonString) {
        JsonObject rootJsonObject = new JsonParser().parse(jsonString).getAsJsonObject();
        int code = rootJsonObject.get("code").getAsInt();
        if (code != 200) {
            return JsonNull.INSTANCE;
        }
        return rootJsonObject.get("datas");
    }
}
