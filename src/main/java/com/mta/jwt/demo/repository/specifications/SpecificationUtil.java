package com.mta.jwt.demo.repository.specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class SpecificationUtil {

    public static void main(String[] args) {
        Date a = SearchCriteria.<Date>convertInstanceOfObject("Wed Mar 01 11:34:22 ICT 2023");
        System.out.println(a);
    }
    public static <T> Predicate createPredicate(SearchCriteria _criteria, Root<T> root, CriteriaBuilder builder) {

        Object value = _criteria.getValue();

        switch (_criteria.getOperation()) {
            case EQUALITY:
                return builder.equal(root.get(_criteria.getKey()), value);
            case NEGATION:
                return builder.notEqual(root.get(_criteria.getKey()), value);
//            case GREATER_THAN:
//                return builder.greaterThan(root.<String>get(_criteria.getKey()),(String)value);
//            case LESS_THAN:
//                return builder.lessThan(
//                        root.get(_criteria.getKey()),
//                        (String) castToRequiredType(root.get(_criteria.getKey()).getJavaType(), _criteria.getValue().toString())
//                );
//            case LIKE:
//                return builder.like(root.<String>get(_criteria.getKey()),
//                        _criteria.getValue().toString()
//                );
//            case STARTS_WITH:
//                return builder.like(root.<String>get(_criteria.getKey()),
//                        _criteria.getValue() + "%"
//                );
//            case ENDS_WITH:
//                return builder.like(root.<String>get(_criteria.getKey()),
//                        "%" + _criteria.getValue()
//                );
//            case CONTAINS:
//                return builder.like(root.<String>get(_criteria.getKey()),
//                        "%" + _criteria.getValue() + "%"
//                );
            case IN:
                if (null == _criteria.getValues() || _criteria.getValues().size() < 1)
                    return null;

                List<String> values = _criteria.getValues();
                return builder.and(
                        builder.greaterThan(
                                root.get(_criteria.getKey()),
                                (String) castToRequiredType(root.get(_criteria.getKey()).getJavaType(), values.get(0))
                        ),
                        builder.lessThan(
                                root.get(_criteria.getKey()),
                                (String) castToRequiredType(root.get(_criteria.getKey()).getJavaType(), values.get(1))
                        )
                );
            default:
                return null;
        }
    }

    static private Object castToRequiredType(Class fieldType, String value) {
        if (fieldType.isAssignableFrom(Double.class)) {
            return Double.valueOf(value);
        } else if (fieldType.isAssignableFrom(String.class)) {
            return String.valueOf(value);
        } else if (fieldType.isAssignableFrom(Integer.class)) {
            return Integer.valueOf(value);
        } else if (Enum.class.isAssignableFrom(fieldType)) {
            return Enum.valueOf(fieldType, value);
        } else if (Date.class.isAssignableFrom(fieldType)) {
            try {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss",
                        new Locale("vi", "VN")
                );
                simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC+7"));
                Date date = simpleDateFormat.parse(value);
                System.out.println(date);
                return date;
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    static private Object castToRequiredType(Class fieldType, List<String> value) {
        List<String> lists = new ArrayList<>();
        for (String s : value) {
            lists.add((String) castToRequiredType(fieldType, s));
        }
        return lists;
    }
}
