package com.weebly.smarthusgruppen.shk_applikasjon.Toppleilighet;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.weebly.smarthusgruppen.shk_applikasjon.R;
/**
 * Controls all the lights for the kitchen Light settings based on mode. Therse settings are
 * also saved in shared preferences
 */
public class LightingKitchen extends AppCompatActivity {

    public static final String savedLight = "SavedLightingKitchen_1" ;
    public static final String savedTemp = "1SavedTemperature_1";
    public static final String savedColor = "SavedBackgroundColor_1";
    public static final String savedLightSettings = "SavedLightSettings_Kitchen_1";

    SharedPreferences sharedpreferences;
    public SharedPreferences tempSetting;
    public SharedPreferences lightSettings;
    public SharedPreferences savedLightSet;


    boolean lmin = false;
    boolean lmed = false;
    boolean lmax = false;

    boolean lmin1 = false;
    boolean lmed1 = false;
    boolean lmax1 = false;

    boolean lmin2 = false;
    boolean lmed2 = false;
    boolean lmax2 = false;

    boolean lmin3 = false;
    boolean lmed3 = false;
    boolean lmax3 = false;

    boolean lmin4 = false;
    boolean lmed4 = false;
    boolean lmax4 = false;

    boolean lmin5 = false;
    boolean lmed5 = false;
    boolean lmax5 = false;

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

    ToggleButton lightOffBtn2;
    ToggleButton lightMinBtn2;
    ToggleButton lightMedBtn2;
    ToggleButton lightMaxBtn2;

    ToggleButton lightOffBtn3;
    ToggleButton lightMinBtn3;
    ToggleButton lightMedBtn3;
    ToggleButton lightMaxBtn3;

    ToggleButton lightOffBtn4;
    ToggleButton lightMinBtn4;
    ToggleButton lightMedBtn4;
    ToggleButton lightMaxBtn4;

    ToggleButton lightOffBtn5;
    ToggleButton lightMinBtn5;
    ToggleButton lightMaxBtn5;
    ToggleButton lightMedBtn5;

    ToggleButton lightOffBtn6;
    ToggleButton lightMinBtn6;
    ToggleButton lightMaxBtn6;
    ToggleButton lightMedBtn6;

    ImageButton homeBtn;
    ImageButton light_setttings_button;
    TextView mode_View;

    String sMode;
    int iMode;

    private int seekBarValue1;
    private int seekBarValue2;

    String minDimvalue = "30";
    String medDimValue = "70";

    Boolean medChanged;
    Boolean minChanged;

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

    public static final String DAY = "Dag";
    public static final String NIGHT = "Natt";
    public static final String AWAY = "Borte";
    public static final String HOLIDAY = "Ferie";

    public static final int iDAY = 2;
    public static final int iNIGHT = 3;
    public static final int iAWAY = 4;
    public static final int iHOLIDAY = 1;

    /**
     * runs on startup and sets up gui and displays light settings dependent on current mode
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lighting_kitchen);
        setupGUI();
        displayLight();
    }
    /**
     * displays light settings dependent on which mode the house is in. saves all these variables
     * in sharedpreferences
     */
    public void displayLight() {
        try {

            lightSettings = getSharedPreferences(savedLight, 0);
            tempSetting = getSharedPreferences(savedTemp, 0);
            sMode = tempSetting.getString("mode", "2");
            iMode = Integer.parseInt(sMode);

            allButtonOffKill();

            String modeStatus1, modeStatus2, modeStatus3, modeStatus4, modeStatus5,
                modeStatus6, modeStatsAll;
            switch (iMode) {
                case 1:
                    modeStatus1 = lightSettings.getString("Hrow1status", "0");
                    modeStatus2 = lightSettings.getString("Hrow2status", "0");
                    modeStatus3 = lightSettings.getString("Hrow3status", "0");
                    modeStatus4 = lightSettings.getString("Hrow4status", "0");
                    modeStatus5 = lightSettings.getString("Hrow5status", "0");
                    modeStatus6 = lightSettings.getString("Hrow6status", "0");
                    modeStatsAll = lightSettings.getString("Hrowallstatus", "0");
                    break;
                case 2:
                    modeStatus1 = lightSettings.getString("Drow1status", "0");
                    modeStatus2 = lightSettings.getString("Drow2status", "0");
                    modeStatus3 = lightSettings.getString("Drow3status", "0");
                    modeStatus4 = lightSettings.getString("Drow4status", "0");
                    modeStatus5 = lightSettings.getString("Drow5status", "0");
                    modeStatus6 = lightSettings.getString("Drow6status", "0");
                    modeStatsAll = lightSettings.getString("Drowallstatus", "0");
                    break;
                case 3:
                    modeStatus1 = lightSettings.getString("Nrow1status", "0");
                    modeStatus2 = lightSettings.getString("Nrow2status", "0");
                    modeStatus3 = lightSettings.getString("Nrow3status", "0");
                    modeStatus4 = lightSettings.getString("Nrow4status", "0");
                    modeStatus5 = lightSettings.getString("Nrow5status", "0");
                    modeStatus6 = lightSettings.getString("Nrow6status", "0");
                    modeStatsAll = lightSettings.getString("Nrowallstatus", "0");
                    break;
                case 4:
                    modeStatus1 = lightSettings.getString("Arow1status", "0");
                    modeStatus2 = lightSettings.getString("Arow2status", "0");
                    modeStatus3 = lightSettings.getString("Arow3status", "0");
                    modeStatus4 = lightSettings.getString("Arow4status", "0");
                    modeStatus5 = lightSettings.getString("Arow5status", "0");
                    modeStatus6 = lightSettings.getString("Arow6status", "0");
                    modeStatsAll = lightSettings.getString("Arowallstatus", "0");
                    break;
                default:
                    modeStatus1 = "0";
                    modeStatus2 = "0";
                    modeStatus3 = "0";
                    modeStatus4 = "0";
                    modeStatus5 = "0";
                    modeStatus6 = "0";
                    modeStatsAll = "0";
                    break;
            }

            int sAll = Integer.parseInt(modeStatsAll);
            int s1 = Integer.parseInt(modeStatus1);
            int s2 = Integer.parseInt(modeStatus2);
            int s3 = Integer.parseInt(modeStatus3);
            int s4 = Integer.parseInt(modeStatus4);
            int s5 = Integer.parseInt(modeStatus5);
            int s6 = Integer.parseInt(modeStatus6);

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

            switch (s3){
                case 0: //row 3 off
                    lightOffBtn2.setChecked(true);
                    break;
                case 1: //row 3 min
                    lmin2 = true;
                    lightMinBtn2.setChecked(true);
                    break;
                case 2: //row 3 med
                    lmed2 = true;
                    lightMedBtn2.setChecked(true);
                    break;
                case 3: //row 3 max
                    lmax2 = true;
                    lightMaxBtn2.setChecked(true);
                    break;
                default: //not set
                    lightOffBtn2.setChecked(true);
                    break;
            }

            switch (s4){
                case 0: //row 4 off
                    lightOffBtn3.setChecked(true);
                    break;
                case 1: //row 4 min
                    lmin3 = true;
                    lightMinBtn3.setChecked(true);
                    break;
                case 2: //row 4 med
                    lmed3 = true;
                    lightMedBtn3.setChecked(true);
                    break;
                case 3: //row 4 max
                    lmax3 = true;
                    lightMaxBtn3.setChecked(true);
                    break;
                default: //not set
                    lightOffBtn3.setChecked(true);
                    break;
            }

            switch (s5){
                case 0: //row 5 off
                    lightOffBtn4.setChecked(true);
                    break;
                case 1: //row 5 min
                    lmin4 = true;
                    lightMinBtn4.setChecked(true);
                    break;
                case 2: //row 5 med
                    lmed4 = true;
                    lightMedBtn4.setChecked(true);
                    break;
                case 3: //row 5 max
                    lmax4 = true;
                    lightMaxBtn4.setChecked(true);
                    break;
                default: //not set
                    lightOffBtn4.setChecked(true);
                    break;
            }
            switch (s6){
                case 0: //row 6 off
                    lightOffBtn5.setChecked(true);
                    break;
                case 1: //row 6 min
                    lmin5 = true;
                    lightMinBtn5.setChecked(true);
                    break;
                case 2: //row 6 med
                    lmed5 = true;
                    lightMedBtn5.setChecked(true);
                    break;
                case 3: //row 6 max
                    lmax5 = true;
                    lightMaxBtn5.setChecked(true);
                    break;
                default: //not set
                    lightOffBtn5.setChecked(true);
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

                setLightSettingsForRow(row1,lightMin);
            }

            else if (lmin) {
                MainActivity.sendText("Command:000002117,1,0");     // all lights off
                allBoolFalse();
                allButtonsOff();
                lightOffBtn.setChecked(true);
                setLightSettingsForRow(row1,lightOff);
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
                setLightSettingsForRow(row1,lightMed);
            }

            else if (lmed) {
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
                setLightSettingsForRow(row1,lightMax);
            }

            else if (lmax) {
                MainActivity.sendText("Command:000002117,1,0");     // all lights off
                allBoolFalse();
                allButtonsOff();
                lightOffBtn.setChecked(true);
                setLightSettingsForRow(row1,lightOff);
            }
        }
    };

    /**
     * toggles all buttons to off
     */
    public void allButtonsOff() {
        lightMinBtn.setChecked(false);
        lightMedBtn.setChecked(false);
        lightMaxBtn.setChecked(false);
        lightOffBtn.setChecked(false);
    }

    /**
     * sets all bool to false
     */
    public void allBoolFalse()  {
        lmin = false;
        lmed = false;
        lmax = false;
    }

    /**
     * sends the user to the MainActivity menu
     */
    public void goToHome() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


    /* next 2nd row */
    /**
     * turns off all lights and sets the other toggle buttons to off.
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
            }
            else if (lmed1) {
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
            }
            else if (lmax1) {
                MainActivity.sendText("Command:000002117,1,0");     // all lights off
                allBoolFalse1();
                allButtonsOff1();
                lightOffBtn1.setChecked(true);
                setLightSettingsForRow(row2, lightOff);
            }
        }
    };

    /**
     * toggles all buttons to off
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
    public void allBoolFalse1()  {
        lmin1 = false;
        lmed1 = false;
        lmax1 = false;
    }

    /* next 3rd row */
    /**
     * turns off all lights and sets the other toggle buttons to off.
     */
    // all lights off
    protected View.OnClickListener light_all__off_Listener2 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (lmin2 || lmed2 || lmax2) {

                MainActivity.sendText("Command:000002117,1,0");
                lmin2 = false;
                lmed2 = false;
                lmax2 = false;

                lightMinBtn2.setChecked(false);
                lightMedBtn2.setChecked(false);
                lightMaxBtn2.setChecked(false);
                setLightSettingsForRow(row3, lightOff);
            }
        }
    };
    /**
     * turns light on to minimum setting. toggles off other buttons. if already set turns off light
     */
    // all lights on minimum
    protected View.OnClickListener light_all_min2 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!lmin2) {
                 /*
                *This is the right command, but the valid device number and channel is
                * not correct. This have to be set when the smart home is configuered.
                String dim = testForMinDimValueSet();
                MainActivity.sendText("Command:000049114,2," + dim + ",0,1");
                */
                MainActivity.sendText("Command:000002117,1,1");     // all lights minimum
                lmin2 = true;
                lmed2 = false;
                lmax2 = false;

                lightMedBtn2.setChecked(false);
                lightMaxBtn2.setChecked(false);
                lightOffBtn2.setChecked(false);
                setLightSettingsForRow(row3, lightMin);
            } else if (lmin2) {
                MainActivity.sendText("Command:000002117,1,0");     // all lights off
                allBoolFalse2();
                allButtonsOff2();
                lightOffBtn2.setChecked(true);
                setLightSettingsForRow(row3, lightOff);
            }
        }
    };
    /**
     * turns light on to medium setting. toggles off other buttons. if already set turns off light
     */
    // all lights on medium
    protected View.OnClickListener light_all_med2 = new View.OnClickListener() {
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
                lmin2 = false;
                lmed2 = true;
                lmax2 = false;

                lightMinBtn2.setChecked(false);
                lightMaxBtn2.setChecked(false);
                lightOffBtn2.setChecked(false);
                setLightSettingsForRow(row3, lightMed);
            }
            else if (lmed2) {
                MainActivity.sendText("Command:000002117,1,0"); // all lights off
                allBoolFalse2();
                allButtonsOff2();
                lightOffBtn2.setChecked(true);
                setLightSettingsForRow(row3,lightOff);
            }
        }
    };
    /**
     * turns light on to maximum setting. toggles off other buttons. if already set turns off light
     */
    // all lights on max
    protected View.OnClickListener light_all_Listener2 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!lmax2) {        //this method should only be called if there is a connection.
                MainActivity.sendText("Command:000002117,1,3"); // all lights to max
                lmin2 = false;
                lmed2 = false;
                lmax2 = true;

                lightMinBtn2.setChecked(false);
                lightMedBtn2.setChecked(false);
                lightOffBtn2.setChecked(false);
                setLightSettingsForRow(row3, lightMax);
            }
            else if (lmax2) {
                MainActivity.sendText("Command:000002117,1,0");     // all lights off
                allBoolFalse2();
                allButtonsOff2();
                lightOffBtn2.setChecked(true);
                setLightSettingsForRow(row3, lightOff);
            }
        }
    };

    /**
     * toggles all buttons off
     */
    public void allButtonsOff2() {
        lightMinBtn2.setChecked(false);
        lightMedBtn2.setChecked(false);
        lightMaxBtn2.setChecked(false);
        lightOffBtn2.setChecked(false);
    }

    /**
     * set all bool to false
     */
    public void allBoolFalse2()  {
        lmin2 = false;
        lmed2 = false;
        lmax2 = false;
    }

     /* next 4th row */
    /**
     * turns off all lights and sets the other toggle buttons to off.
     */
    // all lights off
    protected View.OnClickListener light_all__off_Listener3 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (lmin3 || lmed3 || lmax3) {
                MainActivity.sendText("Command:000002117,1,0");
                lmin3 = false;
                lmed3 = false;
                lmax3 = false;

                lightMinBtn3.setChecked(false);
                lightMedBtn3.setChecked(false);
                lightMaxBtn3.setChecked(false);
                setLightSettingsForRow(row4, lightOff);
            }
        }
    };
    /**
     * turns light on to minimum setting. toggles off other buttons. if already set turns off light
     */
    // all lights on minimum
    protected View.OnClickListener light_all_min3 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!lmin3) {
                 /*
                *This is the right command, but the valid device number and channel is
                * not correct. This have to be set when the smart home is configuered.
                String dim = testForMinDimValueSet();
                MainActivity.sendText("Command:000049114,2," + dim + ",0,1");
                */
                MainActivity.sendText("Command:000002117,1,1");     // all lights minimum
                lmin3 = true;
                lmed3 = false;
                lmax3 = false;

                lightMedBtn3.setChecked(false);
                lightMaxBtn3.setChecked(false);
                lightOffBtn3.setChecked(false);
                setLightSettingsForRow(row4,lightMin);
            } else if (lmin3) {
                MainActivity.sendText("Command:000002117,1,0");     // all lights off
                allBoolFalse3();
                allButtonsOff3();
                lightOffBtn3.setChecked(true);
                setLightSettingsForRow(row4, lightOff);
            }
        }
    };
    /**
     * turns light on to medium setting. toggles off other buttons. if already set turns off light
     */
    // all lights on medium
    protected View.OnClickListener light_all_med3 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!lmed3) {
                    /*
                *This is the right command, but the valid device number and channel is
                * not correct. This have to be set when the smart home is configuered.
                String dim = testForMedDimValueSet();
                MainActivity.sendText("Command:000049114,2," + dim + ",0,1");
                */
                MainActivity.sendText("Command:000002117,1,2");     // all lights medium
                lmin3 = false;
                lmed3 = true;
                lmax3 = false;

                lightMinBtn3.setChecked(false);
                lightMaxBtn3.setChecked(false);
                lightOffBtn3.setChecked(false);
                setLightSettingsForRow(row4, lightMed);
            }
            else if (lmed3) {
                MainActivity.sendText("Command:000002117,1,0"); // all lights off
                allBoolFalse3();
                allButtonsOff3();
                lightOffBtn3.setChecked(true);
                setLightSettingsForRow(row4, lightOff);
            }
        }
    };
    /**
     * turns light on to maximum setting. toggles off other buttons. if already set turns off light
     */
    // all lights on max
    protected View.OnClickListener light_all_Listener3 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!lmax3) {        //this method should only be called if there is a connection.
                MainActivity.sendText("Command:000002117,1,3"); // all lights to max
                lmin3 = false;
                lmed3 = false;
                lmax3 = true;

                lightMinBtn3.setChecked(false);
                lightMedBtn3.setChecked(false);
                lightOffBtn3.setChecked(false);
                setLightSettingsForRow(row4, lightMax);
            }
            else if (lmax3) {
                MainActivity.sendText("Command:000002117,1,0");     // all lights off
                allBoolFalse3();
                allButtonsOff3();
                lightOffBtn3.setChecked(true);
                setLightSettingsForRow(row4, lightOff);
            }
        }
    };

    /**
     * sets all toggle to off
     */
    public void allButtonsOff3() {
        lightMinBtn3.setChecked(false);
        lightMedBtn3.setChecked(false);
        lightMaxBtn3.setChecked(false);
        lightOffBtn3.setChecked(false);
    }

    /**
     * sets all bool to false
     */
    public void allBoolFalse3()  {
        lmin3 = false;
        lmed3 = false;
        lmax3 = false;
    }

      /* next 5th row */
    /**
     * turns off all lights and sets the other toggle buttons to off.
     */
    // all lights off
    protected View.OnClickListener light_all__off_Listener4 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (lmin4 || lmed4 || lmax4) {
                MainActivity.sendText("Command:000002117,1,0");
                lmin4 = false;
                lmed4 = false;
                lmax4 = false;

                lightMinBtn4.setChecked(false);
                lightMedBtn4.setChecked(false);
                lightMaxBtn4.setChecked(false);

                setLightSettingsForRow(row5, lightOff);
            }
        }
    };
    /**
     * turns light on to minimum setting. toggles off other buttons. if already set turns off light
     */
    // all lights on minimum
    protected View.OnClickListener light_all_min4 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!lmin4) {
                   /*
                *This is the right command, but the valid device number and channel is
                * not correct. This have to be set when the smart home is configuered.
                String dim = testForMinDimValueSet();
                MainActivity.sendText("Command:000049114,2," + dim + ",0,1");
                */
                MainActivity.sendText("Command:000002117,1,1");     // all lights minimum
                lmin4 = true;
                lmed4 = false;
                lmax4 = false;

                lightMedBtn4.setChecked(false);
                lightMaxBtn4.setChecked(false);
                lightOffBtn4.setChecked(false);
                setLightSettingsForRow(row5, lightMin);
            } else if (lmin4) {
                MainActivity.sendText("Command:000002117,1,0");     // all lights off
                allBoolFalse4();
                allButtonsOff4();
                lightOffBtn4.setChecked(true);

                setLightSettingsForRow(row5, lightOff);
            }
        }
    };
    /**
     * turns light on to medium setting. toggles off other buttons. if already set turns off light
     */
    // all lights on medium
    protected View.OnClickListener light_all_med4 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!lmed4) {
                /*
                *This is the right command, but the valid device number and channel is
                * not correct. This have to be set when the smart home is configuered.
                String dim = testForMedDimValueSet();
                MainActivity.sendText("Command:000049114,2," + dim + ",0,1");
                */
                MainActivity.sendText("Command:000002117,1,2");     // all lights medium
                lmin4 = false;
                lmed4 = true;
                lmax4 = false;

                lightMinBtn4.setChecked(false);
                lightMaxBtn4.setChecked(false);
                lightOffBtn4.setChecked(false);
                setLightSettingsForRow(row5, lightMed);
            }
            else if (lmed4) {
                MainActivity.sendText("Command:000002117,1,0"); // all lights off
                allBoolFalse4();
                allButtonsOff4();
                lightOffBtn4.setChecked(true);
                setLightSettingsForRow(row5, lightOff);
            }
        }
    };
    /**
     * turns light on to maximum setting. toggles off other buttons. if already set turns off light
     */
    // all lights on max
    protected View.OnClickListener light_all_Listener4 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!lmax4) {        //this method should only be called if there is a connection.
                MainActivity.sendText("Command:000002117,1,3"); // all lights to max
                lmin4 = false;
                lmed4 = false;
                lmax4 = true;

                lightMinBtn4.setChecked(false);
                lightMedBtn4.setChecked(false);
                lightOffBtn4.setChecked(false);
                setLightSettingsForRow(row5, lightMax);
            }
            else if (lmax4) {
                MainActivity.sendText("Command:000002117,1,0");     // all lights off
                allBoolFalse4();
                allButtonsOff4();
                lightOffBtn4.setChecked(true);
                setLightSettingsForRow(row5, lightOff);
            }
        }
    };

    /**
     * sets all toggle button to off
     */
    public void allButtonsOff4() {
        lightMinBtn4.setChecked(false);
        lightMedBtn4.setChecked(false);
        lightMaxBtn4.setChecked(false);
        lightOffBtn4.setChecked(false);
    }

    /**
     * sets all bool to false
     */
    public void allBoolFalse4()  {
        lmin4 = false;
        lmed4 = false;
        lmax4 = false;
    }

       /* next 6th row */
    /**
     * turns off all lights and sets the other toggle buttons to off.
     */
    // all lights off
    protected View.OnClickListener light_all__off_Listener5 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (lmin5 || lmed5 || lmax5) {
                MainActivity.sendText("Command:000002117,1,0");
                lmin5 = false;
                lmed5 = false;
                lmax5 = false;

                lightMinBtn5.setChecked(false);
                lightMedBtn5.setChecked(false);
                lightMaxBtn5.setChecked(false);
                setLightSettingsForRow(row6, lightOff);
            }
        }
    };
    /**
     * turns light on to minimum setting. toggles off other buttons. if already set turns off light
     */
    // all lights on minimum
    protected View.OnClickListener light_all_min5 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!lmin5) {
                   /*
                *This is the right command, but the valid device number and channel is
                * not correct. This have to be set when the smart home is configuered.
                String dim = testForMinDimValueSet();
                MainActivity.sendText("Command:000049114,2," + dim + ",0,1");
                */
                MainActivity.sendText("Command:000002117,1,1");     // all lights minimum
                lmin5 = true;
                lmed5 = false;
                lmax5 = false;

                lightMedBtn5.setChecked(false);
                lightMaxBtn5.setChecked(false);
                lightOffBtn5.setChecked(false);
                setLightSettingsForRow(row6, lightMin);
            } else if (lmin5) {
                MainActivity.sendText("Command:000002117,1,0");     // all lights off
                allBoolFalse5();
                allButtonsOff5();
                lightOffBtn5.setChecked(true);

                setLightSettingsForRow(row6, lightOff);
            }
        }
    };
    /**
     * turns light on to medium setting. toggles off other buttons. if already set turns off light
     */
    // all lights on medium
    protected View.OnClickListener light_all_med5 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!lmed5) {
                    /*
                *This is the right command, but the valid device number and channel is
                * not correct. This have to be set when the smart home is configuered.
                String dim = testForMedDimValueSet();
                MainActivity.sendText("Command:000049114,2," + dim + ",0,1");
                */
                MainActivity.sendText("Command:000002117,1,2");     // all lights medium
                lmin5 = false;
                lmed5 = true;
                lmax5 = false;

                lightMinBtn5.setChecked(false);
                lightMaxBtn5.setChecked(false);
                lightOffBtn5.setChecked(false);
                setLightSettingsForRow(row6, lightMed);
            }
            else if (lmed5) {
                MainActivity.sendText("Command:000002117,1,0"); // all lights off
                allBoolFalse5();
                allButtonsOff5();
                lightOffBtn5.setChecked(true);
                setLightSettingsForRow(row6, lightOff);
            }
        }
    };
    /**
     * turns light on to maximum setting. toggles off other buttons. if already set turns off light
     */
    // all lights on max
    protected View.OnClickListener light_all_Listener5 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!lmax5) {        //this method should only be called if there is a connection.
                MainActivity.sendText("Command:000002117,1,3"); // all lights to max
                lmin5 = false;
                lmed5 = false;
                lmax5 = true;

                lightMinBtn5.setChecked(false);
                lightMedBtn5.setChecked(false);
                lightOffBtn5.setChecked(false);
                setLightSettingsForRow(row6, lightMax);
            }
            else if (lmax5) {
                MainActivity.sendText("Command:000002117,1,0");     // all lights off
                allBoolFalse5();
                allButtonsOff5();
                lightOffBtn5.setChecked(true);
                setLightSettingsForRow(row6, lightOff);
            }
        }
    };

    /**
     * sets all toggle buttons to false
     */
    public void allButtonsOff5() {
        lightMinBtn5.setChecked(false);
        lightMedBtn5.setChecked(false);
        lightMaxBtn5.setChecked(false);
        lightOffBtn5.setChecked(false);
    }

    /**
     * sets all bool to false
     */
    public void allBoolFalse5()  {
        lmin5 = false;
        lmed5 = false;
        lmax5 = false;
    }


    /**
     * saves light settings for all rows to SharedPreferences
     * @param value off, min, med or max value for the light
     */
    public void setLightSettingForAllRow(int value){
        lightSettings = getSharedPreferences(savedLight, 0);
        SharedPreferences.Editor editor = lightSettings.edit();

        switch (iMode){
            case 1:
                switch (value){
                    case 0:
                        editor.putString("Hrowallstatus", "0");
                        editor.putString("Hrow1status", "0");
                        editor.putString("Hrow2status", "0");
                        editor.putString("Hrow3status", "0");
                        editor.putString("Hrow4status", "0");
                        editor.putString("Hrow5status", "0");
                        editor.putString("Hrow6status", "0");
                        editor.commit();
                        break;
                    case 1:
                        editor.putString("Hrowallstatus", "1");
                        editor.putString("Hrow1status", "1");
                        editor.putString("Hrow2status", "1");
                        editor.putString("Hrow3status", "1");
                        editor.putString("Hrow4status", "1");
                        editor.putString("Hrow5status", "1");
                        editor.putString("Hrow6status", "1");
                        editor.commit();
                        break;
                    case 2:
                        editor.putString("Hrowallstatus", "2");
                        editor.putString("Hrow1status", "2");
                        editor.putString("Hrow2status", "2");
                        editor.putString("Hrow3status", "2");
                        editor.putString("Hrow4status", "2");
                        editor.putString("Hrow5status", "2");
                        editor.putString("Hrow6status", "2");
                        editor.commit();
                        break;
                    case 3:
                        editor.putString("Hrowallstatus", "3");
                        editor.putString("Hrow1status", "3");
                        editor.putString("Hrow2status", "3");
                        editor.putString("Hrow3status", "3");
                        editor.putString("Hrow4status", "3");
                        editor.putString("Hrow5status", "3");
                        editor.putString("Hrow6status", "3");
                        editor.commit();
                        break;
                    default:
                        break;
                }
                break;
            case 2:
                switch (value){
                    case 0:
                        editor.putString("Drowallstatus", "0");
                        editor.putString("Drow1status", "0");
                        editor.putString("Drow2status", "0");
                        editor.putString("Drow3status", "0");
                        editor.putString("Drow4status", "0");
                        editor.putString("Drow5status", "0");
                        editor.putString("Drow6status", "0");
                        editor.commit();
                        break;
                    case 1:
                        editor.putString("Drowallstatus", "1");
                        editor.putString("Drow1status", "1");
                        editor.putString("Drow2status", "1");
                        editor.putString("Drow3status", "1");
                        editor.putString("Drow4status", "1");
                        editor.putString("Drow5status", "1");
                        editor.putString("Drow6status", "1");
                        editor.commit();
                        break;
                    case 2:
                        editor.putString("Drowallstatus", "2");
                        editor.putString("Drow1status", "2");
                        editor.putString("Drow2status", "2");
                        editor.putString("Drow3status", "2");
                        editor.putString("Drow4status", "2");
                        editor.putString("Drow5status", "2");
                        editor.putString("Drow6status", "2");
                        editor.commit();
                        break;
                    case 3:
                        editor.putString("Drowallstatus", "3");
                        editor.putString("Drow1status", "3");
                        editor.putString("Drow2status", "3");
                        editor.putString("Drow3status", "3");
                        editor.putString("Drow4status", "3");
                        editor.putString("Drow5status", "3");
                        editor.putString("Drow6status", "3");
                        editor.commit();
                        break;
                    default:
                        break;
                }
                break;
            case 3:
                switch (value){
                    case 0:
                        editor.putString("Nrowallstatus", "0");
                        editor.putString("Nrow1status", "0");
                        editor.putString("Nrow2status", "0");
                        editor.putString("Nrow3status", "0");
                        editor.putString("Nrow4status", "0");
                        editor.putString("Nrow5status", "0");
                        editor.putString("Nrow6status", "0");
                        editor.commit();
                        break;
                    case 1:
                        editor.putString("Nrowallstatus", "1");
                        editor.putString("Nrow1status", "1");
                        editor.putString("Nrow2status", "1");
                        editor.putString("Nrow3status", "1");
                        editor.putString("Nrow4status", "1");
                        editor.putString("Nrow5status", "1");
                        editor.putString("Nrow6status", "1");
                        editor.commit();
                        break;
                    case 2:
                        editor.putString("Nrowallstatus", "2");
                        editor.putString("Nrow1status", "2");
                        editor.putString("Nrow2status", "2");
                        editor.putString("Nrow3status", "2");
                        editor.putString("Nrow4status", "2");
                        editor.putString("Nrow5status", "2");
                        editor.putString("Nrow6status", "2");
                        editor.commit();
                        break;
                    case 3:
                        editor.putString("Nrowallstatus", "3");
                        editor.putString("Nrow1status", "3");
                        editor.putString("Nrow2status", "3");
                        editor.putString("Nrow3status", "3");
                        editor.putString("Nrow4status", "3");
                        editor.putString("Nrow5status", "3");
                        editor.putString("Nrow6status", "3");
                        editor.commit();
                        break;
                    default:
                        break;
                }
                break;
            case 4:
                switch (value){
                    case 0:
                        editor.putString("Arowallstatus", "0");
                        editor.putString("Arow1status", "0");
                        editor.putString("Arow2status", "0");
                        editor.putString("Arow3status", "0");
                        editor.putString("Arow4status", "0");
                        editor.putString("Arow5status", "0");
                        editor.putString("Arow6status", "0");
                        editor.commit();
                        break;
                    case 1:
                        editor.putString("Arowallstatus", "1");
                        editor.putString("Arow1status", "1");
                        editor.putString("Arow2status", "1");
                        editor.putString("Arow3status", "1");
                        editor.putString("Arow4status", "1");
                        editor.putString("Arow5status", "1");
                        editor.putString("Arow6status", "1");
                        editor.commit();
                        break;
                    case 2:
                        editor.putString("Arowallstatus", "2");
                        editor.putString("Arow1status", "2");
                        editor.putString("Arow2status", "2");
                        editor.putString("Arow3status", "2");
                        editor.putString("Arow4status", "2");
                        editor.putString("Arow5status", "2");
                        editor.putString("Arow6status", "2");
                        editor.commit();
                        break;
                    case 3:
                        editor.putString("Arowallstatus", "3");
                        editor.putString("Arow1status", "3");
                        editor.putString("Arow2status", "3");
                        editor.putString("Arow3status", "3");
                        editor.putString("Arow4status", "3");
                        editor.putString("Arow5status", "3");
                        editor.putString("Arow6status", "3");
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
          /* next 6th row CONTROL ALL ABOVE LIGHTS */
    /**
     * turns off all lights and sets the other toggle buttons to off.
     */
    // all lights off
    protected View.OnClickListener light_all__off_Listener6 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            MainActivity.sendText("Command:000002117,1,0");

            setLightSettingForAllRow(lightOff);
            lmin6 = false;
            lmed6 = false;
            lmax6 = false;
            loff6 = true;

            lightMinBtn6.setChecked(false);
            lightMedBtn6.setChecked(false);
            lightMaxBtn6.setChecked(false);
            allButtonOffKill();
            offSwitchoff();


            if(loff6){
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
                * This method need to send single channel command for all lights.
                String dim = testForMinDimValueSet();
                MainActivity.sendText("Command:000049114,2," + dim + ",0,1");

                */
                MainActivity.sendText("Command:000002117,1,1");     // all lights minimum

                setLightSettingForAllRow(lightMin);
                lmin6 = true;
                lmed6 = false;
                lmax6 = false;

                lightMedBtn6.setChecked(false);
                lightMaxBtn6.setChecked(false);
                lightOffBtn6.setChecked(false);

                allButtonOffKill();

                lightMinBtn.setChecked(true);
                lightMinBtn1.setChecked(true);
                lightMinBtn2.setChecked(true);
                lightMinBtn3.setChecked(true);
                lightMinBtn4.setChecked(true);
                lightMinBtn5.setChecked(true);

                lmin = true;
                lmin1 = true;
                lmin2 = true;
                lmin3 = true;
                lmin4 = true;
                lmin5 = true;

            } else if (lmin6) {
                MainActivity.sendText("Command:000002117,1,0");     // all lights off

                setLightSettingForAllRow(lightOff);
                allBoolFalse6();
                allButtonsOff6();
                allButtonOffKill();
                offSwitchoff();
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

                setLightSettingForAllRow(lightMed);
                lmin6 = false;
                lmed6 = true;
                lmax6 = false;

                lightMinBtn6.setChecked(false);
                lightMaxBtn6.setChecked(false);
                lightOffBtn6.setChecked(false);

                allButtonOffKill();
                lightMedBtn.setChecked(true);
                lightMedBtn1.setChecked(true);
                lightMedBtn2.setChecked(true);
                lightMedBtn3.setChecked(true);
                lightMedBtn4.setChecked(true);
                lightMedBtn5.setChecked(true);

                lmed = true;
                lmed1 = true;
                lmed2 = true;
                lmed3 = true;
                lmed4 = true;
                lmed5 = true;

            }
            else if (lmed6) {
                MainActivity.sendText("Command:000002117,1,0"); // all lights off

                setLightSettingForAllRow(lightOff);
                allBoolFalse6();
                allButtonsOff6();
                allButtonOffKill();
                offSwitchoff();
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

                setLightSettingForAllRow(lightMax);
                lmin6 = false;
                lmed6 = false;
                lmax6 = true;

                lightMinBtn6.setChecked(false);
                lightMedBtn6.setChecked(false);
                lightOffBtn6.setChecked(false);

                allButtonOffKill();
                lightMaxBtn.setChecked(true);
                lightMaxBtn1.setChecked(true);
                lightMaxBtn2.setChecked(true);
                lightMaxBtn3.setChecked(true);
                lightMaxBtn4.setChecked(true);
                lightMaxBtn5.setChecked(true);

                lmax = true;
                lmax1 = true;
                lmax2 = true;
                lmax3 = true;
                lmax4 = true;
                lmax5 = true;

            }
            else if (lmax6) {
                MainActivity.sendText("Command:000002117,1,0");     // all lights off
                setLightSettingForAllRow(lightOff);
                allBoolFalse6();

                allButtonsOff6();
                allButtonOffKill();
                offSwitchoff();
            }
        }
    };

    /**
     * toggles all buttons off
     */
    public void allButtonsOff6() {
        lightMinBtn6.setChecked(false);
        lightMedBtn6.setChecked(false);
        lightMaxBtn6.setChecked(false);
        lightOffBtn6.setChecked(false);
    }

    /**
     * sets all bool to false
     */
    public void allBoolFalse6()  {
        lmin6 = false;
        lmed6 = false;
        lmax6 = false;
    }

    /**
     * toggles of all buttons and sets all bool to false
     */
    public void allButtonOffKill() {
        allButtonsOff();
        allButtonsOff1();
        allButtonsOff2();
        allButtonsOff3();
        allButtonsOff4();
        allButtonsOff5();

        allBoolFalse();
        allBoolFalse1();
        allBoolFalse2();
        allBoolFalse3();
        allBoolFalse4();
        allBoolFalse5();
    }

    /**
     * toggles all the off buttons to true
     */
    public void offSwitchoff() {
        lightOffBtn.setChecked(true);
        lightOffBtn1.setChecked(true);
        lightOffBtn2.setChecked(true);
        lightOffBtn3.setChecked(true);
        lightOffBtn4.setChecked(true);
        lightOffBtn5.setChecked(true);
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

    /**
     * Setting intensity value for medium and minimum light settings
     */
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
            /**
             *  When user moves the seekbar the value is saved.
             * @param seekBar seekbar sent
             * @param progress progress is the value on the seekbar
             * @param fromUser if its changed by the user or not
             */
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                medChanged = true;
                seekBarValue1 = progress + 30;
                String dim = Integer.toString(seekBarValue1) + "%";
                sb1Status.setText(dim);

            }

            /**
             * onStartTrackingTouch
             * @param seekBar seekbar sent
             */
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            /**
             * onStopTrackingTouch
             * @param seekBar seekbar sent
             */
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
            /**
             * alerting the user that the seekbar value has been successfully changed
             * @param dialog the alert window
             */
            @Override
            public void onCancel(DialogInterface dialog) {
                savedLightSet = getSharedPreferences(savedLightSettings, Context.MODE_PRIVATE);
                if(medChanged) editor.putInt("seekbarvalue1", seekBarValue1);
                if(minChanged) editor.putInt("seekbarvalue2", seekBarValue2);
                editor.apply();

                if(medChanged && minChanged) {
                    AlertDialog.Builder add = new AlertDialog.Builder(LightingKitchen.this);
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
                    AlertDialog.Builder add = new AlertDialog.Builder(LightingKitchen.this);
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
                    AlertDialog.Builder add = new AlertDialog.Builder(LightingKitchen.this);
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


        lightOffBtn2 = (ToggleButton) findViewById(R.id.toggle_02);
        lightOffBtn2.setOnClickListener(light_all__off_Listener2);

        lightMinBtn2 = (ToggleButton) findViewById(R.id.toggle_302);
        lightMinBtn2.setOnClickListener(light_all_min2);

        lightMedBtn2 = (ToggleButton) findViewById(R.id.toggle_702);
        lightMedBtn2.setOnClickListener(light_all_med2);

        lightMaxBtn2 = (ToggleButton) findViewById(R.id.toggle_1002);
        lightMaxBtn2.setOnClickListener(light_all_Listener2);


        lightOffBtn3 = (ToggleButton) findViewById(R.id.toggle_03);
        lightOffBtn3.setOnClickListener(light_all__off_Listener3);

        lightMinBtn3 = (ToggleButton) findViewById(R.id.toggle_303);
        lightMinBtn3.setOnClickListener(light_all_min3);

        lightMedBtn3 = (ToggleButton) findViewById(R.id.toggle_703);
        lightMedBtn3.setOnClickListener(light_all_med3);

        lightMaxBtn3 = (ToggleButton) findViewById(R.id.toggle_1003);
        lightMaxBtn3.setOnClickListener(light_all_Listener3);

        lightOffBtn4 = (ToggleButton) findViewById(R.id.toggle_04);
        lightOffBtn4.setOnClickListener(light_all__off_Listener4);

        lightMinBtn4 = (ToggleButton) findViewById(R.id.toggle_304);
        lightMinBtn4.setOnClickListener(light_all_min4);

        lightMedBtn4 = (ToggleButton) findViewById(R.id.toggle_704);
        lightMedBtn4.setOnClickListener(light_all_med4);

        lightMaxBtn4 = (ToggleButton) findViewById(R.id.toggle_1004);
        lightMaxBtn4.setOnClickListener(light_all_Listener4);

        lightOffBtn5 = (ToggleButton) findViewById(R.id.toggle_05);
        lightOffBtn5.setOnClickListener(light_all__off_Listener5);

        lightMinBtn5 = (ToggleButton) findViewById(R.id.toggle_305);
        lightMinBtn5.setOnClickListener(light_all_min5);

        lightMedBtn5 = (ToggleButton) findViewById(R.id.toggle_705);
        lightMedBtn5.setOnClickListener(light_all_med5);

        lightMaxBtn5 = (ToggleButton) findViewById(R.id.toggle_1005);
        lightMaxBtn5.setOnClickListener(light_all_Listener5);

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
            public void onClick(View v)  {
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
}
