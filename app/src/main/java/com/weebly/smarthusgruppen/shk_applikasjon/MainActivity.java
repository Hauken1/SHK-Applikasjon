package com.weebly.smarthusgruppen.shk_applikasjon;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import static java.lang.System.out;

public class MainActivity extends AppCompatActivity {



    int portNumber = 1234;
    String hostName= "10.0.2.2";

    String textToSend = "Heisann Henrik";
    String userInput;
    Socket myClient;
    BufferedWriter output;
    BufferedReader input;
    BufferedReader sysIn;
    Button connectBtn;
    boolean connected = false;
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
    }

    protected View.OnClickListener connectListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!connected) {
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

    public class Packet{
        int packet_type;

    }

    public class ClientThread implements Runnable {

        public void run() {
            try {
                //InetAddress serverAddr = InetAddress.getByName(hostName);
                Log.d("ClientActivity", "C: Connecting...");
                //myClient = new Socket(serverAddr, portNumber);
                connectToServer();
                connected = true;
                int test = 0;
                while (test < 10) {
                    try {
                        Log.d("ClientActivity", "C: Sending command.");
                        sendToServer(textToSend);
                        /*
                        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(myClient
                                .getOutputStream())), true);
                         */
                        // WHERE YOU ISSUE THE COMMANDS
                        //out.println("Hey Server!");
                        Log.d("ClientActivity", "C: Sent.");
                        test++;
                    } catch (Exception e) {
                        Log.e("ClientActivity", "S: Error", e);
                    }
                }
                myClient.close();
                output.close();
                input.close();
                Log.d("ClientActivity", "C: Closed.");
            } catch (Exception e) {
                Log.e("ClientActivity", "C: Error", e);
                connected = false;
            }
        }

        private void connectToServer() {
            try {
                myClient = new Socket(hostName, portNumber);
                output = new BufferedWriter(new OutputStreamWriter(
                        myClient.getOutputStream()));
                input = new BufferedReader(new InputStreamReader(
                        myClient.getInputStream()));
                sysIn = new BufferedReader(new InputStreamReader(System.in));
                // Creating socket and checking for exceptions
                // Creating output stream to send information
                // Creating input stream to receive information

               // sendToServer(textToSend);
                //receiveFromServer();


                //myClient.close();
                // closing connections and socket after use
            }
            catch (IOException ioe) {
                System.out.println(ioe);
                System.out.println("Feil ved tilkobling");
            }

            // Sending string to target host

        }
        private void sendToServer(String textToSend) {
            try {
                output.write(textToSend);
                output.newLine();
                output.flush();
                // Sending string to target host
            }
            catch(IOException ioe) {
                System.out.println(ioe);
            }
        }

        private void receiveFromServer() {
            try {
                while ((userInput = sysIn.readLine()) != null) {
                    out.println(userInput);
                    System.out.println("echo" + input.read());
                    // Receiving and printing response from the server
                }
            } catch (IOException ioe) {
                System.out.println(ioe);
            }
        }

    }

}

