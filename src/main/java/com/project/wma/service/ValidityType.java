package com.project.wma.service;

public enum ValidityType {
    PRNUMBERS("Pr Numbers"),
    VARIANTS("Variants");

    private final String value;

    ValidityType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
