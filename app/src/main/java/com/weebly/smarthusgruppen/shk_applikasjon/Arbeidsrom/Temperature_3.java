package com.weebly.smarthusgruppen.shk_applikasjon.Arbeidsrom;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.weebly.smarthusgruppen.shk_applikasjon.R;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Temperature_3 extends AppCompatActivity {
    ImageButton homeBtn;
    ImageButton upBtn;
    ImageButton upBtn1;
    ImageButton upBtn2;
    ImageButton upBtn3;
    ImageButton upBtn4;
    ImageButton upBtn5;
    ImageButton downBtn;
    ImageButton downBtn1;
    ImageButton downBtn2;
    ImageButton downBtn3;
    ImageButton downBtn4;
    ImageButton downBtn5;

    public static final String savedTemp1 = "1SavedTemperature" ;
    public static final String savedTemp2 = "2SavedTemperature" ;
    public static final String savedTemp3 = "3SavedTemperature" ;
    public static final String savedTemp4 = "4SavedTemperature" ;
    public static final String savedTemp5 = "5SavedTemperature" ;
    public static final String savedTemp6 = "6SavedTemperature" ;

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
        setContentView(R.layout.activity_temperature_3);


        setUpTemperatureGUI();
        displayTemperature();
        update();

    }

    public void setUpTemperatureGUI() {
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
        upBtn1 = (ImageButton) findViewById(R.id.adjust_up1);
        upBtn1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                upTemp1();
            }
        });
        upBtn2 = (ImageButton) findViewById(R.id.adjust_up2);
        upBtn2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                upTemp2();
            }
        });
        upBtn3 = (ImageButton) findViewById(R.id.adjust_up3);
        upBtn3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                upTemp3();
            }
        });
        upBtn4 = (ImageButton) findViewById(R.id.adjust_up4);
        upBtn4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                upTemp4();
            }
        });
        upBtn5 = (ImageButton) findViewById(R.id.adjust_up5);
        upBtn5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                upTemp5();
            }
        });

        // down temp button
        downBtn = (ImageButton) findViewById(R.id.adjust_down);
        downBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                downTemp();
            }
        });
        downBtn1 = (ImageButton) findViewById(R.id.adjust_down1);
        downBtn1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                downTemp1();
            }
        });
        downBtn2 = (ImageButton) findViewById(R.id.adjust_down2);
        downBtn2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                downTemp2();
            }
        });
        downBtn3 = (ImageButton) findViewById(R.id.adjust_down3);
        downBtn3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                downTemp3();
            }
        });
        downBtn4 = (ImageButton) findViewById(R.id.adjust_down4);
        downBtn4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                downTemp4();
            }
        });
        downBtn5 = (ImageButton) findViewById(R.id.adjust_down5);
        downBtn5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                downTemp5();
            }
        });

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
    public void goToHome() {
        Intent intent = new Intent(this, MainActivity_3.class);
        startActivity(intent);
    }

    public void update() {
        Thread mThread = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    Random rnd = new Random();
                    try {
                        TimeUnit.SECONDS.sleep(rnd.nextInt(1000));
                        requestNewTemperature();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    try {
                        TimeUnit.SECONDS.sleep(rnd.nextInt(1000));
                        displayTemperature();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        mThread.start();
    }

    public void requestNewTemperature() {
        MainActivity_3.sendText("Command:007262112,1");
        MainActivity_3.sendText("Command:007262112,2");
        MainActivity_3.sendText("Command:007262112,3");
        MainActivity_3.sendText("Command:007262112,4");
        MainActivity_3.sendText("Command:007262112,5");
        MainActivity_3.sendText("Command:007262112,6");
    }

    public void displayTemperature() {
        for(int i=1; i <= 6;i++) {
            switch (i){
                case 1:
                    try {
                        SharedPreferences tempSettings = getSharedPreferences(savedTemp1, 0);
                        if (tempSettings.contains("channel")) {
                            String channel1 = tempSettings.getString("channel", "1");
                            String mode1 = tempSettings.getString("mode", "1");
                            String holiday1 = tempSettings.getString("holiday", "18") + " °C";
                            String day1 = tempSettings.getString("day", "23") + " °C";
                            String night1 = tempSettings.getString("night", "21") + " °C";
                            String away1 = tempSettings.getString("away", "19") + " °C";
                            String current1 = tempSettings.getString("current", "22") + " °C";

                            ctemp1.setText(current1);
                            int m = Integer.parseInt(mode1);
                            switch (m) {
                                case 1:
                                    stemp1.setText(holiday1);
                                    break;
                                case 2:
                                    stemp1.setText(day1);
                                    break;
                                case 3:
                                    stemp1.setText(night1);
                                    break;
                                case 4:
                                    stemp1.setText(away1);
                                    break;
                                default:
                                    stemp1.setText(day1);
                                    break;
                            }
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    try {
                        SharedPreferences tempSettings = getSharedPreferences(savedTemp2, 0);
                        if (tempSettings.contains("channel")) {
                            String channel2 = tempSettings.getString("channel", "1");
                            String mode2 = tempSettings.getString("mode", "1");
                            String holiday2 = tempSettings.getString("holiday", "18") + " °C";
                            String day2 = tempSettings.getString("day", "23") + " °C";
                            String night2 = tempSettings.getString("night", "21") + " °C";
                            String away2 = tempSettings.getString("away", "19") + " °C";
                            String current2 = tempSettings.getString("current", "22") + " °C";

                            ctemp2.setText(current2);
                            int m = Integer.parseInt(mode2);
                            switch (m) {
                                case 1:
                                    stemp2.setText(holiday2);
                                    break;
                                case 2:
                                    stemp2.setText(day2);
                                    break;
                                case 3:
                                    stemp2.setText(night2);
                                    break;
                                case 4:
                                    stemp2.setText(away2);
                                    break;
                                default:
                                    stemp2.setText(day2);
                                    break;
                            }
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    try {
                        SharedPreferences tempSettings = getSharedPreferences(savedTemp3, 0);
                        if (tempSettings.contains("channel")) {
                            String channel3 = tempSettings.getString("channel", "1");
                            String mode3 = tempSettings.getString("mode", "1");
                            String holiday3 = tempSettings.getString("holiday", "18") + " °C";
                            String day3 = tempSettings.getString("day", "23") + " °C";
                            String night3 = tempSettings.getString("night", "21") + " °C";
                            String away3 = tempSettings.getString("away", "19") + " °C";
                            String current3 = tempSettings.getString("current", "22") + " °C";

                            ctemp3.setText(current3);
                            int m = Integer.parseInt(mode3);
                            switch (m) {
                                case 1:
                                    stemp3.setText(holiday3);
                                    break;
                                case 2:
                                    stemp3.setText(day3);
                                    break;
                                case 3:
                                    stemp3.setText(night3);
                                    break;
                                case 4:
                                    stemp3.setText(away3);
                                    break;
                                default:
                                    stemp3.setText(day3);
                                    break;
                            }
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    break;
                case 4:
                    try {
                        SharedPreferences tempSettings = getSharedPreferences(savedTemp4, 0);
                        if (tempSettings.contains("channel")) {
                            String channel4 = tempSettings.getString("channel", "1");
                            String mode4 = tempSettings.getString("mode", "1");
                            String holiday4 = tempSettings.getString("holiday", "18") + " °C";
                            String day4 = tempSettings.getString("day", "23") + " °C";
                            String night4 = tempSettings.getString("night", "21") + " °C";
                            String away4 = tempSettings.getString("away", "19") + " °C";
                            String current4 = tempSettings.getString("current", "22") + " °C";

                            ctemp4.setText(current4);
                            int m = Integer.parseInt(mode4);
                            switch (m) {
                                case 1:
                                    stemp4.setText(holiday4);
                                    break;
                                case 2:
                                    stemp4.setText(day4);
                                    break;
                                case 3:
                                    stemp4.setText(night4);
                                    break;
                                case 4:
                                    stemp4.setText(away4);
                                    break;
                                default:
                                    stemp4.setText(day4);
                                    break;
                            }
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    break;
                case 5:
                    try {
                        SharedPreferences tempSettings = getSharedPreferences(savedTemp5, 0);
                        if (tempSettings.contains("channel")) {
                            String channel5 = tempSettings.getString("channel", "1");
                            String mode5 = tempSettings.getString("mode", "1");
                            String holiday5 = tempSettings.getString("holiday", "18") + " °C";
                            String day5 = tempSettings.getString("day", "23") + " °C";
                            String night5 = tempSettings.getString("night", "21") + " °C";
                            String away5 = tempSettings.getString("away", "19") + " °C";
                            String current5 = tempSettings.getString("current", "22") + " °C";

                            ctemp5.setText(current5);
                            int m = Integer.parseInt(mode5);
                            switch (m) {
                                case 1:
                                    stemp5.setText(holiday5);
                                    break;
                                case 2:
                                    stemp5.setText(day5);
                                    break;
                                case 3:
                                    stemp5.setText(night5);
                                    break;
                                case 4:
                                    stemp5.setText(away5);
                                    break;
                                default:
                                    stemp5.setText(day5);
                                    break;
                            }
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    break;
                case 6:
                    try {
                        SharedPreferences tempSettings = getSharedPreferences(savedTemp6, 0);
                        if (tempSettings.contains("channel")) {
                            String channel6 = tempSettings.getString("channel", "1");
                            String mode6 = tempSettings.getString("mode", "1");
                            String holiday6 = tempSettings.getString("holiday", "18") + " °C";
                            String day6 = tempSettings.getString("day", "23") + " °C";
                            String night6 = tempSettings.getString("night", "21") + " °C";
                            String away6 = tempSettings.getString("away", "19") + " °C";
                            String current6 = tempSettings.getString("current", "22") + " °C";

                            ctemp6.setText(current6);
                            int m = Integer.parseInt(mode6);
                            switch (m) {
                                case 1:
                                    stemp6.setText(holiday6);
                                    break;
                                case 2:
                                    stemp6.setText(day6);
                                    break;
                                case 3:
                                    stemp6.setText(night6);
                                    break;
                                case 4:
                                    stemp6.setText(away6);
                                    break;
                                default:
                                    stemp6.setText(day6);
                                    break;
                            }
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    break;
                default:
                    break;

            }
        }
    }

    public void upTemp() {
        SharedPreferences changeTempSettings = getSharedPreferences(savedTemp1, 0);
        SharedPreferences.Editor editor = changeTempSettings.edit();

        String channel = changeTempSettings.getString("channel", "1");
        String mode = changeTempSettings.getString("mode", "1");
        String holiday = changeTempSettings.getString("holiday", "18");
        String day = changeTempSettings.getString("day", "23");
        String night = changeTempSettings.getString("night", "21");
        String away = changeTempSettings.getString("away", "19");
        String current = changeTempSettings.getString("current", "22");

        int m = Integer.parseInt(mode);
        switch (m) {
            case 1:
                int h1 = Integer.parseInt(holiday);
                int c1 = Integer.parseInt(current);
                String wateringFlag1;
                h1++;
                holiday = Integer.toString(h1);

                editor.putString("holiday", holiday);
                editor.commit();
                if( h1 < c1) {
                    wateringFlag1 = "1";
                }
                else wateringFlag1 = "0";

                MainActivity_3.sendText("Command:007260112," + channel + ",0,0," + mode + ","
                + holiday + "," + day + "," + night + "," + away + "," + wateringFlag1 + ","
                + "15");
                holiday = holiday + " °C";
                stemp1.setText(holiday);
                break;
            case 2:
                int h2 = Integer.parseInt(day);
                int c2 = Integer.parseInt(current);
                String wateringFlag2;
                h2++;
                day = Integer.toString(h2);

                editor.putString("day", day);
                editor.commit();
                if( h2 < c2) {
                    wateringFlag2 = "1";
                }
                else wateringFlag2 = "0";

                MainActivity_3.sendText("Command:007260112," + channel + ",0,0," + mode + ","
                        + holiday + "," + day + "," + night + "," + away + "," + wateringFlag2 + ","
                        + "15");
                day = day + " °C";
                stemp1.setText(day);
                break;
            case 3:
                int h3 = Integer.parseInt(night);
                int c3 = Integer.parseInt(current);
                String wateringFlag3;
                h3++;
                night = Integer.toString(h3);

                editor.putString("night", night);
                editor.commit();
                if( h3 < c3) {
                    wateringFlag3 = "1";
                }
                else wateringFlag3 = "0";

                MainActivity_3.sendText("Command:007260112," + channel + ",0,0," + mode + ","
                        + holiday + "," + day + "," + night + "," + away + "," + wateringFlag3 + ","
                        + "15");
                night = night + " °C";
                stemp1.setText(night);
                break;
            case 4:
                int h4 = Integer.parseInt(away);
                int c4 = Integer.parseInt(current);
                String wateringFlag4;
                h4++;
                away = Integer.toString(h4);

                editor.putString("away", away);
                editor.commit();
                if( h4 < c4) {
                    wateringFlag4 = "1";
                }
                else wateringFlag4 = "0";

                MainActivity_3.sendText("Command:007260112," + channel + ",0,0," + mode + ","
                        + holiday + "," + day + "," + night + "," + away + "," + wateringFlag4 + ","
                        + "15");
                away = away + " °C";
                stemp1.setText(away);
                break;
            default:
                break;
        }
    }
    public void upTemp1() {
        SharedPreferences changeTempSettings = getSharedPreferences(savedTemp2, 0);
        SharedPreferences.Editor editor = changeTempSettings.edit();

        String channel = changeTempSettings.getString("channel", "1");
        String mode = changeTempSettings.getString("mode", "1");
        String holiday = changeTempSettings.getString("holiday", "18");
        String day = changeTempSettings.getString("day", "23");
        String night = changeTempSettings.getString("night", "21");
        String away = changeTempSettings.getString("away", "19");
        String current = changeTempSettings.getString("current", "22");

        int m = Integer.parseInt(mode);
        switch (m) {
            case 1:
                int h1 = Integer.parseInt(holiday);
                int c1 = Integer.parseInt(current);
                String wateringFlag1;
                h1++;
                holiday = Integer.toString(h1);

                editor.putString("holiday", holiday);
                editor.commit();
                if( h1 < c1) {
                    wateringFlag1 = "1";
                }
                else wateringFlag1 = "0";

                MainActivity_3.sendText("Command:007260112," + channel + ",0,0," + mode + ","
                        + holiday + "," + day + "," + night + "," + away + "," + wateringFlag1 + ","
                        + "15");
                holiday = holiday + " °C";
                stemp2.setText(holiday);
                break;
            case 2:
                int h2 = Integer.parseInt(day);
                int c2 = Integer.parseInt(current);
                String wateringFlag2;
                h2++;
                day = Integer.toString(h2);

                editor.putString("day", day);
                editor.commit();
                if( h2 < c2) {
                    wateringFlag2 = "1";
                }
                else wateringFlag2 = "0";

                MainActivity_3.sendText("Command:007260112," + channel + ",0,0," + mode + ","
                        + holiday + "," + day + "," + night + "," + away + "," + wateringFlag2 + ","
                        + "15");
                day = day + " °C";
                stemp2.setText(day);
                break;
            case 3:
                int h3 = Integer.parseInt(night);
                int c3 = Integer.parseInt(current);
                String wateringFlag3;
                h3++;
                night = Integer.toString(h3);

                editor.putString("night", night);
                editor.commit();
                if( h3 < c3) {
                    wateringFlag3 = "1";
                }
                else wateringFlag3 = "0";

                MainActivity_3.sendText("Command:007260112," + channel + ",0,0," + mode + ","
                        + holiday + "," + day + "," + night + "," + away + "," + wateringFlag3 + ","
                        + "15");
                night = night + " °C";
                stemp2.setText(night);
                break;
            case 4:
                int h4 = Integer.parseInt(away);
                int c4 = Integer.parseInt(current);
                String wateringFlag4;
                h4++;
                away = Integer.toString(h4);

                editor.putString("away", away);
                editor.commit();
                if( h4 < c4) {
                    wateringFlag4 = "1";
                }
                else wateringFlag4 = "0";

                MainActivity_3.sendText("Command:007260112," + channel + ",0,0," + mode + ","
                        + holiday + "," + day + "," + night + "," + away + "," + wateringFlag4 + ","
                        + "15");
                away = away + " °C";
                stemp2.setText(away);
                break;
            default:
                break;
        }

    }
    public void upTemp2() {
        SharedPreferences changeTempSettings = getSharedPreferences(savedTemp3, 0);
        SharedPreferences.Editor editor = changeTempSettings.edit();

        String channel = changeTempSettings.getString("channel", "1");
        String mode = changeTempSettings.getString("mode", "1");
        String holiday = changeTempSettings.getString("holiday", "18");
        String day = changeTempSettings.getString("day", "23");
        String night = changeTempSettings.getString("night", "21");
        String away = changeTempSettings.getString("away", "19");
        String current = changeTempSettings.getString("current", "22");

        int m = Integer.parseInt(mode);
        switch (m) {
            case 1:
                int h1 = Integer.parseInt(holiday);
                int c1 = Integer.parseInt(current);
                String wateringFlag1;
                h1++;
                holiday = Integer.toString(h1);

                editor.putString("holiday", holiday);
                editor.commit();
                if( h1 < c1) {
                    wateringFlag1 = "1";
                }
                else wateringFlag1 = "0";

                MainActivity_3.sendText("Command:007260112," + channel + ",0,0," + mode + ","
                        + holiday + "," + day + "," + night + "," + away + "," + wateringFlag1 + ","
                        + "15");
                holiday = holiday + " °C";
                stemp3.setText(holiday);
                break;
            case 2:
                int h2 = Integer.parseInt(day);
                int c2 = Integer.parseInt(current);
                String wateringFlag2;
                h2++;
                day = Integer.toString(h2);

                editor.putString("day", day);
                editor.commit();
                if( h2 < c2) {
                    wateringFlag2 = "1";
                }
                else wateringFlag2 = "0";

                MainActivity_3.sendText("Command:007260112," + channel + ",0,0," + mode + ","
                        + holiday + "," + day + "," + night + "," + away + "," + wateringFlag2 + ","
                        + "15");
                day = day + " °C";
                stemp3.setText(day);
                break;
            case 3:
                int h3 = Integer.parseInt(night);
                int c3 = Integer.parseInt(current);
                String wateringFlag3;
                h3++;
                night = Integer.toString(h3);

                editor.putString("night", night);
                editor.commit();
                if( h3 < c3) {
                    wateringFlag3 = "1";
                }
                else wateringFlag3 = "0";

                MainActivity_3.sendText("Command:007260112," + channel + ",0,0," + mode + ","
                        + holiday + "," + day + "," + night + "," + away + "," + wateringFlag3 + ","
                        + "15");
                night = night + " °C";
                stemp3.setText(night);
                break;
            case 4:
                int h4 = Integer.parseInt(away);
                int c4 = Integer.parseInt(current);
                String wateringFlag4;
                h4++;
                away = Integer.toString(h4);

                editor.putString("away", away);
                editor.commit();
                if( h4 < c4) {
                    wateringFlag4 = "1";
                }
                else wateringFlag4 = "0";

                MainActivity_3.sendText("Command:007260112," + channel + ",0,0," + mode + ","
                        + holiday + "," + day + "," + night + "," + away + "," + wateringFlag4 + ","
                        + "15");
                away = away + " °C";
                stemp3.setText(away);
                break;
            default:
                break;
        }
        //MainActivity.sendText("Command:007260112,1,0,0,1,28,27,26,25,1,15");
    }
    public void upTemp3() {
        SharedPreferences changeTempSettings = getSharedPreferences(savedTemp4, 0);
        SharedPreferences.Editor editor = changeTempSettings.edit();

        String channel = changeTempSettings.getString("channel", "1");
        String mode = changeTempSettings.getString("mode", "1");
        String holiday = changeTempSettings.getString("holiday", "18");
        String day = changeTempSettings.getString("day", "23");
        String night = changeTempSettings.getString("night", "21");
        String away = changeTempSettings.getString("away", "19");
        String current = changeTempSettings.getString("current", "22");

        int m = Integer.parseInt(mode);
        switch (m) {
            case 1:
                int h1 = Integer.parseInt(holiday);
                int c1 = Integer.parseInt(current);
                String wateringFlag1;
                h1++;
                holiday = Integer.toString(h1);

                editor.putString("holiday", holiday);
                editor.commit();
                if( h1 < c1) {
                    wateringFlag1 = "1";
                }
                else wateringFlag1 = "0";

                MainActivity_3.sendText("Command:007260112," + channel + ",0,0," + mode + ","
                        + holiday + "," + day + "," + night + "," + away + "," + wateringFlag1 + ","
                        + "15");
                holiday = holiday + " °C";
                stemp4.setText(holiday);
                break;
            case 2:
                int h2 = Integer.parseInt(day);
                int c2 = Integer.parseInt(current);
                String wateringFlag2;
                h2++;
                day = Integer.toString(h2);

                editor.putString("day", day);
                editor.commit();
                if( h2 < c2) {
                    wateringFlag2 = "1";
                }
                else wateringFlag2 = "0";

                MainActivity_3.sendText("Command:007260112," + channel + ",0,0," + mode + ","
                        + holiday + "," + day + "," + night + "," + away + "," + wateringFlag2 + ","
                        + "15");
                day = day + " °C";
                stemp4.setText(day);
                break;
            case 3:
                int h3 = Integer.parseInt(night);
                int c3 = Integer.parseInt(current);
                String wateringFlag3;
                h3++;
                night = Integer.toString(h3);

                editor.putString("night", night);
                editor.commit();
                if( h3 < c3) {
                    wateringFlag3 = "1";
                }
                else wateringFlag3 = "0";

                MainActivity_3.sendText("Command:007260112," + channel + ",0,0," + mode + ","
                        + holiday + "," + day + "," + night + "," + away + "," + wateringFlag3 + ","
                        + "15");
                night = night + " °C";
                stemp4.setText(night);
                break;
            case 4:
                int h4 = Integer.parseInt(away);
                int c4 = Integer.parseInt(current);
                String wateringFlag4;
                h4++;
                away = Integer.toString(h4);

                editor.putString("away", away);
                editor.commit();
                if( h4 < c4) {
                    wateringFlag4 = "1";
                }
                else wateringFlag4 = "0";

                MainActivity_3.sendText("Command:007260112," + channel + ",0,0," + mode + ","
                        + holiday + "," + day + "," + night + "," + away + "," + wateringFlag4 + ","
                        + "15");
                away = away + " °C";
                stemp4.setText(away);
                break;
            default:
                break;
        }
    }
    public void upTemp4() {
        SharedPreferences changeTempSettings = getSharedPreferences(savedTemp5, 0);
        SharedPreferences.Editor editor = changeTempSettings.edit();

        String channel = changeTempSettings.getString("channel", "1");
        String mode = changeTempSettings.getString("mode", "1");
        String holiday = changeTempSettings.getString("holiday", "18");
        String day = changeTempSettings.getString("day", "23");
        String night = changeTempSettings.getString("night", "21");
        String away = changeTempSettings.getString("away", "19");
        String current = changeTempSettings.getString("current", "22");

        int m = Integer.parseInt(mode);
        switch (m) {
            case 1:
                int h1 = Integer.parseInt(holiday);
                int c1 = Integer.parseInt(current);
                String wateringFlag1;
                h1++;
                holiday = Integer.toString(h1);

                editor.putString("holiday", holiday);
                editor.commit();
                if( h1 < c1) {
                    wateringFlag1 = "1";
                }
                else wateringFlag1 = "0";

                MainActivity_3.sendText("Command:007260112," + channel + ",0,0," + mode + ","
                        + holiday + "," + day + "," + night + "," + away + "," + wateringFlag1 + ","
                        + "15");
                holiday = holiday + " °C";
                stemp5.setText(holiday);
                break;
            case 2:
                int h2 = Integer.parseInt(day);
                int c2 = Integer.parseInt(current);
                String wateringFlag2;
                h2++;
                day = Integer.toString(h2);

                editor.putString("day", day);
                editor.commit();
                if( h2 < c2) {
                    wateringFlag2 = "1";
                }
                else wateringFlag2 = "0";

                MainActivity_3.sendText("Command:007260112," + channel + ",0,0," + mode + ","
                        + holiday + "," + day + "," + night + "," + away + "," + wateringFlag2 + ","
                        + "15");
                day = day + " °C";
                stemp5.setText(day);
                break;
            case 3:
                int h3 = Integer.parseInt(night);
                int c3 = Integer.parseInt(current);
                String wateringFlag3;
                h3++;
                night = Integer.toString(h3);

                editor.putString("night", night);
                editor.commit();
                if( h3 < c3) {
                    wateringFlag3 = "1";
                }
                else wateringFlag3 = "0";

                MainActivity_3.sendText("Command:007260112," + channel + ",0,0," + mode + ","
                        + holiday + "," + day + "," + night + "," + away + "," + wateringFlag3 + ","
                        + "15");
                night = night + " °C";
                stemp5.setText(night);
                break;
            case 4:
                int h4 = Integer.parseInt(away);
                int c4 = Integer.parseInt(current);
                String wateringFlag4;
                h4++;
                away = Integer.toString(h4);

                editor.putString("away", away);
                editor.commit();
                if( h4 < c4) {
                    wateringFlag4 = "1";
                }
                else wateringFlag4 = "0";

                MainActivity_3.sendText("Command:007260112," + channel + ",0,0," + mode + ","
                        + holiday + "," + day + "," + night + "," + away + "," + wateringFlag4 + ","
                        + "15");
                away = away + " °C";
                stemp5.setText(away);
                break;
            default:
                break;
        }
    }
    public void upTemp5() {
        SharedPreferences changeTempSettings = getSharedPreferences(savedTemp6, 0);
        SharedPreferences.Editor editor = changeTempSettings.edit();

        String channel = changeTempSettings.getString("channel", "1");
        String mode = changeTempSettings.getString("mode", "1");
        String holiday = changeTempSettings.getString("holiday", "18");
        String day = changeTempSettings.getString("day", "23");
        String night = changeTempSettings.getString("night", "21");
        String away = changeTempSettings.getString("away", "19");
        String current = changeTempSettings.getString("current", "22");

        int m = Integer.parseInt(mode);
        switch (m) {
            case 1:
                int h1 = Integer.parseInt(holiday);
                int c1 = Integer.parseInt(current);
                String wateringFlag1;
                h1++;
                holiday = Integer.toString(h1);

                editor.putString("holiday", holiday);
                editor.commit();
                if( h1 < c1) {
                    wateringFlag1 = "1";
                }
                else wateringFlag1 = "0";

                MainActivity_3.sendText("Command:007260112," + channel + ",0,0," + mode + ","
                        + holiday + "," + day + "," + night + "," + away + "," + wateringFlag1 + ","
                        + "15");
                holiday = holiday + " °C";
                stemp6.setText(holiday);
                break;
            case 2:
                int h2 = Integer.parseInt(day);
                int c2 = Integer.parseInt(current);
                String wateringFlag2;
                h2++;
                day = Integer.toString(h2);

                editor.putString("day", day);
                editor.commit();
                if( h2 < c2) {
                    wateringFlag2 = "1";
                }
                else wateringFlag2 = "0";

                MainActivity_3.sendText("Command:007260112," + channel + ",0,0," + mode + ","
                        + holiday + "," + day + "," + night + "," + away + "," + wateringFlag2 + ","
                        + "15");
                day = day + " °C";
                stemp6.setText(day);
                break;
            case 3:
                int h3 = Integer.parseInt(night);
                int c3 = Integer.parseInt(current);
                String wateringFlag3;
                h3++;
                night = Integer.toString(h3);

                editor.putString("night", night);
                editor.commit();
                if( h3 < c3) {
                    wateringFlag3 = "1";
                }
                else wateringFlag3 = "0";

                MainActivity_3.sendText("Command:007260112," + channel + ",0,0," + mode + ","
                        + holiday + "," + day + "," + night + "," + away + "," + wateringFlag3 + ","
                        + "15");
                night = night + " °C";
                stemp6.setText(night);
                break;
            case 4:
                int h4 = Integer.parseInt(away);
                int c4 = Integer.parseInt(current);
                String wateringFlag4;
                h4++;
                away = Integer.toString(h4);

                editor.putString("away", away);
                editor.commit();
                if( h4 < c4) {
                    wateringFlag4 = "1";
                }
                else wateringFlag4 = "0";

                MainActivity_3.sendText("Command:007260112," + channel + ",0,0," + mode + ","
                        + holiday + "," + day + "," + night + "," + away + "," + wateringFlag4 + ","
                        + "15");
                away = away + " °C";
                stemp6.setText(away);
                break;
            default:
                break;
        }

    }
    public void downTemp() {
        SharedPreferences changeTempSettings = getSharedPreferences(savedTemp1, 0);
        SharedPreferences.Editor editor = changeTempSettings.edit();

        String channel = changeTempSettings.getString("channel", "1");
        String mode = changeTempSettings.getString("mode", "1");
        String holiday = changeTempSettings.getString("holiday", "18");
        String day = changeTempSettings.getString("day", "23");
        String night = changeTempSettings.getString("night", "21");
        String away = changeTempSettings.getString("away", "19");
        String current = changeTempSettings.getString("current", "22");

        int m = Integer.parseInt(mode);
        switch (m) {
            case 1:
                int h1 = Integer.parseInt(holiday);
                int c1 = Integer.parseInt(current);
                String wateringFlag1;
                h1--;
                holiday = Integer.toString(h1);

                editor.putString("holiday", holiday);
                editor.commit();
                if( h1 < c1) {
                    wateringFlag1 = "1";
                }
                else wateringFlag1 = "0";

                MainActivity_3.sendText("Command:007260112," + channel + ",0,0," + mode + ","
                        + holiday + "," + day + "," + night + "," + away + "," + wateringFlag1 + ","
                        + "15");
                holiday = holiday + " °C";
                stemp1.setText(holiday);
                break;
            case 2:
                int h2 = Integer.parseInt(day);
                int c2 = Integer.parseInt(current);
                String wateringFlag2;
                h2--;
                day = Integer.toString(h2);

                editor.putString("day", day);
                editor.commit();
                if( h2 < c2) {
                    wateringFlag2 = "1";
                }
                else wateringFlag2 = "0";

                MainActivity_3.sendText("Command:007260112," + channel + ",0,0," + mode + ","
                        + holiday + "," + day + "," + night + "," + away + "," + wateringFlag2 + ","
                        + "15");
                day = day + " °C";
                stemp1.setText(day);
                break;
            case 3:
                int h3 = Integer.parseInt(night);
                int c3 = Integer.parseInt(current);
                String wateringFlag3;
                h3--;
                night = Integer.toString(h3);

                editor.putString("night", night);
                editor.commit();
                if( h3 < c3) {
                    wateringFlag3 = "1";
                }
                else wateringFlag3 = "0";

                MainActivity_3.sendText("Command:007260112," + channel + ",0,0," + mode + ","
                        + holiday + "," + day + "," + night + "," + away + "," + wateringFlag3 + ","
                        + "15");
                night = night + " °C";
                stemp1.setText(night);
                break;
            case 4:
                int h4 = Integer.parseInt(away);
                int c4 = Integer.parseInt(current);
                String wateringFlag4;
                h4--;
                away = Integer.toString(h4);

                editor.putString("away", away);
                editor.commit();
                if( h4 < c4) {
                    wateringFlag4 = "1";
                }
                else wateringFlag4 = "0";

                MainActivity_3.sendText("Command:007260112," + channel + ",0,0," + mode + ","
                        + holiday + "," + day + "," + night + "," + away + "," + wateringFlag4 + ","
                        + "15");
                away = away + " °C";
                stemp1.setText(away);
                break;
            default:
                break;
        }
    }
    public void downTemp1() {
        SharedPreferences changeTempSettings = getSharedPreferences(savedTemp2, 0);
        SharedPreferences.Editor editor = changeTempSettings.edit();

        String channel = changeTempSettings.getString("channel", "1");
        String mode = changeTempSettings.getString("mode", "1");
        String holiday = changeTempSettings.getString("holiday", "18");
        String day = changeTempSettings.getString("day", "23");
        String night = changeTempSettings.getString("night", "21");
        String away = changeTempSettings.getString("away", "19");
        String current = changeTempSettings.getString("current", "22");

        int m = Integer.parseInt(mode);
        switch (m) {
            case 1:
                int h1 = Integer.parseInt(holiday);
                int c1 = Integer.parseInt(current);
                String wateringFlag1;
                h1--;
                holiday = Integer.toString(h1);

                editor.putString("holiday", holiday);
                editor.commit();
                if( h1 < c1) {
                    wateringFlag1 = "1";
                }
                else wateringFlag1 = "0";

                MainActivity_3.sendText("Command:007260112," + channel + ",0,0," + mode + ","
                        + holiday + "," + day + "," + night + "," + away + "," + wateringFlag1 + ","
                        + "15");
                holiday = holiday + " °C";
                stemp2.setText(holiday);
                break;
            case 2:
                int h2 = Integer.parseInt(day);
                int c2 = Integer.parseInt(current);
                String wateringFlag2;
                h2--;
                day = Integer.toString(h2);

                editor.putString("day", day);
                editor.commit();
                if( h2 < c2) {
                    wateringFlag2 = "1";
                }
                else wateringFlag2 = "0";

                MainActivity_3.sendText("Command:007260112," + channel + ",0,0," + mode + ","
                        + holiday + "," + day + "," + night + "," + away + "," + wateringFlag2 + ","
                        + "15");
                day = day + " °C";
                stemp2.setText(day);
                break;
            case 3:
                int h3 = Integer.parseInt(night);
                int c3 = Integer.parseInt(current);
                String wateringFlag3;
                h3--;
                night = Integer.toString(h3);

                editor.putString("night", night);
                editor.commit();
                if( h3 < c3) {
                    wateringFlag3 = "1";
                }
                else wateringFlag3 = "0";

                MainActivity_3.sendText("Command:007260112," + channel + ",0,0," + mode + ","
                        + holiday + "," + day + "," + night + "," + away + "," + wateringFlag3 + ","
                        + "15");
                night = night + " °C";
                stemp2.setText(night);
                break;
            case 4:
                int h4 = Integer.parseInt(away);
                int c4 = Integer.parseInt(current);
                String wateringFlag4;
                h4--;
                away = Integer.toString(h4);

                editor.putString("away", away);
                editor.commit();
                if( h4 < c4) {
                    wateringFlag4 = "1";
                }
                else wateringFlag4 = "0";

                MainActivity_3.sendText("Command:007260112," + channel + ",0,0," + mode + ","
                        + holiday + "," + day + "," + night + "," + away + "," + wateringFlag4 + ","
                        + "15");
                away = away + " °C";
                stemp2.setText(away);
                break;
            default:
                break;
        }
    }
    public void downTemp2() {
        SharedPreferences changeTempSettings = getSharedPreferences(savedTemp3, 0);
        SharedPreferences.Editor editor = changeTempSettings.edit();

        String channel = changeTempSettings.getString("channel", "1");
        String mode = changeTempSettings.getString("mode", "1");
        String holiday = changeTempSettings.getString("holiday", "18");
        String day = changeTempSettings.getString("day", "23");
        String night = changeTempSettings.getString("night", "21");
        String away = changeTempSettings.getString("away", "19");
        String current = changeTempSettings.getString("current", "22");

        int m = Integer.parseInt(mode);
        switch (m) {
            case 1:
                int h1 = Integer.parseInt(holiday);
                int c1 = Integer.parseInt(current);
                String wateringFlag1;
                h1--;
                holiday = Integer.toString(h1);

                editor.putString("holiday", holiday);
                editor.commit();
                if( h1 < c1) {
                    wateringFlag1 = "1";
                }
                else wateringFlag1 = "0";

                MainActivity_3.sendText("Command:007260112," + channel + ",0,0," + mode + ","
                        + holiday + "," + day + "," + night + "," + away + "," + wateringFlag1 + ","
                        + "15");
                holiday = holiday + " °C";
                stemp3.setText(holiday);
                break;
            case 2:
                int h2 = Integer.parseInt(day);
                int c2 = Integer.parseInt(current);
                String wateringFlag2;
                h2--;
                day = Integer.toString(h2);

                editor.putString("day", day);
                editor.commit();
                if( h2 < c2) {
                    wateringFlag2 = "1";
                }
                else wateringFlag2 = "0";

                MainActivity_3.sendText("Command:007260112," + channel + ",0,0," + mode + ","
                        + holiday + "," + day + "," + night + "," + away + "," + wateringFlag2 + ","
                        + "15");
                day = day + " °C";
                stemp3.setText(day);
                break;
            case 3:
                int h3 = Integer.parseInt(night);
                int c3 = Integer.parseInt(current);
                String wateringFlag3;
                h3--;
                night = Integer.toString(h3);

                editor.putString("night", night);
                editor.commit();
                if( h3 < c3) {
                    wateringFlag3 = "1";
                }
                else wateringFlag3 = "0";

                MainActivity_3.sendText("Command:007260112," + channel + ",0,0," + mode + ","
                        + holiday + "," + day + "," + night + "," + away + "," + wateringFlag3 + ","
                        + "15");
                night = night + " °C";
                stemp3.setText(night);
                break;
            case 4:
                int h4 = Integer.parseInt(away);
                int c4 = Integer.parseInt(current);
                String wateringFlag4;
                h4--;
                away = Integer.toString(h4);

                editor.putString("away", away);
                editor.commit();
                if( h4 < c4) {
                    wateringFlag4 = "1";
                }
                else wateringFlag4 = "0";

                MainActivity_3.sendText("Command:007260112," + channel + ",0,0," + mode + ","
                        + holiday + "," + day + "," + night + "," + away + "," + wateringFlag4 + ","
                        + "15");
                away = away + " °C";
                stemp3.setText(away);
                break;
            default:
                break;
        }
    }
    public void downTemp3() {
        SharedPreferences changeTempSettings = getSharedPreferences(savedTemp4, 0);
        SharedPreferences.Editor editor = changeTempSettings.edit();

        String channel = changeTempSettings.getString("channel", "1");
        String mode = changeTempSettings.getString("mode", "1");
        String holiday = changeTempSettings.getString("holiday", "18");
        String day = changeTempSettings.getString("day", "23");
        String night = changeTempSettings.getString("night", "21");
        String away = changeTempSettings.getString("away", "19");
        String current = changeTempSettings.getString("current", "22");

        int m = Integer.parseInt(mode);
        switch (m) {
            case 1:
                int h1 = Integer.parseInt(holiday);
                int c1 = Integer.parseInt(current);
                String wateringFlag1;
                h1--;
                holiday = Integer.toString(h1);

                editor.putString("holiday", holiday);
                editor.commit();
                if( h1 < c1) {
                    wateringFlag1 = "1";
                }
                else wateringFlag1 = "0";

                MainActivity_3.sendText("Command:007260112," + channel + ",0,0," + mode + ","
                        + holiday + "," + day + "," + night + "," + away + "," + wateringFlag1 + ","
                        + "15");
                holiday = holiday + " °C";
                stemp4.setText(holiday);
                break;
            case 2:
                int h2 = Integer.parseInt(day);
                int c2 = Integer.parseInt(current);
                String wateringFlag2;
                h2--;
                day = Integer.toString(h2);

                editor.putString("day", day);
                editor.commit();
                if( h2 < c2) {
                    wateringFlag2 = "1";
                }
                else wateringFlag2 = "0";

                MainActivity_3.sendText("Command:007260112," + channel + ",0,0," + mode + ","
                        + holiday + "," + day + "," + night + "," + away + "," + wateringFlag2 + ","
                        + "15");
                day = day + " °C";
                stemp4.setText(day);
                break;
            case 3:
                int h3 = Integer.parseInt(night);
                int c3 = Integer.parseInt(current);
                String wateringFlag3;
                h3--;
                night = Integer.toString(h3);

                editor.putString("night", night);
                editor.commit();
                if( h3 < c3) {
                    wateringFlag3 = "1";
                }
                else wateringFlag3 = "0";

                MainActivity_3.sendText("Command:007260112," + channel + ",0,0," + mode + ","
                        + holiday + "," + day + "," + night + "," + away + "," + wateringFlag3 + ","
                        + "15");
                night = night + " °C";
                stemp4.setText(night);
                break;
            case 4:
                int h4 = Integer.parseInt(away);
                int c4 = Integer.parseInt(current);
                String wateringFlag4;
                h4--;
                away = Integer.toString(h4);

                editor.putString("away", away);
                editor.commit();
                if( h4 < c4) {
                    wateringFlag4 = "1";
                }
                else wateringFlag4 = "0";

                MainActivity_3.sendText("Command:007260112," + channel + ",0,0," + mode + ","
                        + holiday + "," + day + "," + night + "," + away + "," + wateringFlag4 + ","
                        + "15");
                away = away + " °C";
                stemp4.setText(away);
                break;
            default:
                break;
        }
    }
    public void downTemp4() {
        SharedPreferences changeTempSettings = getSharedPreferences(savedTemp5, 0);
        SharedPreferences.Editor editor = changeTempSettings.edit();

        String channel = changeTempSettings.getString("channel", "1");
        String mode = changeTempSettings.getString("mode", "1");
        String holiday = changeTempSettings.getString("holiday", "18");
        String day = changeTempSettings.getString("day", "23");
        String night = changeTempSettings.getString("night", "21");
        String away = changeTempSettings.getString("away", "19");
        String current = changeTempSettings.getString("current", "22");

        int m = Integer.parseInt(mode);
        switch (m) {
            case 1:
                int h1 = Integer.parseInt(holiday);
                int c1 = Integer.parseInt(current);
                String wateringFlag1;
                h1--;
                holiday = Integer.toString(h1);

                editor.putString("holiday", holiday);
                editor.commit();
                if( h1 < c1) {
                    wateringFlag1 = "1";
                }
                else wateringFlag1 = "0";

                MainActivity_3.sendText("Command:007260112," + channel + ",0,0," + mode + ","
                        + holiday + "," + day + "," + night + "," + away + "," + wateringFlag1 + ","
                        + "15");
                holiday = holiday + " °C";
                stemp5.setText(holiday);
                break;
            case 2:
                int h2 = Integer.parseInt(day);
                int c2 = Integer.parseInt(current);
                String wateringFlag2;
                h2--;
                day = Integer.toString(h2);

                editor.putString("day", day);
                editor.commit();
                if( h2 < c2) {
                    wateringFlag2 = "1";
                }
                else wateringFlag2 = "0";

                MainActivity_3.sendText("Command:007260112," + channel + ",0,0," + mode + ","
                        + holiday + "," + day + "," + night + "," + away + "," + wateringFlag2 + ","
                        + "15");
                day = day + " °C";
                stemp5.setText(day);
                break;
            case 3:
                int h3 = Integer.parseInt(night);
                int c3 = Integer.parseInt(current);
                String wateringFlag3;
                h3--;
                night = Integer.toString(h3);

                editor.putString("night", night);
                editor.commit();
                if( h3 < c3) {
                    wateringFlag3 = "1";
                }
                else wateringFlag3 = "0";

                MainActivity_3.sendText("Command:007260112," + channel + ",0,0," + mode + ","
                        + holiday + "," + day + "," + night + "," + away + "," + wateringFlag3 + ","
                        + "15");
                night = night + " °C";
                stemp5.setText(night);
                break;
            case 4:
                int h4 = Integer.parseInt(away);
                int c4 = Integer.parseInt(current);
                String wateringFlag4;
                h4--;
                away = Integer.toString(h4);

                editor.putString("away", away);
                editor.commit();
                if( h4 < c4) {
                    wateringFlag4 = "1";
                }
                else wateringFlag4 = "0";

                MainActivity_3.sendText("Command:007260112," + channel + ",0,0," + mode + ","
                        + holiday + "," + day + "," + night + "," + away + "," + wateringFlag4 + ","
                        + "15");
                away = away + " °C";
                stemp5.setText(away);
                break;
            default:
                break;
        }
    }
    public void downTemp5() {
        SharedPreferences changeTempSettings = getSharedPreferences(savedTemp6, 0);
        SharedPreferences.Editor editor = changeTempSettings.edit();

        String channel = changeTempSettings.getString("channel", "1");
        String mode = changeTempSettings.getString("mode", "1");
        String holiday = changeTempSettings.getString("holiday", "18");
        String day = changeTempSettings.getString("day", "23");
        String night = changeTempSettings.getString("night", "21");
        String away = changeTempSettings.getString("away", "19");
        String current = changeTempSettings.getString("current", "22");

        int m = Integer.parseInt(mode);
        switch (m) {
            case 1:
                int h1 = Integer.parseInt(holiday);
                int c1 = Integer.parseInt(current);
                String wateringFlag1;
                h1--;
                holiday = Integer.toString(h1);

                editor.putString("holiday", holiday);
                editor.commit();
                if( h1 < c1) {
                    wateringFlag1 = "1";
                }
                else wateringFlag1 = "0";

                MainActivity_3.sendText("Command:007260112," + channel + ",0,0," + mode + ","
                        + holiday + "," + day + "," + night + "," + away + "," + wateringFlag1 + ","
                        + "15");
                holiday = holiday + " °C";
                stemp6.setText(holiday);
                break;
            case 2:
                int h2 = Integer.parseInt(day);
                int c2 = Integer.parseInt(current);
                String wateringFlag2;
                h2--;
                day = Integer.toString(h2);

                editor.putString("day", day);
                editor.commit();
                if( h2 < c2) {
                    wateringFlag2 = "1";
                }
                else wateringFlag2 = "0";

                MainActivity_3.sendText("Command:007260112," + channel + ",0,0," + mode + ","
                        + holiday + "," + day + "," + night + "," + away + "," + wateringFlag2 + ","
                        + "15");
                day = day + " °C";
                stemp6.setText(day);
                break;
            case 3:
                int h3 = Integer.parseInt(night);
                int c3 = Integer.parseInt(current);
                String wateringFlag3;
                h3--;
                night = Integer.toString(h3);

                editor.putString("night", night);
                editor.commit();
                if( h3 < c3) {
                    wateringFlag3 = "1";
                }
                else wateringFlag3 = "0";

                MainActivity_3.sendText("Command:007260112," + channel + ",0,0," + mode + ","
                        + holiday + "," + day + "," + night + "," + away + "," + wateringFlag3 + ","
                        + "15");
                night = night + " °C";
                stemp6.setText(night);
                break;
            case 4:
                int h4 = Integer.parseInt(away);
                int c4 = Integer.parseInt(current);
                String wateringFlag4;
                h4--;
                away = Integer.toString(h4);

                editor.putString("away", away);
                editor.commit();
                if( h4 < c4) {
                    wateringFlag4 = "1";
                }
                else wateringFlag4 = "0";

                MainActivity_3.sendText("Command:007260112," + channel + ",0,0," + mode + ","
                        + holiday + "," + day + "," + night + "," + away + "," + wateringFlag4 + ","
                        + "15");
                away = away + " °C";
                stemp6.setText(away);
                break;
            default:
                break;
        }
    }
}

//******************************UNUSED CODE****************************************
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

        getZoneTemp();
        for(int i=0; i < tempZone.size(); ) {
          //  createTempZone(i);
        }


  public void getZoneTemp() {
       /* for(int i = 0; i < MainActivity.tempZone.size(); i++) {
            tempZone.add(MainActivity.tempZone.get(i));
        }

if(!MainActivity.TemperatureIsEmpty()) {
        for(int i=0; i < MainActivity.returnTemperatureSize(); i++) {
        tempZone.add(MainActivity.returnTemperature(i));

        Log.d("string", tempZone.get(i).channel);
        }

        // Log.d("string", tempZone.get(0).channel);
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


        Thread tempThread = new Thread (new Runnable() {

            @Override
            public void run() {
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

            }
        });
        tempThread.start(); */
    /*

    }
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