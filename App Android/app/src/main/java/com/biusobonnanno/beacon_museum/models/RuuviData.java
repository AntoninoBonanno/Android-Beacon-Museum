package com.biusobonnanno.beacon_museum.models;

public class RuuviData {
    private int humidity, airPressure, temperatue;

    public RuuviData(int humidity, int airPressure, int temperatue) {
        this.humidity = humidity;
        this.airPressure = airPressure;
        this.temperatue = temperatue;
    }

    public int getHumidity() {
        return humidity;
    }

    public int getAirPressure() {
        return airPressure;
    }

    public int getTemperatue() {
        return temperatue;
    }
}
