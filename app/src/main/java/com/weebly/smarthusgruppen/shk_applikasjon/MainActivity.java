package com.weebly.smarthusgruppen.shk_applikasjon;


import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.channels.Channel;
import java.util.Random;
import java.util.concurrent.TimeUnit;




public class MainActivity extends AppCompatActivity {
    String userInput;
    static BufferedWriter output;
    static BufferedReader input;
    BufferedReader sysIn;
    TextView receivedText;
    Button lightBtn;
    Button climateBtn;
    Button windowsBtn;
    Button measureBtn;
    Button modeBtn;
    static Socket connection;

    Temperature temperature = new Temperature();


    OutputStream os;
    ClientMessage cm = new ClientMessage();



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
       /* connectBtn = (Button) findViewById(R.id.connectButton);
        connectBtn.setOnClickListener(connectListener);*/

        // light control button
        lightBtn = (Button) findViewById(R.id.lightButton);
        lightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToRoomView();
            }
        });
        // climate button
        climateBtn = (Button) findViewById(R.id.climateButton);
        climateBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)  {
                goToClimateView();
            }
        });

        windowsBtn = (Button) findViewById(R.id.windowButton);
        windowsBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)  {
                goToWindowView();
            }
        });

        measureBtn = (Button) findViewById(R.id.measureButton);
        measureBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)  {
                goToMeasurementView();
            }
        });

        modeBtn = (Button) findViewById(R.id.modeButton);
        modeBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)  {
                goToModeView();
            }
        });

        startMessageListener();

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

        sendText("Disconnect");
        System.exit(1);
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






        private void receiveFromServer() {
            try {
                while ((userInput = sysIn.readLine()) != null) {
                   // out.println(userInput);
                    System.out.println("echo" + input.read());
                    // Receiving and printing response from the server
                }
            } catch (IOException ioe) {
                System.out.println(ioe);
            }
        }

        private void startMessageListener() {
            Thread mThread = new Thread(new Runnable() {
                public void run() {
                    while (true) {
                        Random rnd = new Random();
                        try {
                            String msg = input.readLine();
                            Log.d("Stuff", ""+ msg);

                            if(msg.startsWith("TempInfo:")) {
                                tempInfoController(msg.substring(8, msg.length()));
                            }

                            else {

                            }
                            /*
                            byte[] data = new byte[100];
                            DatagramPacket receivePacket = new DatagramPacket(data,
                                    data.length);
                            //socket.receive(receivePacket);
                            displayMessage("\nPacket received:"
                                    + "\nFrom host: "
                                    + receivePacket.getAddress()
                                    + "\nHost port: "
                                    + receivePacket.getPort()
                                    + "\nLength: "
                                    + receivePacket.getLength()
                                    + "\nContaining: "
                                    + new String(receivePacket.getData(), 0,
                                    receivePacket.getLength()));
                            */

                        } catch (Exception e) {
                            System.out.println("Feil med object");
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
        int Channel = Integer.parseInt(msg.substring(1, 2));
        // Sets the mode number
        int Mode    = Integer.parseInt(msg.substring(2, 3));
        // If the temperature is less than 10, set temp like int after 0, else set
        // temp like int XX
        int Holiday = ( (msg.charAt(3) == 0) ? Integer.parseInt(msg.substring(4, 5)) : Integer.parseInt(msg.substring(3, 5)));
        // Will most likely be over 10
        int Day    = Integer.parseInt(msg.substring(5, 7));
        // Will most likely be over 10
        int Night = Integer.parseInt(msg.substring(7, 9));
        // Will most likely be over 10
        int Away  = Integer.parseInt(msg.substring(9, 11));
        // Gets rest of the string, which will (presumably) be two integers.
        int CurrentTemp = Integer.parseInt(msg.substring(11));

        Log.d("Stuff", ""+ Channel + " " + Mode + " " + Holiday + " " + Day + " " + Night + " " + Away + " " + CurrentTemp );
        temperature.createTempZone(Channel, Mode, Day, Night, Holiday, Away, CurrentTemp);
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


}

