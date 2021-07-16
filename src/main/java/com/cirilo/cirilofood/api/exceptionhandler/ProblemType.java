package com.cirilo.cirilofood.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {

    INVALID_PARAMETER("/invalid-parameter","Invalid Parameter"),
    MESSAGE_NOT_READABLE("/message-not-readable", "Not Readable Message"),
    RESOURCE_NOT_FOUND("/resource-not-found", "Resource Not Found"),
    ENTITY_IN_USE("/entity-in-use", "Entity In Use"),
    BUSINESS_ERROR("/business-error", "Violation Rule"),
    SYSTEM_ERROR("/system-error", "System Error"),
    INVALID_DATA("/invalid-data", "Invalid Data");

    private String title;

    private String uri;

    ProblemType(String path, String title) {
        this.uri = "https://cirilofood.com.br" + path;
        this.title = title;
    }
}
