package com.zimji.auth.utils;

import com.zimji.auth.exception.BusinessException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.metadata.ConstraintDescriptor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

@Component
public class ValidatorUtils {

    static final Logger LOGGER = LoggerFactory.getLogger(ValidatorUtils.class);

    private final Validator validator;

    public ValidatorUtils() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    // ValidatorUtils.validate(yourObject, YourObject.ValidationGroup.class);
    // public <T> Set<ConstraintViolation<T>> validate(boolean isThrow, T object, Class<?>... groups) {
    public <T> void validate(boolean isThrow, T object, Class<?>... groups) {
        Set<ConstraintViolation<T>> violations = new HashSet<>();
        Set<ConstraintViolation<T>> validateFormat = validator.validate(object, groups);
        if (CollectionUtils.isNotEmpty(validateFormat)) {
            executeValidate(isThrow, violations, validateFormat);
        }
    }

    private <T> void executeValidate(boolean isThrow, Set<ConstraintViolation<T>> constraintViolations,
                                     Set<ConstraintViolation<T>> validateFormat) {
        if (CollectionUtils.isNotEmpty(validateFormat)) {
            constraintViolations.addAll(validateFormat);

            if (BooleanUtils.isTrue(isThrow)) {
                validateFormat.forEach(violation -> {
                    // Lấy ConstraintDescriptor
                    ConstraintDescriptor<?> constraintDescriptor = violation.getConstraintDescriptor();

                    // Lấy AnnotationDescriptor
                    Annotation annotation = constraintDescriptor.getAnnotation();

                    // Lấy tên các thuộc tính và giá trị tương ứng của Annotation
                    String errorCode = "0000";
                    Method errorCodeMethod;
                    try {
                        errorCodeMethod = annotation.annotationType().getDeclaredMethod("errorCode");
                        errorCode = (String) errorCodeMethod.invoke(annotation);
                    } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                        LOGGER.error(e.getMessage());
                    }

                    throw new BusinessException(errorCode, violation.getMessage());
                });
            }
        }
    }

}
