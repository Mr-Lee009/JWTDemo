package com.mta.jwt.demo.utill;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mta.jwt.demo.MtaSecurityApplicationTests;
import com.mta.jwt.demo.entity.User;
import com.mta.jwt.demo.service.UserDetailsService;
import javassist.NotFoundException;
import sun.net.www.content.audio.x_aiff;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProcessData {
    public static void main(String[] args) {
        ProcessData.runNow();
    }

    public static void runNow() {
        ProcessData processData = new ProcessData();
        processData.loadData(ProcessData.class, "concat");
    }

    @TestData(FilePath = "./", Key = "success")
    public String concat(String accessToken, String token) {
        return accessToken.concat(token);
    }

    public <T extends Object> void loadData(Class<T> clazz, String functionName) {
        try {

            //Todo: 1.get method
            Method method = RefectionUtil.getMethod(clazz.getDeclaredMethods(), functionName);
            if (method == null) return;

            //Todo: 2. get annotation
//            Annotation annotation = RefectionUtil.getAnnotation(method.getAnnotations(), TestData.class);
//            if (annotation == null) return;

            //Todo: 3. get key and filePath of annotation TestData
            TestData testData = method.getAnnotation(TestData.class);
            String key = testData.Key();
            String filePath = testData.FilePath();

            //Todo: 4. get all param of function
            ObjectMapper objectMapper = new ObjectMapper();

            String jsonUser = "{\"success\":{\"accessToken\":\"ducla\",\"token\":\"hakjcbnayuscbabcjakbcajksbclac;jasicjacbnaicu\"},\"fail\":{\"id\":null,\"username\":\"a\",\"email\":\"b\",\"password\":\"c\",\"currentAddress\":null,\"enabled\":false}}";
            String a = "1";
            String b = "2";

            //Todo: 5 load data form name test
            Map<String, Object> map = JsonUtil.convertValueMap(jsonUser);
            if (!map.containsKey(key)) {
                throw new NotFoundException("not found key!");
            }

            Map<String, Object> mapDataTest = (Map<String, Object>) map.get(key);
            //Todo: 6 fetch data from json

            Parameter[] parameters = method.getParameters();

            List<Object> fieldValues = new ArrayList();
            for (Parameter param : parameters) {
                String nameParam = param.getName();
                Class<?> coreClass = Class.forName(param.getParameterizedType().getTypeName());
                Object data = mapDataTest.get(nameParam);
                if (data == null) {
                    continue;
                }
                if (coreClass.equals(String.class)) {
                    data = String.valueOf(data);
                } else if (coreClass.equals(Integer.class)) {
                    data = (Integer) data;
                } else if (coreClass.equals(User.class)) {
                    data = objectMapper.convertValue(jsonUser, User.class);
                }

                fieldValues.add(data);
            }
            Class<?> coreClass = Class.forName(clazz.getName());
            T obj = (T) coreClass.newInstance();

            //Todo: 7 execute function
            String reponsedata = (String) method.invoke(obj, fieldValues);

            System.out.printf("[key = %s] [filePath= %s]", key, filePath);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
