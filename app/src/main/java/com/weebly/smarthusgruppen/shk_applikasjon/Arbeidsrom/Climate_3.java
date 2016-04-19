package com.weebly.smarthusgruppen.shk_applikasjon.Arbeidsrom;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.weebly.smarthusgruppen.shk_applikasjon.R;


public class Climate_3 extends AppCompatActivity {
    Button tempBtn;
    Button ventBtn;
    ImageButton homeBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_climate);
        // temp button
        tempBtn = (Button) findViewById(R.id.heating_view_button);
        tempBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goToTemperatureView();
            }
        });

        // vent button
        ventBtn = (Button) findViewById(R.id.ventilation_view_button);
        ventBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goToVentilationView();
            }
        });

        //home button
        homeBtn = (ImageButton) findViewById(R.id.home_button);
        homeBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)  {
                goToHome();
            }
        });


    }

    public void goToTemperatureView() {
        Intent intent = new Intent(this, Temperature_3.class);
        startActivity(intent);
    }

    public void goToVentilationView() {
        Intent intent = new Intent(this, Ventilation_3.class);
        startActivity(intent);
    }

    public void goToHome() {
        Intent intent = new Intent(this, MainActivity_3.class);
        startActivity(intent);
    }



}