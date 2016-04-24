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

public class LightingHallway extends AppCompatActivity {

    public static final String savedLight = "SavedLightingHallway" ;
    public static final String savedTemp = "1SavedTemperature";
    public SharedPreferences tempSetting;
    public SharedPreferences lightSettings;

    String sMode;
    int iMode;

    public static final int row1 = 1;
    public static final int row2 = 2;
    public static final int row3 = 3;
    public static final int row4 = 4;
    public static final int row5 = 5;
    public static final int row6 = 6;
    public static final int rowall = 7;

    public static final int lightOff = 0;
    public static final int lightMin = 1;
    public static final int lightMed = 2;
    public static final int lightMax = 3;

    boolean lmin = false;
    boolean lmed = false;
    boolean lmax = false;

    boolean lmin1 = false;
    boolean lmed1 = false;
    boolean lmax1 = false;

    boolean lmin6 = false;
    boolean lmed6 = false;
    boolean lmax6 = false;
    boolean loff6 = false;

    ToggleButton lightOffBtn;
    ToggleButton lightMinBtn;
    ToggleButton lightMedBtn;
    ToggleButton lightMaxBtn;

    ToggleButton lightOffBtn1;
    ToggleButton lightMinBtn1;
    ToggleButton lightMedBtn1;
    ToggleButton lightMaxBtn1;

    ToggleButton lightOffBtn6;
    ToggleButton lightMinBtn6;
    ToggleButton lightMaxBtn6;
    ToggleButton lightMedBtn6;
    ImageButton homeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lighting_hallway);

        setupGUI();
        displayLights();
    }

    public void displayLights() {
        try {

            lightSettings = getSharedPreferences(savedLight, 0);
            tempSetting = getSharedPreferences(savedTemp, 0);
            sMode = tempSetting.getString("mode", "2");
            iMode = Integer.parseInt(sMode);

            allButtonOffKill();

            String modeStatus1, modeStatus2;
            switch (iMode) {
                case 1:
                    modeStatus1 = lightSettings.getString("Hrow1status", "0");
                    modeStatus2 = lightSettings.getString("Hrow2status", "0");
                    break;
                case 2:
                    modeStatus1 = lightSettings.getString("Drow1status", "0");
                    modeStatus2 = lightSettings.getString("Drow2status", "0");
                    break;
                case 3:
                    modeStatus1 = lightSettings.getString("Nrow1status", "0");
                    modeStatus2 = lightSettings.getString("Nrow2status", "0");
                    break;
                case 4:
                    modeStatus1 = lightSettings.getString("Arow1status", "0");
                    modeStatus2 = lightSettings.getString("Arow2status", "0");
                    break;
                default:
                    modeStatus1 = "0";
                    modeStatus2 = "0";
                    break;
            }

            int s1 = Integer.parseInt(modeStatus1);
            int s2 = Integer.parseInt(modeStatus2);

            switch (s1){
                case 0: //row 1 off
                    lightOffBtn.setChecked(true);
                    /*
                    lmin = false;
                    lmed = false;
                    lmax = false;
                    */
                    break;
                case 1: //row 1 min
                    lmin = true;
                    lightMinBtn.setChecked(true);
                    break;
                case 2: //row 2 med
                    lmed = true;
                    lightMedBtn.setChecked(true);
                    break;
                case 3: //row 1 max
                    lmax = true;
                    lightMaxBtn.setChecked(true);
                    break;
                default: //not set
                    lightOffBtn.setChecked(true);
                    break;
            }

            switch (s2){
                case 0: //row 2 off
                    lightOffBtn1.setChecked(true);
                    /*
                    lmin = false;
                    lmed = false;
                    lmax = false;
                    */
                    break;
                case 1: //row 2 min
                    lmin1 = true;
                    lightMinBtn1.setChecked(true);
                    break;
                case 2: //row 2 med
                    lmed1 = true;
                    lightMedBtn1.setChecked(true);
                    break;
                case 3: //row 2 max
                    lmax1 = true;
                    lightMaxBtn1.setChecked(true);
                    break;
                default: //not set
                    lightOffBtn1.setChecked(true);
                    break;
            }
            lightOffBtn6.setChecked(true);
            loff6 = true;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setLightSettingsForRow(int row, int value) {
        lightSettings = getSharedPreferences(savedLight, 0);
        SharedPreferences.Editor editor = lightSettings.edit();

        switch (iMode){
            case 1:
                switch (row){
                    case 1:
                        switch (value){
                            case 0:
                                editor.putString("Hrow1status", "0");
                                editor.commit();
                                break;
                            case 1:
                                editor.putString("Hrow1status", "1");
                                editor.commit();
                                break;
                            case 2:
                                editor.putString("Hrow1status", "2");
                                editor.commit();
                                break;
                            case 3:
                                editor.putString("Hrow1status", "3");
                                editor.commit();
                                break;
                            default:
                                editor.putString("Hrow1status", "0");
                                editor.commit();
                                break;
                        }
                        break;
                    case 2:
                        switch (value){
                            case 0:
                                editor.putString("Hrow2status", "0");
                                editor.commit();
                                break;
                            case 1:
                                editor.putString("Hrow2status", "1");
                                editor.commit();
                                break;
                            case 2:
                                editor.putString("Hrow2status", "2");
                                editor.commit();
                                break;
                            case 3:
                                editor.putString("Hrow2status", "3");
                                editor.commit();
                                break;
                            default:
                                editor.putString("Hrow2status", "0");
                                editor.commit();
                                break;
                        }
                        break;
                    case 3:
                        switch (value){
                            case 0:
                                editor.putString("Hrow3status", "0");
                                editor.commit();
                                break;
                            case 1:
                                editor.putString("Hrow3status", "1");
                                editor.commit();
                                break;
                            case 2:
                                editor.putString("Hrow3status", "2");
                                editor.commit();
                                break;
                            case 3:
                                editor.putString("Hrow3status", "3");
                                editor.commit();
                                break;
                            default:
                                editor.putString("Hrow3status", "0");
                                editor.commit();
                                break;
                        }
                        break;
                    case 4:
                        switch (value){
                            case 0:
                                editor.putString("Hrow4status", "0");
                                editor.commit();
                                break;
                            case 1:
                                editor.putString("Hrow4status", "1");
                                editor.commit();
                                break;
                            case 2:
                                editor.putString("Hrow4status", "2");
                                editor.commit();
                                break;
                            case 3:
                                editor.putString("Hrow4status", "3");
                                editor.commit();
                                break;
                            default:
                                editor.putString("Hrow4status", "0");
                                editor.commit();
                                break;
                        }
                        break;
                    case 5:
                        switch (value){
                            case 0:
                                editor.putString("Hrow5status", "0");
                                editor.commit();
                                break;
                            case 1:
                                editor.putString("Hrow5status", "1");
                                editor.commit();
                                break;
                            case 2:
                                editor.putString("Hrow5status", "2");
                                editor.commit();
                                break;
                            case 3:
                                editor.putString("Hrow5status", "3");
                                editor.commit();
                                break;
                            default:
                                editor.putString("Hrow5status", "0");
                                editor.commit();
                                break;
                        }
                        break;
                    case 6:
                        switch (value){
                            case 0:
                                editor.putString("Hrow6status", "0");
                                editor.commit();
                                break;
                            case 1:
                                editor.putString("Hrow6status", "1");
                                editor.commit();
                                break;
                            case 2:
                                editor.putString("Hrow6status", "2");
                                editor.commit();
                                break;
                            case 3:
                                editor.putString("Hrow6status", "3");
                                editor.commit();
                                break;
                            default:
                                editor.putString("Hrow6status", "0");
                                editor.commit();
                                break;
                        }
                        break;
                    case 7:
                        switch (value){
                            case 0:
                                editor.putString("Hrowallstatus", "0");
                                editor.commit();
                                break;
                            case 1:
                                editor.putString("Hrowallstatus", "1");
                                editor.commit();
                                break;
                            case 2:
                                editor.putString("Hrowallstatus", "2");
                                editor.commit();
                                break;
                            case 3:
                                editor.putString("Hrowallstatus", "3");
                                editor.commit();
                                break;
                            default:
                                editor.putString("Hrowallstatus", "0");
                                editor.commit();
                                break;
                        }
                        break;
                    default:
                        break;
                }
                break;
            case 2:
                switch (row){
                    case 1:
                        switch (value){
                            case 0:
                                editor.putString("Drow1status", "0");
                                editor.commit();
                                break;
                            case 1:
                                editor.putString("Drow1status", "1");
                                editor.commit();
                                break;
                            case 2:
                                editor.putString("Drow1status", "2");
                                editor.commit();
                                break;
                            case 3:
                                editor.putString("Drow1status", "3");
                                editor.commit();
                                break;
                            default:
                                editor.putString("Drow1status", "0");
                                editor.commit();
                                break;
                        }
                        break;
                    case 2:
                        switch (value){
                            case 0:
                                editor.putString("Drow2status", "0");
                                editor.commit();
                                break;
                            case 1:
                                editor.putString("Drow2status", "1");
                                editor.commit();
                                break;
                            case 2:
                                editor.putString("Drow2status", "2");
                                editor.commit();
                                break;
                            case 3:
                                editor.putString("Drow2status", "3");
                                editor.commit();
                                break;
                            default:
                                editor.putString("Drow2status", "0");
                                editor.commit();
                                break;
                        }
                        break;
                    case 3:
                        switch (value){
                            case 0:
                                editor.putString("Drow3status", "0");
                                editor.commit();
                                break;
                            case 1:
                                editor.putString("Drow3status", "1");
                                editor.commit();
                                break;
                            case 2:
                                editor.putString("Drow3status", "2");
                                editor.commit();
                                break;
                            case 3:
                                editor.putString("Drow3status", "3");
                                editor.commit();
                                break;
                            default:
                                editor.putString("Drow3status", "0");
                                editor.commit();
                                break;
                        }
                        break;
                    case 4:
                        switch (value){
                            case 0:
                                editor.putString("Drow4status", "0");
                                editor.commit();
                                break;
                            case 1:
                                editor.putString("Drow4status", "1");
                                editor.commit();
                                break;
                            case 2:
                                editor.putString("Drow4status", "2");
                                editor.commit();
                                break;
                            case 3:
                                editor.putString("Drow4status", "3");
                                editor.commit();
                                break;
                            default:
                                editor.putString("Drow4status", "0");
                                editor.commit();
                                break;
                        }
                        break;
                    case 5:
                        switch (value){
                            case 0:
                                editor.putString("Drow5status", "0");
                                editor.commit();
                                break;
                            case 1:
                                editor.putString("Drow5status", "1");
                                editor.commit();
                                break;
                            case 2:
                                editor.putString("Drow5status", "2");
                                editor.commit();
                                break;
                            case 3:
                                editor.putString("Drow5status", "3");
                                editor.commit();
                                break;
                            default:
                                editor.putString("Drow5status", "0");
                                editor.commit();
                                break;
                        }
                        break;
                    case 6:
                        switch (value){
                            case 0:
                                editor.putString("Drow6status", "0");
                                editor.commit();
                                break;
                            case 1:
                                editor.putString("Drow6status", "1");
                                editor.commit();
                                break;
                            case 2:
                                editor.putString("Drow6status", "2");
                                editor.commit();
                                break;
                            case 3:
                                editor.putString("Drow6status", "3");
                                editor.commit();
                                break;
                            default:
                                editor.putString("Drow6status", "0");
                                editor.commit();
                                break;
                        }
                        break;
                    case 7:
                        switch (value){
                            case 0:
                                editor.putString("Drowallstatus", "0");
                                editor.commit();
                                break;
                            case 1:
                                editor.putString("Drowallstatus", "1");
                                editor.commit();
                                break;
                            case 2:
                                editor.putString("Drowallstatus", "2");
                                editor.commit();
                                break;
                            case 3:
                                editor.putString("Drowallstatus", "3");
                                editor.commit();
                                break;
                            default:
                                editor.putString("Drowallstatus", "0");
                                editor.commit();
                                break;
                        }
                        break;
                    default:
                        break;
                }
                break;
            case 3:
                switch (row){
                    case 1:
                        switch (value){
                            case 0:
                                editor.putString("Nrow1status", "0");
                                editor.commit();
                                break;
                            case 1:
                                editor.putString("Nrow1status", "1");
                                editor.commit();
                                break;
                            case 2:
                                editor.putString("Nrow1status", "2");
                                editor.commit();
                                break;
                            case 3:
                                editor.putString("Nrow1status", "3");
                                editor.commit();
                                break;
                            default:
                                editor.putString("Nrow1status", "0");
                                editor.commit();
                                break;
                        }
                        break;
                    case 2:
                        switch (value){
                            case 0:
                                editor.putString("Nrow2status", "0");
                                editor.commit();
                                break;
                            case 1:
                                editor.putString("Nrow2status", "1");
                                editor.commit();
                                break;
                            case 2:
                                editor.putString("Nrow2status", "2");
                                editor.commit();
                                break;
                            case 3:
                                editor.putString("Nrow2status", "3");
                                editor.commit();
                                break;
                            default:
                                editor.putString("Nrow2status", "0");
                                editor.commit();
                                break;
                        }
                        break;
                    case 3:
                        switch (value){
                            case 0:
                                editor.putString("Nrow3status", "0");
                                editor.commit();
                                break;
                            case 1:
                                editor.putString("Nrow3status", "1");
                                editor.commit();
                                break;
                            case 2:
                                editor.putString("Nrow3status", "2");
                                editor.commit();
                                break;
                            case 3:
                                editor.putString("Nrow3status", "3");
                                editor.commit();
                                break;
                            default:
                                editor.putString("Nrow3status", "0");
                                editor.commit();
                                break;
                        }
                        break;
                    case 4:
                        switch (value){
                            case 0:
                                editor.putString("Nrow4status", "0");
                                editor.commit();
                                break;
                            case 1:
                                editor.putString("Nrow4status", "1");
                                editor.commit();
                                break;
                            case 2:
                                editor.putString("Nrow4status", "2");
                                editor.commit();
                                break;
                            case 3:
                                editor.putString("Nrow4status", "3");
                                editor.commit();
                                break;
                            default:
                                editor.putString("Nrow4status", "0");
                                editor.commit();
                                break;
                        }
                        break;
                    case 5:
                        switch (value){
                            case 0:
                                editor.putString("Nrow5status", "0");
                                editor.commit();
                                break;
                            case 1:
                                editor.putString("Nrow5status", "1");
                                editor.commit();
                                break;
                            case 2:
                                editor.putString("Nrow5status", "2");
                                editor.commit();
                                break;
                            case 3:
                                editor.putString("Nrow5status", "3");
                                editor.commit();
                                break;
                            default:
                                editor.putString("Nrow5status", "0");
                                editor.commit();
                                break;
                        }
                        break;
                    case 6:
                        switch (value){
                            case 0:
                                editor.putString("Nrow6status", "0");
                                editor.commit();
                                break;
                            case 1:
                                editor.putString("Nrow6status", "1");
                                editor.commit();
                                break;
                            case 2:
                                editor.putString("Nrow6status", "2");
                                editor.commit();
                                break;
                            case 3:
                                editor.putString("Nrow6status", "3");
                                editor.commit();
                                break;
                            default:
                                editor.putString("Nrow6status", "0");
                                editor.commit();
                                break;
                        }
                        break;
                    case 7:
                        switch (value){
                            case 0:
                                editor.putString("Nrowallstatus", "0");
                                editor.commit();
                                break;
                            case 1:
                                editor.putString("Nrowallstatus", "1");
                                editor.commit();
                                break;
                            case 2:
                                editor.putString("Nrowallstatus", "2");
                                editor.commit();
                                break;
                            case 3:
                                editor.putString("Nrowallstatus", "3");
                                editor.commit();
                                break;
                            default:
                                editor.putString("Nrowallstatus", "0");
                                editor.commit();
                                break;
                        }
                        break;
                    default:
                        break;
                }
                break;
            case 4:
                switch (row){
                    case 1:
                        switch (value){
                            case 0:
                                editor.putString("Arow1status", "0");
                                editor.commit();
                                break;
                            case 1:
                                editor.putString("Arow1status", "1");
                                editor.commit();
                                break;
                            case 2:
                                editor.putString("Arow1status", "2");
                                editor.commit();
                                break;
                            case 3:
                                editor.putString("Arow1status", "3");
                                editor.commit();
                                break;
                            default:
                                editor.putString("Arow1status", "0");
                                editor.commit();
                                break;
                        }
                        break;
                    case 2:
                        switch (value){
                            case 0:
                                editor.putString("Arow2status", "0");
                                editor.commit();
                                break;
                            case 1:
                                editor.putString("Arow2status", "1");
                                editor.commit();
                                break;
                            case 2:
                                editor.putString("Arow2status", "2");
                                editor.commit();
                                break;
                            case 3:
                                editor.putString("Arow2status", "3");
                                editor.commit();
                                break;
                            default:
                                editor.putString("Arow2status", "0");
                                editor.commit();
                                break;
                        }
                        break;
                    case 3:
                        switch (value){
                            case 0:
                                editor.putString("Arow3status", "0");
                                editor.commit();
                                break;
                            case 1:
                                editor.putString("Arow3status", "1");
                                editor.commit();
                                break;
                            case 2:
                                editor.putString("Arow3status", "2");
                                editor.commit();
                                break;
                            case 3:
                                editor.putString("Arow3status", "3");
                                editor.commit();
                                break;
                            default:
                                editor.putString("Arow3status", "0");
                                editor.commit();
                                break;
                        }
                        break;
                    case 4:
                        switch (value){
                            case 0:
                                editor.putString("Arow4status", "0");
                                editor.commit();
                                break;
                            case 1:
                                editor.putString("Arow4status", "1");
                                editor.commit();
                                break;
                            case 2:
                                editor.putString("Arow4status", "2");
                                editor.commit();
                                break;
                            case 3:
                                editor.putString("Arow4status", "3");
                                editor.commit();
                                break;
                            default:
                                editor.putString("Arow4status", "0");
                                editor.commit();
                                break;
                        }
                        break;
                    case 5:
                        switch (value){
                            case 0:
                                editor.putString("Arow5status", "0");
                                editor.commit();
                                break;
                            case 1:
                                editor.putString("Arow5status", "1");
                                editor.commit();
                                break;
                            case 2:
                                editor.putString("Arow5status", "2");
                                editor.commit();
                                break;
                            case 3:
                                editor.putString("Arow5status", "3");
                                editor.commit();
                                break;
                            default:
                                editor.putString("Arow5status", "0");
                                editor.commit();
                                break;
                        }
                        break;
                    case 6:
                        switch (value){
                            case 0:
                                editor.putString("Arow6status", "0");
                                editor.commit();
                                break;
                            case 1:
                                editor.putString("Arow6status", "1");
                                editor.commit();
                                break;
                            case 2:
                                editor.putString("Arow6status", "2");
                                editor.commit();
                                break;
                            case 3:
                                editor.putString("Arow6status", "3");
                                editor.commit();
                                break;
                            default:
                                editor.putString("Arow6status", "0");
                                editor.commit();
                                break;
                        }
                        break;
                    case 7:
                        switch (value){
                            case 0:
                                editor.putString("Arowallstatus", "0");
                                editor.commit();
                                break;
                            case 1:
                                editor.putString("Arowallstatus", "1");
                                editor.commit();
                                break;
                            case 2:
                                editor.putString("Arowallstatus", "2");
                                editor.commit();
                                break;
                            case 3:
                                editor.putString("Arowallstatus", "3");
                                editor.commit();
                                break;
                            default:
                                editor.putString("Arowallstatus", "0");
                                editor.commit();
                                break;
                        }
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }
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
                setLightSettingsForRow(row1, lightOff);
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
                setLightSettingsForRow(row1, lightMin);
            } else if (lmin) {
                MainActivity.sendText("Command:000002117,1,0");     // all lights off
                allBoolFalse();
                allButtonsOff();
                lightOffBtn.setChecked(true);
                setLightSettingsForRow(row1, lightOff);

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
                setLightSettingsForRow(row1, lightMed);
            } else if (lmed) {
                MainActivity.sendText("Command:000002117,1,0"); // all lights off
                allBoolFalse();
                allButtonsOff();
                lightOffBtn.setChecked(true);
                setLightSettingsForRow(row1, lightOff);
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
                setLightSettingsForRow(row1, lightMax);
            } else if (lmax) {
                MainActivity.sendText("Command:000002117,1,0");     // all lights off
                allBoolFalse();
                allButtonsOff();
                lightOffBtn.setChecked(true);
                setLightSettingsForRow(row1, lightOff);
            }
        }
    };

    public void allButtonsOff() {
        lightMinBtn.setChecked(false);
        lightMedBtn.setChecked(false);
        lightMaxBtn.setChecked(false);
        lightOffBtn.setChecked(false);
    }

    public void allBoolFalse() {
        lmin = false;
        lmed = false;
        lmax = false;
    }

    public void goToHome() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


    /* next 2nd row */

    // all lights off
    protected View.OnClickListener light_all__off_Listener1 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (lmin1 || lmed1 || lmax1) {
                MainActivity.sendText("Command:000002117,1,0");
                lmin1 = false;
                lmed1 = false;
                lmax1 = false;

                lightMinBtn1.setChecked(false);
                lightMedBtn1.setChecked(false);
                lightMaxBtn1.setChecked(false);
                setLightSettingsForRow(row2, lightOff);

            }
        }
    };

    // all lights on minimum
    protected View.OnClickListener light_all_min1 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!lmin1) {
                MainActivity.sendText("Command:000002117,1,1");     // all lights minimum
                lmin1 = true;
                lmed1 = false;
                lmax1 = false;

                lightMedBtn1.setChecked(false);
                lightMaxBtn1.setChecked(false);
                lightOffBtn1.setChecked(false);
                setLightSettingsForRow(row2, lightMin);
            } else if (lmin1) {
                MainActivity.sendText("Command:000002117,1,0");     // all lights off
                allBoolFalse1();
                allButtonsOff1();
                lightOffBtn1.setChecked(true);
                setLightSettingsForRow(row2, lightOff);

            }
        }
    };

    // all lights on medium
    protected View.OnClickListener light_all_med1 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!lmed1) {
                MainActivity.sendText("Command:000002117,1,2");     // all lights medium
                lmin1 = false;
                lmed1 = true;
                lmax1 = false;

                lightMinBtn1.setChecked(false);
                lightMaxBtn1.setChecked(false);
                lightOffBtn1.setChecked(false);
                setLightSettingsForRow(row2, lightMed);
            } else if (lmed1) {
                MainActivity.sendText("Command:000002117,1,0"); // all lights off
                allBoolFalse1();
                allButtonsOff1();
                lightOffBtn1.setChecked(true);
                setLightSettingsForRow(row2, lightOff);
            }
        }
    };

    // all lights on max
    protected View.OnClickListener light_all_Listener1 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!lmax1) {        //this method should only be called if there is a connection.
                MainActivity.sendText("Command:000002117,1,3"); // all lights to max
                lmin1 = false;
                lmed1 = false;
                lmax1 = true;

                lightMinBtn1.setChecked(false);
                lightMedBtn1.setChecked(false);
                lightOffBtn1.setChecked(false);
                setLightSettingsForRow(row2, lightMax);
            } else if (lmax1) {
                MainActivity.sendText("Command:000002117,1,0");     // all lights off
                allBoolFalse1();
                allButtonsOff1();
                lightOffBtn1.setChecked(true);
                setLightSettingsForRow(row2, lightOff);
            }
        }
    };

    public void allButtonsOff1() {
        lightMinBtn1.setChecked(false);
        lightMedBtn1.setChecked(false);
        lightMaxBtn1.setChecked(false);
        lightOffBtn1.setChecked(false);
    }

    public void allBoolFalse1() {
        lmin1 = false;
        lmed1 = false;
        lmax1 = false;
    }



          /* next 6th row CONTROL ALL ABOVE LIGHTS */

    // all lights off
    protected View.OnClickListener light_all__off_Listener6 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            MainActivity.sendText("Command:000002117,1,0");
            lmin6 = false;
            lmed6 = false;
            lmax6 = false;
            loff6 = true;

            lightMinBtn6.setChecked(false);
            lightMedBtn6.setChecked(false);
            lightMaxBtn6.setChecked(false);
            allButtonOffKill();
            offSwitchoff();

            if (loff6) {
                lightOffBtn6.setChecked(true);
            }

        }
    };

    // all lights on minimum
    protected View.OnClickListener light_all_min6 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!lmin6) {
                MainActivity.sendText("Command:000002117,1,1");     // all lights minimum
                lmin6 = true;
                lmed6 = false;
                lmax6 = false;

                lightMedBtn6.setChecked(false);
                lightMaxBtn6.setChecked(false);
                lightOffBtn6.setChecked(false);

                allButtonOffKill();

                lightMinBtn.setChecked(true);
                lightMinBtn1.setChecked(true);

                lmin = true;
                lmin1 = true;
                setLightSettingsForAllMin();


            } else if (lmin6) {
                MainActivity.sendText("Command:000002117,1,0");     // all lights off
                allBoolFalse6();
                allButtonsOff6();
                allButtonOffKill();
                offSwitchoff();
                setLightSettingsForAllOff();
            }
        }
    };

    // all lights on medium
    protected View.OnClickListener light_all_med6 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!lmed6) {
                MainActivity.sendText("Command:000002117,1,2");     // all lights medium
                lmin6 = false;
                lmed6 = true;
                lmax6 = false;

                lightMinBtn6.setChecked(false);
                lightMaxBtn6.setChecked(false);
                lightOffBtn6.setChecked(false);

                allButtonOffKill();
                lightMedBtn.setChecked(true);
                lightMedBtn1.setChecked(true);

                lmed = true;
                lmed1 = true;
                setLightSettingsForAllMed();

            } else if (lmed6) {
                MainActivity.sendText("Command:000002117,1,0"); // all lights off
                allBoolFalse6();
                allButtonsOff6();
                allButtonOffKill();
                offSwitchoff();
                setLightSettingsForAllOff();
            }
        }
    };

    // all lights on max
    protected View.OnClickListener light_all_Listener6 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!lmax6) {        //this method should only be called if there is a connection.
                MainActivity.sendText("Command:000002117,1,3"); // all lights to max
                lmin6 = false;
                lmed6 = false;
                lmax6 = true;

                lightMinBtn6.setChecked(false);
                lightMedBtn6.setChecked(false);
                lightOffBtn6.setChecked(false);

                allButtonOffKill();
                lightMaxBtn.setChecked(true);
                lightMaxBtn1.setChecked(true);

                lmax = true;
                lmax1 = true;
                setLightSettingsForAllMax();

            } else if (lmax6) {
                MainActivity.sendText("Command:000002117,1,0");     // all lights off
                allBoolFalse6();

                allButtonsOff6();
                allButtonOffKill();
                offSwitchoff();
                setLightSettingsForAllOff();
            }
        }
    };

    public void allButtonsOff6() {
        lightMinBtn6.setChecked(false);
        lightMedBtn6.setChecked(false);
        lightMaxBtn6.setChecked(false);
        lightOffBtn6.setChecked(false);
    }

    public void allBoolFalse6() {
        lmin6 = false;
        lmed6 = false;
        lmax6 = false;
    }

    public void allButtonOffKill() {
        allButtonsOff();
        allButtonsOff1();

        allBoolFalse();
        allBoolFalse1();
    }

    public void setLightSettingsForAllOff() {
        setLightSettingsForRow(rowall, lightOff);
        setLightSettingsForRow(row1, lightOff);
        setLightSettingsForRow(row2, lightOff);
    }

    public void setLightSettingsForAllMin() {
        setLightSettingsForRow(rowall, lightMin);
        setLightSettingsForRow(row1, lightMin);
        setLightSettingsForRow(row2, lightMin);
    }

    public void setLightSettingsForAllMed() {
        setLightSettingsForRow(rowall, lightMed);
        setLightSettingsForRow(row1, lightMed);
        setLightSettingsForRow(row2, lightMed);
    }

    public void setLightSettingsForAllMax() {
        setLightSettingsForRow(rowall, lightMax);
        setLightSettingsForRow(row1, lightMax);
        setLightSettingsForRow(row2, lightMax);
    }

    public void offSwitchoff() {
        lightOffBtn.setChecked(true);
        lightOffBtn1.setChecked(true);


    }

    public void setupGUI() {
        lightOffBtn = (ToggleButton) findViewById(R.id.toggle_0);
        lightOffBtn.setOnClickListener(light_all__off_Listener);

        lightMinBtn = (ToggleButton) findViewById(R.id.toggle_30);
        lightMinBtn.setOnClickListener(light_all_min);

        lightMedBtn = (ToggleButton) findViewById(R.id.toggle_70);
        lightMedBtn.setOnClickListener(light_all_med);

        lightMaxBtn = (ToggleButton) findViewById(R.id.toggle_100);
        lightMaxBtn.setOnClickListener(light_all_Listener);

        lightOffBtn1 = (ToggleButton) findViewById(R.id.toggle_01);
        lightOffBtn1.setOnClickListener(light_all__off_Listener1);

        lightMinBtn1 = (ToggleButton) findViewById(R.id.toggle_301);
        lightMinBtn1.setOnClickListener(light_all_min1);

        lightMedBtn1 = (ToggleButton) findViewById(R.id.toggle_701);
        lightMedBtn1.setOnClickListener(light_all_med1);

        lightMaxBtn1 = (ToggleButton) findViewById(R.id.toggle_1001);
        lightMaxBtn1.setOnClickListener(light_all_Listener1);




        lightOffBtn6 = (ToggleButton) findViewById(R.id.toggle_06);
        lightOffBtn6.setOnClickListener(light_all__off_Listener6);

        lightMinBtn6 = (ToggleButton) findViewById(R.id.toggle_306);
        lightMinBtn6.setOnClickListener(light_all_min6);

        lightMedBtn6 = (ToggleButton) findViewById(R.id.toggle_706);
        lightMedBtn6.setOnClickListener(light_all_med6);

        lightMaxBtn6 = (ToggleButton) findViewById(R.id.toggle_1006);
        lightMaxBtn6.setOnClickListener(light_all_Listener6);

        //home button
        homeBtn = (ImageButton) findViewById(R.id.home_button);
        homeBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goToHome();
            }
        });
    }
}