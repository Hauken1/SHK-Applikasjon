package com.weebly.smarthusgruppen.shk_applikasjon.Toppleilighet;

import android.content.Intent;
import android.content.SharedPreferences;
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

    public static final String savedVent = "SavedVentilation";
    //Does matter what saved temp is used, all is set to the same mode
    public static final String savedTemp = "1SavedTemperature";
    public SharedPreferences ventilationSettings;
    public SharedPreferences tempSetting;
    String sMode;
    int iMode;

    int level0;
    int level1;
    int level2;
    int level3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventilation);

        setupGUI();
        level0 = 0;
        level1 = 1;
        level2 = 2;
        level3 = 3;
        displayVentilation();
    }
    public void goToHome() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void displayVentilation() {

        try {
            ventilationSettings = getSharedPreferences(savedVent, 0);
            tempSetting = getSharedPreferences(savedTemp, 0);
            sMode = tempSetting.getString("mode", "2");
            iMode = Integer.parseInt(sMode);
            String modeStatus1, modeStatus2;
            switch (iMode) {
                case 1:
                    modeStatus1 = ventilationSettings.getString("Hvent1status", "0");
                    modeStatus2 = ventilationSettings.getString("Hvent2status", "0");
                    break;
                case 2:
                    modeStatus1 = ventilationSettings.getString("Dvent1status", "0");
                    modeStatus2 = ventilationSettings.getString("Dvent2status", "0");
                    break;
                case 3:
                    modeStatus1 = ventilationSettings.getString("Nvent1status", "0");
                    modeStatus2 = ventilationSettings.getString("Nvent2status", "0");
                    break;
                case 4:
                    modeStatus1 = ventilationSettings.getString("Avent1status", "0");
                    modeStatus2 = ventilationSettings.getString("Avent2status", "0");
                    break;
                default:
                    modeStatus1 = "0";
                    modeStatus2 = "0";
                    break;
            }

            int s1 = Integer.parseInt(modeStatus1);
            int s2 = Integer.parseInt(modeStatus2);

            switch (s1){
                case 0:
                    turnOffBtn.setChecked(true);
                    break;
                case 1:
                    lvlOneBtn.setChecked(true);
                    ch1 = true;
                    break;
                case 2:
                    lvlTwoBtn.setChecked(true);
                    ch2 = true;
                    break;
                case 3:
                    lvlThreeBtn.setChecked(true);
                    ch1 = true;
                    ch2 = true;
                    break;
                default:
                    turnOffBtn.setChecked(true);
                    break;
            }
            switch (s2){
                case 0:
                    turnOffBtn1.setChecked(true);
                    break;
                case 1:
                    lvlOneBtn1.setChecked(true);
                    ch11 = true;
                    break;
                case 2:
                    lvlTwoBtn1.setChecked(true);
                    ch21 = true;
                    break;
                case 3:
                    lvlThreeBtn1.setChecked(true);
                    ch11 = true;
                    ch21 = true;
                    break;
                default:
                    turnOffBtn1.setChecked(true);
                    break;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setVent2BasedOnMode(int i) {
        ventilationSettings = getSharedPreferences(savedVent, 0);
        SharedPreferences.Editor editor = ventilationSettings.edit();
        switch (iMode){
            case 1:
                switch (i){ //Level for holiday
                    case 0:
                        editor.putString("Hvent2status", "0");
                        editor.commit();
                        break;
                    case 1:
                        editor.putString("Hvent2status", "1");
                        editor.commit();
                        break;
                    case 2:
                        editor.putString("Hvent2status", "2");
                        editor.commit();
                        break;
                    case 3:
                        editor.putString("Hvent2status", "3");
                        editor.commit();
                        break;
                    default:
                        break;
                }
                break;
            case 2:
                switch (i){ //Level for day
                    case 0:
                        editor.putString("Dvent2status", "0");
                        editor.commit();
                        break;
                    case 1:
                        editor.putString("Dvent2status", "1");
                        editor.commit();
                        break;
                    case 2:
                        editor.putString("Dvent2status", "2");
                        editor.commit();
                        break;
                    case 3:
                        editor.putString("Dvent2status", "3");
                        editor.commit();
                        break;
                    default:
                        break;
                }
                break;
            case 3:
                switch (i){ //Level for night
                    case 0:
                        editor.putString("Nvent2status", "0");
                        editor.commit();
                        break;
                    case 1:
                        editor.putString("Nvent2status", "1");
                        editor.commit();
                        break;
                    case 2:
                        editor.putString("Nvent2status", "2");
                        editor.commit();
                        break;
                    case 3:
                        editor.putString("Nvent2status", "3");
                        editor.commit();
                        break;
                    default:
                        break;
                }
                break;
            case 4:
                switch (i){ //Level for away
                    case 0:
                        editor.putString("Avent2status", "0");
                        editor.commit();
                        break;
                    case 1:
                        editor.putString("Avent2status", "1");
                        editor.commit();
                        break;
                    case 2:
                        editor.putString("Avent2status", "2");
                        editor.commit();
                        break;
                    case 3:
                        editor.putString("Avent2status", "3");
                        editor.commit();
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }
    }

    public void setVent1BasedOnMode(int i) {
        ventilationSettings = getSharedPreferences(savedVent, 0);
        SharedPreferences.Editor editor = ventilationSettings.edit();
        switch (iMode){
            case 1:
                switch (i){ //Level for holiday
                    case 0:
                        editor.putString("Hvent1status", "0");
                        editor.commit();
                        break;
                    case 1:
                        editor.putString("Hvent1status", "1");
                        editor.commit();
                        break;
                    case 2:
                        editor.putString("Hvent1status", "2");
                        editor.commit();
                        break;
                    case 3:
                        editor.putString("Hvent1status", "3");
                        editor.commit();
                        break;
                    default:
                        break;
                }
                break;
            case 2:
                switch (i){ //Level for day
                    case 0:
                        editor.putString("Dvent1status", "0");
                        editor.commit();
                        break;
                    case 1:
                        editor.putString("Dvent1status", "1");
                        editor.commit();
                        break;
                    case 2:
                        editor.putString("Dvent1status", "2");
                        editor.commit();
                        break;
                    case 3:
                        editor.putString("Dvent1status", "3");
                        editor.commit();
                        break;
                    default:
                        break;
                }
                break;
            case 3:
                switch (i){ //Level for night
                    case 0:
                        editor.putString("Nvent1status", "0");
                        editor.commit();
                        break;
                    case 1:
                        editor.putString("Nvent1status", "1");
                        editor.commit();
                        break;
                    case 2:
                        editor.putString("Nvent1status", "2");
                        editor.commit();
                        break;
                    case 3:
                        editor.putString("Nvent1status", "3");
                        editor.commit();
                        break;
                    default:
                        break;
                }
                break;
            case 4:
                switch (i){ //Level for away
                    case 0:
                        editor.putString("Avent1status", "0");
                        editor.commit();
                        break;
                    case 1:
                        editor.putString("Avent1status", "1");
                        editor.commit();
                        break;
                    case 2:
                        editor.putString("Avent1status", "2");
                        editor.commit();
                        break;
                    case 3:
                        editor.putString("Avent1status", "3");
                        editor.commit();
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }
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
                setVent1BasedOnMode(level1);
            }
            else if(ch1) {
                MainActivity.sendText("Command:000049114,1,0,0,1");     // turns off level 1
                ch1 = false;
                allLvlOff();
                setVent1BasedOnMode(level0);
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
                setVent1BasedOnMode(level2);
            }
            else if(ch2) {
                MainActivity.sendText("Command:000049114,2,0,0,1");     // turns off level 2
                ch2 = false;
                allLvlOff();
                setVent1BasedOnMode(level0);
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
                setVent1BasedOnMode(level3);
            }
            else if(ch2 && !ch1) {
                MainActivity.sendText("Command:000049114,1,100,0,1");     // turns on level 1
                ch1 = true;
                ch3 = true;

                lvlOneTwoOff();
                setVent1BasedOnMode(level3);
            }
            else if(!ch2 && ch1)  {
                MainActivity.sendText("Command:000049114,2,100,0,1");      // turns on level 2
                ch2 = true;
                ch3 = true;

                lvlOneTwoOff();

               setVent1BasedOnMode(level3);
            }
            else if (ch2 && ch1){
                MainActivity.sendText("Command:000002114,1,0");             // turns off 1 and 2
                allBoolFalse();
                setVent1BasedOnMode(level0);
            }
        }
    };

    // turn off
    protected View.OnClickListener turn_off = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            connected = true;
            ventilationSettings = getSharedPreferences(savedVent, 0);
            SharedPreferences.Editor editor = ventilationSettings.edit();
            if ((ch1) || (ch2) || (ch3)) {          // ch1 or ch2 or ch3 true
                MainActivity.sendText("Command:000002114,1,0");            // turns off 1 and 2
                allBoolFalse();
                lvlOneBtn.setChecked(false);
                lvlTwoBtn.setChecked(false);
                lvlThreeBtn.setChecked(false);
                setVent1BasedOnMode(level0);

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


    // for all other rooms

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
                setVent2BasedOnMode(level1);
            }
            else if(ch11) {
                MainActivity.sendText("Command:000049114,1,0,0,1");     // turns off level 1
                ch11 = false;
                allLvlOff1();
                setVent2BasedOnMode(level0);
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
                setVent2BasedOnMode(level2);
            }
            else if(ch21) {
                MainActivity.sendText("Command:000049114,2,0,0,1");     // turns off level 2
                ch21 = false;
                allLvlOff1();
                setVent2BasedOnMode(level0);
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
                setVent2BasedOnMode(level3);
            }
            else if(ch21 && !ch11) {
                MainActivity.sendText("Command:000049114,1,100,0,1");     // turns on level 1
                ch11 = true;
                ch31 = true;

                lvlOneTwoOff1();
                setVent2BasedOnMode(level3);
            }
            else if(!ch21 && ch11)  {
                MainActivity.sendText("Command:000049114,2,100,0,1");      // turns on level 2
                ch21 = true;
                ch31 = true;

                lvlOneTwoOff1();
                setVent2BasedOnMode(level3);
            }
            else if (ch21 && ch11){
                MainActivity.sendText("Command:000002114,1,0");             // turns off 1 and 2
                allBoolFalse1();
                setVent2BasedOnMode(level0);
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
                setVent2BasedOnMode(level0);
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
