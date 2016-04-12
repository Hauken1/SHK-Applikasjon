package com.weebly.smarthusgruppen.shk_applikasjon;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;


import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class LoginClient extends AppCompatActivity {
    Button loginBtn;
    int serverPort = 12345;
    String hostName= "128.39.82.229";
    // 128.39.81.160 10.0.2.2
    static BufferedWriter output;
    static BufferedReader input;
    String username;
    String password;
    Boolean loggedIn;
    Boolean connected;
    static Socket connection;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_client);

        if(savedInstanceState == null){
            loggedIn = false;
            connected = false;
            connect();

            loginBtn = (Button) findViewById(R.id.login_button);
            loginBtn.setOnClickListener(userLogin);

        }

    }

    protected void onRestart() {
        super.onRestart();
        try {
            sendDisconnect("Disconnect");
            connected = false;
            loggedIn = false;
        } catch (Exception e){
            e.printStackTrace();
        }
    }


    protected static Socket returnConnection() {
        return connection;
    }

    protected static BufferedReader returnReader() {
        return input;
    }

    protected static BufferedWriter returnwriter() {
        return output;
    }




    public View.OnClickListener userLogin = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            EditText tempUser = (EditText) findViewById(R.id.edittext_username);
            EditText tempPw = (EditText) findViewById(R.id.edittext_password);
            username = tempUser.getText().toString();
            password = tempPw.getText().toString();

            if(connected == false) {
                connect();

            }
            else {

                Thread thread = new Thread((new Runnable() {
                    @Override
                    public void run() {
                        String login = "Login";
                        if (!username.isEmpty() && !password.isEmpty() && !login.isEmpty()) {


                            if (connection.isConnected()) {

                                Log.d("ClientActivity", "C: Trying to log in.");
                                Random rnd = new Random();
                                sendLogin(login, username, password);
                                try {
                                    //TimeUnit.MILLISECONDS.sleep(rnd.nextInt(100) * 10);
                                    int ID = Integer.valueOf(input.readLine());
                                    System.out.print(ID + " ID");
                                    Log.d("ClientActivity", "C: logging in..." + ID);
                                    if (ID > 0) {
                                        loggedIn = true;
                                        goToHome();
                                    } else {
                                        new AlertDialog.Builder(LoginClient.this)
                                                .setTitle("Failed login")
                                                .setMessage("Wrong username or password")
                                                .setCancelable(false)
                                                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {

                                                    }
                                                }).create().show();
                                    }

                                    // når fleire brukarar og sider skal gå til ting kan vi bruke en switch her og
                                    // sende med forskjellige ID


                                } catch (Exception e) {
                                    System.out.println(e);
                                }
                            }

                        }
                    }
                }));

                thread.start();
            }
        }

    };


    public void sendDisconnect(String msg) {
        try {
            output.write(msg);
            output.newLine();
            output.flush();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    // sending login information to server
    public static void sendLogin(String code, String username, String password) {
        try {
            output.write(code);
            output.newLine();
            output.flush();

            output.write(username);
            output.newLine();
            output.flush();

            output.write(password);
            output.newLine();
            output.flush();
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }



    public void goToHome() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void connect() {
        /*
        Thread cThread = new Thread(new ClientThread());
        cThread.start();
        */
        final Thread thread = new Thread((new Runnable() {
            @Override
            public void run() {
                try {

                    Log.d("ClientActivity", "C: Connecting...");
                    while(!connected) {
                        connectToServer();
                        Thread.sleep(50);
                    }
                 } catch (InterruptedException e) {

                }
            }
        }));
        thread.start();

    }

    private void connectToServer() {
        try {
                connection = new Socket(InetAddress.getByName(hostName), serverPort);
                Log.d("ClientActivity", "C: Connected to server.");
                output = new BufferedWriter(new OutputStreamWriter(
                        connection.getOutputStream()));
                input = new BufferedReader(new InputStreamReader(
                        connection.getInputStream()));
                connected = true;

        } catch (UnknownHostException e) {
            new AlertDialog.Builder(LoginClient.this)
                    .setTitle("Unable to connect to server")
                    .setMessage("Check your internet connection")
                    .setCancelable(false)
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    }).create().show();

        } catch (IOException e) {
        }
    }
/*
    public class ClientThread implements Runnable {

        public void run() {
            try {
                Log.d("ClientActivity", "C: Connecting...");
                //while(!connected) {
                    connectToServer();

                // socket.close();
                    Thread.sleep(50);
                //}

            } catch (Exception e) {
                MainActivity.sendText("Disconnect");
                Log.e("ClientActivity", "C: Error", e);
            }
        }
    }
/
        private void connectToServer() {
            try {

                if(new Socket(InetAddress.getByName(hostName), serverPort).isConnected()) {
                    connection = new Socket(InetAddress.getByName(hostName), serverPort);
                    Log.d("ClientActivity", "C: Connected to server.");
                    output = new BufferedWriter(new OutputStreamWriter(
                            connection.getOutputStream()));
                    input = new BufferedReader(new InputStreamReader(
                            connection.getInputStream()));
                    connected = true;
                }
                else {
                    new AlertDialog.Builder(LoginClient.this)
                            .setTitle("Unable to connect to server")
                            .setMessage("Check your internet connection")
                            .setCancelable(false)
                            .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            }).create().show();
                }
            } catch (UnknownHostException e) {

            } catch (IOException e) {
            }
        }

*/

}