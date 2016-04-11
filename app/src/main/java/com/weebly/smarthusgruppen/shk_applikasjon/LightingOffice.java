package com.weebly.smarthusgruppen.shk_applikasjon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ToggleButton;

public class LightingOffice extends AppCompatActivity {
    boolean lmin = false;
    boolean lmed = false;
    boolean lmax = false;

    ToggleButton lightOffBtn;
    ToggleButton lightMinBtn;
    ToggleButton lightMedBtn;
    ToggleButton lightMaxBtn;
    ImageButton homeBtn;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lighting_office);

        lightOffBtn = (ToggleButton) findViewById(R.id.toggle_0);
        lightOffBtn.setOnClickListener(light_all__off_Listener);

        lightMinBtn = (ToggleButton) findViewById(R.id.toggle_30);
        lightMinBtn.setOnClickListener(light_all_min);

        lightMedBtn = (ToggleButton) findViewById(R.id.toggle_70);
        lightMedBtn.setOnClickListener(light_all_med);

        lightMaxBtn = (ToggleButton) findViewById(R.id.toggle_100);
        lightMaxBtn.setOnClickListener(light_all_Listener);

        //home button
        homeBtn = (ImageButton) findViewById(R.id.home_button);
        homeBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)  {
                goToHome();
            }
        });
    }


    // all lights off
    protected View.OnClickListener light_all__off_Listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (lmin || lmed || lmax) {
                MainActivity.sendText("Command:000002117,1,0");
                lmin = false;
                lmed = false;
                lmax = false;

                lightMinBtn.setChecked(false);
                lightMedBtn.setChecked(false);
                lightMaxBtn.setChecked(false);
            }
        }
    };

    // all lights on minimum
    protected View.OnClickListener light_all_min = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!lmin) {
                MainActivity.sendText("Command:000002117,1,1");     // all lights minimum
                lmin = true;
                lmed = false;
                lmax = false;

                lightMedBtn.setChecked(false);
                lightMaxBtn.setChecked(false);
                lightOffBtn.setChecked(false);
            } else if (lmin) {
                MainActivity.sendText("Command:000002117,1,0");     // all lights off
                allBoolFalse();
                allButtonsOff();

            }
        }
    };

    // all lights on medium
    protected View.OnClickListener light_all_med = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!lmed) {
                MainActivity.sendText("Command:000002117,1,2");     // all lights medium
                lmin = false;
                lmed = true;
                lmax = false;

                lightMinBtn.setChecked(false);
                lightMaxBtn.setChecked(false);
                lightOffBtn.setChecked(false);
            }
            else if (lmed) {
                MainActivity.sendText("Command:000002117,1,0"); // all lights off
                allBoolFalse();
                allButtonsOff();
            }
        }
    };

    // all lights on max
    protected View.OnClickListener light_all_Listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!lmax) {        //this method should only be called if there is a connection.
                MainActivity.sendText("Command:000002117,1,3"); // all lights to max
                lmin = false;
                lmed = false;
                lmax = true;

                lightMinBtn.setChecked(false);
                lightMedBtn.setChecked(false);
                lightOffBtn.setChecked(false);
            }
            else if (lmax) {
                MainActivity.sendText("Command:000002117,1,0");     // all lights off
                allBoolFalse();

                allButtonsOff();
            }
        }
    };

    public void allButtonsOff() {
        lightMinBtn.setChecked(false);
        lightMedBtn.setChecked(false);
        lightMaxBtn.setChecked(false);
        lightOffBtn.setChecked(false);
    }

    public void allBoolFalse()  {
        lmin = false;
        lmed = false;
        lmax = false;
    }

    public void goToHome() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
