package com.example.conferenceroombooking.controller.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum ResponseStatus implements Serializable {
    OPERATION_SUCCESS( "Operation Successful"),
    OPERATION_FAIL("Operation Failed"),
    ;

    @JsonValue
    private String code;

    private ResponseStatus(String code) {
        this.code = code;
    }

    private static final Map<String, ResponseStatus> codesMap = new ConcurrentHashMap<>();

    static {
        for (ResponseStatus e : values()) {
            codesMap.put(e.code, e);
        }
    }

    @JsonCreator
    public static ResponseStatus getByCode(String code) {
        if (code != null && !code.isEmpty()) {
            return codesMap.get(code);
        }
        return null;
    }


    @Override
    public String toString() {
        return this.code;
    }
}
