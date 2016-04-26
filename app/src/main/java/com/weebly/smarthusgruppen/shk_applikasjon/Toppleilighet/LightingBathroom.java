package com.weebly.smarthusgruppen.shk_applikasjon.Toppleilighet;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.weebly.smarthusgruppen.shk_applikasjon.R;

public class LightingBathroom extends AppCompatActivity {

    public static final String savedLight = "SavedLightingBathroom";
    public static final String savedTemp = "1SavedTemperature";
    public static final String savedColor = "SavedBackgroundColor";

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

    boolean lmin2 = false;
    boolean lmed2 = false;
    boolean lmax2 = false;

    boolean lmin3 = false;
    boolean lmed3 = false;
    boolean lmax3 = false;

    boolean lmin4 = false;
    boolean lmed4 = false;
    boolean lmax4 = false;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lighting_bathroom);

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

            String modeStatus1, modeStatus2, modeStatus3, modeStatus4, modeStatus5,
                    modeStatsAll;
            switch (iMode) {
                case 1:
                    modeStatus1 = lightSettings.getString("Hrow1status", "0");
                    modeStatus2 = lightSettings.getString("Hrow2status", "0");
                    modeStatus3 = lightSettings.getString("Hrow3status", "0");
                    modeStatus4 = lightSettings.getString("Hrow4status", "0");
                    modeStatus5 = lightSettings.getString("Hrow5status", "0");
                  //  modeStatus6 = lightSettings.getString("Hrow6status", "0");
                    modeStatsAll = lightSettings.getString("Hrowallstatus", "0");
                    break;
                case 2:
                    modeStatus1 = lightSettings.getString("Drow1status", "0");
                    modeStatus2 = lightSettings.getString("Drow2status", "0");
                    modeStatus3 = lightSettings.getString("Drow3status", "0");
                    modeStatus4 = lightSettings.getString("Drow4status", "0");
                    modeStatus5 = lightSettings.getString("Drow5status", "0");
                  //  modeStatus6 = lightSettings.getString("Drow6status", "0");
                    modeStatsAll = lightSettings.getString("Drowallstatus", "0");
                    break;
                case 3:
                    modeStatus1 = lightSettings.getString("Nrow1status", "0");
                    modeStatus2 = lightSettings.getString("Nrow2status", "0");
                    modeStatus3 = lightSettings.getString("Nrow3status", "0");
                    modeStatus4 = lightSettings.getString("Nrow4status", "0");
                    modeStatus5 = lightSettings.getString("Nrow5status", "0");
                  //  modeStatus6 = lightSettings.getString("Nrow6status", "0");
                    modeStatsAll = lightSettings.getString("Nrowallstatus", "0");
                    break;
                case 4:
                    modeStatus1 = lightSettings.getString("Arow1status", "0");
                    modeStatus2 = lightSettings.getString("Arow2status", "0");
                    modeStatus3 = lightSettings.getString("Arow3status", "0");
                    modeStatus4 = lightSettings.getString("Arow4status", "0");
                    modeStatus5 = lightSettings.getString("Arow5status", "0");
                  //  modeStatus6 = lightSettings.getString("Arow6status", "0");
                    modeStatsAll = lightSettings.getString("Arowallstatus", "0");
                    break;
                default:
                    modeStatus1 = "0";
                    modeStatus2 = "0";
                    modeStatus3 = "0";
                    modeStatus4 = "0";
                    modeStatus5 = "0";
                  //  modeStatus6 = "0";
                    modeStatsAll = "0";
                    break;
            }

            int sAll = Integer.parseInt(modeStatsAll);
            int s1 = Integer.parseInt(modeStatus1);
            int s2 = Integer.parseInt(modeStatus2);
            int s3 = Integer.parseInt(modeStatus3);
            int s4 = Integer.parseInt(modeStatus4);
            int s5 = Integer.parseInt(modeStatus5);
          //  int s6 = Integer.parseInt(modeStatus6);

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

            switch (s3){
                case 0: //row 3 off
                    lightOffBtn2.setChecked(true);
                    /*
                    lmin = false;
                    lmed = false;
                    lmax = false;
                    */
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
                    /*
                    lmin = false;
                    lmed = false;
                    lmax = false;
                    */
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
                    /*
                    lmin = false;
                    lmed = false;
                    lmax = false;
                    */
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
            /*
            switch (s6){
                case 0: //row 6 off
                    lightOffBtn5.setChecked(true);
                    lmin = false;
                    lmed = false;
                    lmax = false;
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
            */
            lightOffBtn6.setChecked(true);
            loff6 = true;
            /*
            switch (sAll) {
                case 0: //row all off
                    lightOffBtn6.setChecked(true);
                    loff6 = true;
                    /*
                    lmin = false;
                    lmed = false;
                    lmax = false;

                    break;
                case 1: //row all min
                    lmin6 = true;
                    lightMinBtn6.setChecked(true);
                    lightOffBtn6.setChecked(false);
                    break;
                case 2: //row all med
                    lmed6 = true;
                    lightMedBtn6.setChecked(true);
                    lightOffBtn6.setChecked(false);
                    break;
                case 3: //row all max
                    lmax6 = true;

                    lightMaxBtn6.setChecked(true);
                    lightOffBtn6.setChecked(false);
                    break;
                default: //not set
                    lightOffBtn6.setChecked(true);
                    loff6 = true;
                    break;
            }
        */
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
            }
            else if (lmax) {
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

    public void allBoolFalse()  {
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

    public void allButtonsOff1() {
        lightMinBtn1.setChecked(false);
        lightMedBtn1.setChecked(false);
        lightMaxBtn1.setChecked(false);
        lightOffBtn1.setChecked(false);
    }

    public void allBoolFalse1()  {
        lmin1 = false;
        lmed1 = false;
        lmax1 = false;
    }

    /* next 3rd row */

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

    // all lights on minimum
    protected View.OnClickListener light_all_min2 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!lmin2) {
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

    // all lights on medium
    protected View.OnClickListener light_all_med2 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!lmed1) {
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
                setLightSettingsForRow(row3, lightOff);
            }
        }
    };

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

    public void allButtonsOff2() {
        lightMinBtn2.setChecked(false);
        lightMedBtn2.setChecked(false);
        lightMaxBtn2.setChecked(false);
        lightOffBtn2.setChecked(false);
    }

    public void allBoolFalse2()  {
        lmin2 = false;
        lmed2 = false;
        lmax2 = false;
    }

     /* next 4th row */

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

    // all lights on minimum
    protected View.OnClickListener light_all_min3 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!lmin3) {
                MainActivity.sendText("Command:000002117,1,1");     // all lights minimum
                lmin3 = true;
                lmed3 = false;
                lmax3 = false;

                lightMedBtn3.setChecked(false);
                lightMaxBtn3.setChecked(false);
                lightOffBtn3.setChecked(false);
                setLightSettingsForRow(row4, lightMin);
            } else if (lmin3) {
                MainActivity.sendText("Command:000002117,1,0");     // all lights off
                allBoolFalse3();
                allButtonsOff3();
                lightOffBtn3.setChecked(true);
                setLightSettingsForRow(row4, lightOff);

            }
        }
    };

    // all lights on medium
    protected View.OnClickListener light_all_med3 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!lmed3) {
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

    public void allButtonsOff3() {
        lightMinBtn3.setChecked(false);
        lightMedBtn3.setChecked(false);
        lightMaxBtn3.setChecked(false);
        lightOffBtn3.setChecked(false);
    }

    public void allBoolFalse3()  {
        lmin3 = false;
        lmed3 = false;
        lmax3 = false;
    }

      /* next 5th row */

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

    // all lights on minimum
    protected View.OnClickListener light_all_min4 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!lmin4) {
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

    // all lights on medium
    protected View.OnClickListener light_all_med4 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!lmed4) {
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

    public void allButtonsOff4() {
        lightMinBtn4.setChecked(false);
        lightMedBtn4.setChecked(false);
        lightMaxBtn4.setChecked(false);
        lightOffBtn4.setChecked(false);
    }

    public void allBoolFalse4()  {
        lmin4 = false;
        lmed4 = false;
        lmax4 = false;
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
            setLightSettingsForAllOff();

            if(loff6) {
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
                lightMinBtn2.setChecked(true);
                lightMinBtn3.setChecked(true);
                lightMinBtn4.setChecked(true);


                lmin = true;
                lmin1 = true;
                lmin2 = true;
                lmin3 = true;
                lmin4 = true;

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
                lightMedBtn2.setChecked(true);
                lightMedBtn3.setChecked(true);
                lightMedBtn4.setChecked(true);


                lmed = true;
                lmed1 = true;
                lmed2 = true;
                lmed3 = true;
                lmed4 = true;

               setLightSettingsForAllMed();

            }
            else if (lmed6) {
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
                lightMaxBtn2.setChecked(true);
                lightMaxBtn3.setChecked(true);
                lightMaxBtn4.setChecked(true);


                lmax = true;
                lmax1 = true;
                lmax2 = true;
                lmax3 = true;
                lmax4 = true;

                setLightSettingsForAllMax();
            }
            else if (lmax6) {
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

    public void allBoolFalse6()  {
        lmin6 = false;
        lmed6 = false;
        lmax6 = false;
    }

    public void allButtonOffKill() {
        allButtonsOff();
        allButtonsOff1();
        allButtonsOff2();
        allButtonsOff3();
        allButtonsOff4();


        allBoolFalse();
        allBoolFalse1();
        allBoolFalse2();
        allBoolFalse3();
        allBoolFalse4();

    }

    public void offSwitchoff() {
        lightOffBtn.setChecked(true);
        lightOffBtn1.setChecked(true);
        lightOffBtn2.setChecked(true);
        lightOffBtn3.setChecked(true);
        lightOffBtn4.setChecked(true);

    }

    public void setLightSettingsForAllOff() {
        setLightSettingsForRow(rowall, lightOff);
        setLightSettingsForRow(row1, lightOff);
        setLightSettingsForRow(row2, lightOff);
        setLightSettingsForRow(row3, lightOff);
        setLightSettingsForRow(row4, lightOff);
        setLightSettingsForRow(row5, lightOff);
    }

    public void setLightSettingsForAllMin() {
        setLightSettingsForRow(rowall, lightMin);
        setLightSettingsForRow(row1, lightMin);
        setLightSettingsForRow(row2, lightMin);
        setLightSettingsForRow(row3, lightMin);
        setLightSettingsForRow(row4, lightMin);
        setLightSettingsForRow(row5, lightMin);
    }

    public void setLightSettingsForAllMed() {
        setLightSettingsForRow(rowall, lightMed);
        setLightSettingsForRow(row1, lightMed);
        setLightSettingsForRow(row2, lightMed);
        setLightSettingsForRow(row3, lightMed);
        setLightSettingsForRow(row4, lightMed);
        setLightSettingsForRow(row5, lightMed);
    }

    public void setLightSettingsForAllMax() {
        setLightSettingsForRow(rowall, lightMax);
        setLightSettingsForRow(row1, lightMax);
        setLightSettingsForRow(row2, lightMax);
        setLightSettingsForRow(row3, lightMax);
        setLightSettingsForRow(row4, lightMax);
        setLightSettingsForRow(row5, lightMax);
    }


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

        sharedpreferences = getSharedPreferences(savedColor, Context.MODE_PRIVATE);

        int value1 = sharedpreferences.getInt("value1", 0);
        int value2 = sharedpreferences.getInt("value2", 0);
        int value3 = sharedpreferences.getInt("value3", 0);
        int value4 = sharedpreferences.getInt("set", 0);
        if(value4 != 0){
            View v = findViewById(R.id.ScrollView01);
            v.setBackgroundColor(Color.rgb(value1, value3, value2));
            this.setContentView(v);
        }
    }


}
