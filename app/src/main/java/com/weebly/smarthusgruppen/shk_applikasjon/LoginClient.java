package com.weebly.smarthusgruppen.shk_applikasjon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;



import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;



public class LoginClient extends AppCompatActivity {
    Button loginBtn;
    int serverPort = 12345;
    String hostName= "128.39.82.65";
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
            String login = "Login";

            int ID = 1;
            if (!username.isEmpty() && !password.isEmpty() && !login.isEmpty()) {
                sendLogin(login, username, password);
                try {
                    //ID = Integer.valueOf(MainActivity.input.readLine());

                    // når fleire brukarar og sider skal gå til ting kan vi bruke en switch her og
                    // sende med forskjellige ID

                    if (ID == 1) {

                        goToHome();
                    } else {
                        System.out.print("Feil passord eller brukernavn, prøv igjen");
                    }

                } catch (Exception e) {
                    System.out.println(e);
                }
            }

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
                try {
                    Log.d("ClientActivity", "C: Sending command.");
                    String message = "Test fra mobil app";

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