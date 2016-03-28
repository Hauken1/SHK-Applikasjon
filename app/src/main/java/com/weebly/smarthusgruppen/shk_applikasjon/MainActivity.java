package com.weebly.smarthusgruppen.shk_applikasjon;

import android.app.Activity;
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

import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import java.io.OutputStreamWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.concurrent.TimeUnit;




public class MainActivity extends AppCompatActivity {

    int serverPort = 12345;
    String hostName= "10.0.2.2";

    String textToSend = "Heisann Henrik";
    //private DatagramSocket socket;
    private Socket connection;
    String userInput;
  //  Socket myClient;
    BufferedWriter output;
    BufferedReader input;
    BufferedReader sysIn;
    Button connectBtn;
    TextView receivedText;
    Button lightBtn;
    Button climateBtn;

    OutputStream os;
    ObjectOutputStream oos;
    boolean connected = false;
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

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
        connectBtn = (Button) findViewById(R.id.connectButton);
        connectBtn.setOnClickListener(connectListener);

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


    }

    protected View.OnClickListener connectListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            connected = true;
            if (connected) {
                Thread cThread = new Thread(new ClientThread());
                cThread.start();
            }
        }
    };

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
    public void goToRoomView() {
        Intent intent = new Intent(this, RoomList.class);
        startActivity(intent);
    }


    public void goToClimateView() {
        Intent intent = new Intent(this, Climate.class);
        startActivity(intent);
    }

    public class ClientThread implements Runnable {

        public void run() {
            try {
                Log.d("ClientActivity", "C: Connecting...");
                connectToServer();
                    try {
                        Log.d("ClientActivity", "C: Sending command.");
                        String message = "Test fra mobil app";
                        sendText(message);

                        Log.d("ClientActivity", "C: Sent.");

                    } catch (Exception e) {
                        Log.e("ClientActivity", "S: Error", e);
                    }
               // socket.close();
                Log.d("ClientActivity", "C: Closed.");
            } catch (Exception e) {
                Log.e("ClientActivity", "C: Error", e);
            }
        }

        private void connectToServer() {
            try {
                //socket = new DatagramSocket();
                connection = new Socket(InetAddress.getByName(hostName), serverPort);
                Log.d("ClientActivity", "C: Connected to server.");
                output = new BufferedWriter(new OutputStreamWriter(
                        connection.getOutputStream()));
                input = new BufferedReader(new InputStreamReader(
                        connection.getInputStream()));

            } catch (UnknownHostException e) {

            } catch (IOException e) {
            }
        }
        public void sendText(String textToSend) {
            try {
                output.write(textToSend);
                output.newLine();
                output.flush();
            } catch (IOException ioe) {
            }
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
                        } catch (Exception e) {
                            System.out.println("Feil med object");
                            e.printStackTrace();
                        }
                        try {
                            TimeUnit.MILLISECONDS.sleep(rnd.nextInt(100) * 10);
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }

                ;
            });
        }

        private void displayMessage(String text) {
           receivedText.append(text);
           // SwingUtilities.invokeLater(() -> receivedText.append(text));
        }



    }

}

