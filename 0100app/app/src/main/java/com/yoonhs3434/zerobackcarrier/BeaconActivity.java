package com.yoonhs3434.zerobackcarrier;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.RangeNotifier;
import org.altbeacon.beacon.Region;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BeaconActivity extends AppCompatActivity implements BeaconConsumer {

    private BeaconManager beaconManager;
    private List<Beacon> beaconList = new ArrayList<>();
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beacon);

        textView = (TextView) findViewById(R.id.textView);
        beaconManager = BeaconManager.getInstanceForApplication(this);
        beaconManager.getBeaconParsers().add(new BeaconParser().setBeaconLayout("m:2-3=0215,i:4-19,i:20-21,i:22-23,p:24-24,d:25-25"));

        beaconManager.bind(this);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        beaconManager.unbind(this);
    }

    @Override
    public void onBeaconServiceConnect() {
        beaconManager.setRangeNotifier(new RangeNotifier(){
            @Override
            public void didRangeBeaconsInRegion(Collection<Beacon> beacons, Region region){
                if(beacons.size()>0){
                    beaconList.clear();
                    for(Beacon beacon : beacons){
                        Log.d("tag", ""+beacon.getId2());
                        beaconList.add(beacon);
                    }
                }
            }
        });

        try{
            beaconManager.startRangingBeaconsInRegion(new Region("myRangingUniqueId", null, null, null));
        }catch(RemoteException e){

        }
    }

    public void OnButtonClicked(View v){
        handler.sendEmptyMessage(0);
    }

    Handler handler = new Handler(){
        public void handleMessage(Message msg){
            textView.setText("");

            for(Beacon beacon : beaconList){
                textView.append("ID : " + beacon.getId2() + "/" + "Distance : " + Double.parseDouble(String.format("%.3f", beacon.getDistance())) + "m\n");
            }

            handler.sendEmptyMessageDelayed(0, 1000);
        }
    };
}
