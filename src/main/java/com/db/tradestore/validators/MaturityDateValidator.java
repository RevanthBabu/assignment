package com.db.tradestore.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class MaturityDateValidator implements ConstraintValidator<MaturityDateValidate, LocalDate> {
    @Override
    public void initialize(MaturityDateValidate constraintAnnotation) {

    }

    @Override
    public boolean isValid(LocalDate localDate, ConstraintValidatorContext constraintValidatorContext) {
        if (localDate.isBefore(LocalDate.now())) {
            return false;
        }
        return true;
    }
}
