package com.mta.jwt.demo.repository.specifications;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class SearchCriteria {
    private String key;
    private SearchOperation operation;

    private String valueType;
    private Object value;
    private List<String> values;


    public static Class getClass(String valueType) {
        try {
            switch (valueType) {
                case "String":
                    return Class.forName("java.lang.String");
                case "Integer":
                    return Class.forName("java.util.Integer");
                case "Float":
                    return Class.forName("java.util.Float");
                case "Short":
                    return Class.forName("java.lang.Short");
                case "Date":
                    return Class.forName("java.util.Date");
                case "BigDecimal":
                    return Class.forName("java.math.BigDecimal");
                default:
                    return null;
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws ClassNotFoundException {


    }

    public SearchCriteria() {

    }

    public String getValueType() {
        return valueType;
    }

    public void setValueType(String valueType) {
        this.valueType = valueType;
    }

    public SearchCriteria(String key, SearchOperation operation, Object value) {
        this.key = key;
        this.operation = operation;
        this.value = value;
    }

    public SearchCriteria(String key, SearchOperation operation, String valueType, Object value) {
        this.key = key;
        this.operation = operation;
        this.valueType = valueType;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public SearchOperation getOperation() {
        return operation;
    }

    public void setOperation(SearchOperation operation) {
        this.operation = operation;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }
}
