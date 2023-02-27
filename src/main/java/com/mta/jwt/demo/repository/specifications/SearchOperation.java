package com.mta.jwt.demo.repository.specifications;

public enum SearchOperation {
    EQUALITY, NEGATION, GREATER_THAN, LESS_THAN, LIKE, STARTS_WITH, ENDS_WITH, CONTAINS, IN;

    public static final String[] SIMPLE_OPERATION_SET = {":", "!", ">", "<", "~"};

    public static SearchOperation getSimpleOperation(String input) {
        switch (input) {
            case ":":
                return EQUALITY;
            case "!":
                return NEGATION;
            case ">":
                return GREATER_THAN;
            case "<":
                return LESS_THAN;
            case "~":
                return LIKE;
            case "STARTS_WITH":
                return STARTS_WITH;
            case "ENDS_WITH":
                return ENDS_WITH;
            case "CONTAINS":
                return CONTAINS;
            case "IN":
                return IN;
            default:
                return null;
        }
    }
}
