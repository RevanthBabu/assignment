package com.db.tradestore.validators;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class MaturityDateValidatorTest {
    private MaturityDateValidator maturityDateValidator;

    @Mock ConstraintValidatorContext constraintValidatorContext;

    @BeforeEach()
    public void setUp() {
        maturityDateValidator = new MaturityDateValidator();
    }

    @Test
    public void testMaturityDateEqualsCurrentDate() {
        assertTrue(maturityDateValidator.isValid(LocalDate.now(), constraintValidatorContext));
    }

    @Test
    public void testMaturityDateLessThanCurrentDate() {
        assertFalse(maturityDateValidator.isValid(LocalDate.parse("2021-02-26"), constraintValidatorContext));
    }

    @Test
    public void testMaturityDateGreaterThanCurrentDate() {
        assertTrue(maturityDateValidator.isValid(LocalDate.parse("2022-03-26"), constraintValidatorContext));
    }
}