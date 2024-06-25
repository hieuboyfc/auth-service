package com.zimji.auth.exception;

import com.zimji.auth.configuration.message_source.MessageTranslator;

public class EntityNotFoundException extends jakarta.persistence.EntityNotFoundException {

    public EntityNotFoundException(Object... args) {
        super(MessageTranslator.getMessage("not-be-found-with-id", args));
    }

    public EntityNotFoundException(Class<?> entity, Object entityId) {
        super(String.format(entity.getSimpleName() + " " + MessageTranslator.getMessage("not-be-found-with-id") + ": " + entityId));
    }

}
