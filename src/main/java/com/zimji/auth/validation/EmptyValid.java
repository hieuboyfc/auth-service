package com.zimji.auth.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmptyValidator.class)
public @interface EmptyValid {

    String message() default "LengthValid ---> Error";

    int errorCode() default 1997;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
