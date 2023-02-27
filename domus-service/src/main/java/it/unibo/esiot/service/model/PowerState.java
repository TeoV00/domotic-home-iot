package it.unibo.esiot.service.model;

import java.util.Arrays;

public enum PowerState {
    OFF("Spento"),
    ON("Acceso"),
    TOGGLE_STATE("Toggle Stato"),
    UNKNOWN("Sconosciuto");

    private String descr;

    PowerState(final String description) {
        this.descr = description;
    }

    public String getDescr() {
        return this.descr;
    }

    public int getValue() {
        return this.ordinal();
    }

    static PowerState getFromValue(final int value) {
        return Arrays.stream(PowerState.values()).filter(s -> s.ordinal() == value).findFirst().orElse(PowerState.UNKNOWN);
    }
}
