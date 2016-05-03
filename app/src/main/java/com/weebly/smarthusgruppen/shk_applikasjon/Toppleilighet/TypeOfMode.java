package com.weebly.smarthusgruppen.shk_applikasjon.Toppleilighet;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.ToggleButton;

import org.w3c.dom.Text;
import java.util.Calendar;
import com.weebly.smarthusgruppen.shk_applikasjon.R;

public class TypeOfMode extends AppCompatActivity {
    public static final String savedTemp1 = "1SavedTemperature_1";
    public static final String savedTemp2 = "2SavedTemperature_1";
    public static final String savedTemp3 = "3SavedTemperature_1";
    public static final String savedTemp4 = "4SavedTemperature_1";
    public static final String savedTemp5 = "5SavedTemperature_1";
    public static final String savedTemp6 = "6SavedTemperature_1";

    public static final String holidayTime = "SavedHolidayTime_1";

    public static final String savedVent = "SavedVentilation_1" ;
    public static final String savedLightKitchen = "SavedLightingKitchen_1";
    public static final String savedLightBathroom = "SavedLightingBathroom_1";
    public static final String savedLightBedroom1 = "SavedLightingBedroom1_1";
    public static final String savedLightBedroom2 = "SavedLightingBedroom2_1";
    public static final String savedLightHallway = "SavedLightingHallway_1";
    public static final String savedLightLivingroom = "SavedLightingLivingRoom_1";
    public static final String savedLightOffice = "SavedLightingOffice_1";
    public static final String savedColor = "SavedBackgroundColor_1";
    public static final String savedDayNight = "SavedDayNightSettings_1";

    SharedPreferences sharedpreferences;

    SharedPreferences ventInfo;
    SharedPreferences lightSettings;

    private String holidayMode = "1";
    private String dayMode = "2";
    private String nightMode = "3";
    private String awayMode = "4";

    boolean isDay = false;
    boolean isNight = false;
    boolean isAway = false;
    boolean isHoliday = false;

    ImageButton homeBtn;
    ToggleButton dayBtn;
    ToggleButton nightBtn;
    ToggleButton awayBtn;
    ToggleButton holidayBtn;
    ImageButton settings;

    Handler gHandler;

    private DatePicker datePicker;
    private TimePicker timePicker;
    private Calendar calendar;
    private int year, month, day, hour, min;

    String mTextValue;
    Character mLastChar = '\0'; // init with empty character
    int mKeyDel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_of_mode);

        gHandler = new Handler();
        setUpGUI();
        modeSetup();
    }

    public void modeSetup(){
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        min = calendar.get(Calendar.MINUTE);


        //Checks what mode the the heatingcontrollers are set to.
        //All heatingcontrollolers are set to the same mode
        sharedpreferences = getSharedPreferences(savedTemp1, Context.MODE_PRIVATE);
        String currentMode = sharedpreferences.getString("mode", "2");
        int cM = Integer.parseInt(currentMode);
        switch (cM) {
            case 1:
                dayBtn.setChecked(false);
                nightBtn.setChecked(false);
                awayBtn.setChecked(false);
                holidayBtn.setChecked(true);
                break;
            case 2:
                dayBtn.setChecked(true);
                nightBtn.setChecked(false);
                awayBtn.setChecked(false);
                holidayBtn.setChecked(false);
                break;
            case 3:
                dayBtn.setChecked(false);
                nightBtn.setChecked(true);
                awayBtn.setChecked(false);
                holidayBtn.setChecked(false);
                break;
            case 4:
                dayBtn.setChecked(false);
                nightBtn.setChecked(false);
                awayBtn.setChecked(true);
                holidayBtn.setChecked(false);
                break;
            default:
                dayBtn.setChecked(true);
                nightBtn.setChecked(false);
                awayBtn.setChecked(false);
                holidayBtn.setChecked(false);
                break;
        }

        sharedpreferences = getSharedPreferences(holidayTime, Context.MODE_PRIVATE);
        int yYear = sharedpreferences.getInt("year", 1992);
        int yMonth = sharedpreferences.getInt("month", 1992);
        int yDay = sharedpreferences.getInt("day", 1992);
        int hHour = sharedpreferences.getInt("hour", 1992);
        int hMin = sharedpreferences.getInt("minute", 1992);
    }
    public void setUpGUI() {
        //home button
        homeBtn = (ImageButton) findViewById(R.id.home_button);
        homeBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goToHome();
            }
        });

        dayBtn = (ToggleButton) findViewById(R.id.toggle_day);
        dayBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                day();
            }
        });

        nightBtn = (ToggleButton) findViewById(R.id.toggle_night);
        nightBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                night();
            }
        });

        awayBtn = (ToggleButton) findViewById(R.id.toggle_away);
        awayBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                away();
            }
        });

        holidayBtn = (ToggleButton) findViewById(R.id.toggle_holiday);
        holidayBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                holiday();
            }
        });

        settings = (ImageButton) findViewById(R.id.settings_mode);
        settings.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                settingsView();
            }
        });

        sharedpreferences = getSharedPreferences(savedColor, Context.MODE_PRIVATE);

        int value1 = sharedpreferences.getInt("value1", 0);
        int value2 = sharedpreferences.getInt("value2", 0);
        int value3 = sharedpreferences.getInt("value3", 0);
        int value4 = sharedpreferences.getInt("set", 0);
        if(value4 != 0){
            View v = findViewById(R.id.type_of_mode_id);
            v.setBackgroundColor(Color.rgb(value1, value3, value2));
            setContentView(v);
        }

    }

    public void goToHome() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void settingsView() {
        sharedpreferences = getSharedPreferences(savedDayNight, Context.MODE_PRIVATE);

        Dialog settingsDialog = new Dialog(this);
        settingsDialog.setContentView(R.layout.settings_modus);
        settingsDialog.setCancelable(true);
        final EditText dayText = (EditText)settingsDialog.findViewById(R.id.editText_day);
        final EditText nightText = (EditText)settingsDialog.findViewById(R.id.editText_night);
        TextView setDay = (TextView)settingsDialog.findViewById(R.id.set_day);
        TextView setNight = (TextView)settingsDialog.findViewById(R.id.set_night);
        dayText.setInputType(InputType.TYPE_CLASS_NUMBER);
        nightText.setInputType(InputType.TYPE_CLASS_NUMBER);
        InputFilter[] filter1 = new InputFilter[1];
        filter1[0] = new InputFilter.LengthFilter(5);
        dayText.setFilters(filter1);
        nightText.setFilters(filter1);
        dayText.addTextChangedListener(new TimeTextWatcher(dayText));
        nightText.addTextChangedListener(new TimeTextWatcher(nightText));

        String sDay = Integer.toString(sharedpreferences.getInt("dayhour", 0)) + ":"
                    + Integer.toString(sharedpreferences.getInt("daymin",0));
        String sNight = Integer.toString(sharedpreferences.getInt("nighthour", 0)) + ":"
                    + Integer.toString(sharedpreferences.getInt("nightmin",0));
        setDay.setText(sDay);
        setNight.setText(sNight);

                settingsDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        sharedpreferences = getSharedPreferences(savedDayNight, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        int hDay = 0, mDay = 0, hNight = 0, mNight = 0;
                        boolean gotTimeDay, gotTimeNight, daySet = false, nightSet = false;
                        String getDayTime = dayText.getText().toString();
                        gotTimeDay = getDayTime.isEmpty();
                        if (getDayTime.length() < 5)
                            gotTimeDay = true;
                        if (!gotTimeDay) {
                            hDay = Integer.parseInt(getDayTime.substring(0, 2));
                            mDay = Integer.parseInt(getDayTime.substring(3, getDayTime.length()));
                        }
                        if (hDay < 25 && mDay < 60 && !gotTimeDay) {
                            editor.putInt("dayhour", hDay);
                            editor.putInt("daymin", mDay);
                            editor.putInt("dayset", 1);
                            editor.commit();
                            daySet = true;
                        }
                        String getNightTime = nightText.getText().toString();
                        gotTimeNight = getNightTime.isEmpty();
                        if (getNightTime.length() < 5)
                            gotTimeNight = true;
                        if (!gotTimeNight) {
                            hNight = Integer.parseInt(getNightTime.substring(0, 2));
                            mNight = Integer.parseInt(getNightTime.substring(3, getNightTime.length()));
                        }
                        if (hNight < 25 && mNight < 60 && !gotTimeNight) {
                            editor.putInt("nighthour", hNight);
                            editor.putInt("nightmin", mNight);
                            editor.putInt("nightset", 1);
                            editor.commit();
                            nightSet = true;
                        }
                        if (nightSet && daySet) {
                            MainActivity.sendText("DayNightSetT:" + "B" + "," + hDay + "," + mDay + "," + hNight + ","
                                    + mNight);
                            AlertDialog.Builder add = new AlertDialog.Builder(TypeOfMode.this);
                            add.setTitle("Suksess");
                            add.setMessage("Tid for når modus skifter til natt og dag er endret.");
                            add.setCancelable(false);
                            add.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                            add.create();
                            add.show();
                        } else if (daySet) {
                            MainActivity.sendText("DayNightSetT:" + "D" + "," + hDay + "," + mDay);
                            AlertDialog.Builder add = new AlertDialog.Builder(TypeOfMode.this);
                            add.setTitle("");
                            add.setMessage("Suksess."
                                    + "\nTiden for når modus skifter til dag er endret."
                                    + "\n\nVerdiene for når det skiftes til natt, er ikke lagret.");
                            add.setCancelable(false);
                            add.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                            add.create();
                            add.show();
                        } else if (nightSet) {
                            MainActivity.sendText("DayNightSetT:" + "N" + "," + hNight + "," + mNight);
                            AlertDialog.Builder add = new AlertDialog.Builder(TypeOfMode.this);
                            add.setTitle("Suksess");
                            add.setMessage("Tiden for når modus skifter til natt er endret"
                                    + "\n\nVerdiene for når det skiftes til dag, er ikke lagret");
                            add.setCancelable(false);
                            add.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                            add.create();
                            add.show();
                        } else {
                            AlertDialog.Builder add = new AlertDialog.Builder(TypeOfMode.this);
                            add.setTitle("Feil");
                            add.setMessage("Noe gikk galt."
                                    + "\nSjekk verdiene og prøv igjen.");
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

    public void day() {

        sharedpreferences = getSharedPreferences(savedTemp1, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor1 = sharedpreferences.edit();
        editor1.putString("mode",dayMode);
        editor1.commit();

        //Changing temperature for day mode for heating sone 1
        if (sharedpreferences.contains("channel")) {
            String channel1 = sharedpreferences.getString("channel", "1");
            String holiday1 = sharedpreferences.getString("holiday", "18");
            String day1 = sharedpreferences.getString("day", "23");
            String night1 = sharedpreferences.getString("night", "21");
            String away1 = sharedpreferences.getString("away", "19");
            String current1 = sharedpreferences.getString("current", "22");

            int currentTemp = Integer.parseInt(current1);
            int day = Integer.parseInt(day1);
            String wateringFlag1;
            if (currentTemp < day) wateringFlag1 = "1"; else wateringFlag1 = "0";

            MainActivity.sendText("Command:007260112," + channel1 + ",0,0," + dayMode + ","
                    + holiday1 + "," + day1 + "," + night1 + "," + away1 + "," + wateringFlag1 + ","
                    + "15");
        }

        sharedpreferences = getSharedPreferences(savedTemp2, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor2 = sharedpreferences.edit();
        editor2.putString("mode", dayMode);
        editor2.commit();
        //Changing temperature for day mode for heating sone 2
        if (sharedpreferences.contains("channel")) {
            String channel1 = sharedpreferences.getString("channel", "1");
            String holiday1 = sharedpreferences.getString("holiday", "18");
            String day1 = sharedpreferences.getString("day", "23");
            String night1 = sharedpreferences.getString("night", "21");
            String away1 = sharedpreferences.getString("away", "19");
            String current1 = sharedpreferences.getString("current", "22");

            int currentTemp = Integer.parseInt(current1);
            int day = Integer.parseInt(day1);
            String wateringFlag1;
            if (currentTemp < day) wateringFlag1 = "1"; else wateringFlag1 = "0";

            MainActivity.sendText("Command:007260112," + channel1 + ",0,0," + dayMode + ","
                    + holiday1 + "," + day1 + "," + night1 + "," + away1 + "," + wateringFlag1 + ","
                    + "15");
        }

        sharedpreferences = getSharedPreferences(savedTemp3, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor3 = sharedpreferences.edit();
        editor3.putString("mode",dayMode);
        editor3.commit();
        //Changing temperature for day mode for heating sone 3
        if (sharedpreferences.contains("channel")) {
            String channel1 = sharedpreferences.getString("channel", "1");
            String holiday1 = sharedpreferences.getString("holiday", "18");
            String day1 = sharedpreferences.getString("day", "23");
            String night1 = sharedpreferences.getString("night", "21");
            String away1 = sharedpreferences.getString("away", "19");
            String current1 = sharedpreferences.getString("current", "22");

            int currentTemp = Integer.parseInt(current1);
            int day = Integer.parseInt(day1);
            String wateringFlag1;
            if (currentTemp < day) wateringFlag1 = "1"; else wateringFlag1 = "0";

            MainActivity.sendText("Command:007260112," + channel1 + ",0,0," + dayMode + ","
                    + holiday1 + "," + day1 + "," + night1 + "," + away1 + "," + wateringFlag1 + ","
                    + "15");
        }
        sharedpreferences = getSharedPreferences(savedTemp4, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor4 = sharedpreferences.edit();
        editor4.putString("mode",dayMode);
        editor4.commit();
        //Changing temperature for day mode for heating sone 4
        if (sharedpreferences.contains("channel")) {
            String channel1 = sharedpreferences.getString("channel", "1");
            String holiday1 = sharedpreferences.getString("holiday", "18");
            String day1 = sharedpreferences.getString("day", "23");
            String night1 = sharedpreferences.getString("night", "21");
            String away1 = sharedpreferences.getString("away", "19");
            String current1 = sharedpreferences.getString("current", "22");

            int currentTemp = Integer.parseInt(current1);
            int day = Integer.parseInt(day1);
            String wateringFlag1;
            if (currentTemp < day) wateringFlag1 = "1"; else wateringFlag1 = "0";

            MainActivity.sendText("Command:007260112," + channel1 + ",0,0," + dayMode + ","
                    + holiday1 + "," + day1 + "," + night1 + "," + away1 + "," + wateringFlag1 + ","
                    + "15");
        }

        sharedpreferences = getSharedPreferences(savedTemp5, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor5 = sharedpreferences.edit();
        editor5.putString("mode",dayMode);
        editor5.commit();
        //Changing temperature for day mode for heating sone 5
        if (sharedpreferences.contains("channel")) {
            String channel1 = sharedpreferences.getString("channel", "1");
            String holiday1 = sharedpreferences.getString("holiday", "18");
            String day1 = sharedpreferences.getString("day", "23");
            String night1 = sharedpreferences.getString("night", "21");
            String away1 = sharedpreferences.getString("away", "19");
            String current1 = sharedpreferences.getString("current", "22");

            int currentTemp = Integer.parseInt(current1);
            int day = Integer.parseInt(day1);
            String wateringFlag1;
            if (currentTemp < day) wateringFlag1 = "1"; else wateringFlag1 = "0";

            MainActivity.sendText("Command:007260112," + channel1 + ",0,0," + dayMode + ","
                    + holiday1 + "," + day1 + "," + night1 + "," + away1 + "," + wateringFlag1 + ","
                    + "15");
        }

        sharedpreferences = getSharedPreferences(savedTemp6, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor6 = sharedpreferences.edit();
        editor6.putString("mode",dayMode);
        editor6.commit();
        //Changing temperature for day mode for heating sone 6
        if (sharedpreferences.contains("channel")) {
            String channel1 = sharedpreferences.getString("channel", "1");
            String holiday1 = sharedpreferences.getString("holiday", "18");
            String day1 = sharedpreferences.getString("day", "23");
            String night1 = sharedpreferences.getString("night", "21");
            String away1 = sharedpreferences.getString("away", "19");
            String current1 = sharedpreferences.getString("current", "22");

            int currentTemp = Integer.parseInt(current1);
            int day = Integer.parseInt(day1);
            String wateringFlag1;
            if (currentTemp < day) wateringFlag1 = "1"; else wateringFlag1 = "0";

            MainActivity.sendText("Command:007260112," + channel1 + ",0,0," + dayMode + ","
                    + holiday1 + "," + day1 + "," + night1 + "," + away1 + "," + wateringFlag1 + ","
                    + "15");
        }

        //Setting other buttons to false
        holidayBtn.setChecked(false);
        nightBtn.setChecked(false);
        awayBtn.setChecked(false);

        int dMode = Integer.parseInt(dayMode);

        //changing lighting to day mode
        lightSettings = getSharedPreferences(savedLightKitchen, Context.MODE_PRIVATE);
        getAndSendLightCommand(dMode);
        lightSettings = getSharedPreferences(savedLightBathroom, Context.MODE_PRIVATE);
        getAndSendLightCommand(dMode);
        lightSettings = getSharedPreferences(savedLightBedroom1, Context.MODE_PRIVATE);
        getAndSendLightCommand(dMode);
        lightSettings = getSharedPreferences(savedLightBedroom2, Context.MODE_PRIVATE);
        getAndSendLightCommand(dMode);
        lightSettings = getSharedPreferences(savedLightHallway, Context.MODE_PRIVATE);
        getAndSendLightCommand(dMode);
        lightSettings = getSharedPreferences(savedLightLivingroom, Context.MODE_PRIVATE);
        getAndSendLightCommand(dMode);
        lightSettings = getSharedPreferences(savedLightOffice, Context.MODE_PRIVATE);
        getAndSendLightCommand(dMode);

        //Changing ventilation to day mode
        sharedpreferences = getSharedPreferences(savedVent, Context.MODE_PRIVATE);
        sendVent1Command(dMode);
        sendVent2Command(dMode);
    }

    public void getAndSendLightCommand(int mode){

        String modeStatus1, modeStatus2, modeStatus3, modeStatus4, modeStatus5,
                modeStatus6, modeStatsAll;
        switch (mode) {
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

        sendLightCommand(s1,s2,s3,s4,s5,s6,sAll);
    }

    public void sendLightCommand(int s1, int s2, int s3, int s4, int s5, int s6, int sAll ){

        /*
        All these commands are temporary. All commands will be sent to the same three lights in the
        democase. This will be the case until all lighst and dimmers is installed in the
        smarthhome.

         */

        switch (s1){
            case 0: //row 1 off
                MainActivity.sendText("Command:000002117,1,0");
                break;
            case 1: //row 1 min
                MainActivity.sendText("Command:000002117,1,1");     // all lights minimum
                break;
            case 2: //row 2 med
                MainActivity.sendText("Command:000002117,1,2");     // all lights medium
                break;
            case 3: //row 1 max
                MainActivity.sendText("Command:000002117,1,3"); // all lights to max
                break;
            default:
                MainActivity.sendText("Command:000002117,1,0");     // all lights off
                break;
        }

        switch (s2){
            case 0: //row 2 off
                MainActivity.sendText("Command:000002117,1,0");
                break;
            case 1: //row 2 min
                MainActivity.sendText("Command:000002117,1,1");     // all lights minimum
                break;
            case 2: //row 2 med
                MainActivity.sendText("Command:000002117,1,2");     // all lights medium
                break;
            case 3: //row 2 max
                MainActivity.sendText("Command:000002117,1,3"); // all lights to max
                break;
            default: //not set
                MainActivity.sendText("Command:000002117,1,0");
                break;
        }

        switch (s3){
            case 0: //row 3 off
                MainActivity.sendText("Command:000002117,1,0");
                break;
            case 1: //row 3 min
                MainActivity.sendText("Command:000002117,1,1");     // all lights minimum
                break;
            case 2: //row 3 med
                MainActivity.sendText("Command:000002117,1,2");     // all lights medium
                break;
            case 3: //row 3 max
                MainActivity.sendText("Command:000002117,1,3"); // all lights to max
                break;
            default: //not set
                MainActivity.sendText("Command:000002117,1,0");
                break;
        }

        switch (s4){
            case 0: //row 4 off
                MainActivity.sendText("Command:000002117,1,0");
                break;
            case 1: //row 4 min
                MainActivity.sendText("Command:000002117,1,1");     // all lights minimum
                break;
            case 2: //row 4 med
                MainActivity.sendText("Command:000002117,1,2");     // all lights medium
                break;
            case 3: //row 4 max
                MainActivity.sendText("Command:000002117,1,3"); // all lights to max
                break;
            default: //not set
                MainActivity.sendText("Command:000002117,1,0");
                break;
        }

        switch (s5){
            case 0: //row 5 off
                MainActivity.sendText("Command:000002117,1,0");
                break;
            case 1: //row 5 min
                MainActivity.sendText("Command:000002117,1,1");     // all lights minimum
                break;
            case 2: //row 5 med
                MainActivity.sendText("Command:000002117,1,2");     // all lights medium
                break;
            case 3: //row 5 max
                MainActivity.sendText("Command:000002117,1,3"); // all lights to max
                break;
            default: //not set
                MainActivity.sendText("Command:000002117,1,0");
                break;
        }
        switch (s6){
            case 0: //row 6 off
                MainActivity.sendText("Command:000002117,1,0");
                break;
            case 1: //row 6 min
                MainActivity.sendText("Command:000002117,1,1");     // all lights minimum
                break;
            case 2: //row 6 med
                MainActivity.sendText("Command:000002117,1,2");     // all lights medium
                break;
            case 3: //row 6 max
                MainActivity.sendText("Command:000002117,1,3"); // all lights to max
                break;
            default: //not set
                MainActivity.sendText("Command:000002117,1,0");
                break;
        }

    }
    public void sendVent1Command(int mode) {

        /*
        All these commands are temporary. All commands will be sent to the same relé in the
        democase. This will be the case until all rele and ventilationssytems is installed in the
        smarthhome.
        */

        int s1 = 0;
        String status1;
        //Retrieving the right value based on mode
        switch (mode){
            case 1:
                status1 = sharedpreferences.getString("Hvent1status", "0");
                s1 = Integer.parseInt(status1);
                break;
            case 2:
                status1 = sharedpreferences.getString("Dvent1status", "0");
                s1 = Integer.parseInt(status1);
                break;
            case 3:
                status1 = sharedpreferences.getString("Nvent1status", "0");
                s1 = Integer.parseInt(status1);
                break;
            case 4:
                status1 = sharedpreferences.getString("Avent1status", "0");
                s1 = Integer.parseInt(status1);
                break;
        }

        //Sending commands for ventilation 1
        switch (s1) {
            case 0:
                MainActivity.sendText("Command:000002114,1,0");             // turns off 1 and 2
                break;
            case 1:
                MainActivity.sendText("Command:000049114,2,0,0,1");     // turns off level 2
                MainActivity.sendText("Command:000049114,1,100,0,1");   // turns on level 1
                break;
            case 2:
                MainActivity.sendText("Command:000049114,1,0,0,1");      // turns off level 1
                MainActivity.sendText("Command:000049114,2,100,0,1");     // turns on level 2
                break;
            case 3:
                MainActivity.sendText("Command:000002114,1,1");      // turns on level 1 and 2
                break;
            default:
                break;
        }

    }
    public void sendVent2Command(int mode) {

         /*
        All these commands are temporary. All commands will be sent to the same relé in the
        democase. This will be the case until all rele and ventilationssytems is installed in the
        smarthhome.
        */

        int s2 = 0;
        String status2;
        //Retrieving the right value based on mode
        switch (mode){
            case 1:
                status2 = sharedpreferences.getString("Hvent2status", "0");
                s2 = Integer.parseInt(status2);
                break;
            case 2:
                status2 = sharedpreferences.getString("Dvent2status", "0");
                s2 = Integer.parseInt(status2);
                break;
            case 3:
                status2 = sharedpreferences.getString("Nvent2status", "0");
                s2 = Integer.parseInt(status2);
                break;
            case 4:
                status2 = sharedpreferences.getString("Avent2status", "0");
                s2 = Integer.parseInt(status2);
                break;
        }

        //Sending commands for ventilation 2
        //Need to change these to match the right values of the Smarthome (subnet, adr)
        switch (s2) {
            case 0:
                MainActivity.sendText("Command:000002114,1,0");             // turns off 1 and 2
                break;
            case 1:
                MainActivity.sendText("Command:000049114,2,0,0,1");     // turns off level 2
                MainActivity.sendText("Command:000049114,1,100,0,1");   // turns on level 1
                break;
            case 2:
                MainActivity.sendText("Command:000049114,1,0,0,1");      // turns off level 1
                MainActivity.sendText("Command:000049114,2,100,0,1");     // turns on level 2
                break;
            case 3:
                MainActivity.sendText("Command:000002114,1,1");      // turns on level 1 and 2
                break;
            default:
                break;
        }

    }
    public void night() {

        sharedpreferences = getSharedPreferences(savedTemp1, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor1 = sharedpreferences.edit();
        editor1.putString("mode",nightMode);
        editor1.commit();

        //Changing temperature for night mode for heating sone 1
        if (sharedpreferences.contains("channel")) {
            String channel1 = sharedpreferences.getString("channel", "1");
            String holiday1 = sharedpreferences.getString("holiday", "18");
            String day1 = sharedpreferences.getString("day", "23");
            String night1 = sharedpreferences.getString("night", "21");
            String away1 = sharedpreferences.getString("away", "19");
            String current1 = sharedpreferences.getString("current", "22");

            int currentTemp = Integer.parseInt(current1);
            int night = Integer.parseInt(night1);
            String wateringFlag1;
            if (currentTemp < night) wateringFlag1 = "1"; else wateringFlag1 = "0";

            MainActivity.sendText("Command:007260112," + channel1 + ",0,0," + nightMode + ","
                    + holiday1 + "," + day1 + "," + night1 + "," + away1 + "," + wateringFlag1 + ","
                    + "15");
        }

        sharedpreferences = getSharedPreferences(savedTemp2, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor2 = sharedpreferences.edit();
        editor2.putString("mode", nightMode);
        editor2.commit();
        //Changing temperature for day mode for heating sone 2
        if (sharedpreferences.contains("channel")) {
            String channel1 = sharedpreferences.getString("channel", "1");
            String holiday1 = sharedpreferences.getString("holiday", "18");
            String day1 = sharedpreferences.getString("day", "23");
            String night1 = sharedpreferences.getString("night", "21");
            String away1 = sharedpreferences.getString("away", "19");
            String current1 = sharedpreferences.getString("current", "22");

            int currentTemp = Integer.parseInt(current1);
            int night = Integer.parseInt(night1);
            String wateringFlag1;
            if (currentTemp < night) wateringFlag1 = "1"; else wateringFlag1 = "0";

            MainActivity.sendText("Command:007260112," + channel1 + ",0,0," + nightMode + ","
                    + holiday1 + "," + day1 + "," + night1 + "," + away1 + "," + wateringFlag1 + ","
                    + "15");
        }

        sharedpreferences = getSharedPreferences(savedTemp3, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor3 = sharedpreferences.edit();
        editor3.putString("mode",nightMode);
        editor3.commit();
        //Changing temperature for day mode for heating sone 3
        if (sharedpreferences.contains("channel")) {
            String channel1 = sharedpreferences.getString("channel", "1");
            String holiday1 = sharedpreferences.getString("holiday", "18");
            String day1 = sharedpreferences.getString("day", "23");
            String night1 = sharedpreferences.getString("night", "21");
            String away1 = sharedpreferences.getString("away", "19");
            String current1 = sharedpreferences.getString("current", "22");

            int currentTemp = Integer.parseInt(current1);
            int night = Integer.parseInt(night1);
            String wateringFlag1;
            if (currentTemp < night) wateringFlag1 = "1"; else wateringFlag1 = "0";

            MainActivity.sendText("Command:007260112," + channel1 + ",0,0," + nightMode + ","
                    + holiday1 + "," + day1 + "," + night1 + "," + away1 + "," + wateringFlag1 + ","
                    + "15");
        }

        sharedpreferences = getSharedPreferences(savedTemp4, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor4 = sharedpreferences.edit();
        editor4.putString("mode",nightMode);
        editor4.commit();
        //Changing temperature for day mode for heating sone 4
        if (sharedpreferences.contains("channel")) {
            String channel1 = sharedpreferences.getString("channel", "1");
            String holiday1 = sharedpreferences.getString("holiday", "18");
            String day1 = sharedpreferences.getString("day", "23");
            String night1 = sharedpreferences.getString("night", "21");
            String away1 = sharedpreferences.getString("away", "19");
            String current1 = sharedpreferences.getString("current", "22");

            int currentTemp = Integer.parseInt(current1);
            int night = Integer.parseInt(night1);
            String wateringFlag1;
            if (currentTemp < night) wateringFlag1 = "1"; else wateringFlag1 = "0";

            MainActivity.sendText("Command:007260112," + channel1 + ",0,0," + nightMode + ","
                    + holiday1 + "," + day1 + "," + night1 + "," + away1 + "," + wateringFlag1 + ","
                    + "15");
        }

        sharedpreferences = getSharedPreferences(savedTemp5, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor5 = sharedpreferences.edit();
        editor5.putString("mode",nightMode);
        editor5.commit();
        //Changing temperature for day mode for heating sone 5
        if (sharedpreferences.contains("channel")) {
            String channel1 = sharedpreferences.getString("channel", "1");
            String holiday1 = sharedpreferences.getString("holiday", "18");
            String day1 = sharedpreferences.getString("day", "23");
            String night1 = sharedpreferences.getString("night", "21");
            String away1 = sharedpreferences.getString("away", "19");
            String current1 = sharedpreferences.getString("current", "22");

            int currentTemp = Integer.parseInt(current1);
            int night = Integer.parseInt(night1);
            String wateringFlag1;
            if (currentTemp < night) wateringFlag1 = "1"; else wateringFlag1 = "0";

            MainActivity.sendText("Command:007260112," + channel1 + ",0,0," + nightMode + ","
                    + holiday1 + "," + day1 + "," + night1 + "," + away1 + "," + wateringFlag1 + ","
                    + "15");
        }

        sharedpreferences = getSharedPreferences(savedTemp6, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor6 = sharedpreferences.edit();
        editor6.putString("mode",nightMode);
        editor6.commit();
        //Changing temperature for day mode for heating sone 6
        if (sharedpreferences.contains("channel")) {
            String channel1 = sharedpreferences.getString("channel", "1");
            String holiday1 = sharedpreferences.getString("holiday", "18");
            String day1 = sharedpreferences.getString("day", "23");
            String night1 = sharedpreferences.getString("night", "21");
            String away1 = sharedpreferences.getString("away", "19");
            String current1 = sharedpreferences.getString("current", "22");

            int currentTemp = Integer.parseInt(current1);
            int night = Integer.parseInt(night1);
            String wateringFlag1;
            if (currentTemp < night) wateringFlag1 = "1"; else wateringFlag1 = "0";

            MainActivity.sendText("Command:007260112," + channel1 + ",0,0," + nightMode + ","
                    + holiday1 + "," + day1 + "," + night1 + "," + away1 + "," + wateringFlag1 + ","
                    + "15");
        }

        //setting other buttons to false
        holidayBtn.setChecked(false);
        dayBtn.setChecked(false);
        awayBtn.setChecked(false);

        int dMode = Integer.parseInt(nightMode);
        //changing lighting to night mode
        lightSettings = getSharedPreferences(savedLightKitchen, Context.MODE_PRIVATE);
        getAndSendLightCommand(dMode);
        lightSettings = getSharedPreferences(savedLightBathroom, Context.MODE_PRIVATE);
        getAndSendLightCommand(dMode);
        lightSettings = getSharedPreferences(savedLightBedroom1, Context.MODE_PRIVATE);
        getAndSendLightCommand(dMode);
        lightSettings = getSharedPreferences(savedLightBedroom2, Context.MODE_PRIVATE);
        getAndSendLightCommand(dMode);
        lightSettings = getSharedPreferences(savedLightHallway, Context.MODE_PRIVATE);
        getAndSendLightCommand(dMode);
        lightSettings = getSharedPreferences(savedLightLivingroom, Context.MODE_PRIVATE);
        getAndSendLightCommand(dMode);
        lightSettings = getSharedPreferences(savedLightOffice, Context.MODE_PRIVATE);
        getAndSendLightCommand(dMode);

        //Changing ventilation to night mode
        sharedpreferences = getSharedPreferences(savedVent, Context.MODE_PRIVATE);
        sendVent1Command(dMode);
        sendVent2Command(dMode);

    }
    public void away(){

        sharedpreferences = getSharedPreferences(savedTemp1, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor1 = sharedpreferences.edit();
        editor1.putString("mode",awayMode);
        editor1.commit();

        //Changing temperature for away mode for heating sone 1
        if (sharedpreferences.contains("channel")) {
            String channel1 = sharedpreferences.getString("channel", "1");
            String holiday1 = sharedpreferences.getString("holiday", "18");
            String day1 = sharedpreferences.getString("day", "23");
            String night1 = sharedpreferences.getString("night", "21");
            String away1 = sharedpreferences.getString("away", "19");
            String current1 = sharedpreferences.getString("current", "22");

            int currentTemp = Integer.parseInt(current1);
            int away = Integer.parseInt(away1);
            String wateringFlag1;
            if (currentTemp < away) wateringFlag1 = "1"; else wateringFlag1 = "0";

            MainActivity.sendText("Command:007260112," + channel1 + ",0,0," + awayMode + ","
                    + holiday1 + "," + day1 + "," + night1 + "," + away1 + "," + wateringFlag1 + ","
                    + "15");
        }

        sharedpreferences = getSharedPreferences(savedTemp2, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor2 = sharedpreferences.edit();
        editor2.putString("mode", awayMode);
        editor2.commit();
        //Changing temperature for away mode for heating sone 2
        if (sharedpreferences.contains("channel")) {
            String channel1 = sharedpreferences.getString("channel", "1");
            String holiday1 = sharedpreferences.getString("holiday", "18");
            String day1 = sharedpreferences.getString("day", "23");
            String night1 = sharedpreferences.getString("night", "21");
            String away1 = sharedpreferences.getString("away", "19");
            String current1 = sharedpreferences.getString("current", "22");

            int currentTemp = Integer.parseInt(current1);
            int away = Integer.parseInt(away1);
            String wateringFlag1;
            if (currentTemp < away) wateringFlag1 = "1"; else wateringFlag1 = "0";

            MainActivity.sendText("Command:007260112," + channel1 + ",0,0," + awayMode + ","
                    + holiday1 + "," + day1 + "," + night1 + "," + away1 + "," + wateringFlag1 + ","
                    + "15");
        }

        sharedpreferences = getSharedPreferences(savedTemp3, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor3 = sharedpreferences.edit();
        editor3.putString("mode",awayMode);
        editor3.commit();
        //Changing temperature for away mode for heating sone 3
        if (sharedpreferences.contains("channel")) {
            String channel1 = sharedpreferences.getString("channel", "1");
            String holiday1 = sharedpreferences.getString("holiday", "18");
            String day1 = sharedpreferences.getString("day", "23");
            String night1 = sharedpreferences.getString("night", "21");
            String away1 = sharedpreferences.getString("away", "19");
            String current1 = sharedpreferences.getString("current", "22");

            int currentTemp = Integer.parseInt(current1);
            int away = Integer.parseInt(away1);
            String wateringFlag1;
            if (currentTemp < away) wateringFlag1 = "1"; else wateringFlag1 = "0";

            MainActivity.sendText("Command:007260112," + channel1 + ",0,0," + awayMode + ","
                    + holiday1 + "," + day1 + "," + night1 + "," + away1 + "," + wateringFlag1 + ","
                    + "15");
        }

        sharedpreferences = getSharedPreferences(savedTemp4, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor4 = sharedpreferences.edit();
        editor4.putString("mode",awayMode);
        editor4.commit();
        //Changing temperature for away mode for heating sone 4
        if (sharedpreferences.contains("channel")) {
            String channel1 = sharedpreferences.getString("channel", "1");
            String holiday1 = sharedpreferences.getString("holiday", "18");
            String day1 = sharedpreferences.getString("day", "23");
            String night1 = sharedpreferences.getString("night", "21");
            String away1 = sharedpreferences.getString("away", "19");
            String current1 = sharedpreferences.getString("current", "22");

            int currentTemp = Integer.parseInt(current1);
            int away = Integer.parseInt(away1);
            String wateringFlag1;
            if (currentTemp < away) wateringFlag1 = "1"; else wateringFlag1 = "0";

            MainActivity.sendText("Command:007260112," + channel1 + ",0,0," + awayMode + ","
                    + holiday1 + "," + day1 + "," + night1 + "," + away1 + "," + wateringFlag1 + ","
                    + "15");
        }

        sharedpreferences = getSharedPreferences(savedTemp5, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor5 = sharedpreferences.edit();
        editor5.putString("mode",awayMode);
        editor5.commit();
        //Changing temperature for away mode for heating sone 5
        if (sharedpreferences.contains("channel")) {
            String channel1 = sharedpreferences.getString("channel", "1");
            String holiday1 = sharedpreferences.getString("holiday", "18");
            String day1 = sharedpreferences.getString("day", "23");
            String night1 = sharedpreferences.getString("night", "21");
            String away1 = sharedpreferences.getString("away", "19");
            String current1 = sharedpreferences.getString("current", "22");

            int currentTemp = Integer.parseInt(current1);
            int away = Integer.parseInt(away1);
            String wateringFlag1;
            if (currentTemp < away) wateringFlag1 = "1"; else wateringFlag1 = "0";

            MainActivity.sendText("Command:007260112," + channel1 + ",0,0," + awayMode + ","
                    + holiday1 + "," + day1 + "," + night1 + "," + away1 + "," + wateringFlag1 + ","
                    + "15");
        }

        sharedpreferences = getSharedPreferences(savedTemp6, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor6 = sharedpreferences.edit();
        editor6.putString("mode",awayMode);
        editor6.commit();
        //Changing temperature for away mode for heating sone 6
        if (sharedpreferences.contains("channel")) {
            String channel1 = sharedpreferences.getString("channel", "1");
            String holiday1 = sharedpreferences.getString("holiday", "18");
            String day1 = sharedpreferences.getString("day", "23");
            String night1 = sharedpreferences.getString("night", "21");
            String away1 = sharedpreferences.getString("away", "19");
            String current1 = sharedpreferences.getString("current", "22");

            int currentTemp = Integer.parseInt(current1);
            int away = Integer.parseInt(away1);
            String wateringFlag1;
            if (currentTemp < away) wateringFlag1 = "1"; else wateringFlag1 = "0";

            MainActivity.sendText("Command:007260112," + channel1 + ",0,0," + awayMode + ","
                    + holiday1 + "," + day1 + "," + night1 + "," + away1 + "," + wateringFlag1 + ","
                    + "15");
        }

        //setting other buttons to false
        holidayBtn.setChecked(false);
        dayBtn.setChecked(false);
        nightBtn.setChecked(false);

        int dMode = Integer.parseInt(awayMode);

        //changing lighting to away mode
        lightSettings = getSharedPreferences(savedLightKitchen, Context.MODE_PRIVATE);
        getAndSendLightCommand(dMode);
        lightSettings = getSharedPreferences(savedLightBathroom, Context.MODE_PRIVATE);
        getAndSendLightCommand(dMode);
        lightSettings = getSharedPreferences(savedLightBedroom1, Context.MODE_PRIVATE);
        getAndSendLightCommand(dMode);
        lightSettings = getSharedPreferences(savedLightBedroom2, Context.MODE_PRIVATE);
        getAndSendLightCommand(dMode);
        lightSettings = getSharedPreferences(savedLightHallway, Context.MODE_PRIVATE);
        getAndSendLightCommand(dMode);
        lightSettings = getSharedPreferences(savedLightLivingroom, Context.MODE_PRIVATE);
        getAndSendLightCommand(dMode);
        lightSettings = getSharedPreferences(savedLightOffice, Context.MODE_PRIVATE);
        getAndSendLightCommand(dMode);

        //Changing ventilation to away mode
        sharedpreferences = getSharedPreferences(savedVent, Context.MODE_PRIVATE);
        sendVent1Command(dMode);
        sendVent2Command(dMode);
    }

    final private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
             year = arg1;
             month = arg2;
             day = arg3;
            sharedpreferences = getSharedPreferences(holidayTime, Context.MODE_PRIVATE);
            SharedPreferences.Editor holidayEditor = sharedpreferences.edit();
            holidayEditor.putInt("year", arg1);
            holidayEditor.putInt("month", arg2);
            holidayEditor.putInt("day", arg3);
            holidayEditor.commit();
        }
    };

    final private TimePickerDialog.OnTimeSetListener myTimeSetListener =
            new TimePickerDialog.OnTimeSetListener() {
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    hour = hourOfDay;
                    min = minute;

                }
            };
    public void holiday() {

        sharedpreferences= getSharedPreferences(savedTemp1, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor1 = sharedpreferences.edit();
        editor1.putString("mode",holidayMode);
        editor1.commit();

        //Changing temperature for holiday mode for heating sone 1
        if(sharedpreferences.contains("channel")) {
            String channel1 = sharedpreferences.getString("channel", "1");
            String holiday1 = sharedpreferences.getString("holiday", "18");
            String day1 = sharedpreferences.getString("day", "23");
            String night1 = sharedpreferences.getString("night", "21");
            String away1 = sharedpreferences.getString("away", "19");
            String current1 = sharedpreferences.getString("current", "22");

            int currentTemp = Integer.parseInt(current1);
            int holiday = Integer.parseInt(holiday1);
            String wateringFlag1;
            if (currentTemp < holiday) wateringFlag1 = "1";
            else wateringFlag1 = "0";

            MainActivity.sendText("Command:007260112," + channel1 + ",0,0," + holidayMode + ","
                        + holiday1 + "," + day1 + "," + night1 + "," + away1 + "," + wateringFlag1 + ","
                        + "15");
        }

        sharedpreferences=getSharedPreferences(savedTemp2, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor2 = sharedpreferences.edit();
        editor2.putString("mode",holidayMode);
        editor2.commit();
        //Changing temperature for holiday mode for heating sone 2
        if(sharedpreferences.contains("channel"))

        {
            String channel1 = sharedpreferences.getString("channel", "1");
            String holiday1 = sharedpreferences.getString("holiday", "18");
            String day1 = sharedpreferences.getString("day", "23");
            String night1 = sharedpreferences.getString("night", "21");
            String away1 = sharedpreferences.getString("away", "19");
            String current1 = sharedpreferences.getString("current", "22");

            int currentTemp = Integer.parseInt(current1);
            int holiday = Integer.parseInt(holiday1);
            String wateringFlag1;
            if (currentTemp < holiday) wateringFlag1 = "1";
            else wateringFlag1 = "0";

            MainActivity.sendText("Command:007260112," + channel1 + ",0,0," + holidayMode + ","
                        + holiday1 + "," + day1 + "," + night1 + "," + away1 + "," + wateringFlag1 + ","
                        + "15");
        }

        sharedpreferences=getSharedPreferences(savedTemp3, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor3 = sharedpreferences.edit();
        editor3.putString("mode",holidayMode);
        editor3.commit();
        //Changing temperature for holiday mode for heating sone 3
        if(sharedpreferences.contains("channel"))

        {
            String channel1 = sharedpreferences.getString("channel", "1");
            String holiday1 = sharedpreferences.getString("holiday", "18");
            String day1 = sharedpreferences.getString("day", "23");
            String night1 = sharedpreferences.getString("night", "21");
            String away1 = sharedpreferences.getString("away", "19");
            String current1 = sharedpreferences.getString("current", "22");
            int currentTemp = Integer.parseInt(current1);
            int holiday = Integer.parseInt(holiday1);
            String wateringFlag1;
            if (currentTemp < holiday) wateringFlag1 = "1";
            else wateringFlag1 = "0";
             MainActivity.sendText("Command:007260112," + channel1 + ",0,0," + holidayMode + ","
                        + holiday1 + "," + day1 + "," + night1 + "," + away1 + "," + wateringFlag1 + ","
                        + "15");
        }

        sharedpreferences=getSharedPreferences(savedTemp4, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor4 = sharedpreferences.edit();
        editor4.putString("mode",holidayMode);
        editor4.commit();
        //Changing temperature for holiday mode for heating sone 4
        if(sharedpreferences.contains("channel"))
        {
            String channel1 = sharedpreferences.getString("channel", "1");
            String holiday1 = sharedpreferences.getString("holiday", "18");
            String day1 = sharedpreferences.getString("day", "23");
            String night1 = sharedpreferences.getString("night", "21");
            String away1 = sharedpreferences.getString("away", "19");
            String current1 = sharedpreferences.getString("current", "22");

            int currentTemp = Integer.parseInt(current1);
            int holiday = Integer.parseInt(holiday1);
            String wateringFlag1;
            if (currentTemp < holiday) wateringFlag1 = "1";
            else wateringFlag1 = "0";

            MainActivity.sendText("Command:007260112," + channel1 + ",0,0," + holidayMode + ","
                    + holiday1 + "," + day1 + "," + night1 + "," + away1 + "," + wateringFlag1 + ","
                    + "15");
        }

        sharedpreferences=getSharedPreferences(savedTemp5, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor5 = sharedpreferences.edit();
        editor5.putString("mode",holidayMode);
        editor5.commit();
        //Changing temperature for holiday mode for heating sone 5
        if(sharedpreferences.contains("channel"))
        {
            String channel1 = sharedpreferences.getString("channel", "1");
            String holiday1 = sharedpreferences.getString("holiday", "18");
            String day1 = sharedpreferences.getString("day", "23");
            String night1 = sharedpreferences.getString("night", "21");
            String away1 = sharedpreferences.getString("away", "19");
            String current1 = sharedpreferences.getString("current", "22");
            int currentTemp = Integer.parseInt(current1);
            int holiday = Integer.parseInt(holiday1);
            String wateringFlag1;
            if (currentTemp < holiday) wateringFlag1 = "1";
            else wateringFlag1 = "0";

            MainActivity.sendText("Command:007260112," + channel1 + ",0,0," + holidayMode + ","
                    + holiday1 + "," + day1 + "," + night1 + "," + away1 + "," + wateringFlag1 + ","
                    + "15");
        }

        sharedpreferences=getSharedPreferences(savedTemp6, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor6 = sharedpreferences.edit();
        editor6.putString("mode",holidayMode);
        editor6.commit();
        //Changing temperature for holiday mode for heating sone 6
        if(sharedpreferences.contains("channel"))
        {
            String channel1 = sharedpreferences.getString("channel", "1");
            String holiday1 = sharedpreferences.getString("holiday", "18");
            String day1 = sharedpreferences.getString("day", "23");
            String night1 = sharedpreferences.getString("night", "21");
            String away1 = sharedpreferences.getString("away", "19");
            String current1 = sharedpreferences.getString("current", "22");
            int currentTemp = Integer.parseInt(current1);
            int holiday = Integer.parseInt(holiday1);
            String wateringFlag1;
            if (currentTemp < holiday) wateringFlag1 = "1";
            else wateringFlag1 = "0";
            MainActivity.sendText("Command:007260112," + channel1 + ",0,0," + holidayMode + ","
                    + holiday1 + "," + day1 + "," + night1 + "," + away1 + "," + wateringFlag1 + ","
                    + "15");
        }
        //setting other buttons to false
        nightBtn.setChecked(false);
        dayBtn.setChecked(false);
        awayBtn.setChecked(false);

        int dMode = Integer.parseInt(holidayMode);

        //changing lighting to holiday mode
        lightSettings = getSharedPreferences(savedLightKitchen, Context.MODE_PRIVATE);
        getAndSendLightCommand(dMode);
        lightSettings = getSharedPreferences(savedLightBathroom, Context.MODE_PRIVATE);
        getAndSendLightCommand(dMode);
        lightSettings = getSharedPreferences(savedLightBedroom1, Context.MODE_PRIVATE);
        getAndSendLightCommand(dMode);
        lightSettings = getSharedPreferences(savedLightBedroom2, Context.MODE_PRIVATE);
        getAndSendLightCommand(dMode);
        lightSettings = getSharedPreferences(savedLightHallway, Context.MODE_PRIVATE);
        getAndSendLightCommand(dMode);
        lightSettings = getSharedPreferences(savedLightLivingroom, Context.MODE_PRIVATE);
        getAndSendLightCommand(dMode);
        lightSettings = getSharedPreferences(savedLightOffice, Context.MODE_PRIVATE);
        getAndSendLightCommand(dMode);

        //Changing ventilation to holiday mode
        sharedpreferences = getSharedPreferences(savedVent, Context.MODE_PRIVATE);
        sendVent1Command(dMode);
        sendVent2Command(dMode);

        Thread hThread = new Thread(new Runnable() {
            public void run() {
                try {
                    gHandler.post(new Runnable() {
                                      @Override
                                      public void run() {
                                          sharedpreferences = getSharedPreferences(holidayTime, Context.MODE_PRIVATE);
                                          SharedPreferences.Editor holidayEditor = sharedpreferences.edit();
                                          holidayEditor.putInt("year", year);
                                          holidayEditor.putInt("month", month + 1);
                                          holidayEditor.putInt("day", day);
                                          holidayEditor.commit();
                                          AlertDialog.Builder add = new AlertDialog.Builder(TypeOfMode.this);
                                          add.setTitle("Feriemodus");
                                          add.setMessage("Vil du sette verdier for når du kommer tilbake fra ferie?" +
                                                  "\n\n" + "Disse verdiene vil bli lagret og huset vil settes til dag/natt modus" +
                                                  " når tiden er nådd.");
                                          add.setCancelable(false);
                                          add.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                                      @Override
                                                      public void onClick(DialogInterface dialog, int which) {
                                                          dialog.dismiss();

                                                          final DatePickerDialog dateDialog = new DatePickerDialog(TypeOfMode.this, myDateListener, year, month, day);
                                                          dateDialog.onDateChanged(dateDialog.getDatePicker(), year, month, day);
                                                          dateDialog.getDatePicker().init(year, month, day, new DatePicker.OnDateChangedListener() {
                                                              @Override
                                                              public void onDateChanged(DatePicker datePicker, int i, int i2, int i3) {
                                                                  calendar.set(i, i2, i3);
                                                                  sharedpreferences = getSharedPreferences(holidayTime, Context.MODE_PRIVATE);
                                                                  SharedPreferences.Editor holidayEditor = sharedpreferences.edit();
                                                                  holidayEditor.putInt("year", i);
                                                                  holidayEditor.putInt("month", i2 + 1);
                                                                  holidayEditor.putInt("day", i3);
                                                                  holidayEditor.commit();
                                                              }
                                                          });
                                                          dateDialog.show();
                                                          dateDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Lagre",
                                                                  new DialogInterface.OnClickListener() {
                                                                      public void onClick(DialogInterface dialog, int which) {
                                                                          dialog.dismiss();

                                                                          InputFilter[] filter1 = new InputFilter[1];
                                                                          filter1[0] = new InputFilter.LengthFilter(5);
                                                                          InputFilter[] filter2 = new InputFilter[1];
                                                                          filter2[0] = new InputFilter.LengthFilter(1);
                                                                          final AlertDialog.Builder timeDialog = new AlertDialog.Builder(TypeOfMode.this);
                                                                          timeDialog.setTitle("Velg klokkeslett");
                                                                          final EditText time = new EditText(TypeOfMode.this);
                                                                          final EditText timeForChange = new EditText(TypeOfMode.this);
                                                                          final TextView text = new TextView(TypeOfMode.this);
                                                                          final TextView text2 = new TextView(TypeOfMode.this);
                                                                          final ToggleButton day = new ToggleButton(TypeOfMode.this);
                                                                          final ToggleButton night = new ToggleButton(TypeOfMode.this);
                                                                          LinearLayout layout = new LinearLayout(TypeOfMode.this);
                                                                          layout.setOrientation(LinearLayout.VERTICAL);
                                                                          time.setHint("Klokkeslett for ankomst (HH:MM)");
                                                                          time.setGravity(Gravity.CENTER);
                                                                          time.setFilters(filter1);
                                                                          time.setInputType(InputType.TYPE_CLASS_NUMBER);
                                                                          time.addTextChangedListener(new TextWatcher() {

                                                                              @Override
                                                                              public void onTextChanged(CharSequence s, int start, int before, int count) {

                                                                                  boolean flag = true;
                                                                                  String eachBlock[] = time.getText().toString().split(":");
                                                                                  for (int i = 0; i < eachBlock.length; i++) {
                                                                                      if (eachBlock[i].length() > 6) {
                                                                                          flag = false;
                                                                                      }
                                                                                  }
                                                                                  if (flag) {

                                                                                      time.setOnKeyListener(new View.OnKeyListener() {

                                                                                          @Override
                                                                                          public boolean onKey(View v, int keyCode, KeyEvent event) {

                                                                                              if (keyCode == KeyEvent.KEYCODE_DEL)
                                                                                                  mKeyDel = 1;
                                                                                              return false;
                                                                                          }
                                                                                      });

                                                                                      if (mKeyDel == 0) {

                                                                                          if (((time.getText().length() + 1) % 3) == 0) {
                                                                                              if (time.getText().length() < 5) {
                                                                                                  time.setText(time.getText() + ":");
                                                                                                  time.setSelection(time.getText().length());
                                                                                              }
                                                                                          }
                                                                                          mTextValue = time.getText().toString();
                                                                                      } else {
                                                                                          mTextValue = time.getText().toString();
                                                                                          if (mLastChar.equals(':')) {
                                                                                              mTextValue = mTextValue.substring(0, mTextValue.length() - 1);
                                                                                              time.setText(mTextValue);
                                                                                              time.setSelection(mTextValue.length());
                                                                                          }
                                                                                          mKeyDel = 0;
                                                                                      }

                                                                                  } else {
                                                                                      time.setText(mTextValue);
                                                                                  }
                                                                              }

                                                                              @Override
                                                                              public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                                                                                  if (s.length() > 0) {// save the last char value
                                                                                      mLastChar = s.charAt(s.length() - 1);
                                                                                  } else {
                                                                                      mLastChar = '\0';
                                                                                  }
                                                                              }

                                                                              @Override
                                                                              public void afterTextChanged(Editable s) {

                                                                              }
                                                                          });
                                                                          timeForChange.setText("");
                                                                          timeForChange.setGravity(Gravity.CENTER);
                                                                          timeForChange.setFilters(filter2);
                                                                          timeForChange.setInputType(InputType.TYPE_CLASS_NUMBER);
                                                                          timeForChange.setHint("(H)");
                                                                          text.setText("Du kan sette hvor mange timer før ankomst "
                                                                                  + "du vil at modus skal skifte til dag/natt her:");

                                                                          text2.setText("Endre til natt eller dag modus "
                                                                                  + "når tiden er nådd?");
                                                                          text.setGravity(Gravity.CENTER);
                                                                          text2.setGravity(Gravity.CENTER);
                                                                          day.setTextOff("Dag");
                                                                          day.setTextOn("Dag");
                                                                          night.setTextOff("Natt");
                                                                          night.setTextOn("Natt");
                                                                          night.setChecked(false);
                                                                          day.setChecked(false);
                                                                          night.setOnClickListener(new View.OnClickListener() {
                                                                              public void onClick(View v) {
                                                                                  if (day.isChecked()) {
                                                                                      day.setChecked(false);
                                                                                  }
                                                                              }
                                                                          });
                                                                          day.setOnClickListener(new View.OnClickListener() {
                                                                              public void onClick(View v) {
                                                                                  if (night.isChecked()) {
                                                                                      night.setChecked(false);
                                                                                  }
                                                                              }
                                                                          });
                                                                          layout.addView(time);
                                                                          layout.addView(text);
                                                                          layout.addView(timeForChange);
                                                                          layout.addView(text2);
                                                                          layout.addView(day);
                                                                          layout.addView(night);
                                                                          timeDialog.setView(layout);

                                                                          timeDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                                                                      @Override
                                                                                      public void onClick(final DialogInterface dialog, int which) {
                                                                                          dialog.dismiss();
                                                                                          int h = 0, m = 0;
                                                                                          boolean gotTime;
                                                                                          String getTime = time.getText().toString();
                                                                                          gotTime = getTime.isEmpty();
                                                                                          if (getTime.length() < 5)
                                                                                              gotTime = true;
                                                                                          if (!gotTime) {
                                                                                              h = Integer.parseInt(getTime.substring(0, 2));
                                                                                              m = Integer.parseInt(getTime.substring(3, getTime.length()));
                                                                                          }
                                                                                          if (h < 25 && m < 60 && !gotTime) {
                                                                                              Log.d("TGest", h + " " + m);
                                                                                              String getTimeForChange = timeForChange.getText().toString();
                                                                                              if (getTimeForChange.isEmpty()) {
                                                                                                  getTimeForChange = "0";
                                                                                              }
                                                                                              String modeForChange;
                                                                                              if (day.isChecked()) {
                                                                                                  modeForChange = "day";

                                                                                              } else if (night.isChecked()) {
                                                                                                  modeForChange = "night";
                                                                                              } else {
                                                                                                  modeForChange = "day";
                                                                                              }

                                                                                              sharedpreferences = getSharedPreferences(holidayTime, Context.MODE_PRIVATE);
                                                                                              SharedPreferences.Editor endEditor = sharedpreferences.edit();
                                                                                              String hYear = Integer.toString(sharedpreferences.getInt("year", 0));
                                                                                              String hMonth = Integer.toString(sharedpreferences.getInt("month", 0));
                                                                                              String hDay = Integer.toString(sharedpreferences.getInt("day", 0));

                                                                                              endEditor.putInt("htime", h);
                                                                                              endEditor.putInt("mtime", m);
                                                                                              endEditor.putString("tchange", getTimeForChange);
                                                                                              endEditor.putString("daymode", modeForChange);
                                                                                              endEditor.commit();

                                                                                              MainActivity.sendText("HolidayT:" + hYear + "," + hMonth + "," + hDay
                                                                                                      + "," + h + "," + m + "," + getTimeForChange
                                                                                                      + "," + modeForChange);

                                                                                              Log.d("Ferdig", hYear + " " + hMonth + " " + hDay + "\n"
                                                                                                      + getTime + " " + getTimeForChange + " " + modeForChange);
                                                                                              AlertDialog.Builder add = new AlertDialog.Builder(TypeOfMode.this);
                                                                                              add.setTitle("Verdiene er lagret");
                                                                                              final TextView value = new TextView(TypeOfMode.this);
                                                                                              final TextView value1 = new TextView(TypeOfMode.this);
                                                                                              final TextView value2 = new TextView(TypeOfMode.this);
                                                                                              final TextView value3 = new TextView(TypeOfMode.this);
                                                                                              value.setText("\nDag: " + hDay + "\nMåned: " + hMonth + "\nÅr: " + hYear);
                                                                                              value1.setText("Tid for ankomst: " + getTime);
                                                                                              value2.setText("Tid for endring: " + getTimeForChange + " timer før ankomst");
                                                                                              value3.setText("\nGod Ferie!");
                                                                                                /*
                                                                                                value.setGravity(Gravity.CENTER);
                                                                                                value1.setGravity(Gravity.CENTER);
                                                                                                value2.setGravity(Gravity.CENTER);
                                                                                                value3.setGravity(Gravity.CENTER);
                                                                                                */
                                                                                              LinearLayout layout = new LinearLayout(TypeOfMode.this);
                                                                                              layout.setOrientation(LinearLayout.VERTICAL);
                                                                                              layout.addView(value);
                                                                                              layout.addView(value1);
                                                                                              layout.addView(value2);
                                                                                              layout.addView(value3);
                                                                                              add.setView(layout);

                                                                                              add.setCancelable(false);
                                                                                              add.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                                                                                  @Override
                                                                                                  public void onClick(DialogInterface dialog, int which) {
                                                                                                      dialog.dismiss();
                                                                                                  }
                                                                                              });
                                                                                              add.create();
                                                                                              add.show();
                                                                                          } else {
                                                                                              AlertDialog.Builder add = new AlertDialog.Builder(TypeOfMode.this);
                                                                                              add.setTitle("Feriemodus");
                                                                                              add.setMessage("Ugyldig time eller minutt valg."
                                                                                                      + "\nVerdiene har ikke blitt lagret."
                                                                                                      + "\n\nPrøv igjen.");
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
                                                                                  }

                                                                          );
                                                                          timeDialog.create();
                                                                          timeDialog.show();
                                        /*
                                        final TimePickerDialog timeDialog = new TimePickerDialog(TypeOfMode.this,
                                                new TimePickerDialog.OnTimeSetListener() {
                                                    @Override
                                                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                                        hour = hourOfDay;
                                                        min = minute;
                                                        sharedpreferences = getSharedPreferences(holidayTime, Context.MODE_PRIVATE);
                                                        SharedPreferences.Editor holidayEditor = sharedpreferences.edit();
                                                        holidayEditor.putInt("hour", hour);
                                                        holidayEditor.putInt("minute", min);
                                                        holidayEditor.commit();
                                                    }
                                                }, hour, min, true);
                                       // timeDialog.onTimeChanged(timePicker,,min);
                                        timeDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Lagre",
                                                new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        dialog.dismiss();
                                                        sharedpreferences = getSharedPreferences(holidayTime, Context.MODE_PRIVATE);
                                                        int yYear = sharedpreferences.getInt("year", 1992);
                                                        int yMonth = sharedpreferences.getInt("month", 1992);
                                                        int yDay = sharedpreferences.getInt("day", 1992);
                                                        int hHour = sharedpreferences.getInt("hour", 1992);
                                                        int hMin = sharedpreferences.getInt("minute", 1992);
                                                        Log.d("Date", yYear + " " + yMonth + " " + yDay);
                                                        Log.d("Time", hHour + " " + hMin);
                                                        Log.d("Time", hour + " " + min);
                                                    }
                                                });
                                        timeDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Avbryt",
                                                new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        dialog.dismiss();
                                                    }
                                                });

                                        timeDialog.show();

                                          int holidayYear = sharedpreferences.getInt("year", 0);
                                        int holidayMonth = sharedpreferences.getInt("month", 0);
                                        int holidayDay = sharedpreferences.getInt("day", 0);

                                        if(holidayYear == 0 || holidayMonth ==0 || holidayDay == 0 ) {

                                            AlertDialog.Builder add = new AlertDialog.Builder(TypeOfMode.this);
                                            add.setTitle("Feriemodus");
                                            add.setMessage("Modus er satt til ferie, men verdiene ble ikke lagret. Vennligst prøv igjen"
                                                            + "\n\n"
                                                            + "Du kan også skifte modus selv du kommer hjem"
                                                            + "og endre verdier selv");
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
                                        else {

                                        }
                                        */


                                                                      }
                                                                  }

                                                          );
                                                          dateDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Avbryt",
                                                                  new DialogInterface.OnClickListener()

                                                                  {
                                                                      public void onClick(DialogInterface dialog,
                                                                                          int which) {
                                                                          dialog.dismiss();
                                                                      }
                                                                  }
                                                          );
                                                      }
                                                  }
                                          );
                                          add.setNegativeButton("Avbryt", new DialogInterface.OnClickListener()
                                                  {
                                                      @Override
                                                      public void onClick(DialogInterface dialog, int which) {
                                                          dialog.cancel();
                                                      }
                                                  }
                                          );
                                          add.create();
                                          add.show();
                                      }
                    });
                }catch (Exception e){
                    e.printStackTrace();
                    AlertDialog.Builder add = new AlertDialog.Builder(TypeOfMode.this);
                    add.setTitle("Feriemodus");
                    add.setMessage("Noe gikk feil"
                                    + "\n\nPrøv igjen.");
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
        hThread.start();
        }

    private class TimeTextWatcher implements TextWatcher {

        private EditText time;

        private TimeTextWatcher(EditText time) {
            this.time = time;
        }
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            boolean flag = true;
            String eachBlock[] = time.getText().toString().split(":");
            for (int i = 0; i < eachBlock.length; i++) {
                if (eachBlock[i].length() > 6) {
                    flag = false;
                }
            }
            if (flag) {

                time.setOnKeyListener(new View.OnKeyListener() {

                    @Override
                    public boolean onKey(View v, int keyCode, KeyEvent event) {

                        if (keyCode == KeyEvent.KEYCODE_DEL)
                            mKeyDel = 1;
                        return false;
                    }
                });

                if (mKeyDel == 0) {

                    if (((time.getText().length() + 1) % 3) == 0) {
                        if (time.getText().length() < 5) {
                            time.setText(time.getText() + ":");
                            time.setSelection(time.getText().length());
                        }
                    }
                    mTextValue = time.getText().toString();
                } else {
                    mTextValue = time.getText().toString();
                    if (mLastChar.equals(':')) {
                        mTextValue = mTextValue.substring(0, mTextValue.length() - 1);
                        time.setText(mTextValue);
                        time.setSelection(mTextValue.length());
                    }
                    mKeyDel = 0;
                }

            } else {
                time.setText(mTextValue);
            }
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            if (s.length() > 0) {// save the last char value
                mLastChar = s.charAt(s.length() - 1);
            } else {
                mLastChar = '\0';
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }
}
