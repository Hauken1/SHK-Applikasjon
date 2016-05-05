package com.weebly.smarthusgruppen.shk_applikasjon;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.weebly.smarthusgruppen.shk_applikasjon.Arbeidsrom.MainActivity_3;
import com.weebly.smarthusgruppen.shk_applikasjon.Leilighet.MainActivity_2;
import com.weebly.smarthusgruppen.shk_applikasjon.Toppleilighet.MainActivity;

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
    TextView forgotPw;

    int serverPort = 12345;
    String hostName= "192.168.38.110";
    // 128.39.81.160 10.0.2.2
    static BufferedWriter output;
    static BufferedReader input;
    String username;
    String password;
    Boolean loggedIn;
    Boolean connected;
    Boolean rememberMeBool;
    static Socket connection;
    String pwChanged;
    Handler gHandler;

    int ep, fPwCounter;

    private SharedPreferences loginSettings;
    private SharedPreferences.Editor loginEditor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_client);

        gHandler = new Handler();
        if (savedInstanceState == null) {
            loggedIn = false;
            connected = false;
            connect();
        }
        rememberMe = (CheckBox) findViewById(R.id.saveLoginCheckBox);
        loginBtn = (Button) findViewById(R.id.login_button);
        loginBtn.setOnClickListener(userLogin);
        tempUser = (EditText) findViewById(R.id.edittext_username);
        tempPw = (EditText) findViewById(R.id.edittext_password);
        changePw = (TextView) findViewById(R.id.change_pw);
        changePw.setOnClickListener(changePassword);
        forgotPw = (TextView)findViewById(R.id.forgot_pw);
        forgotPw.setOnClickListener(forgotPassword);

        loginSettings = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        loginEditor = loginSettings.edit();

        rememberMeBool = loginSettings.getBoolean("saveLogin", false);
        if (rememberMeBool) {
            tempUser.setText(loginSettings.getString("username", ""));
            tempPw.setText(loginSettings.getString("password", ""));
            rememberMe.setChecked(true);
        }
        randomWait();
        if(!connected) {
            AlertDialog.Builder add = new AlertDialog.Builder(LoginClient.this);
            add.setTitle("Kan ikke koble til server");
            add.setMessage("Sjekk internettilkoblingen");
            add.setCancelable(false);
            add.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            add.create();
            add.show();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    public static Socket returnConnection() {
        return connection;
    }

    public static BufferedReader returnReader() {
        return input;
    }

    public static BufferedWriter returnwriter() {
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

                        if (connected &&
                                !newP.isEmpty() && !confirmP.isEmpty() && newP.equals(confirmP)) {
                            final String code = "ChangePW";
/*
                            gHandler.post(new Runnable() {
                                @Override
                                public void run() {
*/
                            try {
                                /*
                                */
                                final ProgressDialog pDialog = new ProgressDialog(LoginClient.this);
                                pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                                pDialog.setMessage("Endrer passord..");
                                pDialog.setIndeterminate(true);
                                pDialog.setCancelable(false);
                                pDialog.show();
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
                                       // thread.start();
                                        //thread.join();
                                        pDialog.dismiss();
                                        gHandler.post(new Runnable() {
                                            @Override
                                            public void run() {
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
                                                }
                                                else {
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
                                            }
                                        });
                                    }
                                });
                                thread.start();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                                //}
                          //  });

                        } else {
                            AlertDialog.Builder add = new AlertDialog.Builder(LoginClient.this);
                            add.setTitle("Feil");
                            add.setMessage("Nytt passord og bekreft passord må være like." + "\n" +
                                    "Eller sjekk din tilkobling til server" +
                                    "\n\nVennligst prøv på nytt.");
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

    public View.OnClickListener forgotPassword = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if(!connected) {
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
                final Dialog settingsDialog = new Dialog(LoginClient.this);
                settingsDialog.setContentView(R.layout.forgot_password);
                settingsDialog.setCancelable(true);

                final EditText user = (EditText)settingsDialog.findViewById(R.id.edittext_user_name);
                final EditText pw = (EditText)settingsDialog.findViewById(R.id.edittext_forgot_pw);
                Button okButton = (Button)settingsDialog.findViewById(R.id.ok_button);
                okButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final String forgot_user = user.getText().toString();
                        final String forgot_pw = pw.getText().toString();

                        if(!connected) {
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
                            pDialog.setMessage("Tester nødpassord..");
                            pDialog.setIndeterminate(true);
                            pDialog.setCancelable(false);
                            pDialog.show();
                            Thread thread = new Thread((new Runnable() {
                                @Override
                                public void run() {
                                    String eLogin = "EmergencyLogin";
                                    if (!forgot_user.isEmpty() && !forgot_pw.isEmpty() && !eLogin.isEmpty()) {

                                        if (connection.isConnected()) {

                                            Log.d("ClientActivity", "C: Trying to log in.");
                                            Random rnd = new Random();

                                            try {
                                                sendLogin(eLogin, forgot_user, forgot_pw);
                                                // Random rng = new Random();
                                                //TimeUnit.MILLISECONDS.sleep(rng.nextInt(100) * 10);
                                                // ProgressDialog dialog = ProgressDialog.show(LoginClient.this, "Loading", "Please wait...", true);

                                                String read = "Ping";
                                                int n =0;
                                                while(read.contains("Ping") && read != null) {
                                                    read = input.readLine();
                                                    Random rng = new Random();
                                                    TimeUnit.MILLISECONDS.sleep(rng.nextInt(100));
                                                    if(n == 20) break;
                                                    n++;

                                                }
                                                fPwCounter = n;
                                                pwChanged = read;
                                                pDialog.dismiss();
                                                settingsDialog.dismiss();
                                                //dialog.dismiss();
                                                //int ID = Integer.valueOf(input.readLine());
                                                gHandler.post(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        if (fPwCounter != 20) {
                                                            int ID = Integer.parseInt(pwChanged);
                                                            Log.d("ClientActivity", "C: logging in..." + ID);

                                                            if (ID != 0) {
                                                                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(LoginClient.this);
                                                                alertDialog.setTitle("Bytt passord");
                                                                TextView userText = new TextView(LoginClient.this);
                                                                TextView infoText = new TextView(LoginClient.this);
                                                                userText.setText("\nKun bruk tall eller bokstaver, utenom ÆØÅ");
                                                                userText.setGravity(Gravity.CENTER);
                                                                infoText.setText("\nEtter bekreft er trykket, vent mens passordet endres");
                                                                infoText.setGravity(Gravity.CENTER);
                                                                final EditText newPass = new EditText(LoginClient.this);
                                                                final EditText confirmPass = new EditText(LoginClient.this);
                                                                newPass.setGravity(Gravity.CENTER);
                                                                confirmPass.setGravity(Gravity.CENTER);
                                                                LinearLayout layout = new LinearLayout(LoginClient.this);
                                                                layout.setOrientation(LinearLayout.VERTICAL);
                                                                newPass.setHint("Nytt passord");
                                                                confirmPass.setHint("Bekreft passord");
                                                                newPass.setTransformationMethod(new PasswordTransformationMethod());
                                                                confirmPass.setTransformationMethod(new PasswordTransformationMethod());

                                                                layout.addView(userText);
                                                                layout.addView(newPass);
                                                                layout.addView(confirmPass);
                                                                layout.addView(infoText);
                                                                alertDialog.setView(layout);

                                                                alertDialog.setPositiveButton("Bekreft", new DialogInterface.OnClickListener() {
                                                                    @Override
                                                                    public void onClick(final DialogInterface dialog, int which) {
                                                                        try {
                                                                            dialog.dismiss();
                                                                            final String newP = newPass.getText().toString();
                                                                            final String confirmP = confirmPass.getText().toString();

                                                                            if (connection.isConnected() &&
                                                                                    !newP.isEmpty() && !confirmP.isEmpty() && newP.equals(confirmP)) {
                                                                                final String code = "ForgotPw";
                                                                                        try {
                                                                                            Thread thread = new Thread(new Runnable() {
                                                                                                @Override
                                                                                                public void run() {
                                                                                                    try {
                                                                                                        sendNewPw(code, forgot_user, newP);
                                                                                                        ep = 0;
                                                                                                        pwChanged = "Ping";
                                                                                                        while (pwChanged.contains("Ping") && pwChanged != null) {
                                                                                                            if (input.ready()) {
                                                                                                                pwChanged = input.readLine();
                                                                                                            }
                                                                                                            Random rng = new Random();
                                                                                                            TimeUnit.MILLISECONDS.sleep(rng.nextInt(150) * 10);
                                                                                                            if (ep == 20)
                                                                                                                break;
                                                                                                            ep++;
                                                                                                        }
                                                                                                    } catch (Exception e) {
                                                                                                        e.printStackTrace();
                                                                                                    }
                                                                                                }
                                                                                            });
                                                                                            thread.start();
                                                                                            thread.join();
                                                                                            gHandler.post(new Runnable() {
                                                                                                @Override
                                                                                                public void run() {
                                                                                                    if (ep != 20) {
                                                                                                        if (pwChanged.equals("PWChanged")) {
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
                                                                                                            add.setMessage("Det nye passordet ble ikke lagret."
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
                                                                                                }
                                                                                            });

                                                                                        } catch (Exception e) {
                                                                                            e.printStackTrace();
                                                                                        }
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
                                                            } else failLogin();

                                                        } else {
                                                            failLogin2();
                                                        }
                                                    }
                                                });
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
                });
                settingsDialog.show();
            }
        }
    };

    public View.OnClickListener userLogin = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            username = tempUser.getText().toString();
            password = tempPw.getText().toString();

            if(!connected) {
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
                                    int n = 0;
                                    while(read.contains("Ping") && read != null) {
                                        read = input.readLine();
                                        Random rng = new Random();
                                        TimeUnit.MILLISECONDS.sleep(rng.nextInt(100));
                                        if(n == 20) break;
                                        n++;

                                    }
                                    pDialog.dismiss();
                                    //dialog.dismiss();
                                    //int ID = Integer.valueOf(input.readLine());
                                    if(n != 20) {
                                        int ID = Integer.parseInt(read);
                                        Log.d("ClientActivity", "C: logging in..." + ID);

                                       switch (ID) {
                                           case 1:
                                                   loggedIn = true;
                                                   goToAdmin();
                                               break;
                                           case 2:
                                                   loggedIn = true;
                                                   goToHome();
                                               break;
                                           case 3:
                                                   loggedIn = true;
                                                   goToHome2();
                                               break;
                                           case 4:
                                                   loggedIn = true;
                                                   goToHome3();
                                               break;
                                           default:
                                               failLogin();
                                               break;

                                       }
                                    }
                                    else {
                                        failLogin2();

                                    }
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

    public void sendNewPw(String code, String user, String password){
        try {
            output.write(code);
            output.newLine();
            output.flush();

            output.write(user);
            output.newLine();
            output.flush();

            output.write(password);
            output.newLine();
            output.flush();

        } catch (IOException ioe) {
            System.out.println(ioe);
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

    public void randomWait() {
        try {
            Random rng = new Random();
            TimeUnit.MILLISECONDS.sleep(rng.nextInt(150) * 10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void goToHome() {
        Log.d("Logger inn",  "toppleilighet");
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void goToAdmin() {
        Log.d("Logger inn admin", "Adminside..");
        Intent intent = new Intent(this, Admin.class);
        startActivity(intent);
    }

    public void goToHome2() {
        Log.d("Logger inn", "Leilighet");
        Intent intent = new Intent(this, MainActivity_2.class);
        startActivity(intent);

    }

    public void goToHome3() {
        Log.d("Logger inn", "Hybel");
        Intent intent = new Intent(this, MainActivity_3.class);
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
            add.setTitle("Kan ikke koble til server");
            add.setMessage("Sjekk internettilkoblingen");
            add.setCancelable(false);
            add.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
                    });
            add.create();
            add.show();
        } catch (IOException e) {
        }
    }

    public void failLogin()  {
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

    public void failLogin2() {
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


}