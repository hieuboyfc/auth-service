package com.zimji.auth.validation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.hibernate.validator.internal.constraintvalidators.bv.PatternValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PatternValidator.class)
public @interface PatternValid {

    String message() default "PatternValid ---> Error";

    String errorCode() default "1997";

    String regexPattern();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
