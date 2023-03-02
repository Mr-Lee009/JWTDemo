package com.mta.jwt.demo.repository.specifications;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class SearchCriteria {
    private String key;
    private SearchOperation operation;
    private String valueType;
    private String value;
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

    public SearchCriteria(String key, SearchOperation operation, String value) {
        this.key = key;
        this.operation = operation;
        this.value = value;
    }

    public SearchCriteria(String key, SearchOperation operation, String valueType, String value) {
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

    public String getValue() {
        return value;
    }

    public static <T extends Object> T getValue(String value, Class<T> clazz) {
        return convertInstanceOfObject(value, clazz);
    }

    private static <T extends Object> T convertInstanceOfObject(Object o, Class<T> clazz) {
        return null;
    }

    public static <T extends Object> T convertInstanceOfObject(Object o) {
        String regexDate = "^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]|(?:Jan|Mar|May|Jul|Aug|Oct|Dec)))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[1,3-9]|1[0-2]|(?:Jan|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec))\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)(?:0?2|(?:Feb))\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9]|(?:Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep))|(?:1[0-2]|(?:Oct|Nov|Dec)))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$";

        try {
            if(o instanceof String ){
                String value = (String) o;
                if(value.matches(regexDate)){

                }
                return (T) new Date();
            }
            return (T) o;
        } catch (ClassCastException e) {
            return null;
        }
    }


    public void setValue(String value) {
        this.value = value;
    }

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }
}
