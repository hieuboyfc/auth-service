package com.zimji.auth.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.ReportAsSingleViolation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {EmptyValidator.class})
@ReportAsSingleViolation
public @interface EmptyValid {

    String message() default "LengthValid ---> Error";

    String errorCode() default "1997";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
