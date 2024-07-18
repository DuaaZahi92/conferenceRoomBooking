package com.example.conferenceroombooking.exception;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Getter
public enum ConferenceRoomErrorEnum implements Serializable {
    GENERAL_ERROR( 1000, 400, "General Error occurred"),
    UNAUTHORIZED_ERROR(1001, 401, "Unauthorized to do action"),
    INVALID_VALUE(1002, 400, "Invalid value"),
    CONFIGURATION_ERROR(1003, 500, "Configuration Error"),
    NOT_ALLOWED(1004, 400, "Not Allowed")
    ;

    private Integer code;

    private Integer httpStatusCode;

    private String message;

    private ConferenceRoomErrorEnum(Integer code, Integer httpStatusCode, String message) {
        this.code = code;
        this.httpStatusCode = httpStatusCode;
        this.message = message;
    }

    private static final Map<Integer, ConferenceRoomErrorEnum> codesMap = new ConcurrentHashMap<>();

    static {
        for (ConferenceRoomErrorEnum e : values()) {
            codesMap.put(e.code, e);
        }
    }

    @JsonCreator
    public static ConferenceRoomErrorEnum getByCode(Integer code) {
        if (code != null) {
            return codesMap.get(code);
        }
        return null;
    }

}
