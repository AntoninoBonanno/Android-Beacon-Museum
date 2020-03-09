package com.biusobonnanno.progetto_iot.Models;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.altbeacon.beacon.Beacon;


public class BeaconUtility{
    private static AsyncHttpClient client = new AsyncHttpClient();

    static public String getType(Beacon beacon){
        if(beacon.getServiceUuid() ==  0xFEAA) { // This is an Eddystone format
            if(beacon.getBeaconTypeCode() == 0x00) return "eddystone_uid";
            return "eddystone_url"; //getBeaconTypeCode is 0x10 (si può controllare l'url e verificare se è un "ruuvitag"
        }
        if(beacon.getServiceUuid() ==  0xBEAC) return "altBeacon";
        return "iBeacon";
    }

    static public void getInfos(String beaconUuid, AsyncHttpResponseHandler responseHandler){
      //  client.post() 1017432952412-f42upqmm0vc6imkhc6m32kr6d35kelkt.apps.googleusercontent.com
    }
}
