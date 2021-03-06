package com.weebly.smarthusgruppen.shk_applikasjon.Leilighet;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.weebly.smarthusgruppen.shk_applikasjon.R;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * This class allows the user to see the current temperature of all heatingsones of
 * this dwelling unit. The user can also change the temperature of all these heating sones.
 * When a temperature is changed, a message is sent to the server and then to the heating-
 * controller which changes the temperature currently set.
 */
public class Temperature_2 extends AppCompatActivity {
    ImageButton homeBtn;
    ImageButton upBtn;
    ImageButton upBtn1;
    ImageButton upBtn2;
    ImageButton upBtn3;
    ImageButton upBtn4;
    ImageButton downBtn;
    ImageButton downBtn1;
    ImageButton downBtn2;
    ImageButton downBtn3;
    ImageButton downBtn4;

    public static final String savedTemp1 = "1SavedTemperature_2" ;
    public static final String savedTemp2 = "2SavedTemperature_2" ;
    public static final String savedTemp3 = "3SavedTemperature_2" ;
    public static final String savedTemp4 = "4SavedTemperature_2" ;
    public static final String savedTemp5 = "5SavedTemperature_2" ;

    TextView stemp1;
    TextView stemp2;
    TextView stemp3;
    TextView stemp4;
    TextView stemp5;

    TextView ctemp1;
    TextView ctemp2;
    TextView ctemp3;
    TextView ctemp4;
    TextView ctemp5;

    TextView mode_View;

    public static final String DAY = "Dag";
    public static final String NIGHT = "Natt";
    public static final String AWAY = "Borte";
    public static final String HOLIDAY = "Ferie";

    public static final int iDAY = 2;
    public static final int iNIGHT = 3;
    public static final int iAWAY = 4;
    public static final int iHOLIDAY = 1;

    public static final String savedTemp = "1SavedTemperature_2";
    public static final String savedColor = "SavedBackgroundColor_2";

    SharedPreferences sharedpreferences;
    public SharedPreferences tempSetting;

    /**
     * Method that is called when the View is created.
     * This method sets up the GUI, provides the user with valid
     * temperatures and starts a threads which retrieves temperature from the heating controllers.
     * If the temperatures has been changed manually (with on of the dwelling units many controller
     * panels), this View will be updated.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature_2);

        setUpTemperatureGUI();
        displayTemperature();
        update();
    }

    /**
     * Setting up GUI. sets up all onClickListener buttons. Sets background depending on user
     * settings. SharedPreferences for each mode. load temperature information
     */
    public void setUpTemperatureGUI() {

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

        //home button
        homeBtn = (ImageButton) findViewById(R.id.home_button);
        homeBtn.setOnClickListener(new View.OnClickListener() {
            /**
             * Method that is called when the button is pressed.
             * Sends the user back to the main menu.
             * @param v the button
             */
            public void onClick(View v) {
                goToHome();
            }
        });

        // up temp button
        upBtn = (ImageButton) findViewById(R.id.adjust_up);
        upBtn.setOnClickListener(new View.OnClickListener() {
            /**
             * Method that is called when button is pressed.
             * Calls method that increases the temperature and updates the
             * temperature View.
             * @param v
             */
            public void onClick(View v) {
                upTemp();
            }
        });
        upBtn1 = (ImageButton) findViewById(R.id.adjust_up1);
        upBtn1.setOnClickListener(new View.OnClickListener() {
            /**
             * Method that is called when button is pressed.
             * Calls method that increases the temperature and updates the
             * temperature View.
             * @param v
             */
            public void onClick(View v) {
                upTemp1();
            }
        });
        upBtn2 = (ImageButton) findViewById(R.id.adjust_up2);
        upBtn2.setOnClickListener(new View.OnClickListener() {
            /**
             * Method that is called when button is pressed.
             * Calls method that increases the temperature and updates the
             * temperature View.
             * @param v
             */
            public void onClick(View v) {
                upTemp2();
            }
        });
        upBtn3 = (ImageButton) findViewById(R.id.adjust_up3);
        upBtn3.setOnClickListener(new View.OnClickListener() {
            /**
             * Method that is called when button is pressed.
             * Calls method that increases the temperature and updates the
             * temperature View.
             * @param v
             */
            public void onClick(View v) {
                upTemp3();
            }
        });
        upBtn4 = (ImageButton) findViewById(R.id.adjust_up4);
        upBtn4.setOnClickListener(new View.OnClickListener() {
            /**
             * Method that is called when button is pressed.
             * Calls method that increases the temperature and updates the
             * temperature View.
             * @param v
             */
            public void onClick(View v) {
                upTemp4();
            }
        });

        // down temp button
        downBtn = (ImageButton) findViewById(R.id.adjust_down);
        downBtn.setOnClickListener(new View.OnClickListener() {
            /**
             * Method that is called when button is pressed.
             * Calls method that decreases the temperature and updates the
             * temperature View.
             * @param v
             */
            public void onClick(View v) {
                downTemp();
            }
        });
        downBtn1 = (ImageButton) findViewById(R.id.adjust_down1);
        downBtn1.setOnClickListener(new View.OnClickListener() {
            /**
             * Method that is called when button is pressed.
             * Calls method that decreases the temperature and updates the
             * temperature View.
             * @param v
             */
            public void onClick(View v) {
                downTemp1();
            }
        });
        downBtn2 = (ImageButton) findViewById(R.id.adjust_down2);
        downBtn2.setOnClickListener(new View.OnClickListener() {
            /**
             * Method that is called when button is pressed.
             * Calls method that decreases the temperature and updates the
             * temperature View.
             * @param v
             */
            public void onClick(View v) {
                downTemp2();
            }
        });
        downBtn3 = (ImageButton) findViewById(R.id.adjust_down3);
        downBtn3.setOnClickListener(new View.OnClickListener() {
            /**
             * Method that is called when button is pressed.
             * Calls method that decreases the temperature and updates the
             * temperature View.
             * @param v
             */
            public void onClick(View v) {
                downTemp3();
            }
        });
        downBtn4 = (ImageButton) findViewById(R.id.adjust_down4);
        downBtn4.setOnClickListener(new View.OnClickListener() {
            /**
             * Method that is called when button is pressed.
             * Calls method that decreases the temperature and updates the
             * temperature View.
             * @param v
             */
            public void onClick(View v) {
                downTemp4();
            }
        });

        stemp1 = (TextView) findViewById(R.id.static1_tv);
        stemp2 = (TextView) findViewById(R.id.static2_tv);
        stemp3 = (TextView) findViewById(R.id.static3_tv);
        stemp4 = (TextView) findViewById(R.id.static4_tv);
        stemp5 = (TextView) findViewById(R.id.static5_tv);

        ctemp1 = (TextView) findViewById(R.id.current1_tv);
        ctemp2 = (TextView) findViewById(R.id.current2_tv);
        ctemp3 = (TextView) findViewById(R.id.current3_tv);
        ctemp4 = (TextView) findViewById(R.id.current4_tv);
        ctemp5 = (TextView) findViewById(R.id.current5_tv);

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
     * Method that sends the user back to the main menu View/Class.
     */
    public void goToHome() {
        Intent intent = new Intent(this, MainActivity_2.class);
        startActivity(intent);
    }

    /**
     * Method that starts a thread which updates the temperature if the user
     * has changed the temperatures manually with one of the dwelling units many
     * control-panels.
     */
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

    /**
     * Method that requests temperature information from the heating controller for this
     * dwlling unit.
     */
    public void requestNewTemperature() {
        MainActivity_2.sendText("Command:007262112,1");
        MainActivity_2.sendText("Command:007262112,2");
        MainActivity_2.sendText("Command:007262112,3");
        MainActivity_2.sendText("Command:007262112,4");
        MainActivity_2.sendText("Command:007262112,5");
        MainActivity_2.sendText("Command:007262112,6");
    }

    /**
     * Method that is called when the View is started (in OnCreate).
     * Display the saved temperatures.
     */
    public void displayTemperature() {
        for(int i=1; i <= 5;i++) {
            switch (i){
                case 1:
                    try {
                        SharedPreferences tempSettings = getSharedPreferences(savedTemp1, 0);
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
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    try {
                        SharedPreferences tempSettings = getSharedPreferences(savedTemp2, 0);
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
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    try {
                        SharedPreferences tempSettings = getSharedPreferences(savedTemp3, 0);
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

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    break;
                case 4:
                    try {
                        SharedPreferences tempSettings = getSharedPreferences(savedTemp4, 0);
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
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    break;
                case 5:
                    try {
                        SharedPreferences tempSettings = getSharedPreferences(savedTemp5, 0);
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
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    break;
                default:
                    break;

            }
        }
    }

    /**
     * Method that is called when the user wants to increase the temperature of channel one.
     * Updates the temperature for channel one
     */
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

                MainActivity_2.sendText("Command:007260112," + channel + ",0,0," + mode + ","
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

                MainActivity_2.sendText("Command:007260112," + channel + ",0,0," + mode + ","
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

                MainActivity_2.sendText("Command:007260112," + channel + ",0,0," + mode + ","
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

                MainActivity_2.sendText("Command:007260112," + channel + ",0,0," + mode + ","
                        + holiday + "," + day + "," + night + "," + away + "," + wateringFlag4 + ","
                        + "15");
                away = away + " °C";
                stemp1.setText(away);
                break;
            default:
                break;
        }
    }
    /**
     * Method that is called when the user wants to increase the temperature of channel two.
     * Updates the temperature for channel two.
     */
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

                MainActivity_2.sendText("Command:007260112," + channel + ",0,0," + mode + ","
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

                MainActivity_2.sendText("Command:007260112," + channel + ",0,0," + mode + ","
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

                MainActivity_2.sendText("Command:007260112," + channel + ",0,0," + mode + ","
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

                MainActivity_2.sendText("Command:007260112," + channel + ",0,0," + mode + ","
                        + holiday + "," + day + "," + night + "," + away + "," + wateringFlag4 + ","
                        + "15");
                away = away + " °C";
                stemp2.setText(away);
                break;
            default:
                break;
        }

    }
    /**
     * Method that is called when the user wants to increase the temperature of channel three.
     * Updates the temperature for channel three.
     */
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

                MainActivity_2.sendText("Command:007260112," + channel + ",0,0," + mode + ","
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

                MainActivity_2.sendText("Command:007260112," + channel + ",0,0," + mode + ","
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

                MainActivity_2.sendText("Command:007260112," + channel + ",0,0," + mode + ","
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

                MainActivity_2.sendText("Command:007260112," + channel + ",0,0," + mode + ","
                        + holiday + "," + day + "," + night + "," + away + "," + wateringFlag4 + ","
                        + "15");
                away = away + " °C";
                stemp3.setText(away);
                break;
            default:
                break;
        }
    }

    /**
     * Method that is called when the user wants to increase the temperature of channel four.
     * Updates the temperature for channel four.
     */
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

                MainActivity_2.sendText("Command:007260112," + channel + ",0,0," + mode + ","
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

                MainActivity_2.sendText("Command:007260112," + channel + ",0,0," + mode + ","
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

                MainActivity_2.sendText("Command:007260112," + channel + ",0,0," + mode + ","
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

                MainActivity_2.sendText("Command:007260112," + channel + ",0,0," + mode + ","
                        + holiday + "," + day + "," + night + "," + away + "," + wateringFlag4 + ","
                        + "15");
                away = away + " °C";
                stemp4.setText(away);
                break;
            default:
                break;
        }
    }

    /**
     * Method that is called when the user wants to increase the temperature of channel five.
     * Updates the temperature for channel five.
     */
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

                MainActivity_2.sendText("Command:007260112," + channel + ",0,0," + mode + ","
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

                MainActivity_2.sendText("Command:007260112," + channel + ",0,0," + mode + ","
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

                MainActivity_2.sendText("Command:007260112," + channel + ",0,0," + mode + ","
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

                MainActivity_2.sendText("Command:007260112," + channel + ",0,0," + mode + ","
                        + holiday + "," + day + "," + night + "," + away + "," + wateringFlag4 + ","
                        + "15");
                away = away + " °C";
                stemp5.setText(away);
                break;
            default:
                break;
        }
    }

    /**
     * Method that is called when the user wants to decrease the temperature of channel one.
     * Updates the temperature for channel one
     */
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

                MainActivity_2.sendText("Command:007260112," + channel + ",0,0," + mode + ","
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

                MainActivity_2.sendText("Command:007260112," + channel + ",0,0," + mode + ","
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

                MainActivity_2.sendText("Command:007260112," + channel + ",0,0," + mode + ","
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

                MainActivity_2.sendText("Command:007260112," + channel + ",0,0," + mode + ","
                        + holiday + "," + day + "," + night + "," + away + "," + wateringFlag4 + ","
                        + "15");
                away = away + " °C";
                stemp1.setText(away);
                break;
            default:
                break;
        }
    }

    /**
     * Method that is called when the user wants to decrease the temperature of channel two.
     * Updates the temperature for channel two.
     */
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

                MainActivity_2.sendText("Command:007260112," + channel + ",0,0," + mode + ","
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

                MainActivity_2.sendText("Command:007260112," + channel + ",0,0," + mode + ","
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

                MainActivity_2.sendText("Command:007260112," + channel + ",0,0," + mode + ","
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

                MainActivity_2.sendText("Command:007260112," + channel + ",0,0," + mode + ","
                        + holiday + "," + day + "," + night + "," + away + "," + wateringFlag4 + ","
                        + "15");
                away = away + " °C";
                stemp2.setText(away);
                break;
            default:
                break;
        }
    }

    /**
     * Method that is called when the user wants to decrease the temperature of channel three.
     * Updates the temperature for channel three.
     */
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

                MainActivity_2.sendText("Command:007260112," + channel + ",0,0," + mode + ","
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

                MainActivity_2.sendText("Command:007260112," + channel + ",0,0," + mode + ","
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

                MainActivity_2.sendText("Command:007260112," + channel + ",0,0," + mode + ","
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

                MainActivity_2.sendText("Command:007260112," + channel + ",0,0," + mode + ","
                        + holiday + "," + day + "," + night + "," + away + "," + wateringFlag4 + ","
                        + "15");
                away = away + " °C";
                stemp3.setText(away);
                break;
            default:
                break;
        }
    }

    /**
     * Method that is called when the user wants to decrease the temperature of channel four.
     * Updates the temperature for channel four.
     */
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

                MainActivity_2.sendText("Command:007260112," + channel + ",0,0," + mode + ","
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

                MainActivity_2.sendText("Command:007260112," + channel + ",0,0," + mode + ","
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

                MainActivity_2.sendText("Command:007260112," + channel + ",0,0," + mode + ","
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

                MainActivity_2.sendText("Command:007260112," + channel + ",0,0," + mode + ","
                        + holiday + "," + day + "," + night + "," + away + "," + wateringFlag4 + ","
                        + "15");
                away = away + " °C";
                stemp4.setText(away);
                break;
            default:
                break;
        }
    }

    /**
     * Method that is called when the user wants to decrease the temperature of channel five.
     * Updates the temperature for channel five.
     */
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

                MainActivity_2.sendText("Command:007260112," + channel + ",0,0," + mode + ","
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

                MainActivity_2.sendText("Command:007260112," + channel + ",0,0," + mode + ","
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

                MainActivity_2.sendText("Command:007260112," + channel + ",0,0," + mode + ","
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

                MainActivity_2.sendText("Command:007260112," + channel + ",0,0," + mode + ","
                        + holiday + "," + day + "," + night + "," + away + "," + wateringFlag4 + ","
                        + "15");
                away = away + " °C";
                stemp5.setText(away);
                break;
            default:
                break;
        }
    }
}
