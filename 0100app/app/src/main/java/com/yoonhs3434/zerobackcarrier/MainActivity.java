package com.yoonhs3434.zerobackcarrier;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button buttonOnOff, buttonNFC, buttonGps, buttonManual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this, LoadingActivity.class);
        startActivity(intent);

        buttonOnOff = (Button) findViewById(R.id.buttonOnOff);
        buttonNFC = (Button) findViewById(R.id.buttonNFC);
        buttonGps = (Button) findViewById(R.id.buttonGps);
        buttonManual = (Button) findViewById(R.id.buttonManual);
    }

    public void buttonOnOffClicked(View v){
        Intent intent = new Intent(getApplicationContext(), BeaconActivity.class);
        startActivity(intent);
    }

    public void buttonNFCClicked(View v){
        return;
    }

    public void buttonGpsClicked(View v){
        Intent intent = new Intent(getApplicationContext(), GpsLocation.class);
        startActivity(intent);
    }

    public void setButtonManualClicked(View v){
        return;
    }
}
