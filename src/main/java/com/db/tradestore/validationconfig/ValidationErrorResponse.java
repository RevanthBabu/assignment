package com.db.tradestore.validationconfig;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ValidationErrorResponse implements Serializable {

    public List<Violation> getViolations() {
        return violations;
    }

    public void setViolations(List<Violation> violations) {
        this.violations = violations;
    }

    private List<Violation> violations = new ArrayList<>();

}