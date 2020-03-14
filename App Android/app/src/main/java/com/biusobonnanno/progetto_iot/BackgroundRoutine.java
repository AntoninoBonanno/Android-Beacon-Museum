package com.biusobonnanno.progetto_iot;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

import com.biusobonnanno.progetto_iot.Models.BeaconUtility;

import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.Region;
import org.altbeacon.beacon.powersave.BackgroundPowerSaver;
import org.altbeacon.beacon.startup.BootstrapNotifier;
import org.altbeacon.beacon.startup.RegionBootstrap;

public class BackgroundRoutine extends Application implements BootstrapNotifier {
    private static final String TAG = "BackgroundRoutine";
    private RegionBootstrap regionBootstrap;
    private BackgroundPowerSaver backgroundPowerSaver;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "App started up");
        BeaconManager beaconManager = BeaconUtility.getBeaconManager(this);

        regionBootstrap = new RegionBootstrap(this, new Region(" com.biusobonnanno.progetto_iot", null, null, null));
       // backgroundPowerSaver = new BackgroundPowerSaver(this);

        beaconManager.setBackgroundBetweenScanPeriod(0);
        beaconManager.setBackgroundScanPeriod(1100);
        beaconManager.setBackgroundMode(true);
    }

    @Override
    public void didEnterRegion(Region region) {
        Log.d(TAG, "Got a didEnterRegion call");
        //regionBootstrap.disable(); //da rimuovere
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        this.startActivity(intent);
    }

    @Override
    public void didExitRegion(Region region) {
        Log.d(TAG, "Got a didExitRegion call");
    }

    @Override
    public void didDetermineStateForRegion(int i, Region region) {

    }
}
