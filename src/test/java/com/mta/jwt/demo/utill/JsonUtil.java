package com.mta.jwt.demo.utill;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class JsonUtil {
    private static final Logger log = LoggerFactory.getLogger(JsonUtil.class);
    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public JsonUtil() {

    }

    public static String writeValueAsString(Object object) {
        String result = "";
        try {
            result = OBJECT_MAPPER.writeValueAsString(object);
        } catch (Exception ex) {
            System.out.printf("Can't seriablize object to string", ex);
        }
        return result;
    }

    public static <T> T convertValue(String object, Class<T> clazz) {
        T result = null;
        try {
            result = OBJECT_MAPPER.convertValue(object, clazz);
        } catch (Exception ex) {
            System.out.printf("Can't seriablize object to " + clazz.getName(), ex);
        }
        return result;
    }

    public static <T> T convertToObject(String stringJson, Class<T> clazz) {
        T result = null;
        try {
            result = OBJECT_MAPPER.readValue(stringJson, clazz);
        } catch (Exception ex) {
            System.out.printf("Can't convert json to " + clazz.getName(), ex);
        }
        return result;
    }

    public static Map convertValueMap(Object object) {
        Map result = null;
        try {
            result = (Map) OBJECT_MAPPER.convertValue(object, Map.class);
        } catch (Exception ex) {
            System.out.printf("Can't seriablize object to Map", ex);
        }
        return result;
    }

    public static Map convertValueMap(String jsonString) {
        Map result = null;
        try {
            result = (Map) OBJECT_MAPPER.readValue(jsonString, Map.class);
        } catch (Exception ex) {
            System.out.printf("Can't seriablize object to Map", ex);
        }
        return result;
    }

    public static <T> T convertMapToObject(Map map, Class<T> clazz) {
        T result = null;
        try {
            result = (T) OBJECT_MAPPER.readValue(writeValueAsString(map), clazz);
        } catch (Exception ex) {
            System.out.printf("Can't seriablize Map to ovj", ex);
        }
        return result;
    }

    public static <T> List convertJsonToLstObject(String json, Class<T[]> x) throws Exception {
        return Arrays.asList(OBJECT_MAPPER.readValue(json, x));
    }
}
