package com.cirilo.cirilofood.core.validation;

import java.math.BigDecimal;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ValidationException;

import org.springframework.beans.BeanUtils;

public class ZeroValueIncludeDescriptionValidator implements ConstraintValidator<ZeroValueIncludeDescription, Object> {

    private String valueField;

    private String descriptionField;

    private String descriptionMandatory;

    @Override
    public void initialize(ZeroValueIncludeDescription constraintAnnotation) {
        this.valueField = constraintAnnotation.valueField();
        this.descriptionField = constraintAnnotation.descriptionField();
        this.descriptionMandatory = constraintAnnotation.descriptionMandatory();
    }

    @Override
    public boolean isValid(Object objectToValidate, ConstraintValidatorContext context) {
        boolean valid = true;

        try {
            BigDecimal value = (BigDecimal) BeanUtils.getPropertyDescriptor(objectToValidate.getClass(), valueField)
                    .getReadMethod().invoke(objectToValidate);

            String description = (String) BeanUtils.getPropertyDescriptor(objectToValidate.getClass(), descriptionField)
                    .getReadMethod().invoke(objectToValidate);

            if (value != null && BigDecimal.ZERO.compareTo(value) == 0 && description != null) {
                valid = description.toLowerCase().contains(descriptionMandatory.toLowerCase());
            }

            return valid;
        } catch (Exception e) {
            throw new ValidationException(e);
        }

    }
}
