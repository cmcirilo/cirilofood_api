package com.cirilo.cirilofood.core.validation;

import java.math.BigDecimal;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MultipleValidator implements ConstraintValidator<Multiple, Number> {

    private int numberMultiple;

    @Override
    public void initialize(Multiple constraintAnnotation) {
        this.numberMultiple = constraintAnnotation.number();
    }

    @Override
    public boolean isValid(Number value, ConstraintValidatorContext context) {
        boolean valid = true;

        if (value != null) {
            var decimalValue = BigDecimal.valueOf(value.doubleValue());
            var decimalMultiple = BigDecimal.valueOf(this.numberMultiple);
            var module = decimalValue.remainder(decimalMultiple);

            valid = BigDecimal.ZERO.compareTo(module) == 0;
        }

        return valid;
    }
}
