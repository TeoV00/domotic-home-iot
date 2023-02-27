package it.unibo.esiot.service.model;

import java.util.Arrays;
import java.util.Optional;

public enum GarageState {
    CLOSE("Chiuso"),
    OPEN("Aperto"),
    CLOSING("In chiusura"),
    OPENING("In apertura"),
    PAUSE("Pausa"), //closing in pause
    REQ_CLOSE("Elaborazione chiusura"), //close request
    REQ_OPEN("Elaborazione apertura"), //open request
    REQ_PAUSE("Elaborazione pausa"),
    UNKNOWN("Sconosciuto");

    private final String descr;

    GarageState( final String description) {
        this.descr = description;
    }

    public String getDescr() {
        return this.descr;
    }

    public int getValue() {
        return this.ordinal();
    }

    static GarageState getFromValue(final int value) {
        return Arrays.stream(GarageState.values()).filter(s -> s.ordinal() == value).findFirst().orElse(GarageState.UNKNOWN);
    }
}
