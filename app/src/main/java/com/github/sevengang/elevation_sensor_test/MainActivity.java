package com.github.sevengang.elevation_sensor_test;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private TextView textView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        LinearLayout lView = new LinearLayout(this);
        textView = new TextView(this);

        // start listening is the desired sensor is supported
        SensorManager sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        List<Sensor> sensors = sm.getSensorList(Sensor.TYPE_PRESSURE);
        if (sensors.size() > 0) {
            Sensor sensor = sensors.get(0);
            sm.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
        else {
            textView.setText("device does not support desired sensor functionality!");
        }

        lView.addView(textView);
        setContentView(lView);
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // TODO: figure out what this is for
    }

    public void onSensorChanged(SensorEvent event) {
        float pressure = event.values[0];
        float altitude = SensorManager.getAltitude(SensorManager.PRESSURE_STANDARD_ATMOSPHERE, pressure);
        textView.setText("Current altitude: " + altitude);
    }

}
