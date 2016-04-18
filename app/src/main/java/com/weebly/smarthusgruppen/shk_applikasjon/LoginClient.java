package com.weebly.smarthusgruppen.shk_applikasjon;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Button;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.widget.LinearLayout;
import android.widget.TextView;

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
    TextView changePw;
    CheckBox rememberMe;
    EditText tempUser;
    EditText tempPw;

    int serverPort = 12345;
    String hostName= "128.39.81.44";
    // 128.39.81.160 10.0.2.2
    static BufferedWriter output;
    static BufferedReader input;
    String username;
    String password;
    Boolean loggedIn;
    Boolean connected;
    Boolean rememberMeBool;
    static Socket connection;
    private SwipeRefreshLayout swipeContainer;
    String pwChanged;
    Handler gHandler;

    int ep;

    private SharedPreferences loginSettings;
    private SharedPreferences.Editor loginEditor;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_client);

        gHandler = new Handler();
        if(savedInstanceState == null){
            loggedIn = false;
            connected = false;
            connect();
        }
        rememberMe = (CheckBox)findViewById(R.id.saveLoginCheckBox);
        loginBtn = (Button) findViewById(R.id.login_button);
        loginBtn.setOnClickListener(userLogin);
        tempUser = (EditText) findViewById(R.id.edittext_username);
        tempPw = (EditText) findViewById(R.id.edittext_password);
        changePw = (TextView) findViewById(R.id.change_pw);
        changePw.setOnClickListener(changePassword);

        loginSettings = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        loginEditor = loginSettings.edit();

        rememberMeBool = loginSettings.getBoolean("saveLogin", false);
        if (rememberMeBool == true) {
            tempUser.setText(loginSettings.getString("username", ""));
            tempPw.setText(loginSettings.getString("password", ""));
            rememberMe.setChecked(true);
        }


        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        // Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener(new OnRefreshListener() {
            public void onRefresh() {
                // Your code to refresh the list here.
                // Make sure you call swipeContainer.setRefreshing(false)
                // once the network request has completed successfully.
                connect();

             //   fetchTimelineAsync(0);
            }
        });
        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

    }
 /*   public void fetchTimelineAsync(int page) {
        // Send the network request to fetch the updated data
        // `client` here is an instance of Android Async HTTP
        client.getHomeTimeline(0, new JsonHttpResponseHandler() {
            public void onSuccess(JSONArray json) {
                // Remember to CLEAR OUT old items before appending in the new ones
                adapter.clear();
                // ...the data has come back, add new items to your adapter...
                adapter.addAll(...);
                // Now we call setRefreshing(false) to signal refresh has finished
                swipeContainer.setRefreshing(false);
            }

            public void onFailure(Throwable e) {
                Log.d("DEBUG", "Fetch timeline error: " + e.toString());
            }
        });
    }
*/

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

    @Override
    public void onDestroy() {
        super.onDestroy();
        try {
            output.write("Disconnect");
            output.newLine();
            output.flush();
        }catch (Exception e) {
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



    public View.OnClickListener changePassword = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            final AlertDialog.Builder alertDialog = new AlertDialog.Builder(LoginClient.this);
            alertDialog.setTitle("Bytt passord");
            final EditText userName = new EditText(LoginClient.this);
            final EditText oldPass = new EditText(LoginClient.this);
            final EditText newPass = new EditText(LoginClient.this);
            final EditText confirmPass = new EditText(LoginClient.this);
            LinearLayout layout = new LinearLayout(LoginClient.this);
            layout.setOrientation(LinearLayout.VERTICAL);
            userName.setHint("Brukernavn");
            oldPass.setHint("Nåværende passord");
            newPass.setHint("Nytt passord");
            confirmPass.setHint("Bekreft passord");
            oldPass.setTransformationMethod(new PasswordTransformationMethod());
            newPass.setTransformationMethod(new PasswordTransformationMethod());
            confirmPass.setTransformationMethod(new PasswordTransformationMethod());

            layout.addView(userName);
            layout.addView(oldPass);
            layout.addView(newPass);
            layout.addView(confirmPass);
            alertDialog.setView(layout);

            alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(final DialogInterface dialog, int which) {
                    try {
                        dialog.dismiss();

                        final String oP = oldPass.getText().toString();
                        final String newP = newPass.getText().toString();
                        final String confirmP = confirmPass.getText().toString();
                        final String user = userName.getText().toString();
                        pwChanged = "Ping";

                        if (connection.isConnected() &&
                                !newP.isEmpty() && !confirmP.isEmpty() && newP.equals(confirmP)) {
                            final String code = "ChangePW";

                            /*
                            final ProgressDialog pDialog = new ProgressDialog(LoginClient.this);
                            pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                            pDialog.setMessage("Endrer passord..");
                            pDialog.setIndeterminate(true);
                            pDialog.setCancelable(false);
                            pDialog.show();
                            */

                            gHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        /*
                                        */
                                        Thread thread = new Thread(new Runnable() {
                                            @Override
                                            public void run() {
                                                try {
                                                    sendNewUserInformation(code, user, oP, newP);
                                                    ep = 0;
                                                    while (pwChanged.contains("Ping") && pwChanged != null) {
                                                        if (input.ready()) {
                                                            pwChanged = input.readLine();
                                                        }
                                                        Random rng = new Random();
                                                        TimeUnit.MILLISECONDS.sleep(rng.nextInt(150) * 10);
                                                        if (ep == 20) break;
                                                        ep++;
                                                    }
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        });
                                        thread.start();
                                        thread.join();
                                        if (ep != 20) {
                                            if (pwChanged.equals("PChanged")) {
                                                pwChanged = "Ping";
                                                AlertDialog.Builder add = new AlertDialog.Builder(LoginClient.this);
                                                add.setTitle("Suksess");
                                                add.setMessage("Passordet ditt har blitt endret!");
                                                add.setCancelable(false);
                                                add.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        rememberMe.setChecked(false);
                                                    }
                                                });
                                                add.create();
                                                add.show();
                                            } else {
                                                AlertDialog.Builder add = new AlertDialog.Builder(LoginClient.this);
                                                add.setTitle("Feil");
                                                add.setMessage("Feil brukernavn/gammelt passord."
                                                                + "\n\n" +
                                                                "Vennligst prøv på nytt.");
                                                add.setCancelable(false);
                                                add.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                    }
                                                });
                                                add.create();
                                                add.show();
                                            }
                                        } else {
                                            AlertDialog.Builder add = new AlertDialog.Builder(LoginClient.this);
                                            add.setTitle("Feil");
                                            add.setMessage("Endringen av passord tok for lang tid. Prøv igjen");
                                            add.setCancelable(false);
                                            add.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                }
                                            });
                                            add.create();
                                            add.show();
                                        }

                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            });

                        } else {
                            AlertDialog.Builder add = new AlertDialog.Builder(LoginClient.this);
                            add.setTitle("Feil");
                            add.setMessage("Nytt passord og bekreft passord må være like." + "\n\n" +
                                    "Vennligst prøv på nytt.");
                            add.setCancelable(false);
                            add.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                            add.create();
                            add.show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        AlertDialog.Builder add = new AlertDialog.Builder(LoginClient.this);
                        add.setTitle("Feil");
                        add.setMessage("Noe gikk galt. Sjekk tilkobling til server.");
                        add.setCancelable(false);
                        add.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                        add.create();
                        add.show();
                    }
                }
            });
            alertDialog.setNegativeButton("Avbryt", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            alertDialog.create();
            alertDialog.show();
        }
    };

    public View.OnClickListener userLogin = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            username = tempUser.getText().toString();
            password = tempPw.getText().toString();

            if(connected == false) {
                AlertDialog.Builder add = new AlertDialog.Builder(LoginClient.this);
                add.setTitle("Ikke tilkoblet");
                add.setMessage("Prøv å logg inn igjen");
                add.setCancelable(false);
                add.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                add.create();
                add.show();
                //Tries to connect again
                connect();
            }
            else {

                final ProgressDialog pDialog = new ProgressDialog(LoginClient.this);
                pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                pDialog.setMessage("Logger inn..");
                pDialog.setIndeterminate(true);
                pDialog.setCancelable(false);
                pDialog.show();
                Thread thread = new Thread((new Runnable() {
                    @Override
                    public void run() {
                        String login = "Login";
                        if (!username.isEmpty() && !password.isEmpty() && !login.isEmpty()) {
                            //If rememberMe is checked, saves the user for further logins
                            if (rememberMe.isChecked()) {
                                loginEditor.putBoolean("saveLogin", true);
                                loginEditor.putString("username", username);
                                loginEditor.putString("password", password);
                                loginEditor.commit();
                            } else {
                                loginEditor.clear();
                                loginEditor.commit();
                            }

                            if (connection.isConnected()) {

                                Log.d("ClientActivity", "C: Trying to log in.");
                                Random rnd = new Random();

                                try {
                                    sendLogin(login, username, password);
                                   // Random rng = new Random();
                                    //TimeUnit.MILLISECONDS.sleep(rng.nextInt(100) * 10);
                                   // ProgressDialog dialog = ProgressDialog.show(LoginClient.this, "Loading", "Please wait...", true);

                                    String read = "Ping";
                                    int n =0;
                                    while(read.contains("Ping") && read != null) {
                                        read = input.readLine();
                                        Random rng = new Random();
                                        TimeUnit.MILLISECONDS.sleep(rng.nextInt(100) * 10);
                                        if(n == 20) break;
                                        n++;
                                        pDialog.dismiss();
                                    }
                                    //dialog.dismiss();
                                    //int ID = Integer.valueOf(input.readLine());
                                    if(n != 20) {
                                        int ID = Integer.parseInt(read);
                                        Log.d("ClientActivity", "C: logging in..." + ID);
                                        if (ID > 0) {
                                            loggedIn = true;
                                            goToHome();
                                        } else {
                                            gHandler.post(new Runnable() {
                                                @Override
                                                public void run() {
                                                    AlertDialog.Builder add = new AlertDialog.Builder(LoginClient.this);
                                                    add.setTitle("Pålogging feilet");
                                                    add.setMessage("Feil brukernavn eller passord");
                                                    add.setCancelable(false);
                                                    add.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialog, int which) {
                                                        }
                                                    });
                                                    add.create();
                                                    add.show();
                                                }
                                            });
                                        }
                                    }
                                    else {
                                        gHandler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                AlertDialog.Builder add = new AlertDialog.Builder(LoginClient.this);
                                                add.setTitle("Pålogging failet");
                                                add.setMessage("Påloggingen tok for lang tid. Prøv igjen");
                                                add.setCancelable(false);
                                                add.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                    }
                                                });
                                                add.create();
                                                add.show();
                                            }
                                        });

                                    }
                                    // når fleire brukarar og sider skal gå til ting kan vi bruke en switch her og
                                    // sende med forskjellige ID
                                } catch (Exception e) {
                                    e.printStackTrace();
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

    public static void sendNewUserInformation(String code, String userName, String oldPw,
                                              String newPw){
        try {
            output.write(code);
            output.newLine();
            output.flush();

            output.write(userName);
            output.newLine();
            output.flush();

            output.write(oldPw);
            output.newLine();
            output.flush();

            output.write(newPw);
            output.newLine();
            output.flush();

        }catch (IOException e) {
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
                //try {

                    Log.d("ClientActivity", "C: Connecting...");
                    //while(!connected) {
                        connectToServer();
                     //   Thread.sleep(50);
                   // }
               //  } catch (InterruptedException e) {
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
            if(connection.isConnected()) connected = true;

        } catch (UnknownHostException e) {
            AlertDialog.Builder add = new AlertDialog.Builder(LoginClient.this);
            add.setTitle("Unable to connect to server");
            add.setMessage("Check your internet connection");
            add.setCancelable(false);
            add.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
                    });
            add.create();
            add.show();
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