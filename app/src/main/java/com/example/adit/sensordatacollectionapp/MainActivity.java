package com.example.adit.sensordatacollectionapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.SensorEventListener;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.hardware.SensorEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SensorManager mSensorManager;
    private Sensor xAccSensor;
    private Sensor xGravSensor;
    private Sensor xGyroSensor;
    private Sensor lightSensor;
    private Sensor pressureSensor;
    private TextView mSensorAvailables;
    private Button button2;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSensorAvailables = (TextView)findViewById(R.id.mSensorAvailables);

        button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(v.getContext(), AccelerometerTest.class);
                startActivityForResult(intent, 0);
            }
        });


        mSensorManager= (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        xAccSensor = mSensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0);
        xGravSensor = mSensorManager.getSensorList(Sensor.TYPE_GRAVITY).get(0);
        xGyroSensor = mSensorManager.getSensorList(Sensor.TYPE_GYROSCOPE).get(0);
        lightSensor = mSensorManager.getSensorList(Sensor.TYPE_LIGHT).get(0);
        pressureSensor = mSensorManager.getSensorList(Sensor.TYPE_PRESSURE).get(0);


        List<Sensor> msensorList = mSensorManager.getSensorList(Sensor.TYPE_ALL);


        String sSensList = new String("");
        Sensor tmp;
        int x,i;
        for (i=0;i<msensorList.size();i++){
            tmp = msensorList.get(i);
            sSensList = " "+sSensList+tmp.getName() + '\n'; // Add the sensor name to the string of sensors available
        }

        sSensList = sSensList + xAccSensor + '\n' + '\n';
        sSensList = sSensList + xGravSensor + '\n' + '\n';
        sSensList = sSensList + xGyroSensor + '\n' + '\n';
        sSensList = sSensList + lightSensor + '\n' + '\n';
        sSensList = sSensList + pressureSensor + '\n' + '\n';

        mSensorAvailables.setText(sSensList);

        }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
