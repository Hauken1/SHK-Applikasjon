package com.weebly.smarthusgruppen.shk_applikasjon.Arbeidsrom;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.weebly.smarthusgruppen.shk_applikasjon.R;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * This class allows the user to see the current temperature of all heatingsones of
 * this dwelling unit. The user can also change the temperature of all these heating sones.
 * When a temperature is changed, a message is sent to the server and then to the heating-
 * controller which changes the temperature currently set.
 */
public class Temperature_3 extends AppCompatActivity {
    ImageButton homeBtn;
    ImageButton upBtn;
    ImageButton upBtn1;

    ImageButton downBtn;
    ImageButton downBtn1;

    public static final String savedTemp1 = "1SavedTemperature_3" ;
    public static final String savedTemp2 = "2SavedTemperature_3" ;

    TextView stemp1;
    TextView stemp2;

    TextView ctemp1;
    TextView ctemp2;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    TextView mode_View;

    public static final String DAY = "Dag";
    public static final String NIGHT = "Natt";
    public static final String AWAY = "Borte";
    public static final String HOLIDAY = "Ferie";

    public static final int iDAY = 2;
    public static final int iNIGHT = 3;
    public static final int iAWAY = 4;
    public static final int iHOLIDAY = 1;

    public static final String savedTemp = "1SavedTemperature_3";
    public static final String savedColor = "SavedBackgroundColor_3";

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
        setContentView(R.layout.activity_temperature_3);

        setUpTemperatureGUI();
        displayTemperature();
        update();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    /**
     * Sets up onClickListeners for all buttons. loads settings from sharedpreferences based on
     * which mode the house is in. Sets background color depending on user.
     */
    public void setUpTemperatureGUI() {
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

        stemp1 = (TextView) findViewById(R.id.static1_tv);
        stemp2 = (TextView) findViewById(R.id.static2_tv);
        ctemp1 = (TextView) findViewById(R.id.current1_tv);
        ctemp2 = (TextView) findViewById(R.id.current2_tv);

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
     * sends the user to the mainactivity menu
     */
    public void goToHome() {
        Intent intent = new Intent(this, MainActivity_3.class);
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
        MainActivity_3.sendText("Command:007262112,1");
        MainActivity_3.sendText("Command:007262112,2");
        MainActivity_3.sendText("Command:007262112,3");
        MainActivity_3.sendText("Command:007262112,4");
        MainActivity_3.sendText("Command:007262112,5");
        MainActivity_3.sendText("Command:007262112,6");
    }

    /**
     * Method that is called when the View is started (in OnCreate).
     * Display the saved temperatures.
     */
    public void displayTemperature() {
        for (int i = 1; i <= 3; i++) {
            switch (i) {
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
                    } catch (Exception e) {
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
                    } catch (Exception e) {
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
                if (h1 < c1) {
                    wateringFlag1 = "1";
                } else wateringFlag1 = "0";

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
                if (h2 < c2) {
                    wateringFlag2 = "1";
                } else wateringFlag2 = "0";

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
                if (h3 < c3) {
                    wateringFlag3 = "1";
                } else wateringFlag3 = "0";

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
                if (h4 < c4) {
                    wateringFlag4 = "1";
                } else wateringFlag4 = "0";

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
                if (h1 < c1) {
                    wateringFlag1 = "1";
                } else wateringFlag1 = "0";

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
                if (h2 < c2) {
                    wateringFlag2 = "1";
                } else wateringFlag2 = "0";

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
                if (h3 < c3) {
                    wateringFlag3 = "1";
                } else wateringFlag3 = "0";

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
                if (h4 < c4) {
                    wateringFlag4 = "1";
                } else wateringFlag4 = "0";

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
                if (h1 < c1) {
                    wateringFlag1 = "1";
                } else wateringFlag1 = "0";

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
                if (h2 < c2) {
                    wateringFlag2 = "1";
                } else wateringFlag2 = "0";

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
                if (h3 < c3) {
                    wateringFlag3 = "1";
                } else wateringFlag3 = "0";

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
                if (h4 < c4) {
                    wateringFlag4 = "1";
                } else wateringFlag4 = "0";

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
                if (h1 < c1) {
                    wateringFlag1 = "1";
                } else wateringFlag1 = "0";

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
                if (h2 < c2) {
                    wateringFlag2 = "1";
                } else wateringFlag2 = "0";

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
                if (h3 < c3) {
                    wateringFlag3 = "1";
                } else wateringFlag3 = "0";

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
                if (h4 < c4) {
                    wateringFlag4 = "1";
                } else wateringFlag4 = "0";

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

    /**
     * Auto generated method that is called when the View is started.
     */
    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Temperature_3 Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
               // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.weebly.smarthusgruppen.shk_applikasjon/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    /**
     * Auto generated method that is called when the View is stopped.
     */
    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Temperature_3 Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
               Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.weebly.smarthusgruppen.shk_applikasjon/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}