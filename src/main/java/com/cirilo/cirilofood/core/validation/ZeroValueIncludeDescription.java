package com.cirilo.cirilofood.core.validation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = {ZeroValueIncludeDescriptionValidator.class})
public @interface ZeroValueIncludeDescription {

    String message() default "description mandatory invalid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String valueField();

    String descriptionField();

    String descriptionMandatory();

}
