package co.com.ajac.messaging.listeners;

import co.com.ajac.base.errors.AppError;

public enum ListenerError implements AppError {
    MESSAGE_STRUCTURE_NOT_FOUNT("ELM-1", "Massage structure not found", ""),
    LISTENER_MESSAGE_NOT_FOUNT("ELM-2", "Listener structure not found", ""),
    LISTENER_NOT_FOUNT("ELM-3", "Listener not found", ""),
    MESSAGE_NOT_DESERIALIZED("ELM-4", "The message could not be deserialized", "");
    private final String code;
    private final String message;
    private final String description;

    ListenerError(String code, String message, String description) {
        this.code = code;
        this.message = message;
        this.description = description;
    }

    @Override
    public String code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }

    @Override
    public String description() {
        return description;
    }
}
