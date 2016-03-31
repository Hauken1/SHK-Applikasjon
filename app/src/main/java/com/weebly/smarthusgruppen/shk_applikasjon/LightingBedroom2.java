package com.weebly.smarthusgruppen.shk_applikasjon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class LightingBedroom2 extends AppCompatActivity {
    boolean connected;
    boolean lmin = false;
    boolean lmed = false;
    boolean lmax = false;

    Button lightOffBtn;
    Button lightMinBtn;
    Button lightMedBtn;
    Button lightMaxBtn;
    ImageButton homeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lighting_bedroom2);

        lightOffBtn = (Button) findViewById(R.id.light_off_toggle_button);
        lightOffBtn.setOnClickListener(light_all__off_Listener);

        lightMinBtn = (Button) findViewById(R.id.toggle_30);
        lightMinBtn.setOnClickListener(light_all_min);

        lightMedBtn = (Button) findViewById(R.id.toggle_70);
        lightMedBtn.setOnClickListener(light_all_med);

        lightMaxBtn = (Button) findViewById(R.id.toggle_100);
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
            connected = true;
            if ((lmin == true) || (lmed == true) || (lmax == true)) {
                MainActivity.sendText("Command:000002117,1,0");
                lmin = false;
                lmed = false;
                lmax = false;
            }
        }
    };

    // all lights on minimum
    protected View.OnClickListener light_all_min = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            connected = true;
            if (lmin == false) {
                MainActivity.sendText("Command:000002117,1,1");     // all lights minimum
                lmin = true;
                lmed = false;
                lmax = false;
            } else if (lmin == true) {
                MainActivity.sendText("Command:000002117,1,0");     // all lights off
                lmin = false;
                lmed = false;
                lmax = false;
            }
        }
    };

    // all lights on medium
    protected View.OnClickListener light_all_med = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            connected = true;
            if (lmed == false) {
                MainActivity.sendText("Command:000002117,1,2");     // all lights medium
                lmin = false;
                lmed = true;
                lmax = false;
            }
            else if (lmed == true) {
                MainActivity.sendText("Command:000002117,1,0"); // all lights off
                lmin = false;
                lmed = false;
                lmax = false;
            }
        }
    };

    // all lights on max
    protected View.OnClickListener light_all_Listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            connected = true;
            if (lmax == false) {        //this method should only be called if there is a connection.
                MainActivity.sendText("Command:000002117,1,3"); // all lights to max
                lmin = false;
                lmed = false;
                lmax = true;
            }
            else if (lmax == true) {
                MainActivity.sendText("Command:000002117,1,0");     // all lights off
                lmin = false;
                lmed = false;
                lmax = false;
            }
        }
    };

    public void goToHome() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
