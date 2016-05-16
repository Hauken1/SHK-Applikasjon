package com.weebly.smarthusgruppen.shk_applikasjon.Toppleilighet;



import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;

import android.view.View;
import android.widget.Button;

import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;


import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.weebly.smarthusgruppen.shk_applikasjon.LoginClient;
import com.weebly.smarthusgruppen.shk_applikasjon.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Main class of the "Toppleilighet (Upper apartment)". This class sets up a View which gives the user access
 * to "controlling" views. The views is light-,ventilation, window and heatingcontroll. The user is also
 * provided with a buttons which send the user to the View where he/she can change the mode the
 * house is set to. The user can also access a settings View on this page, which allows the user to
 * customize his/her background color.
 */
public class MainActivity extends AppCompatActivity {
    static BufferedWriter output;
    static BufferedReader input;

    ImageButton settings;
    ImageButton lightBtn;
    ImageButton climateBtn;
    ImageButton windowsBtn;
    ImageButton measureBtn;
    ImageButton modeBtn;

    static Socket connection;

    static public ArrayList<TemperatureInformation> tempZone = new ArrayList<>();

    public static final String savedTemp1 = "1SavedTemperature_1";
    public static final String savedTemp2 = "2SavedTemperature_1";
    public static final String savedTemp3 = "3SavedTemperature_1";
    public static final String savedTemp4 = "4SavedTemperature_1";
    public static final String savedTemp5 = "5SavedTemperature_1";
    public static final String savedTemp6 = "6SavedTemperature_1";
    public static final String savedColor = "SavedBackgroundColor_1";

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

    /**
     * Method that is called the the View is created.
     * This method retrives the connection used to login for further use, sets up the GUI and
     * sends messages to the server where the server will respond with providing the user with
     * the current temperatures in this apartment. It also changes the mode of the application to
     * be the same as what the house is set to.
     * Starts the messagelistner which listens for messages sent from the server.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getConnection();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

        setupGUI();

        startMessageListener();

        MainActivity.sendText("Command:007262112,1");
        MainActivity.sendText("Command:007262112,2");
        MainActivity.sendText("Command:007262112,3");
        MainActivity.sendText("Command:007262112,4");
        MainActivity.sendText("Command:007262112,5");
        MainActivity.sendText("Command:007262112,6");

    }
    /**
     * Setting up GUI. sets up all onClickListener buttons. Sets background depending on user
     * settings. SharedPreferences for each mode.
     */
    public void setupGUI() {
        // light control button
        lightBtn = (ImageButton) findViewById(R.id.lightButton);
        lightBtn.setOnClickListener(new View.OnClickListener() {
            /**
             * Method that is called when the button is pressed.
             * @param v the button
             */
            @Override
            public void onClick(View v) {
                goToRoomView();
            }
        });
        // climate button
        climateBtn = (ImageButton) findViewById(R.id.climateButton);
        climateBtn.setOnClickListener(new View.OnClickListener() {
            /**
             * Method that is called the button is pressed.
             * @param v the button
             */
            public void onClick(View v)  {
                goToClimateView();
            }
        });

        windowsBtn = (ImageButton) findViewById(R.id.windowButton);
        windowsBtn.setOnClickListener(new View.OnClickListener() {
            /**
             * Method that is called the button is pressed.
             * @param v the button
             */
            public void onClick(View v)  {
                goToWindowView();
            }
        });

        measureBtn = (ImageButton) findViewById(R.id.measureButton);
        measureBtn.setOnClickListener(new View.OnClickListener() {
            /**
             * Method that is called the button is pressed.
             * @param v the button
             */
            public void onClick(View v)  {
                goToMeasurementView();
            }
        });

        modeBtn = (ImageButton) findViewById(R.id.modeButton);
        modeBtn.setOnClickListener(new View.OnClickListener() {
            /**
             * Method that is called the button is pressed.
             * @param v the button
             */
            public void onClick(View v) {
                goToModeView();
            }
        });

        settings = (ImageButton) findViewById(R.id.settings);
        settings.setOnClickListener(new View.OnClickListener() {
            /**
             * Method that is called the button is pressed.
             * @param v the button
             */
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
            View v = findViewById(R.id.main_id);
            v.setBackgroundColor(Color.rgb(value1,value3, value2));
            setContentView(v);
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

    /**
     * Method that is called when the View is destroyed.
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        //sendText("Disconnect");
    }

    /**
     * sends the user to the room view menu
     */
    public void goToRoomView() {
        Intent intent = new Intent(this, RoomList.class);
        startActivity(intent);
    }

    /**
     * sends the user to the climate view menu
     */
    public void goToClimateView() {
        Intent intent = new Intent(this, Climate.class);
        startActivity(intent);
    }

    /**
     * sends the user to the window view menu
     */
    public void goToWindowView() {
        Intent intent = new Intent(this, Windows.class);
        startActivity(intent);
    }

    /**
     * sends the user to measurement view
     */
    public void goToMeasurementView() {
        Intent intent = new Intent(this, Measurement.class);
        startActivity(intent);
    }

    /**
     * sends the user to the mode view
     */
    public void goToModeView() {
        Intent intent = new Intent(this, TypeOfMode.class);
        startActivity(intent);
    }

    /**
     * Provides the user with a view, which allows the user to customize his/her background color.
     * The background color is saved, so that it will be set when the user restarts the application
     * and moves to another View.
     */
    public void settingsView() {
        sharedpreferences = getSharedPreferences(savedColor, Context.MODE_PRIVATE);
        seekBarValue1 = sharedpreferences.getInt("value1", 0);
        seekBarValue2 = sharedpreferences.getInt("value2", 0);
        seekBarValue3 = sharedpreferences.getInt("value3", 0);
        final Dialog settingsDialog = new Dialog(this);
        settingsDialog.setContentView(R.layout.settings_main1);
        settingsDialog.setCancelable(true);

        final ImageView colorV = (ImageView)settingsDialog.findViewById(R.id.colorView);
        SeekBar seekBar1 = (SeekBar)settingsDialog.findViewById(R.id.seekBar1);
        //seekBarValue1 = seekBar1.getProgress();

        seekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            /**
             * Method that is called when the seekbar is moved.
             * This method sets the value of the seekbar to a variable, that is used to
             * make the background color
             * @param seekBar the seekbar that has changed
             * @param progress the value it has changed to
             * @param fromUser if it is changed by the user or not
             */
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {

                seekBarValue1 = progress;
                colorV.setBackgroundColor(Color.rgb(seekBarValue1,seekBarValue3, seekBarValue2));
            }

            /**
             * Auto generated method that is made when making a seekbar
             * @param seekBar the seekbar
             */
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            /**
             * Auto generated method that is made when making a seekbar
             * @param seekBar the seekbar
             */
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }
        });

        SeekBar seekBar2 = (SeekBar)settingsDialog.findViewById(R.id.seekBar2);

        //seekBarValue2 = seekBar2.getProgress();
        seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            /**
             * Method that is called when the seekbar is moved.
             * This method sets the value of the seekbar to a variable, that is used to
             * make the background color
             * @param seekBar the seekbar that has changed
             * @param progress the value it has changed to
             * @param fromUser if it is changed by the user or not
             */
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {

                seekBarValue2 = progress;
                colorV.setBackgroundColor(Color.rgb(seekBarValue1,seekBarValue3, seekBarValue2));
            }

            /**
             * Auto generated method that is made when making a seekbar
             * @param seekBar the seekbar
             */
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            /**
             * Auto generated method that is made when making a seekbar
             * @param seekBar the seekbar
             */
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }
        });

        SeekBar seekBar3 = (SeekBar)settingsDialog.findViewById(R.id.seekBar3);
        //seekBarValue3 = seekBar3.getProgress();
        seekBar3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            /**
             * Method that is called when the seekbar is moved.
             * This method sets the value of the seekbar to a variable, that is used to
             * make the background color
             * @param seekBar the seekbar that has changed
             * @param progress the value it has changed to
             * @param fromUser if it is changed by the user or not
             */
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {

                seekBarValue3 = progress;
                colorV.setBackgroundColor(Color.rgb(seekBarValue1,seekBarValue3, seekBarValue2));
            }

            /**
             * Auto generated method that is made when making a seekbar
             * @param seekBar the seekbar
             */
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            /**
             * Auto generated method that is made when making a seekbar
             * @param seekBar the seekbar
             */
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }
        });

        Button cancelButton = (Button)settingsDialog.findViewById(R.id.cancel_button);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Method that is called when the cancelbutton is pressed.
             * Cancels the dialog, which again runs the OnCancelListener of the dialog.
             * @param v the button
             */
            @Override
            public void onClick(View v) {
                settingsDialog.cancel();
            }
        });
        colorV.setBackgroundColor(Color.rgb(seekBarValue1, seekBarValue3, seekBarValue2));
        settingsDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            /**
             * Method that is called when the method is canceled.
             * Saves the current values of the seekbar and changes the color based on these
             * values.
             * @param dialog the dialog.
             */
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

    /**
     * Method starts a threads which listens for server messages.
     * The main function of this listener is to retrieve temperature information from the server.
     *
     */
    private void startMessageListener() {
        Thread mThread = new Thread(new Runnable() {
            /**
             * Run method of the thread that is called when the thread has started.
             * Starts the message listener.
             */
            public void run() {
                while (true) {
                    Random rnd = new Random();
                    try {

                        if (input.ready()) {
                            String msg = input.readLine();

                            Log.d("msg", ""+ msg);

                            if(msg.startsWith("TempInfo:")) {
                                tempInfoController(msg.substring(8, msg.length()));
                            }

                            else {

                            }

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


    /**
     * Sets the temperatures retrieved by the server
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



    }

    /**
     * used to send string commands to server
     * @param textToSend string with command to send to server
     */
    public static void sendText(String textToSend) {
        try {
            output.write(textToSend);
            output.newLine();
            output.flush();
        } catch (IOException ioe) {
        }
    }

    /**
     * Retrieves the Socket, BufferedReader and Writer used when the
     * user logged in.
     */
    public static void getConnection() {
        connection = LoginClient.returnConnection();
        output = LoginClient.returnwriter();
        input = LoginClient.returnReader();
    }

    /*
    ***************************Code that is no longer in use*********************************
     */
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

