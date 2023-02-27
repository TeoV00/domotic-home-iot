package it.unibo.esiot.service.model;

import java.util.Arrays;
import java.util.Optional;

public enum AlarmState {
    OFF("Disattivato"),
    ON("Attivato"),
    RINGING("Movimento rilevato!"),
    RESETTING("Reset"),
    SWITCH_OFF("In disattivazione"),
    SWITCH_ON("In Attivazione"),
    UNKNOWN("Sconosciuto");

    private String descr;

    AlarmState (final String description) {
        this.descr = description;
    }

    public int getValue() {
        return this.ordinal();
    }

    public String getDescr() {
        return this.descr;
    }

    static AlarmState getFromValue(final int value) {
        return Arrays.stream(AlarmState.values()).filter(s -> s.ordinal() == value).findFirst().orElse(AlarmState.UNKNOWN);
    }
}
