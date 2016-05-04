package com.weebly.smarthusgruppen.shk_applikasjon;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.weebly.smarthusgruppen.shk_applikasjon.Leilighet.Measurement_2;
import com.weebly.smarthusgruppen.shk_applikasjon.Toppleilighet.Measurement;
import com.weebly.smarthusgruppen.shk_applikasjon.Arbeidsrom.Measurement_3;

/**
 * page for the admin user to view water and electricity consumption pages for each apartment
 */

public class Admin extends AppCompatActivity {

    Button meas1Btn;
    Button meas2Btn;
    Button meas3Btn;

    /**
     * runs on startup and sets up the GUI
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        setupGUI();


    }

    /**
     * sets up all onClickListener buttons
     */
    public void setupGUI() {
        meas1Btn = (Button) findViewById(R.id.meas1Button);
        meas1Btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goToMeasurement1();
            }
        });

        meas2Btn = (Button) findViewById(R.id.meas2Button);
        meas2Btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goToMeasurement2();
            }
        });

        meas3Btn = (Button) findViewById(R.id.meas3Button);
        meas3Btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goToMeasurement3();
            }
        });
    }


    /**
     * sends the admin to the top floor apartment measurement view
     */
    public void goToMeasurement1() {
        Intent intent = new Intent(this, Measurement.class);
        startActivity(intent);
    }

    /**
     * sends the admin to the first floor apartment measurement view
     */
    public void goToMeasurement2() {
        Intent intent = new Intent(this, Measurement_2.class);
        startActivity(intent);
    }

    /**
     * sends the admin to the flat measurement view.
     */
    public void goToMeasurement3() {
        Intent intent = new Intent(this, Measurement_3.class);
        startActivity(intent);
    }
}
