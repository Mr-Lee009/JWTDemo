package com.mta.jwt.demo.dto;

import java.lang.reflect.Field;
import java.util.Arrays;


public abstract class AbstractDto {
    public <T> void copyField(Field field, T t) throws NoSuchFieldException, IllegalAccessException {
        field.setAccessible(true);
        Field fields = t.getClass().getDeclaredField(field.getName());
        if (null != fields) {
            fields.setAccessible(true);
            if (field.getType().equals(fields.getType()) && field.getName().equals(fields.getName())) {
                Object value = field.get(t);
                fields.setAccessible(true);
                fields.set(this, value);
            }
        }
    }
    public <T> void copyProperties(T t) {
        try {
            Field[] fields = t.getClass().getDeclaredFields();
            for (Field subField : fields) {
                copyField(subField, t);
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }
    public <T> void copySomeProperties(T t, String... nameFiles) {
        try {
            Field[] fields = t.getClass().getDeclaredFields();
            for (Field subField : fields) {
                if (Arrays.stream(nameFiles).anyMatch(subField.getName()::contains)) {
                    copyField(subField, t);
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }
}
