package com.weebly.smarthusgruppen.shk_applikasjon.Leilighet;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.weebly.smarthusgruppen.shk_applikasjon.R;
/**
 * Allows the user to change ventilation settings. Three different stages of ventilation.
 * Allows settings to be saved between different modes of the house
 */
public class Ventilation_2 extends AppCompatActivity {
    ImageButton homeBtn;
    boolean connected;
    ToggleButton lvlOneBtn;
    ToggleButton lvlTwoBtn;
    ToggleButton lvlThreeBtn;
    ToggleButton turnOffBtn;

    boolean ch1 = false;        // channel 1 = level 1
    boolean ch2 = false;        // channel 2 = level 2
    boolean ch3 = false;        // channel 3 = level 3

    public static final String savedVent = "SavedVentilation_2";
    //Does matter what saved temp is used, all is set to the same mode
    public static final String savedTemp = "1SavedTemperature_2";
    public static final String savedColor = "SavedBackgroundColor_2";

    SharedPreferences sharedpreferences;
    public SharedPreferences ventilationSettings;
    public SharedPreferences tempSetting;
    String sMode;
    int iMode;

    int level0;
    int level1;
    int level2;
    int level3;

    public static final String DAY = "Dag";
    public static final String NIGHT = "Natt";
    public static final String AWAY = "Borte";
    public static final String HOLIDAY = "Ferie";

    public static final int iDAY = 2;
    public static final int iNIGHT = 3;
    public static final int iAWAY = 4;
    public static final int iHOLIDAY = 1;

    TextView mode_View;

    @Override
    /**
     * runs on startup and sets up gui  and displays ventilation status information
     * @param savedInstanceState
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventilation_2);
        setupGUI();
        displayVentilation();
    }
    /**
     * Setting up GUI. sets up all onClickListener buttons. Sets background depending on user
     * settings. SharedPreferences for each mode.
     */
    public void setupGUI() {
        lvlOneBtn = (ToggleButton) findViewById(R.id.lvl12_button);
        lvlOneBtn.setOnClickListener(toggle_level1);

        lvlTwoBtn = (ToggleButton) findViewById(R.id.lvl22_button);
        lvlTwoBtn.setOnClickListener(toggle_level2);

        lvlThreeBtn = (ToggleButton) findViewById(R.id.lvl32_button);
        lvlThreeBtn.setOnClickListener(toggle_level3);

        turnOffBtn = (ToggleButton) findViewById(R.id.lvl42_button);
        turnOffBtn.setOnClickListener(turn_off);

        //home button
        homeBtn = (ImageButton) findViewById(R.id.home_button);
        homeBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)  {
                goToHome();
            }
        });

        sharedpreferences = getSharedPreferences(savedColor, Context.MODE_PRIVATE);

        int value1 = sharedpreferences.getInt("value1", 0);
        int value2 = sharedpreferences.getInt("value2", 0);
        int value3 = sharedpreferences.getInt("value3", 0);
        int value4 = sharedpreferences.getInt("set", 0);
        if(value4 != 0){
            View v = findViewById(R.id.vent_id_2);
            v.setBackgroundColor(Color.rgb(value1, value3, value2));
            setContentView(v);
        }
        level0 = 0;
        level1 = 1;
        level2 = 2;
        level3 = 3;

        mode_View = (TextView) findViewById(R.id.mode_view);

        try {
            tempSetting = getSharedPreferences(savedTemp, 0);
            String mode = tempSetting.getString("mode", "2");
            int imode = Integer.parseInt(mode);
            switch (imode) {
                case iHOLIDAY:
                    mode = HOLIDAY;
                    break;
                case iDAY:
                    mode = DAY;
                    break;
                case iNIGHT:
                    mode = NIGHT;
                    break;
                case iAWAY:
                    mode = AWAY;
                    break;
                default:
                    mode = DAY;
                    break;
            }
            mode_View.setGravity(Gravity.CENTER);
            mode_View.setText("Modus: " + mode);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Displays ventilation status on screen. Sets buttons toggle on/off to what was saved
     * in that mode
     */
    public void displayVentilation() {

        try {
            ventilationSettings = getSharedPreferences(savedVent, 0);
            tempSetting = getSharedPreferences(savedTemp, 0);
            sMode = tempSetting.getString("mode", "2");
            iMode = Integer.parseInt(sMode);
            String modeStatus1;
            switch (iMode) {
                case 1:
                    modeStatus1 = ventilationSettings.getString("Hvent1status", "0");
                    break;
                case 2:
                    modeStatus1 = ventilationSettings.getString("Dvent1status", "0");
                    break;
                case 3:
                    modeStatus1 = ventilationSettings.getString("Nvent1status", "0");
                    break;
                case 4:
                    modeStatus1 = ventilationSettings.getString("Avent1status", "0");
                    break;
                default:
                    modeStatus1 = "0";
                    break;
            }

            int s1 = Integer.parseInt(modeStatus1);

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
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Saves ventilation status to sharedpreferences for each mode.
     * @param i used to determine which mode the house is in
     */
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

    // toggle level one
    /**
     * Toggles level 1 if level 1 is not activated. sends command to server to turn of lvl 2.
     * turn on lvl 1. in the action to toggle on level 1. If level one is toggled it will tell the
     * server to turn off level 1.turns off the other toggle buttons
     */
    protected View.OnClickListener toggle_level1 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            connected = true;
            if (!ch1) {
                MainActivity_2.sendText("Command:000049114,2,0,0,1");     // turns off level 2
                MainActivity_2.sendText("Command:000049114,1,100,0,1");   // turns on level 1
                ch1 = true;
                ch2 = false;

                lvlTwoBtn.setChecked(false);
                lvlThreeBtn.setChecked(false);
                turnOffBtn.setChecked(false);
                setVent1BasedOnMode(level1);
            }
            else if(ch1) {
                MainActivity_2.sendText("Command:000049114,1,0,0,1");     // turns off level 1
                ch1 = false;
                allLvlOff();
                setVent1BasedOnMode(level0);
            }
        }
    };

    /**
     * Toggles level 2 if level 2 is not activated. sends command to server to turn of lvl 1.
     * turn on lvl 2. in the action to toggle on level 2. If level one is toggled it will tell the
     * server to turn off level 2.turns off the other toggle buttons
     */
    // toggle level 2
    protected View.OnClickListener toggle_level2 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            connected = true;
            if (!ch2) {
                MainActivity_2.sendText("Command:000049114,1,0,0,1");      // turns off level 1
                MainActivity_2.sendText("Command:000049114,2,100,0,1");     // turns on level 2
                ch2 = true;
                ch1 = false;

                lvlOneBtn.setChecked(false);
                lvlThreeBtn.setChecked(false);
                turnOffBtn.setChecked(false);
                setVent1BasedOnMode(level2);
            }
            else if(ch2) {
                MainActivity_2.sendText("Command:000049114,2,0,0,1");     // turns off level 2
                ch2 = false;
                allLvlOff();
                setVent1BasedOnMode(level0);
            }
        }
    };

    /**
     * Toggles on level 3 by turning no level 1 and 2. toggles off 1 and 2. If one function is
     * not being used but the other is . the first is turned on to make sure both is on
     * so toggle 3 can be activated. If toggle 3 is activated already it turns off everything
     */
    // toggle level 3
    protected View.OnClickListener toggle_level3 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            connected = true;
            if (!ch1 && !ch2) {
                MainActivity_2.sendText("Command:000002114,1,1");      // turns on level 1 and 2
                ch1 = true;
                ch2 = true;
                ch3 = true;
                lvlOneTwoOff();
                setVent1BasedOnMode(level3);
            }
            else if(ch2 && !ch1) {
                MainActivity_2.sendText("Command:000049114,1,100,0,1");     // turns on level 1
                ch1 = true;
                ch3 = true;
                lvlOneTwoOff();
                setVent1BasedOnMode(level3);
            }
            else if(!ch2 && ch1)  {
                MainActivity_2.sendText("Command:000049114,2,100,0,1");      // turns on level 2
                ch2 = true;
                ch3 = true;
                lvlOneTwoOff();
                setVent1BasedOnMode(level3);
            }
            else if (ch2 && ch1){
                MainActivity_2.sendText("Command:000002114,1,0");             // turns off 1 and 2
                allBoolFalse();
                setVent1BasedOnMode(level0);
            }
        }
    };

    /**
     * turns off mode 1 and 2. Also toggles of all 3 other buttons.
     */
    // turn off
    protected View.OnClickListener turn_off = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            connected = true;
            if ((ch1) || (ch2) || (ch3)) {          // ch1 or ch2 or ch3 true
                MainActivity_2.sendText("Command:000002114,1,0");            // turns off 1 and 2
                allBoolFalse();
                lvlOneBtn.setChecked(false);
                lvlTwoBtn.setChecked(false);
                lvlThreeBtn.setChecked(false);
                setVent1BasedOnMode(level0);
            }
        }
    };

    /**
     * sets toggle buttons to false
     */
    public void lvlOneTwoOff() {
        lvlOneBtn.setChecked(false);
        lvlTwoBtn.setChecked(false);
        turnOffBtn.setChecked(false);
    }

    /**
     * sets all bool to false
     */
    public void allBoolFalse() {
        ch1 = false;
        ch2 = false;
        ch3 = false;
    }

    /**
     * sets all toggle buttons to false
     */
    public void allLvlOff()  {
        lvlOneBtn.setChecked(false);
        lvlTwoBtn.setChecked(false);
        lvlThreeBtn.setChecked(false);
        turnOffBtn.setChecked(false);
    }

    /**
     * sends the user to the mainactivity menu
     */
    public void goToHome() {
        Intent intent = new Intent(this, MainActivity_2.class);
        startActivity(intent);
    }
}
