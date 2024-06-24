package com.zimji.auth.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class PatternValidator implements ConstraintValidator<PatternValid, String> {

    private String regexPattern;

    @Override
    public void initialize(PatternValid constraintAnnotation) {
        regexPattern = constraintAnnotation.regexPattern();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null) {
            return true;
        }
        return Pattern.matches(regexPattern, value);
    }

}
