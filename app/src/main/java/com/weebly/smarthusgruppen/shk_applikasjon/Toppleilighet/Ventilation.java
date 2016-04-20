package com.weebly.smarthusgruppen.shk_applikasjon.Toppleilighet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ToggleButton;

import com.weebly.smarthusgruppen.shk_applikasjon.R;

public class Ventilation extends AppCompatActivity {
    ImageButton homeBtn;
    boolean connected;
    ToggleButton lvlOneBtn;
    ToggleButton lvlTwoBtn;
    ToggleButton lvlThreeBtn;
    ToggleButton turnOffBtn;

    ToggleButton lvlOneBtn1;
    ToggleButton lvlTwoBtn1;
    ToggleButton lvlThreeBtn1;
    ToggleButton turnOffBtn1;

    boolean ch1 = false;        // channel 1 = level 1
    boolean ch2 = false;        // channel 2 = level 2
    boolean ch3 = false;        // channel 3 = level 3

    boolean ch11 = false;        // channel 1 = level 1
    boolean ch21 = false;        // channel 2 = level 2
    boolean ch31 = false;        // channel 3 = level 3

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventilation);

        setupGUI();
    }
    public void goToHome() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    // for bedrooms
    // toggle level one
    protected View.OnClickListener toggle_level1 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            connected = true;
            if (!ch1) {
                MainActivity.sendText("Command:000049114,2,0,0,1");     // turns off level 2
                MainActivity.sendText("Command:000049114,1,100,0,1");   // turns on level 1
                ch1 = true;
                ch2 = false;

                lvlTwoBtn.setChecked(false);
                lvlThreeBtn.setChecked(false);
                turnOffBtn.setChecked(false);
            }
            else if(ch1) {
                MainActivity.sendText("Command:000049114,1,0,0,1");     // turns off level 1
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
                MainActivity.sendText("Command:000049114,1,0,0,1");      // turns off level 1
                MainActivity.sendText("Command:000049114,2,100,0,1");     // turns on level 2
                ch2 = true;
                ch1 = false;

                lvlOneBtn.setChecked(false);
                lvlThreeBtn.setChecked(false);
                turnOffBtn.setChecked(false);
            }
            else if(ch2) {
                MainActivity.sendText("Command:000049114,2,0,0,1");     // turns off level 2
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
                MainActivity.sendText("Command:000002114,1,1");      // turns on level 1 and 2
                ch1 = true;
                ch2 = true;
                ch3 = true;

                lvlOneTwoOff();
            }
            else if(ch2 && !ch1) {
                MainActivity.sendText("Command:000049114,1,100,0,1");     // turns on level 1
                ch1 = true;
                ch3 = true;

              lvlOneTwoOff();
            }
            else if(!ch2 && ch1)  {
                MainActivity.sendText("Command:000049114,2,100,0,1");      // turns on level 2
                ch2 = true;
                ch3 = true;

                lvlOneTwoOff();
            }
            else if (ch2 && ch1){
                MainActivity.sendText("Command:000002114,1,0");             // turns off 1 and 2
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
                MainActivity.sendText("Command:000002114,1,0");            // turns off 1 and 2
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


    // for all rooms

    // toggle level one
    protected View.OnClickListener toggle_level11 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            connected = true;
            if (!ch11) {
                MainActivity.sendText("Command:000049114,2,0,0,1");     // turns off level 2
                MainActivity.sendText("Command:000049114,1,100,0,1");   // turns on level 1
                ch11 = true;
                ch21 = false;

                lvlTwoBtn1.setChecked(false);
                lvlThreeBtn1.setChecked(false);
                turnOffBtn1.setChecked(false);
            }
            else if(ch11) {
                MainActivity.sendText("Command:000049114,1,0,0,1");     // turns off level 1
                ch11 = false;
                allLvlOff1();
            }
        }
    };

    // toggle level 2
    protected View.OnClickListener toggle_level21 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            connected = true;
            if (!ch21) {
                MainActivity.sendText("Command:000049114,1,0,0,1");      // turns off level 1
                MainActivity.sendText("Command:000049114,2,100,0,1");     // turns on level 2
                ch21 = true;
                ch11 = false;

                lvlOneBtn1.setChecked(false);
                lvlThreeBtn1.setChecked(false);
                turnOffBtn1.setChecked(false);
            }
            else if(ch21) {
                MainActivity.sendText("Command:000049114,2,0,0,1");     // turns off level 2
                ch21 = false;
                allLvlOff1();
            }
        }
    };

    // toggle level 3
    protected View.OnClickListener toggle_level31 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            connected = true;
            if (!ch11 && !ch21) {
                MainActivity.sendText("Command:000002114,1,1");      // turns on level 1 and 2
                ch11 = true;
                ch21 = true;
                ch31 = true;

                lvlOneTwoOff1();
            }
            else if(ch21 && !ch11) {
                MainActivity.sendText("Command:000049114,1,100,0,1");     // turns on level 1
                ch11 = true;
                ch31 = true;

                lvlOneTwoOff1();
            }
            else if(!ch21 && ch11)  {
                MainActivity.sendText("Command:000049114,2,100,0,1");      // turns on level 2
                ch21 = true;
                ch31 = true;

                lvlOneTwoOff1();
            }
            else if (ch21 && ch11){
                MainActivity.sendText("Command:000002114,1,0");             // turns off 1 and 2
                allBoolFalse1();
            }
        }
    };

    // turn off
    protected View.OnClickListener turn_off1 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            connected = true;
            if ((ch11) || (ch21) || (ch31)) {          // ch1 or ch2 or ch3 true
                MainActivity.sendText("Command:000002114,1,0");            // turns off 1 and 2
                allBoolFalse1();
                lvlOneBtn1.setChecked(false);
                lvlTwoBtn1.setChecked(false);
                lvlThreeBtn1.setChecked(false);

            }
        }
    };

    public void lvlOneTwoOff1() {
        lvlOneBtn1.setChecked(false);
        lvlTwoBtn1.setChecked(false);
        turnOffBtn1.setChecked(false);
    }
    public void allBoolFalse1() {
        ch11 = false;
        ch21 = false;
        ch31 = false;
    }

    public void allLvlOff1()  {
        lvlOneBtn1.setChecked(false);
        lvlTwoBtn1.setChecked(false);
        lvlThreeBtn1.setChecked(false);
        turnOffBtn1.setChecked(false);
    }


    public void setupGUI() {
        lvlOneBtn = (ToggleButton) findViewById(R.id.lvl1_button);
        lvlOneBtn.setOnClickListener(toggle_level1);

        lvlTwoBtn = (ToggleButton) findViewById(R.id.lvl2_button);
        lvlTwoBtn.setOnClickListener(toggle_level2);

        lvlThreeBtn = (ToggleButton) findViewById(R.id.lvl3_button);
        lvlThreeBtn.setOnClickListener(toggle_level3);

        turnOffBtn = (ToggleButton) findViewById(R.id.lvl4_button);
        turnOffBtn.setOnClickListener(turn_off);


        lvlOneBtn1 = (ToggleButton) findViewById(R.id.lvl11_button);
        lvlOneBtn1.setOnClickListener(toggle_level11);

        lvlTwoBtn1 = (ToggleButton) findViewById(R.id.lvl21_button);
        lvlTwoBtn1.setOnClickListener(toggle_level21);

        lvlThreeBtn1 = (ToggleButton) findViewById(R.id.lvl31_button);
        lvlThreeBtn1.setOnClickListener(toggle_level31);

        turnOffBtn1 = (ToggleButton) findViewById(R.id.lvl41_button);
        turnOffBtn1.setOnClickListener(turn_off1);

        //home button
        homeBtn = (ImageButton) findViewById(R.id.home_button);
        homeBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)  {
                goToHome();
            }
        });

    }

}
