package com.weebly.smarthusgruppen.shk_applikasjon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;



public class Climate extends AppCompatActivity {
    Button tempBtn;
    Button ventBtn;



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
    }

    public void goToTemperatureView() {
        Intent intent = new Intent(this, RoomListTemp.class);
        startActivity(intent);
    }

    public void goToVentilationView() {
        Intent intent = new Intent(this, Ventilation.class);
        startActivity(intent);
    }
}
