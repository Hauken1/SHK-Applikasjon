package com.weebly.smarthusgruppen.shk_applikasjon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;



public class Climate extends AppCompatActivity {
    boolean connected;
    Button tempBtn;
    Button ventBtn;
    Button lvlOneBtn;
    Button lvlTwoBtn;
    Button lvlThreeBtn;
    Button turnOffBtn;

    boolean ch1 = false;        // channel 1 = level 1
    boolean ch2 = false;        // channel 2 = level 2
    boolean ch3 = false;        // channel 3 = level 3



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
// PLACEHOLDER BUTTON NAMES MAKE BUTTONS.
        lvlOneBtn = (Button) findViewById(R.id.light_all_toggle_button);
        lvlOneBtn.setOnClickListener(toggle_level1);

        lvlTwoBtn = (Button) findViewById(R.id.light_off_toggle_button);
        lvlTwoBtn.setOnClickListener(toggle_level2);

        lvlThreeBtn = (Button) findViewById(R.id.light_off_toggle_button);
        lvlThreeBtn.setOnClickListener(toggle_level3);

        turnOffBtn = (Button) findViewById(R.id.light_off_toggle_button);
        turnOffBtn.setOnClickListener(turn_off);

    }

    public void goToTemperatureView() {
        Intent intent = new Intent(this, RoomListTemp.class);
        startActivity(intent);
    }

    public void goToVentilationView() {
        Intent intent = new Intent(this, Ventilation.class);
        startActivity(intent);
    }


    // toggle level one
    protected View.OnClickListener toggle_level1 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            connected = true;
            if (ch1 == false) {
                MainActivity.sendText("Command:000049114,2,0,0,1");     // turns off level 2
                MainActivity.sendText("Command:000049114,1,100,0,1");   // turns on level 1
                ch1 = true;
                ch2 = false;
            }
            else if(ch1 == true) {
                MainActivity.sendText("Command:000049114,1,0,0,1");     // turns off level 1
                ch1 = false;
            }
        }
    };

    // toggle level 2
    protected View.OnClickListener toggle_level2 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            connected = true;
            if (ch2 == false) {
                MainActivity.sendText("Command:000049114,1,0,0,1");      // turns off level 1
                MainActivity.sendText("Command:000049114,2,100,0,1");     // turns on level 2
                ch2 = true;
                ch1 = false;
            }
            else if(ch2 == true) {
                MainActivity.sendText("Command:000049114,2,0,0,1");     // turns off level 2
                ch2 = false;
            }
        }
    };

    // toggle level 3
    protected View.OnClickListener toggle_level3 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            connected = true;
            if ((ch1 == false) && (ch2 = false)) {
                MainActivity.sendText("Command:000002114,1,1");      // turns on level 1 and 2
                ch1 = true;
                ch2 = true;
                ch3 = true;
            }
            else if((ch2 == true) && (ch1 == false)) {
                MainActivity.sendText("Command:000049114,1,100,0,1");     // turns on level 1
                ch1 = true;
                ch3 = true;
            }
            else if((ch2 == false) && (ch1 == true))  {
                MainActivity.sendText("Command:000049114,2,100,0,1");      // turns on level 2
                ch2 = true;
                ch3 = true;
            }
            else if ((ch2 == true) && (ch1 == true)) {
                MainActivity.sendText("Command:000002114,1,0");             // turns off 1 and 2
                ch1 = false;
                ch2 = false;
                ch3 = false;
            }
        }
    };

    // turn off
    protected View.OnClickListener turn_off = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            connected = true;
            if ((ch1 == true) || (ch2 == true) || (ch3 == true)) {
                MainActivity.sendText("Command:000002114,1,0");            // turns off 1 and 2
                ch1 = false;
                ch2 = false;
                ch3 = false;
            }
        }
    };
}
