package com.mta.jwt.demo.repository.specifications;

import com.mta.jwt.demo.entity.User;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class UserSpecification implements Specification<User> {

    private SearchCriteria _criteria;

    public UserSpecification(SearchCriteria criteria) {
        this._criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        switch (_criteria.getOperation()) {
            case EQUALITY:
                return builder.equal(root.get(_criteria.getKey()), _criteria.getValue());
            case NEGATION:
                return builder.notEqual(root.get(_criteria.getKey()), _criteria.getValue());
            case GREATER_THAN:
                return builder.greaterThan(root.<String>get(_criteria.getKey()),
                        _criteria.getValue().toString()
                );
            case LESS_THAN:
                return builder.lessThan(root.<String>get(_criteria.getKey()),
                        _criteria.getValue().toString()
                );
            case LIKE:
                return builder.like(root.<String>get(_criteria.getKey()),
                        _criteria.getValue().toString()
                );
            case STARTS_WITH:
                return builder.like(root.<String>get(_criteria.getKey()),
                        _criteria.getValue() + "%"
                );
            case ENDS_WITH:
                return builder.like(root.<String>get(_criteria.getKey()),
                        "%" + _criteria.getValue()
                );
            case CONTAINS:
                return builder.like(root.<String>get(_criteria.getKey()),
                        "%" + _criteria.getValue() + "%"
                );
            case IN:
                return builder
                        .in(root.get(_criteria.getKey()))
                        .value(
                                castToRequiredType(
                                        root.get(_criteria.getKey()).getJavaType(),
                                        _criteria.getValues()
                                )
                        );
            default:
                return null;
        }
    }

    private Object castToRequiredType(Class fieldType, String value) {
        if (fieldType.isAssignableFrom(Double.class)) {
            return Double.valueOf(value);
        } else if (fieldType.isAssignableFrom(Integer.class)) {
            return Integer.valueOf(value);
        } else if (Enum.class.isAssignableFrom(fieldType)) {
            return Enum.valueOf(fieldType, value);
        }
        return null;
    }

    private Object castToRequiredType(Class fieldType, List<String> value) {
        List<Object> lists = new ArrayList<>();
        for (String s : value) {
            lists.add(castToRequiredType(fieldType, s));
        }
        return lists;
    }

}
