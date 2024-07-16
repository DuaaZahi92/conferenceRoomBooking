package com.example.conferenceroombooking.exception;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Getter
public enum ConferenceRoomError implements Serializable {
    GENERAL_ERROR( 1000, 400, "General Error occurred"),
    UNAUTHORIZED_ERROR(1001, 401, "Unauthorized to do action"),
    INVALID_VALUE(1002, 400, "Invalid value"),
    CONFIGURATION_ERROR(1003, 500, "Configuration Error"),
    NOT_ALLOWED(1004, 400, "Not Allowed")
    ;

    @JsonValue
    private Integer code;

    private Integer httpStatusCode;

    private String message;

    private ConferenceRoomError(Integer code, Integer httpStatusCode, String message) {
        this.code = code;
        this.httpStatusCode = httpStatusCode;
        this.message = message;
    }

    private static final Map<Integer, ConferenceRoomError> codesMap = new ConcurrentHashMap<>();

    static {
        for (ConferenceRoomError e : values()) {
            codesMap.put(e.code, e);
        }
    }

    @JsonCreator
    public static ConferenceRoomError getByCode(Integer code) {
        if (code != null) {
            return codesMap.get(code);
        }
        return null;
    }


    @Override
    public String toString() {
        return this.message;
    }
}
