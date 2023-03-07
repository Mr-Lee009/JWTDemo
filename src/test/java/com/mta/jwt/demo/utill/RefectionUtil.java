package com.mta.jwt.demo.utill;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

public class RefectionUtil {
    public static <T> Annotation getAnnotation(Annotation[] annotations, Class<T> clazz) {
        for (Annotation annotation : annotations) {
            if (annotation.annotationType() == clazz) {
                return annotation;
            }
        }
        return null;
    }

    public static Method getMethod(Method[] methods, String functionName) {
        for (Method method : methods) {
            if (functionName.equals(method.getName())) {
                return method;
            }
        }
        return null;
    }

    public static Class[] getParam(Method method) {
        Class[] type = method.getParameterTypes();
        return type;
    }

}
