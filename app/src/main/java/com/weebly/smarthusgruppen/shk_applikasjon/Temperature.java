package com.weebly.smarthusgruppen.shk_applikasjon;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Temperature extends AppCompatActivity {
    ImageButton homeBtn;
    ImageButton upBtn;
    static public ArrayList<MainActivity.TemperatureInformation> tempZone = new ArrayList<>();
    TextView stemp1;
    TextView stemp2;
    TextView stemp3;
    TextView stemp4;
    TextView stemp5;
    TextView stemp6;
    TextView ctemp1;
    TextView ctemp2;
    TextView ctemp3;
    TextView ctemp4;
    TextView ctemp5;
    TextView ctemp6;



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

        getZoneTemp();
        for(int i=0; i < tempZone.size(); ) {
            createTempZone(i);
        }
/*      For later
        MainActivity.sendText("Command:007262112,1");
        MainActivity.sendText("Command:007262112,2");
        MainActivity.sendText("Command:007262112,3");
        MainActivity.sendText("Command:007262112,4");
        MainActivity.sendText("Command:007262112,5");
        MainActivity.sendText("Command:007262112,6");


*/


        stemp1 = (TextView) findViewById(R.id.static1_tv);
        stemp2 = (TextView) findViewById(R.id.static2_tv);
        stemp3 = (TextView) findViewById(R.id.static3_tv);
        stemp4 = (TextView) findViewById(R.id.static4_tv);
        stemp5 = (TextView) findViewById(R.id.static5_tv);
        stemp6 = (TextView) findViewById(R.id.static6_tv);

        ctemp1 = (TextView) findViewById(R.id.current1_tv);
        ctemp2 = (TextView) findViewById(R.id.current2_tv);
        ctemp3 = (TextView) findViewById(R.id.current3_tv);
        ctemp4 = (TextView) findViewById(R.id.current4_tv);
        ctemp5 = (TextView) findViewById(R.id.current5_tv);
        ctemp6 = (TextView) findViewById(R.id.current6_tv);



    }
/*
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK){
            }
                Bundle extras = getIntent().getExtras();
                if (extras != null) {
                    String channel = extras.getString("channel");
                    String mode = extras.getString("mode");
                    String holiday = extras.getString("holiday");
                    String day = extras.getString("day");
                    String night = extras.getString("night");
                    String away = extras.getString("away");
                    String currentTemp = extras.getString("currentTemp");
                    Log.d("Stuff", "" + channel + " " + mode + " " + holiday + " " + day + " " + night + " " + away + " " + currentTemp);
                    createTempZone(channel,mode,day,night,holiday,away,currentTemp);
                   // String stredittext=data.getStringExtra("edittextvalue");
            }
        }
    }
/*
    @Override
    public void onReceive(Context context, Intent intent) {

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String channel = extras.getString("channel");
            String mode = extras.getString("mode");
            String holiday = extras.getString("holiday");
            String day = extras.getString("day");
            String night = extras.getString("night");
            String away = extras.getString("away");
            String currentTemp = extras.getString("currentTemp");
            createTempZone(channel,mode,day,night,holiday,away,currentTemp);
        }
       // String phoneNumber = intent.getExtras().getString();


    }
*/
    public void goToHome() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void getZoneTemp() {
       /* for(int i = 0; i < MainActivity.tempZone.size(); i++) {
            tempZone.add(MainActivity.tempZone.get(i));
        }*/

        if(!MainActivity.TemperatureIsEmpty()) {
            for(int i=0; i < MainActivity.returnTemperatureSize(); i++) {
                tempZone.add(MainActivity.returnTemperature(i));
            }
            Log.d("string", tempZone.get(2).currTemp);
        }

    }
    void createTempZone(int i) {
        //TemperatureInformation zone = new TemperatureInformation(ch,mo,cd,cn,cho,ca,ct);
        //tempZone.add(zone);

        int c = Integer.parseInt(tempZone.get(i).channel);
        int m = Integer.parseInt(tempZone.get(i).mode);
        String currHoliday = tempZone.get(i).currHoliday;
        String currDay = tempZone.get(i).currDay;
        String currNight = tempZone.get(i).currNight;
        String currAway = tempZone.get(i).currAway;
        String currTemp = tempZone.get(i).currTemp;


        /*Thread tempThread = new Thread (new Runnable() {

            @Override
            public void run() { */
                try {

                    switch (c) {

                        case 1:
                            String cctemp1 = currTemp + " °C";
                            ctemp1.setText(cctemp1);

                            // setting static temp to show depending on which mode the house is in
                            switch (m) {
                                case 1:
                                    String temp1 = currHoliday + " °C";
                                    stemp1.setText(temp1);
                                    break;
                                case 2:
                                    String temp2 = currDay + " °C";
                                    stemp1.setText(temp2);
                                    break;
                                case 3:
                                    String temp3 = currNight + " °C";
                                    stemp1.setText(temp3);
                                    break;
                                case 4:
                                    String temp4 = currAway + " °C";
                                    stemp1.setText(temp4);
                                    break;
                                default:
                                    break;
                            }


                            break;
                        case 2:

                            String cctemp2 = currTemp + " °C";
                            ctemp2.setText(cctemp2);

                            // setting static temp to show depending on which mode the house is in
                            switch (m) {
                                case 1:
                                    String temp1 = currHoliday + " °C";
                                    stemp2.setText(temp1);
                                    break;
                                case 2:
                                    String temp2 = currDay + " °C";
                                    stemp2.setText(temp2);
                                    break;
                                case 3:
                                    String temp3 = currNight + " °C";
                                    stemp2.setText(temp3);
                                    break;
                                case 4:
                                    String temp4 = currAway + " °C";
                                    stemp2.setText(temp4);
                                    break;
                                default:
                                    break;
                            }

                            break;
                        case 3:

                            String cctemp3 = currTemp + " °C";
                            ctemp3.setText(cctemp3);

                            // setting static temp to show depending on which mode the house is in
                            switch (m) {
                                case 1:
                                    String temp1 = currHoliday + " °C";
                                    stemp3.setText(temp1);
                                    break;
                                case 2:
                                    String temp2 = currDay + " °C";
                                    stemp3.setText(temp2);
                                    break;
                                case 3:
                                    String temp3 = currNight + " °C";
                                    stemp3.setText(temp3);
                                    break;
                                case 4:
                                    String temp4 = currAway + " °C";
                                    stemp3.setText(temp4);
                                    break;
                                default:
                                    break;
                            }

                            break;
                        case 4:
                            String cctemp4 = currTemp + " °C";
                            ctemp4.setText(cctemp4);

                            // setting static temp to show depending on which mode the house is in
                            switch (m) {
                                case 1:
                                    String temp1 = currHoliday + " °C";
                                    stemp4.setText(temp1);
                                    break;
                                case 2:
                                    String temp2 = currDay + " °C";
                                    stemp4.setText(temp2);
                                    break;
                                case 3:
                                    String temp3 = currNight + " °C";
                                    stemp4.setText(temp3);
                                    break;
                                case 4:
                                    String temp4 = currAway + " °C";
                                    stemp4.setText(temp4);
                                    break;
                                default:
                                    break;
                            }

                            break;
                        case 5:
                            String cctemp5 = currTemp + " °C";
                            ctemp5.setText(cctemp5);

                            // setting static temp to show depending on which mode the house is in
                            switch (m) {
                                case 1:
                                    String temp1 = currHoliday + " °C";
                                    stemp5.setText(temp1);
                                    break;
                                case 2:
                                    String temp2 = currDay + " °C";
                                    stemp5.setText(temp2);
                                    break;
                                case 3:
                                    String temp3 = currNight + " °C";
                                    stemp5.setText(temp3);
                                    break;
                                case 4:
                                    String temp4 = currAway + " °C";
                                    stemp5.setText(temp4);
                                    break;
                                default:
                                    break;
                            }

                            break;
                        case 6:
                            String cctemp6 = currTemp + " °C";
                            ctemp6.setText(cctemp6);

                            // setting static temp to show depending on which mode the house is in
                            switch (m) {
                                case 1:
                                    String temp1 = currHoliday + " °C";
                                    stemp6.setText(temp1);
                                    break;
                                case 2:
                                    String temp2 = currDay + " °C";
                                    stemp6.setText(temp2);
                                    break;
                                case 3:
                                    String temp3 = currNight + " °C";
                                    stemp6.setText(temp3);
                                    break;
                                case 4:
                                    String temp4 = currAway + " °C";
                                    stemp6.setText(temp4);
                                    break;
                            }
                            break;
                        default:
                            break;
                    }
                }
                catch (NullPointerException e) {
                    e.printStackTrace();
                }
        /*
            }
        });
        tempThread.start(); */

    }
/*
    static public class TemperatureInformation {
        String currDay;
        String currNight;
        String currHoliday;
        String currAway;
        String currTemp;
        String channel;
        String mode;

        TemperatureInformation(String ch, String mo, String cd, String cn, String cho, String ca, String ct) {
            channel = ch;
            mode = mo;
            currDay = cd;
            currNight = cn;
            currHoliday = cho;
            currAway = ca;
            currTemp = ct;


        }
    }
*/
    public void upTemp() {
        MainActivity.sendText("Command:007260112,1,0,0,1,28,27,26,25,1,15");
    }
}


