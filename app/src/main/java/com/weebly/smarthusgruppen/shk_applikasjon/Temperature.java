package com.weebly.smarthusgruppen.shk_applikasjon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class Temperature extends AppCompatActivity {
    ImageButton homeBtn;
    ImageButton upBtn;
    static public ArrayList<TemperatureInformation> tempZone = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature);

        //home button
        homeBtn = (ImageButton) findViewById(R.id.home_button);
        homeBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goToHome();
            }
        });

        // up temp button
        upBtn = (ImageButton) findViewById(R.id.adjust_up);
        upBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                upTemp();
            }
        });


        MainActivity.sendText("Command:007262112,1");
        MainActivity.sendText("Command:007262112,2");
        MainActivity.sendText("Command:007262112,3");
        MainActivity.sendText("Command:007262112,4");
        MainActivity.sendText("Command:007262112,5");
        MainActivity.sendText("Command:007262112,6");


    }
    public Temperature() {

    }

    public void goToHome() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    void createTempZone(int ch, int mo, int cd, int cn, int cho, int ca, int ct) {
        Log.d("Stuff", ""+ ch + " " + mo + " " + cho + " " + cd + " " + cn + " " + ca + " " + ct );
        TemperatureInformation zone = new TemperatureInformation(ch,mo,cd,cn,cho,ca,ct);
        tempZone.add(zone);

        switch (zone.channel) {

            case 1:
                 TextView ctemp1 = (TextView) findViewById(R.id.current1_tv);
                ctemp1.setText(zone.currTemp +" °C");

                // setting static temp to show depending on which mode the house is in
                switch(zone.mode) {
                    case 1:
                        TextView stemp1 = (TextView) findViewById(R.id.static1_tv);
                        stemp1.setText(zone.currHoliday +" °C");
                        break;
                    case 2:
                        TextView stemp2 = (TextView) findViewById(R.id.static1_tv);
                        stemp2.setText(zone.currDay +" °C");
                        break;
                    case 3:
                        TextView stemp3 = (TextView) findViewById(R.id.static1_tv);
                        stemp3.setText(zone.currNight +" °C");
                        break;
                    case 4:
                        TextView stemp4 = (TextView) findViewById(R.id.static1_tv);
                        stemp4.setText(zone.currAway +" °C");
                        break;
                }


                break;
            case 2:
                TextView ctemp3 = (TextView) findViewById(R.id.current2_tv);
                ctemp3.setText(zone.currTemp +" °C");

                // setting static temp to show depending on which mode the house is in
                switch(zone.mode) {
                    case 1:
                        TextView stemp1 = (TextView) findViewById(R.id.static2_tv);
                        stemp1.setText(zone.currHoliday +" °C");
                        break;
                    case 2:
                        TextView stemp2 = (TextView) findViewById(R.id.static2_tv);
                        stemp2.setText(zone.currDay +" °C");
                        break;
                    case 3:
                        TextView stemp3 = (TextView) findViewById(R.id.static2_tv);
                        stemp3.setText(zone.currNight +" °C");
                        break;
                    case 4:
                        TextView stemp4 = (TextView) findViewById(R.id.static2_tv);
                        stemp4.setText(zone.currAway +" °C");
                        break;
                }

                break;
            case 3:
                TextView ctemp5 = (TextView) findViewById(R.id.current3_tv);
                ctemp5.setText(zone.currTemp +" °C");

                // setting static temp to show depending on which mode the house is in
                switch(zone.mode) {
                    case 1:
                        TextView stemp1 = (TextView) findViewById(R.id.static3_tv);
                        stemp1.setText(zone.currHoliday +" °C");
                        break;
                    case 2:
                        TextView stemp2 = (TextView) findViewById(R.id.static3_tv);
                        stemp2.setText(zone.currDay +" °C");
                        break;
                    case 3:
                        TextView stemp3 = (TextView) findViewById(R.id.static3_tv);
                        stemp3.setText(zone.currNight +" °C");
                        break;
                    case 4:
                        TextView stemp4 = (TextView) findViewById(R.id.static3_tv);
                        stemp4.setText(zone.currAway +" °C");
                        break;
                }

                break;
            case 4:
                TextView ctemp7 = (TextView) findViewById(R.id.current4_tv);
                ctemp7.setText(zone.currTemp +" °C");

                // setting static temp to show depending on which mode the house is in
                switch(zone.mode) {
                    case 1:
                        TextView stemp1 = (TextView) findViewById(R.id.static4_tv);
                        stemp1.setText(zone.currHoliday +" °C");
                        break;
                    case 2:
                        TextView stemp2 = (TextView) findViewById(R.id.static4_tv);
                        stemp2.setText(zone.currDay +" °C");
                        break;
                    case 3:
                        TextView stemp3 = (TextView) findViewById(R.id.static4_tv);
                        stemp3.setText(zone.currNight +" °C");
                        break;
                    case 4:
                        TextView stemp4 = (TextView) findViewById(R.id.static4_tv);
                        stemp4.setText(zone.currAway +" °C");
                        break;
                }

                break;
            case 5:
                TextView ctemp9 = (TextView) findViewById(R.id.current5_tv);
                ctemp9.setText(zone.currTemp +" °C");

                // setting static temp to show depending on which mode the house is in
                switch(zone.mode) {
                    case 1:
                        TextView stemp1 = (TextView) findViewById(R.id.static5_tv);
                        stemp1.setText(zone.currHoliday +" °C");
                        break;
                    case 2:
                        TextView stemp2 = (TextView) findViewById(R.id.static5_tv);
                        stemp2.setText(zone.currDay +" °C");
                        break;
                    case 3:
                        TextView stemp3 = (TextView) findViewById(R.id.static5_tv);
                        stemp3.setText(zone.currNight +" °C");
                        break;
                    case 4:
                        TextView stemp4 = (TextView) findViewById(R.id.static5_tv);
                        stemp4.setText(zone.currAway +" °C");
                        break;
                }

                break;
            case 6:
                TextView ctemp11 = (TextView) findViewById(R.id.current6_tv);
                ctemp11.setText(zone.currTemp +" °C");

                // setting static temp to show depending on which mode the house is in
                switch(zone.mode) {
                    case 1:
                        TextView stemp1 = (TextView) findViewById(R.id.static6_tv);
                        stemp1.setText(zone.currHoliday +" °C");
                        break;
                    case 2:
                        TextView stemp2 = (TextView) findViewById(R.id.static6_tv);
                        stemp2.setText(zone.currDay +" °C");
                        break;
                    case 3:
                        TextView stemp3 = (TextView) findViewById(R.id.static6_tv);
                        stemp3.setText(zone.currNight +" °C");
                        break;
                    case 4:
                        TextView stemp4 = (TextView) findViewById(R.id.static6_tv);
                        stemp4.setText(zone.currAway +" °C");
                        break;
                }
                break;
        }

    }

    static public class TemperatureInformation {
        int currDay;
        int currNight;
        int currHoliday;
        int currAway;
        int currTemp;
        int channel;
        int mode;

        TemperatureInformation(int ch, int mo, int cd, int cn, int cho, int ca, int ct) {
            channel = ch;
            mode = mo;
            currDay = cd;
            currNight = cn;
            currHoliday = cho;
            currAway = ca;
            currTemp = ct;


        }
    }

    public void upTemp() {
        MainActivity.sendText("Command:007260112,1,0,0,1,28,27,26,25,1,15");
    }
}
