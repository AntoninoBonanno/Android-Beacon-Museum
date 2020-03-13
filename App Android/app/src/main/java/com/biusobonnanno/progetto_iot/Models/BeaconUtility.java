package com.biusobonnanno.progetto_iot.Models;

import android.widget.TextView;

import com.biusobonnanno.progetto_iot.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.altbeacon.beacon.Beacon;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;


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
