package com.biusobonnanno.beacon_museum.models;

public class TelemetryData {
    private long version = 0, batteryMilliVolts = 0, pduCount = 0, uptime = 0;
    private float temperature = 0F;

    public TelemetryData(long version, long batteryMilliVolts, float temperature, long pduCount, long uptime) {
        this.version = version;
        this.batteryMilliVolts = batteryMilliVolts;
        this.pduCount = pduCount;
        this.uptime = uptime;

        float ret = temperature / 256F;
        if (ret == (1 << 7)) { // 0x8000 -> kt: (ret == (1 shl 7).toFloat()) //da verificare se la conversione è corretta
            this.temperature = 0F;
        }
        else this.temperature = (ret > (1 << 7)) ? ret - (1 << 8) : ret; // kt: if (ret > (1 shl 7)) ret - (1 shl 8) else ret //da verificare se la conversione è corretta
    }

    public long getVersion() {
        return version;
    }

    public long getBatteryMilliVolts() {
        return batteryMilliVolts;
    }

    public long getPduCount() {
        return pduCount;
    }

    public long getUptime() {
        return uptime;
    }

    public float getTemperature() {
        return temperature;
    }
}
