package com.weebly.smarthusgruppen.shk_applikasjon.Toppleilighet;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.Layout;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;

import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.weebly.smarthusgruppen.shk_applikasjon.LoginClient;
import com.weebly.smarthusgruppen.shk_applikasjon.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.channels.Channel;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;




public class MainActivity extends AppCompatActivity {
    String userInput;
    static BufferedWriter output;
    static BufferedReader input;
    BufferedReader sysIn;
    TextView receivedText;

    ImageButton settings;
    ImageButton lightBtn;
    ImageButton climateBtn;
    ImageButton windowsBtn;
    ImageButton measureBtn;
    ImageButton modeBtn;

    static Socket connection;

    static public ArrayList<TemperatureInformation> tempZone = new ArrayList<>();

    public static final String savedTemp1 = "1SavedTemperature";
    public static final String savedTemp2 = "2SavedTemperature";
    public static final String savedTemp3 = "3SavedTemperature";
    public static final String savedTemp4 = "4SavedTemperature";
    public static final String savedTemp5 = "5SavedTemperature";
    public static final String savedTemp6 = "6SavedTemperature";
    public static final String savedColor = "SavedBackgroundColor";

    SharedPreferences sharedpreferences;
    OutputStream os;

    public int seekBarValue1;
    public int seekBarValue2;
    public int seekBarValue3;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getConnection();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();


        // light control button
        lightBtn = (ImageButton) findViewById(R.id.lightButton);
        lightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToRoomView();
            }
        });
        // climate button
        climateBtn = (ImageButton) findViewById(R.id.climateButton);
        climateBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)  {
                goToClimateView();
            }
        });

        windowsBtn = (ImageButton) findViewById(R.id.windowButton);
        windowsBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)  {
                goToWindowView();
            }
        });

        measureBtn = (ImageButton) findViewById(R.id.measureButton);
        measureBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)  {
                goToMeasurementView();
            }
        });

        modeBtn = (ImageButton) findViewById(R.id.modeButton);
        modeBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goToModeView();
            }
        });

        settings = (ImageButton) findViewById(R.id.settings);
        settings.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                settingsView();
            }
        });


        startMessageListener();
        MainActivity.sendText("Command:007262112,1");
        MainActivity.sendText("Command:007262112,2");
        MainActivity.sendText("Command:007262112,3");
        MainActivity.sendText("Command:007262112,4");
        MainActivity.sendText("Command:007262112,5");
        MainActivity.sendText("Command:007262112,6");

        sharedpreferences = getSharedPreferences(savedColor, Context.MODE_PRIVATE);

        int value1 = sharedpreferences.getInt("value1", 0);
        int value2 = sharedpreferences.getInt("value2", 0);
        int value3 = sharedpreferences.getInt("value3", 0);
        int value4 = sharedpreferences.getInt("set", 0);
        if(value4 != 0){
            View v = findViewById(R.id.main_id);
            v.setBackgroundColor(Color.rgb(value1,value3, value2));
            setContentView(v);
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.weebly.smarthusgruppen.shk_applikasjon/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.weebly.smarthusgruppen.shk_applikasjon/http/host/path")

        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        //sendText("Disconnect");
    }
    public void goToRoomView() {
        Intent intent = new Intent(this, RoomList.class);
        startActivity(intent);
    }


    public void goToClimateView() {
        Intent intent = new Intent(this, Climate.class);
        startActivity(intent);
    }

    public void goToWindowView() {
        Intent intent = new Intent(this, Windows.class);
        startActivity(intent);
    }

    public void goToMeasurementView() {
        Intent intent = new Intent(this, Measurement.class);
        startActivity(intent);
    }
    public void goToModeView() {
        Intent intent = new Intent(this, TypeOfMode.class);
        startActivity(intent);
    }

    public void settingsView() {

        Dialog settingsDialog = new Dialog(this);
        settingsDialog.setContentView(R.layout.settings_main1);
        settingsDialog.setCancelable(true);

        final ImageView colorV = (ImageView)settingsDialog.findViewById(R.id.colorView);
        SeekBar seekBar1 = (SeekBar)settingsDialog.findViewById(R.id.seekBar1);
        seekBarValue1 = seekBar1.getProgress();

        seekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {

                seekBarValue1 = progress;
                colorV.setBackgroundColor(Color.rgb(seekBarValue1,seekBarValue3, seekBarValue2));
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

        SeekBar seekBar2 = (SeekBar)settingsDialog.findViewById(R.id.seekBar2);

        seekBarValue2 = seekBar2.getProgress();
        seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {

                seekBarValue2 = progress;
                colorV.setBackgroundColor(Color.rgb(seekBarValue1,seekBarValue3, seekBarValue2));
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

        SeekBar seekBar3 = (SeekBar)settingsDialog.findViewById(R.id.seekBar3);
        seekBarValue3 = seekBar3.getProgress();
        seekBar3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {

                seekBarValue3 = progress;
                colorV.setBackgroundColor(Color.rgb(seekBarValue1,seekBarValue3, seekBarValue2));
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

        colorV.setBackgroundColor(Color.rgb(seekBarValue1, seekBarValue3, seekBarValue2));
        settingsDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                sharedpreferences = getSharedPreferences(savedColor, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putInt("value1", seekBarValue1);
                editor.putInt("value2", seekBarValue2);
                editor.putInt("value3", seekBarValue3);
                editor.putInt("set", 1);
                editor.commit();
                View v = findViewById(R.id.main_id);
                v.setBackgroundColor(Color.rgb(seekBarValue1,seekBarValue3, seekBarValue2));
                setContentView(v);
            }
        });
        settingsDialog.show();


    }

    private void startMessageListener() {
        Thread mThread = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    Random rnd = new Random();
                    try {

                        String msg = input.readLine();
                        Log.d("msg", ""+ msg);

                        if(msg.startsWith("TempInfo:")) {
                            tempInfoController(msg.substring(8, msg.length()));
                        }

                        else {

                        }
                    } catch (Exception e) {
                        System.out.println("Error when reading msg");
                        //e.printStackTrace();
                    }
                    try {
                        TimeUnit.MILLISECONDS.sleep(rnd.nextInt(100) * 10);
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        MainActivity.sendText("Disconnect");
                        e.printStackTrace();
                    }
                }
            }


        });
        mThread.start();
    }

    private void displayMessage(String text) {
       receivedText.append(text);
       // SwingUtilities.invokeLater(() -> receivedText.append(text));
    }

    /**
     * Sets the temperatures given from server
     * @param msg String containing temperatures
     */
    public void tempInfoController(String msg) {
        // Sets the channel number
        String channel = msg.substring(1, 2);
        Log.d("channel", channel);
        // Sets the mode number
        String mode = msg.substring(2, 3);
        Log.d("mode", mode);
        // If the temperature is less than 10, set temp like int after 0, else set
        // temp like int XX
        String holiday = (msg.charAt(3) == 0) ? msg.substring(4, 5) : msg.substring(3, 5);
        Log.d("holiday", holiday);
        // Will most likely be over 10
        String day = msg.substring(5, 7);
        Log.d("day", day);
        // Will most likely be over 10
        String night = msg.substring(7, 9);
        Log.d("night", night);
        // Will most likely be over 10
        String away = msg.substring(9, 11);
        Log.d("away", away);
        // Gets rest of the string, which will (presumably) be two integers.
        String currentTemp = msg.substring(11);
        Log.d("current temp", currentTemp);

        /*
        boolean existingZone = false;

        TemperatureInformation zone = new TemperatureInformation(channel,mode,day,night,holiday,away,currentTemp);
        if(!tempZone.isEmpty()) {
            for (int i = 0; i < tempZone.size(); i++) {
                if (tempZone.get(i).channel == channel) {
                    tempZone.set(i, zone);
                    existingZone = true;
                }
            }
        }
        if(existingZone) {
            existingZone = false;
        }
        else tempZone.add(zone);
        */

        int ch = Integer.parseInt(channel);
        switch (ch) {
            case 1:
                sharedpreferences = getSharedPreferences(savedTemp1, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor1 = sharedpreferences.edit();

                editor1.putString("channel",channel);
                editor1.putString("mode",mode);
                editor1.putString("holiday",holiday);
                editor1.putString("day",day);
                editor1.putString("night",night);
                editor1.putString("away",away);
                editor1.putString("current",currentTemp);
                editor1.commit();
                break;
            case 2:
                sharedpreferences = getSharedPreferences(savedTemp2, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor2 = sharedpreferences.edit();

                editor2.putString("channel",channel);
                editor2.putString("mode",mode);
                editor2.putString("holiday",holiday);
                editor2.putString("day",day);
                editor2.putString("night",night);
                editor2.putString("away",away);
                editor2.putString("current",currentTemp);
                editor2.commit();
                break;
            case 3:
                sharedpreferences = getSharedPreferences(savedTemp3, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor3 = sharedpreferences.edit();

                editor3.putString("channel",channel);
                editor3.putString("mode",mode);
                editor3.putString("holiday",holiday);
                editor3.putString("day",day);
                editor3.putString("night",night);
                editor3.putString("away",away);
                editor3.putString("current",currentTemp);
                editor3.commit();
                break;
            case 4:
                sharedpreferences = getSharedPreferences(savedTemp4, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor4 = sharedpreferences.edit();

                editor4.putString("channel",channel);
                editor4.putString("mode",mode);
                editor4.putString("holiday",holiday);
                editor4.putString("day",day);
                editor4.putString("night",night);
                editor4.putString("away",away);
                editor4.putString("current",currentTemp);
                editor4.commit();
                break;
            case 5:
                sharedpreferences = getSharedPreferences(savedTemp5, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor5 = sharedpreferences.edit();

                editor5.putString("channel",channel);
                editor5.putString("mode",mode);
                editor5.putString("holiday",holiday);
                editor5.putString("day",day);
                editor5.putString("night",night);
                editor5.putString("away",away);
                editor5.putString("current",currentTemp);
                editor5.commit();
                break;
            case 6:
                sharedpreferences = getSharedPreferences(savedTemp6, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor6 = sharedpreferences.edit();

                editor6.putString("channel",channel);
                editor6.putString("mode",mode);
                editor6.putString("holiday",holiday);
                editor6.putString("day",day);
                editor6.putString("night",night);
                editor6.putString("away",away);
                editor6.putString("current",currentTemp);
                editor6.commit();
                break;
            default:
                break;
        }

       // Log.d("Stuff", "" + zone.channel + " " + zone.mode + " " + zone.currHoliday + " " + zone.currDay + " " + zone.currNight+ " " + zone.currAway + " " + zone.currTemp);

        /*
        Log.d("Stuff", "" + Channel + " " + Mode + " " + Holiday + " " + Day + " " + Night + " " + Away + " " + CurrentTemp);
        Intent i = new Intent(getApplicationContext(), Temperature.class);
        i.putExtra("channel", msg.substring(2, 3));
        i.putExtra("mode", msg.substring(1,2));
        i.putExtra("holiday", ( (msg.charAt(3) == 0) ? Integer.parseInt(msg.substring(4, 5)) : Integer.parseInt(msg.substring(3, 5))));
        i.putExtra("day", msg.substring(5, 7));
        i.putExtra("night", msg.substring(7,9));
        i.putExtra("away", msg.substring(9,11));
        i.putExtra("currentTemp", msg.substring(11));
        setResult(RESULT_OK,i);
        startActivityForResult(i, 1);
        */
        //temperature.createTempZone(Channel, Mode, Day, Night, Holiday, Away, CurrentTemp);
        //Temperature.createTempZone(Channel, Mode, Day, Night, Holiday, Away, CurrentTemp);

    }

    public static void sendText(String textToSend) {
        try {
            output.write(textToSend);
            output.newLine();
            output.flush();
        } catch (IOException ioe) {
        }
    }

    public static void getConnection() {
        connection = LoginClient.returnConnection();
        output = LoginClient.returnwriter();
        input = LoginClient.returnReader();
    }

    public static TemperatureInformation returnTemperature(int n) {
        return tempZone.get(n);
    }

    public static int returnTemperatureSize() {
        return tempZone.size();
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

    public static boolean TemperatureIsEmpty() {
        return tempZone.isEmpty();
    }
}

