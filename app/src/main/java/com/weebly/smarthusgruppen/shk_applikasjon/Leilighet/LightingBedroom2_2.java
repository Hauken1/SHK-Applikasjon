package com.weebly.smarthusgruppen.shk_applikasjon.Leilighet;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.weebly.smarthusgruppen.shk_applikasjon.R;
/**
 * Controls all the lights for bedroom 2 Light settings based on mode. Therse settings are
 * also saved in shared preferences
 */
public class LightingBedroom2_2 extends AppCompatActivity {

    public static final String savedLight = "SavedLightingBedroom2_2";
    public static final String savedTemp = "1SavedTemperature_2";
    public static final String savedColor = "SavedBackgroundColor_2";
    public static final String savedLightSettings = "SavedLightSettings_Bedroom2_2";

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

    public static final String DAY = "Dag";
    public static final String NIGHT = "Natt";
    public static final String AWAY = "Borte";
    public static final String HOLIDAY = "Ferie";

    public static final int iDAY = 2;
    public static final int iNIGHT = 3;
    public static final int iAWAY = 4;
    public static final int iHOLIDAY = 1;

    TextView mode_View;

    boolean lmin = false;
    boolean lmed = false;
    boolean lmax = false;

    ToggleButton lightOffBtn;
    ToggleButton lightMinBtn;
    ToggleButton lightMedBtn;
    ToggleButton lightMaxBtn;
    ImageButton homeBtn;

    @Override
    /**
     * runs on startup and sets up gui and displays light settings dependent on current mode
     * @param savedInstanceState
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lighting_bedroom2_2);
        setupGUI();
        displayLight();
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
                    AlertDialog.Builder add = new AlertDialog.Builder(LightingBedroom2_2.this);
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
                    AlertDialog.Builder add = new AlertDialog.Builder(LightingBedroom2_2.this);
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
                    AlertDialog.Builder add = new AlertDialog.Builder(LightingBedroom2_2.this);
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

        lightOffBtn = (ToggleButton) findViewById(R.id.toggle_0);
        lightOffBtn.setOnClickListener(light_all__off_Listener);

        lightMinBtn = (ToggleButton) findViewById(R.id.toggle_30);
        lightMinBtn.setOnClickListener(light_all_min);

        lightMedBtn = (ToggleButton) findViewById(R.id.toggle_70);
        lightMedBtn.setOnClickListener(light_all_med);

        lightMaxBtn = (ToggleButton) findViewById(R.id.toggle_100);
        lightMaxBtn.setOnClickListener(light_all_Listener);

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

            allBoolFalse();
            allButtonsOff();

            String modeStatus1;
            switch (iMode) {
                case 1:
                    modeStatus1 = lightSettings.getString("Hrow1status", "0");
                    break;
                case 2:
                    modeStatus1 = lightSettings.getString("Drow1status", "0");
                    break;
                case 3:
                    modeStatus1 = lightSettings.getString("Nrow1status", "0");
                    break;
                case 4:
                    modeStatus1 = lightSettings.getString("Arow1status", "0");
                    break;
                default:
                    modeStatus1 = "0";
                    break;
            }

            int s1 = Integer.parseInt(modeStatus1);

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
                MainActivity_2.sendText("Command:000002117,1,0");
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
                MainActivity_2.sendText("Command:000002117,1,1");     // all lights minimum
                lmin = true;
                lmed = false;
                lmax = false;

                lightMedBtn.setChecked(false);
                lightMaxBtn.setChecked(false);
                lightOffBtn.setChecked(false);
                setLightSettingsForRow(row1, lightMin);
            } else if (lmin) {
                MainActivity_2.sendText("Command:000002117,1,0");     // all lights off
                allBoolFalse();
                allButtonsOff();
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
                MainActivity_2.sendText("Command:000002117,1,2");     // all lights medium
                lmin = false;
                lmed = true;
                lmax = false;

                lightMinBtn.setChecked(false);
                lightMaxBtn.setChecked(false);
                lightOffBtn.setChecked(false);
                setLightSettingsForRow(row1, lightMed);
            }
            else if (lmed) {
                MainActivity_2.sendText("Command:000002117,1,0"); // all lights off
                allBoolFalse();
                allButtonsOff();
                setLightSettingsForRow(row1, lightOff);
            }
        }
    };
    /**
     * turns light on to max setting. toggles off other buttons. if already set turns off light
     */
    // all lights on max
    protected View.OnClickListener light_all_Listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!lmax) {        //this method should only be called if there is a connection.
                MainActivity_2.sendText("Command:000002117,1,3"); // all lights to max
                lmin = false;
                lmed = false;
                lmax = true;

                lightMinBtn.setChecked(false);
                lightMedBtn.setChecked(false);
                lightOffBtn.setChecked(false);
                setLightSettingsForRow(row1, lightMax);
            }
            else if (lmax) {
                MainActivity_2.sendText("Command:000002117,1,0");     // all lights off
                allBoolFalse();

                allButtonsOff();
                setLightSettingsForRow(row1, lightOff);
            }
        }
    };

    /**
     * sets all toggle buttons to off
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
     * sends the user to the mainActivity menu
     */
    public void goToHome() {
        Intent intent = new Intent(this, MainActivity_2.class);
        startActivity(intent);
    }
}
