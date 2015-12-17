package com.example.adit.sensordatacollectionapp;

import android.content.Context;
import android.hardware.SensorEventListener;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.hardware.SensorEventListener;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.widget.Button;
import android.widget.TextView;

public class AccelerometerTest extends AppCompatActivity  implements SensorEventListener {

    private Sensor Accelerometer;
    private SensorManager mSensorManager;
    private TextView accelStats;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometer_test);

        mSensorManager= (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Accelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(this, Accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        accelStats = (TextView) findViewById(R.id.accelStats);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float x = 0, y = 0, z = 0;
        String temp = "";
        Sensor mySensor = event.sensor;
        if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER){
            x = event.values[0];
            y = event.values[1];
            z = event.values[2];
        }
        temp = "X axis: " + x + '\n'
                + "Y axis: " + y + '\n'
                + "Z axis: " + z + '\n';
        accelStats.setText(temp);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, Accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }
}
