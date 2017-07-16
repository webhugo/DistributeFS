package utils;



import com.google.gson.Gson;

/**
 * Created by webhugo on 3/11/17.
 */
public final class JsonUtils {
    private JsonUtils() {}

    /**
     * 将对象序列化成json字符串
     * @param object javaBean
     * @return jsonString json字符串
     */
    public static String toJson(Object object) {
        return getInstance().toJson(object);
    }

    /**
     * 将json反序列化成对象
     * @param jsonString jsonString
     * @param valueType class
     * @param <T> T 泛型标记
     * @return Bean
     */
    public static <T> T parse(String jsonString, Class<T> valueType) {
        return getInstance().fromJson(jsonString,valueType);
    }

    private static Gson getInstance() {
        return GsonHolder.INSTANCE;
    }

    private static class GsonHolder {
        private static Gson INSTANCE = new Gson();
    }
}
