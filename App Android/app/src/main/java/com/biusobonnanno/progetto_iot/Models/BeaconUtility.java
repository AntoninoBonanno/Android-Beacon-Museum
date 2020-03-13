package com.biusobonnanno.progetto_iot.Models;

import org.altbeacon.beacon.Beacon;


public class BeaconUtility{

    static public String getType(Beacon beacon){
        if(beacon.getServiceUuid() ==  0xFEAA) { // This is an Eddystone format
            if(beacon.getBeaconTypeCode() == 0x00) return "eddystone_uid";
            return "eddystone_url"; //getBeaconTypeCode is 0x10 (si può controllare l'url e verificare se è un "ruuvitag"
        }
        if(beacon.getServiceUuid() ==  0xBEAC) return "altBeacon";
        return "iBeacon";
    }
}
