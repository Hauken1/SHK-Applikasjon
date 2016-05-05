package com.weebly.smarthusgruppen.shk_applikasjon.Toppleilighet;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.weebly.smarthusgruppen.shk_applikasjon.R;
/**
 * Controls all the lights for the hallway Light settings based on mode. Therse settings are
 * also saved in shared preferences
 */
public class LightingHallway extends AppCompatActivity {

    public static final String savedLight = "SavedLightingHallway_1" ;
    public static final String savedTemp = "1SavedTemperature_1";
    public static final String savedColor = "SavedBackgroundColor_1";
    public static final String savedLightSettings = "SavedLightSettings_Hallway_1";

    ImageButton light_setttings_button;

    private int seekBarValue1;
    private int seekBarValue2;

    String minDimvalue = "30";
    String medDimValue = "70";

    Boolean medChanged;
    Boolean minChanged;

    public SharedPreferences savedLightSet;
    SharedPreferences sharedpreferences;
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

    public static final String DAY = "Dag";
    public static final String NIGHT = "Natt";
    public static final String AWAY = "Borte";
    public static final String HOLIDAY = "Ferie";

    public static final int iDAY = 2;
    public static final int iNIGHT = 3;
    public static final int iAWAY = 4;
    public static final int iHOLIDAY = 1;

    TextView mode_View;

    /**
     * runs on startup and sets up gui and displays light settings dependent on current mode
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lighting_hallway);

        setupGUI();
        displayLights();
    }
    /**
     * displays light settings dependent on which mode the house is in. saves all these variables
     * in sharedpreferences
     */
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
    /**
     * Changing and saving value for light settings. Depending on the mode the house is in.
     * @param row the row in the grid layout. for which button is clicked
     * @param value off, min, med, or max value for the light.
     */
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
    /**
     * turns off all lights and sets the other toggle buttons to off.
     */
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
    /**
     * turns light on to minimum setting. toggles off other buttons. if already set turns off light
     */
    // all lights on minimum
    protected View.OnClickListener light_all_min = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!lmin) {
                  /*
                *This is the right command, but the valid device number and channel is
                * not correct. This have to be set when the smart home is configuered.
                String dim = testForMinDimValueSet();
                MainActivity.sendText("Command:000049114,2," + dim + ",0,1");
                */
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
    /**
     * turns light on to medium setting. toggles off other buttons. if already set turns off light
     */
    // all lights on medium
    protected View.OnClickListener light_all_med = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!lmed) {
                   /*
                *This is the right command, but the valid device number and channel is
                * not correct. This have to be set when the smart home is configuered.
                String dim = testForMedDimValueSet();
                MainActivity.sendText("Command:000049114,2," + dim + ",0,1");
                */
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
    /**
     * turns light on to maximum setting. toggles off other buttons. if already set turns off light
     */
    // lights on max
    protected View.OnClickListener light_all_Listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!lmax) {
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

    /**
     * set all toggle buttons to false
     */
    public void allButtonsOff() {
        lightMinBtn.setChecked(false);
        lightMedBtn.setChecked(false);
        lightMaxBtn.setChecked(false);
        lightOffBtn.setChecked(false);
    }

    /**
     * set all bool to false
     */
    public void allBoolFalse() {
        lmin = false;
        lmed = false;
        lmax = false;
    }

    /**
     * sends the user to MainActivity menu
     */
    public void goToHome() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


    /* next 2nd row */
    /**
     * turns all lights and toggle buttons off
     */
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
    /**
     * turns light on to minimum setting. toggles off other buttons. if already set turns off light
     */
    // all lights on minimum
    protected View.OnClickListener light_all_min1 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!lmin1) {
                  /*
                *This is the right command, but the valid device number and channel is
                * not correct. This have to be set when the smart home is configuered.
                String dim = testForMinDimValueSet();
                MainActivity.sendText("Command:000049114,2," + dim + ",0,1");
                */
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
    /**
     * turns light on to medium setting. toggles off other buttons. if already set turns off light
     */
    // all lights on medium
    protected View.OnClickListener light_all_med1 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!lmed1) {
                   /*
                *This is the right command, but the valid device number and channel is
                * not correct. This have to be set when the smart home is configuered.
                String dim = testForMedDimValueSet();
                MainActivity.sendText("Command:000049114,2," + dim + ",0,1");
                */
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
    /**
     * turns light on to maximum setting. toggles off other buttons. if already set turns off light
     */
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

    /**
     * toggle all buttons to false
     */
    public void allButtonsOff1() {
        lightMinBtn1.setChecked(false);
        lightMedBtn1.setChecked(false);
        lightMaxBtn1.setChecked(false);
        lightOffBtn1.setChecked(false);
    }

    /**
     * sets all bool to false
     */
    public void allBoolFalse1() {
        lmin1 = false;
        lmed1 = false;
        lmax1 = false;
    }



          /* next 6th row CONTROL ALL ABOVE LIGHTS */
    /**
     * turns off all lights and toggle all buttons off
     */
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

            setLightSettingsForAllOff();
            if (loff6) {
                lightOffBtn6.setChecked(true);
            }

        }
    };
    /**
     * turns light on to minimum setting. toggles off other buttons. if already set turns off light
     */
    // all lights on minimum
    protected View.OnClickListener light_all_min6 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!lmin6) {
                  /*
                *This is the right command, but the valid device number and channel is
                * not correct. This have to be set when the smart home is configuered.
                String dim = testForMinDimValueSet();
                MainActivity.sendText("Command:000049114,2," + dim + ",0,1");
                */
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
    /**
     * turns light on to medium setting. toggles off other buttons. if already set turns off light
     */
    // all lights on medium
    protected View.OnClickListener light_all_med6 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!lmed6) {
                   /*
                *This is the right command, but the valid device number and channel is
                * not correct. This have to be set when the smart home is configuered.
                String dim = testForMedDimValueSet();
                MainActivity.sendText("Command:000049114,2," + dim + ",0,1");
                */
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
    /**
     * turns light on to maximum setting. toggles off other buttons. if already set turns off light
     */
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

    /**
     * sets all toggle buttons to false
     */
    public void allButtonsOff6() {
        lightMinBtn6.setChecked(false);
        lightMedBtn6.setChecked(false);
        lightMaxBtn6.setChecked(false);
        lightOffBtn6.setChecked(false);
    }

    /**
     * sets all bools to false
     */
    public void allBoolFalse6() {
        lmin6 = false;
        lmed6 = false;
        lmax6 = false;
    }

    /**
     * turns off all buttons and sets all bools to false
     */
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
    /**
     * Setting up GUI. sets up all onClickListener buttons. Sets background depending on user
     * settings. SharedPreferences for each mode.
     */
    public void setupGUI() {

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

        light_setttings_button = (ImageButton) findViewById(R.id.settings_light);
        light_setttings_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)  {
                settingsView();
            }
        });

        sharedpreferences = getSharedPreferences(savedColor, Context.MODE_PRIVATE);

        int value1 = sharedpreferences.getInt("value1", 0);
        int value2 = sharedpreferences.getInt("value2", 0);
        int value3 = sharedpreferences.getInt("value3", 0);
        int value4 = sharedpreferences.getInt("set", 0);
        if(value4 != 0){
            View v = findViewById(R.id.ScrollView01);
            v.setBackgroundColor(Color.rgb(value1, value3, value2));
            setContentView(v);
        }

    }

    public String testForMedDimValueSet(){
        savedLightSet = getSharedPreferences(savedLightSettings,0);

        String mD = Integer.toString(savedLightSet.getInt("seekbarvalue1", 0));
        if(mD.equals("0")){
            return medDimValue;
        }
        else return mD;
    }

    public String testForMinDimValueSet(){
        savedLightSet = getSharedPreferences(savedLightSettings, 0);

        String mD = Integer.toString(savedLightSet.getInt("seekbarvalue2", 0));
        if(mD.equals("0")){
            return minDimvalue;
        }
        return mD;
    }

    public void settingsView() {
        savedLightSet = getSharedPreferences(savedLightSettings, 0);
        final SharedPreferences.Editor editor = savedLightSet.edit();
        String dimValue, dimValueMin;
        seekBarValue1 = savedLightSet.getInt("seekbarvalue1", 0);
        seekBarValue2 = savedLightSet.getInt("seekbarvalue2", 0);

        medChanged = false;
        minChanged = false;

        if(seekBarValue1 == 0) {
            dimValue = "30%";
        }
        else dimValue = Integer.toString(seekBarValue1) + "%";
        if(seekBarValue2 ==0){
            dimValueMin = "10%";
        }
        else dimValueMin = Integer.toString(seekBarValue2) + "%";

        final Dialog settingsDialog = new Dialog(this);
        settingsDialog.setContentView(R.layout.settings_light);
        settingsDialog.setCancelable(true);

        final TextView sb1Status = (TextView)settingsDialog.findViewById(R.id.textView_dim_value);
        sb1Status.setText(dimValue);
        final TextView sb2Status = (TextView)settingsDialog.findViewById(R.id.textView_dim_value_min);
        sb2Status.setText(dimValueMin);

        SeekBar sB1 = (SeekBar)settingsDialog.findViewById(R.id.seekBar1);
        sB1.setProgress(seekBarValue1);
        sB1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                medChanged = true;
                seekBarValue1 = progress + 30;
                String dim = Integer.toString(seekBarValue1) + "%";
                sb1Status.setText(dim);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }
        });
        SeekBar sB2 = (SeekBar)settingsDialog.findViewById(R.id.seekBar2);
        sB2.setProgress(seekBarValue2);
        sB2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                minChanged = true;
                seekBarValue2 = progress + 10;
                String dim = Integer.toString(seekBarValue2) + "%";
                sb2Status.setText(dim);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }
        });
        Button cancelButton = (Button)settingsDialog.findViewById(R.id.cancel_button);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                settingsDialog.cancel();
            }
        });
        settingsDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                savedLightSet = getSharedPreferences(savedLightSettings, Context.MODE_PRIVATE);
                if(medChanged) editor.putInt("seekbarvalue1", seekBarValue1);
                if(minChanged) editor.putInt("seekbarvalue2", seekBarValue2);

                editor.apply();

                if(medChanged && minChanged) {
                    AlertDialog.Builder add = new AlertDialog.Builder(LightingHallway.this);
                    add.setTitle("Suksess");
                    add.setMessage("Medium og minimum dimmeverdi er endret");
                    add.setCancelable(false);
                    add.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    add.create();
                    add.show();
                }
                else if(medChanged) {
                    AlertDialog.Builder add = new AlertDialog.Builder(LightingHallway.this);
                    add.setTitle("Suksess");
                    add.setMessage("Medium dimmeverdi er endret");
                    add.setCancelable(false);
                    add.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    add.create();
                    add.show();
                }
                else if (minChanged) {
                    AlertDialog.Builder add = new AlertDialog.Builder(LightingHallway.this);
                    add.setTitle("Suksess");
                    add.setMessage("Minimum dimmeverdi er endret");
                    add.setCancelable(false);
                    add.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    add.create();
                    add.show();
                }
            }
        });
        settingsDialog.show();
    }
}