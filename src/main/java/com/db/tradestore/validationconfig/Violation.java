package com.db.tradestore.validationconfig;

import java.io.Serializable;

public class Violation implements Serializable {

    private final String fieldName;

    public String getFieldName() {
        return fieldName;
    }

    public String getMessage() {
        return message;
    }

    private final String message;

    public Violation(String fieldName, String message) {
        this.fieldName = fieldName;
        this.message = message;
    }

}