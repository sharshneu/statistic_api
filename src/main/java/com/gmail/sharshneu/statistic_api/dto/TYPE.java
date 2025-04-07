package com.gmail.sharshneu.statistic_api.dto;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TYPE {

    PRESSURE("Pressure"),
    VOLTAGE("Voltage"),
    TEMPERATURE("Temperature"),
    HUMIDITY("Humidity");

    private final String alias;

    TYPE(String alias) {
        this.alias = alias;
    }

    public static TYPE fromAlias(String alias) {
        if (alias == null) {
            throw new IllegalArgumentException("TYPE can't be null: ");
        }
        for (TYPE type : values()) {
            if (type.alias.equalsIgnoreCase(alias)) {
                return type;
            }
        }
        throw new IllegalArgumentException("No TYPE for : " + alias);
    }

    @JsonValue
    public String getAlias() {
        return alias;
    }
}