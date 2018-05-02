package com.healthcare.main.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class EmailValidator implements ConstraintValidator<EmailAnnotation, String> {

    @Override
    public void initialize(EmailAnnotation constraintAnnotation) {
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s.contains("@");
    }
}
