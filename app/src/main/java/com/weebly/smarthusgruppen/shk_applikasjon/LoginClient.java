package com.weebly.smarthusgruppen.shk_applikasjon;

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

    String hostName= "128.39.81.156";

    // 128.39.81.160 10.0.2.2
    static BufferedWriter output;
    static BufferedReader input;
    String username;

    String password;
    static Socket connection;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_client);


        //connectToServer();
        Thread cThread = new Thread(new ClientThread());
        cThread.start();

        loginBtn = (Button) findViewById(R.id.login_button);
        loginBtn.setOnClickListener(userLogin);


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


            Thread thread = new Thread((new Runnable() {
                @Override
                public void run() {
                    String login = "Login";
                    if (!username.isEmpty() && !password.isEmpty() && !login.isEmpty()) {

                        if (connection.isConnected()) {
                            Random rnd = new Random();
                            sendLogin(login, username, password);
                            try {
                                //TimeUnit.MILLISECONDS.sleep(rnd.nextInt(100) * 10);
                                int ID = Integer.valueOf(input.readLine());
                                System.out.print(ID + " ID");
                                Log.d("ClientActivity", "C: logging in..." + ID);
                                if (ID > 0) {
                                    goToHome();
                                } else {
                                    System.out.print("Feil passord eller brukernavn, prøv igjen");
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

    };


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



    public class ClientThread implements Runnable {

        public void run() {
            try {
                Log.d("ClientActivity", "C: Connecting...");
                connectToServer();

                // socket.close();
            } catch (Exception e) {
                MainActivity.sendText("Disconnect");
                Log.e("ClientActivity", "C: Error", e);
            }
        }
    }

        private void connectToServer() {
            try {
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



}