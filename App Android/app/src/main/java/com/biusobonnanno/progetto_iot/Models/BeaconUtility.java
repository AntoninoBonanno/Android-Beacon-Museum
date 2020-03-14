package com.biusobonnanno.progetto_iot.Models;

import android.content.Context;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.utils.UrlBeaconUrlCompressor;

import java.util.HashMap;


public class BeaconUtility {

    /** Inizializza il BeaconManager**/
    static public BeaconManager getBeaconManager(Context context) {
        BeaconManager beaconManager = BeaconManager.getInstanceForApplication(context);
        beaconManager.getBeaconParsers().clear();
        // Add all the beacon types we want to discover
        beaconManager.getBeaconParsers().add(new BeaconParser().setBeaconLayout("m:0-2=0499,i:4-19,i:20-21,i:22-23,p:24-24")); // TBD - RUUVI_LAYOUT
        beaconManager.getBeaconParsers().add(new BeaconParser().setBeaconLayout("m:0-3=4c000215,i:4-19,i:20-21,i:22-23,p:24-24")); // iBeacon - IBEACON_LAYOUT
        beaconManager.getBeaconParsers().add(new BeaconParser().setBeaconLayout(BeaconParser.EDDYSTONE_UID_LAYOUT));
        beaconManager.getBeaconParsers().add(new BeaconParser().setBeaconLayout(BeaconParser.EDDYSTONE_URL_LAYOUT));
        beaconManager.getBeaconParsers().add(new BeaconParser().setBeaconLayout(BeaconParser.EDDYSTONE_TLM_LAYOUT));
        return beaconManager;
    }

    static public String getType(Beacon beacon){
        if(beacon.getServiceUuid() ==  0xFEAA) { // This is an Eddystone format
            if(beacon.getBeaconTypeCode() == 0x00) return "eddystone_uid";
            if(beacon.getBeaconTypeCode() == 0x10 && UrlBeaconUrlCompressor.uncompress(beacon.getId1().toByteArray()).startsWith("https://ruu.vi/#") == true) return "ruuvitag";
            return "eddystone_url";
        }
        if(beacon.getServiceUuid() ==  0xBEAC) return "altBeacon";
        return "iBeacon";
    }

    static public HashMap<String, String> getData(Beacon beacon){
        HashMap<String, String> data = new HashMap<String, String>();

        switch (getType(beacon)) {
            case "iBeacon":
            case  "altBeacon":
                data.put("uuid", beacon.getId1().toString());
                data.put("major", beacon.getId2().toString());
                data.put("minor", beacon.getId3().toString());
                break;
            case "eddystone_uid":
                data.put("namespaceId", beacon.getId1().toString());
                data.put("instanceId", beacon.getId2().toString());
                break;
            case "eddystone_url":
                data.put("url", UrlBeaconUrlCompressor.uncompress(beacon.getId1().toByteArray()));
                break;
            default: // ruuvitag
                data.put("humidity", "");
                data.put("airPressure", "");
                data.put("temperatue", "");
                break;
        }
       return data;
    }
}
