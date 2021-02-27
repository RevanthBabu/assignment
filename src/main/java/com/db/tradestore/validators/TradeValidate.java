package com.db.tradestore.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD, METHOD, PARAMETER, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = TradeValidator.class)
@Documented
public @interface TradeValidate {

    String message() default "Please enter the version greater than or equal to the current version against trade id";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}