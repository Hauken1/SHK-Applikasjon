package com.weebly.smarthusgruppen.shk_applikasjon.Arbeidsrom;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ToggleButton;

import com.weebly.smarthusgruppen.shk_applikasjon.R;

public class Ventilation_3 extends AppCompatActivity {
    ImageButton homeBtn;
    boolean connected;
    ToggleButton lvlOneBtn;
    ToggleButton lvlTwoBtn;
    ToggleButton lvlThreeBtn;
    ToggleButton turnOffBtn;

    boolean ch1 = false;        // channel 1 = level 1
    boolean ch2 = false;        // channel 2 = level 2
    boolean ch3 = false;        // channel 3 = level 3
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventilation_3);



        lvlOneBtn = (ToggleButton) findViewById(R.id.lvl13_button);
        lvlOneBtn.setOnClickListener(toggle_level1);

        lvlTwoBtn = (ToggleButton) findViewById(R.id.lvl23_button);
        lvlTwoBtn.setOnClickListener(toggle_level2);

        lvlThreeBtn = (ToggleButton) findViewById(R.id.lvl33_button);
        lvlThreeBtn.setOnClickListener(toggle_level3);

        turnOffBtn = (ToggleButton) findViewById(R.id.lvl43_button);
        turnOffBtn.setOnClickListener(turn_off);

        //home button
        homeBtn = (ImageButton) findViewById(R.id.home_button);
        homeBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)  {
                goToHome();
            }
        });

        
    }

    // toggle level one
    protected View.OnClickListener toggle_level1 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            connected = true;
            if (!ch1) {
                MainActivity_3.sendText("Command:000049114,2,0,0,1");     // turns off level 2
                MainActivity_3.sendText("Command:000049114,1,100,0,1");   // turns on level 1
                ch1 = true;
                ch2 = false;

                lvlTwoBtn.setChecked(false);
                lvlThreeBtn.setChecked(false);
                turnOffBtn.setChecked(false);
            }
            else if(ch1) {
                MainActivity_3.sendText("Command:000049114,1,0,0,1");     // turns off level 1
                ch1 = false;
                allLvlOff();
            }
        }
    };

    // toggle level 2
    protected View.OnClickListener toggle_level2 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            connected = true;
            if (!ch2) {
                MainActivity_3.sendText("Command:000049114,1,0,0,1");      // turns off level 1
                MainActivity_3.sendText("Command:000049114,2,100,0,1");     // turns on level 2
                ch2 = true;
                ch1 = false;

                lvlOneBtn.setChecked(false);
                lvlThreeBtn.setChecked(false);
                turnOffBtn.setChecked(false);
            }
            else if(ch2) {
                MainActivity_3.sendText("Command:000049114,2,0,0,1");     // turns off level 2
                ch2 = false;
                allLvlOff();
            }
        }
    };

    // toggle level 3
    protected View.OnClickListener toggle_level3 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            connected = true;
            if (!ch1 && !ch2) {
                MainActivity_3.sendText("Command:000002114,1,1");      // turns on level 1 and 2
                ch1 = true;
                ch2 = true;
                ch3 = true;

                lvlOneTwoOff();
            }
            else if(ch2 && !ch1) {
                MainActivity_3.sendText("Command:000049114,1,100,0,1");     // turns on level 1
                ch1 = true;
                ch3 = true;

              lvlOneTwoOff();
            }
            else if(!ch2 && ch1)  {
                MainActivity_3.sendText("Command:000049114,2,100,0,1");      // turns on level 2
                ch2 = true;
                ch3 = true;

                lvlOneTwoOff();
            }
            else if (ch2 && ch1){
                MainActivity_3.sendText("Command:000002114,1,0");             // turns off 1 and 2
                allBoolFalse();
            }
        }
    };

    // turn off
    protected View.OnClickListener turn_off = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            connected = true;
            if ((ch1) || (ch2) || (ch3)) {          // ch1 or ch2 or ch3 true
                MainActivity_3.sendText("Command:000002114,1,0");            // turns off 1 and 2
                allBoolFalse();
                lvlOneBtn.setChecked(false);
                lvlTwoBtn.setChecked(false);
                lvlThreeBtn.setChecked(false);

            }
        }
    };

    public void lvlOneTwoOff() {
        lvlOneBtn.setChecked(false);
        lvlTwoBtn.setChecked(false);
        turnOffBtn.setChecked(false);
    }
    public void allBoolFalse() {
        ch1 = false;
        ch2 = false;
        ch3 = false;
    }

    public void allLvlOff()  {
        lvlOneBtn.setChecked(false);
        lvlTwoBtn.setChecked(false);
        lvlThreeBtn.setChecked(false);
        turnOffBtn.setChecked(false);
    }
    public void goToHome() {
        Intent intent = new Intent(this, MainActivity_3.class);
        startActivity(intent);
    }
}
