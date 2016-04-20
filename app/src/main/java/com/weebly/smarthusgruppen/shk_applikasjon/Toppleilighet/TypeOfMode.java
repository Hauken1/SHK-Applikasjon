package com.weebly.smarthusgruppen.shk_applikasjon.Toppleilighet;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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

public class TypeOfMode extends AppCompatActivity {
    public static final String savedTemp1 = "1SavedTemperature";
    public static final String savedTemp2 = "2SavedTemperature";
    public static final String savedTemp3 = "3SavedTemperature";
    public static final String savedTemp4 = "4SavedTemperature";
    public static final String savedTemp5 = "5SavedTemperature";
    public static final String savedTemp6 = "6SavedTemperature";

    public static final String holidayTime = "SavedHolidayTime";

    SharedPreferences sharedpreferences;

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

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        min = calendar.get(Calendar.MINUTE);


        //Checks what mode the the heatingcontrollers are set to.
        //All heatingcontrollolers are set to the same mode
        sharedpreferences = getSharedPreferences(savedTemp1, Context.MODE_PRIVATE);
        if (sharedpreferences.contains("channel")) {
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
        }

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

    }

    public void goToHome() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
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
        //changing lighting to day mode (minimum)
        MainActivity.sendText("Command:000002117,1,1");
        //Changing ventilation to day mode

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
        //changing lighting to night mode (medium)
        MainActivity.sendText("Command:000002117,1,2");
        //Changing ventilation to day mode


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
        //changing lighting to away mode (off)
        MainActivity.sendText("Command:000002117,1,0");
        //Changing ventilation to day mode
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
        //changing lighting to holiday mode (off)
        MainActivity.sendText("Command:000002117,1,0");
        //Changing ventilation to day mode

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
    }
