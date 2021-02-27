package com.db.tradestore.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = MaturityDateValidator.class)
@Documented
public @interface MaturityDateValidate {

    String message() default "Please enter Maturity date on or after today's date";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}