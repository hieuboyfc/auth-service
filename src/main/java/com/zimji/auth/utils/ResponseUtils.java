package com.zimji.auth.utils;

import com.zimji.auth.configuration.message_source.MessageTranslator;
import com.zimji.auth.exception.BusinessException;
import com.zimji.auth.payload.response.BaseResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

public class ResponseUtils {

    public static <T> ResponseEntity<BaseResponse<?>> handlerSuccess() {
        return ResponseEntity.ok(BaseResponse.builder()
                .code(String.valueOf(HttpStatus.OK.value()))
                .timestamp(new Date())
                .build());
    }

    public static <T> ResponseEntity<BaseResponse<?>> handlerSuccess(T result) {
        return ResponseEntity.ok(BaseResponse.builder()
                .code(String.valueOf(HttpStatus.OK.value()))
                .timestamp(new Date())
                .result(result)
                .build());
    }

    public static ResponseEntity<BaseResponse<?>> handlerException(Throwable exception, HttpStatus status) {
        String description = StringUtils.EMPTY;
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (ObjectUtils.isNotEmpty(attributes)) {
            HttpServletRequest request = ObjectUtils.isNotEmpty(attributes) ? attributes.getRequest() : null;
            description = ObjectUtils.isNotEmpty(request) ? request.getServletPath() : StringUtils.EMPTY;
        }

        if (exception instanceof BusinessException) {
            return handleBusinessException((BusinessException) exception, description);
        } else {
            BaseResponse<?> response = BaseResponse.builder()
                    .code(String.valueOf(status.value()))
                    .timestamp(new Date())
                    .message(exception.getMessage())
                    .description(description)
                    .build();

            return new ResponseEntity<>(response, status);
        }
    }

    public static ResponseEntity<BaseResponse<?>> handleBusinessException(BusinessException exception, String description) {
        String message = ObjectUtils.isNotEmpty(exception.getArgs()) ?
                MessageTranslator.getMessage(exception.getMessage(), exception.getArgs()) :
                MessageTranslator.getMessage(exception.getMessage());

        BaseResponse<?> response = BaseResponse.builder()
                .code(exception.getCode())
                .message(message)
                .timestamp(new Date())
                .description(description)
                .build();

        return ResponseEntity.badRequest().body(response);
    }

    public static BaseResponse<?> buildBaseResponse(Exception exception, WebRequest request, int statusCode) {
        return BaseResponse.builder()
                .code(String.valueOf(statusCode))
                .timestamp(new Date())
                .message(MessageTranslator.getMessage(exception.getMessage()))
                .description(request.getDescription(false))
                .build();
    }

}
