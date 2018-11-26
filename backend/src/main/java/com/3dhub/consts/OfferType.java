package com.greencrane.consts;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

public enum OfferType {
    ALL("all", 0),
    FORK_LIFT("fork_lift", 1),
    CRANE("crane", 2),
    DIGGER("digger", 3);

    @JsonProperty("name")
    public String name;
    @JsonProperty("code")
    public int code;

    OfferType(String name, int code) {
        this.name = name;
        this.code = code;
    }

    @JsonCreator
    public static OfferType find(@JsonProperty("name") final String name, @JsonProperty("code") final int code) {
        return Arrays.stream(OfferType.values()).filter(ot -> (ot.code == code && ot.name.equals(name))).findFirst().orElse(OfferType.ALL);
    }
}
