package com.biusobonnanno.beacon_museum.models;

import com.biusobonnanno.beacon_museum.utils.RuuviParser;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.utils.UrlBeaconUrlCompressor;

import java.util.Date;

public class MyBeacon {
    public static String TYPE_EDDYSTONE_UID = "eddystone_uid",
            TYPE_EDDYSTONE_URL = "eddystone_url",
            TYPE_ALTBEACON = "altBeacon",
            TYPE_IBEACON = "iBeacon",
            TYPE_RUUVITAG = "ruuvitag";

    private String beaconType = null, beaconAddress, beaconName;
    private int hashCode, manufacturer, txPower, rssi;
    private double distance;
    private long lastSeen;

    private IbeaconData ibeaconData;
    private EddystoneUrlData eddystoneUrlData;
    private EddystoneUidData eddystoneUidData;
    private TelemetryData telemetryData;
    private RuuviData ruuviData;

    public MyBeacon(Beacon beacon) {
        this.hashCode = beacon.hashCode();
        this.beaconAddress = beacon.getBluetoothAddress();
        this.beaconName = beacon.getBluetoothName();
        this.manufacturer = beacon.getManufacturer();
        update(beacon);

        if(beacon.getServiceUuid() ==  0xFEAA) { // This is an Eddystone format

            // Do we have telemetry data?
            if (beacon.getExtraDataFields().size() >= 5) {
                telemetryData = new TelemetryData(beacon.getExtraDataFields().get(0), //da testare
                        beacon.getExtraDataFields().get(1),
                        beacon.getExtraDataFields().get(2),
                        beacon.getExtraDataFields().get(3),
                        beacon.getExtraDataFields().get(4));
            }

            if(beacon.getBeaconTypeCode() == 0x00){ // This is a Eddystone-UID frame
                this.beaconType = TYPE_EDDYSTONE_UID;
                this.eddystoneUidData = new EddystoneUidData(beacon.getId1().toString(), beacon.getId2().toString());
            } else if(beacon.getBeaconTypeCode() == 0x10){ // This is a Eddystone-URL frame
                this.beaconType = TYPE_EDDYSTONE_URL;
                String url = UrlBeaconUrlCompressor.uncompress(beacon.getId1().toByteArray());
                this.eddystoneUrlData = new EddystoneUrlData(url);

                if(url.startsWith("https://ruu.vi/#") == true) { // This is a RuuviTag
                    this.beaconType = TYPE_RUUVITAG;
                    if(this.beaconAddress != null && this.beaconAddress.hashCode() != 0) this.hashCode = -1;
                    RuuviParser ruuviParser = new RuuviParser(url.split("#")[1]);
                    ruuviData = new RuuviData(ruuviParser.getHumidity(), ruuviParser.getAirPressure(), ruuviParser.getTemp());
                }
            }
        } else { // This is an iBeacon or ALTBeacon
            this.beaconType = (beacon.getServiceUuid() ==  0xBEAC) ? TYPE_ALTBEACON : TYPE_IBEACON;
            this.ibeaconData = new IbeaconData(beacon.getId1().toString(), beacon.getId2().toString(), beacon.getId3().toString());
        }
    }

    public void update(Beacon beacon){
        this.txPower = beacon.getTxPower();
        this.rssi = beacon.getRssi();
        this.distance = beacon.getDistance();
        this.lastSeen = new Date().getTime();
    }

    public int hashCode() {
        return hashCode;
    }

    public String getHashCode() {
        return String.valueOf(hashCode);
    }

    public String getBeaconType() {
        return beaconType;
    }

    public String getBeaconAddress() {
        return beaconAddress;
    }

    public int getManufacturer() {
        return manufacturer;
    }

    public int getTxPower() {
        return txPower;
    }

    public int getRssi() {
        return rssi;
    }

    public String getDistance() {
        return String.format("%.2f", distance);
    }

    public long getLastSeen() {
        return lastSeen;
    }

    public String getBeaconName() {
        return beaconName;
    }

    public IbeaconData getIbeaconData() {
        return ibeaconData;
    }

    public EddystoneUrlData getEddystoneUrlData() {
        return eddystoneUrlData;
    }

    public EddystoneUidData getEddystoneUidData() {
        return eddystoneUidData;
    }

    public TelemetryData getTelemetryData() {
        return telemetryData;
    }

    public RuuviData getRuuviData() {
        return ruuviData;
    }
}
