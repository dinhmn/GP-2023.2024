package com.graduationproject.backend.backendwebsiteshoe.Common;

import lombok.Getter;

@Getter
public enum Action {

    INSERT("1", "insert"),

    UPDATE("2", "update");

    private final String code;

    private final String value;

    Action(String code, String value) {
        this.code = code;
        this.value = value;
    }
}
