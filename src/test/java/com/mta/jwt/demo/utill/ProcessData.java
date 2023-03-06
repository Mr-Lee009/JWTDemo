package com.mta.jwt.demo.utill;

import com.mta.jwt.demo.MtaSecurityApplicationTests;
import com.mta.jwt.demo.service.UserDetailsService;
import sun.net.www.content.audio.x_aiff;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

public class ProcessData {
    public static void main(String[] args) {
        ProcessData.runNow();
    }

    public static void runNow() {
        loadData(MtaSecurityApplicationTests.class, "contextLoads2");
    }

    public static <T> void loadData(Class<T> clazz, String functionName) {
        try {
            Method rootMethod = null;
            Annotation root = null;
            for (Method method : clazz.getDeclaredMethods()) {
                if (functionName.equals(method.getName())) {
                    rootMethod = method;
                }
            }

            Annotation[] annotations = rootMethod.getAnnotations();
            for (Annotation annotation : annotations) {
                if (annotations[1].annotationType() == TestData.class) {
                    root = annotation;
                    break;
                }
            }
            if (root == null) {
                return;
            }
            String path = String.valueOf(root.getClass().getAnnotation(TestData.class).FilePath());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
