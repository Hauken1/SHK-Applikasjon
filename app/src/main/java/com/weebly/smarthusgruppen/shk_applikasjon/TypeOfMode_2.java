package com.weebly.smarthusgruppen.shk_applikasjon;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ToggleButton;

public class TypeOfMode_2 extends AppCompatActivity {
    public static final String savedTemp1 = "1SavedTemperature";
    public static final String savedTemp2 = "2SavedTemperature";
    public static final String savedTemp3 = "3SavedTemperature";
    public static final String savedTemp4 = "4SavedTemperature";
    public static final String savedTemp5 = "5SavedTemperature";
    public static final String savedTemp6 = "6SavedTemperature";

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_of_mode);

          setUpGUI();

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
        Intent intent = new Intent(this, MainActivity_2.class);
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
    public void holiday() {

        sharedpreferences = getSharedPreferences(savedTemp1, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor1 = sharedpreferences.edit();
        editor1.putString("mode",holidayMode);
        editor1.commit();

        //Changing temperature for holiday mode for heating sone 1
        if (sharedpreferences.contains("channel")) {
            String channel1 = sharedpreferences.getString("channel", "1");
            String holiday1 = sharedpreferences.getString("holiday", "18");
            String day1 = sharedpreferences.getString("day", "23");
            String night1 = sharedpreferences.getString("night", "21");
            String away1 = sharedpreferences.getString("away", "19");
            String current1 = sharedpreferences.getString("current", "22");

            int currentTemp = Integer.parseInt(current1);
            int holiday = Integer.parseInt(holiday1);
            String wateringFlag1;
            if (currentTemp < holiday) wateringFlag1 = "1"; else wateringFlag1 = "0";

            MainActivity.sendText("Command:007260112," + channel1 + ",0,0," + holidayMode + ","
                    + holiday1 + "," + day1 + "," + night1 + "," + away1 + "," + wateringFlag1 + ","
                    + "15");
        }

        sharedpreferences = getSharedPreferences(savedTemp2, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor2 = sharedpreferences.edit();
        editor2.putString("mode", holidayMode);
        editor2.commit();
        //Changing temperature for holiday mode for heating sone 2
        if (sharedpreferences.contains("channel")) {
            String channel1 = sharedpreferences.getString("channel", "1");
            String holiday1 = sharedpreferences.getString("holiday", "18");
            String day1 = sharedpreferences.getString("day", "23");
            String night1 = sharedpreferences.getString("night", "21");
            String away1 = sharedpreferences.getString("away", "19");
            String current1 = sharedpreferences.getString("current", "22");

            int currentTemp = Integer.parseInt(current1);
            int holiday = Integer.parseInt(holiday1);
            String wateringFlag1;
            if (currentTemp < holiday) wateringFlag1 = "1"; else wateringFlag1 = "0";

            MainActivity.sendText("Command:007260112," + channel1 + ",0,0," + holidayMode + ","
                    + holiday1 + "," + day1 + "," + night1 + "," + away1 + "," + wateringFlag1 + ","
                    + "15");
        }

        sharedpreferences = getSharedPreferences(savedTemp3, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor3 = sharedpreferences.edit();
        editor3.putString("mode",holidayMode);
        editor3.commit();
        //Changing temperature for holiday mode for heating sone 3
        if (sharedpreferences.contains("channel")) {
            String channel1 = sharedpreferences.getString("channel", "1");
            String holiday1 = sharedpreferences.getString("holiday", "18");
            String day1 = sharedpreferences.getString("day", "23");
            String night1 = sharedpreferences.getString("night", "21");
            String away1 = sharedpreferences.getString("away", "19");
            String current1 = sharedpreferences.getString("current", "22");

            int currentTemp = Integer.parseInt(current1);
            int holiday = Integer.parseInt(holiday1);
            String wateringFlag1;
            if (currentTemp < holiday) wateringFlag1 = "1"; else wateringFlag1 = "0";

            MainActivity.sendText("Command:007260112," + channel1 + ",0,0," + holidayMode + ","
                    + holiday1 + "," + day1 + "," + night1 + "," + away1 + "," + wateringFlag1 + ","
                    + "15");
        }

        sharedpreferences = getSharedPreferences(savedTemp4, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor4 = sharedpreferences.edit();
        editor4.putString("mode",holidayMode);
        editor4.commit();
        //Changing temperature for holiday mode for heating sone 4
        if (sharedpreferences.contains("channel")) {
            String channel1 = sharedpreferences.getString("channel", "1");
            String holiday1 = sharedpreferences.getString("holiday", "18");
            String day1 = sharedpreferences.getString("day", "23");
            String night1 = sharedpreferences.getString("night", "21");
            String away1 = sharedpreferences.getString("away", "19");
            String current1 = sharedpreferences.getString("current", "22");

            int currentTemp = Integer.parseInt(current1);
            int holiday = Integer.parseInt(holiday1);
            String wateringFlag1;
            if (currentTemp < holiday) wateringFlag1 = "1"; else wateringFlag1 = "0";

            MainActivity.sendText("Command:007260112," + channel1 + ",0,0," + holidayMode + ","
                    + holiday1 + "," + day1 + "," + night1 + "," + away1 + "," + wateringFlag1 + ","
                    + "15");
        }

        sharedpreferences = getSharedPreferences(savedTemp5, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor5 = sharedpreferences.edit();
        editor5.putString("mode",holidayMode);
        editor5.commit();
        //Changing temperature for holiday mode for heating sone 5
        if (sharedpreferences.contains("channel")) {
            String channel1 = sharedpreferences.getString("channel", "1");
            String holiday1 = sharedpreferences.getString("holiday", "18");
            String day1 = sharedpreferences.getString("day", "23");
            String night1 = sharedpreferences.getString("night", "21");
            String away1 = sharedpreferences.getString("away", "19");
            String current1 = sharedpreferences.getString("current", "22");

            int currentTemp = Integer.parseInt(current1);
            int holiday = Integer.parseInt(holiday1);
            String wateringFlag1;
            if (currentTemp < holiday) wateringFlag1 = "1"; else wateringFlag1 = "0";

            MainActivity.sendText("Command:007260112," + channel1 + ",0,0," + holidayMode + ","
                    + holiday1 + "," + day1 + "," + night1 + "," + away1 + "," + wateringFlag1 + ","
                    + "15");
        }

        sharedpreferences = getSharedPreferences(savedTemp6, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor6 = sharedpreferences.edit();
        editor6.putString("mode",holidayMode);
        editor6.commit();
        //Changing temperature for holiday mode for heating sone 6
        if (sharedpreferences.contains("channel")) {
            String channel1 = sharedpreferences.getString("channel", "1");
            String holiday1 = sharedpreferences.getString("holiday", "18");
            String day1 = sharedpreferences.getString("day", "23");
            String night1 = sharedpreferences.getString("night", "21");
            String away1 = sharedpreferences.getString("away", "19");
            String current1 = sharedpreferences.getString("current", "22");

            int currentTemp = Integer.parseInt(current1);
            int holiday = Integer.parseInt(holiday1);
            String wateringFlag1;
            if (currentTemp < holiday) wateringFlag1 = "1"; else wateringFlag1 = "0";

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
    }
}
