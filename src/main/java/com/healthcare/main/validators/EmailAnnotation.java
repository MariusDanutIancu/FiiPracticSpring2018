package com.healthcare.main.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


@Constraint(validatedBy = {EmailValidator.class})
@Target({METHOD, FIELD, CONSTRUCTOR, PARAMETER})
@Retention(RUNTIME)
public @interface EmailAnnotation {
    String message() default "The email format is not correct";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
