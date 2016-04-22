package com.weebly.smarthusgruppen.shk_applikasjon.Toppleilighet;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.weebly.smarthusgruppen.shk_applikasjon.R;

public class Windows extends AppCompatActivity {
    ImageButton homeBtn;
    ToggleButton win1Open;
    ToggleButton win1Close;
    ToggleButton win1Stop;
    ToggleButton win2Open;
    ToggleButton win2Close;
    ToggleButton win2Stop;
    ToggleButton win3Open;
    ToggleButton win3Close;
    ToggleButton win3Stop;
    TextView degreeOpen1;
    TextView degreeOpen2;
    TextView degreeOpen3;
    Float winOpeningTime1 = 150f;
    Float winOpeningTime2 = 120f;
    Float winOpeningTime3 = 140f;
    Float winOpener = 4.5f;
    Boolean stopping1 = true;
    Boolean stopping2 = true;
    Boolean stopping3 = true;
    Boolean chk1 = true;
    Boolean chk2 = true;
    Boolean chk3 = true;
    Handler gHandler;
    Boolean thrOpen1 = true;
    Boolean thrOpen2 = true;
    Boolean thrOpen3 = true;
    Boolean thrClose1 = true;
    Boolean thrClose2 = true;
    Boolean thrClose3 = true;

    int min = 180;
    int max = 90;



    boolean w1open = false;
    boolean w1close = false;
    boolean w1stop = false;
    boolean w2open = false;
    boolean w2close = false;
    boolean w2stop = false;
    boolean w3open = false;
    boolean w3close = false;
    boolean w3stop = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_windows);
        gHandler = new Handler();
        setupGUI();

    }



    // Open window 1 (kitchen)
    protected View.OnClickListener win1_open = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!w1open) {
                MainActivity.sendText("Command:058336113,1,1");     // open window 1
                w1open = true;
                w1close = false;
                w1stop = false;
                stopping1 = true;
                chk1 = true;
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                                    windowOpening(winOpeningTime1, 1, chk1);
                        }
                        catch(Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                thread.start();

                win1Close.setChecked(false);
                win1Stop.setChecked(false);

            }else if (w1open) {
                // do nothing
            }
        }
    };

    // Close window 1 (kitchen)
    protected View.OnClickListener win1_close = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!w1close) {
                MainActivity.sendText("Command:058336113,1,2");     // close window 1
                w1open = false;
                w1close = true;
                w1stop = false;
                stopping1 = true;
                chk1 = false;
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                                    windowOpening(winOpeningTime1, 1, chk1);

                        }
                        catch(Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                thread.start();
                win1Open.setChecked(false);
                win1Stop.setChecked(false);

            }else if (w1close) {
                // do nothing
            }
        }
    };

    // Stop window 1 (kitchen)
    protected View.OnClickListener win1_stop = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!w1stop) {
                MainActivity.sendText("Command:058336113,1,0");     // stop window 1
                w1open = false;
                w1close = false;
                w1stop = true;
                stopping1 = false;
                chk1 = false;
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                                    windowOpening(winOpeningTime1, 1, chk1);
                        }
                        catch(Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                thread.start();
                win1Close.setChecked(false);
                win1Open.setChecked(false);

            }else if (w1stop) {
                // do nothing
            }
        }
    };


    // Open window 2 (kitchen)
    protected View.OnClickListener win2_open = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!w2open) {
                MainActivity.sendText("Command:058336113,1,1");     // open window 2
                w2open = true;
                w2close = false;
                w2stop = false;
                stopping2 = true;
                chk2 = true;
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {

                                    windowOpening(winOpeningTime2, 2, chk2);
                        }
                        catch(Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                thread.start();
                win2Close.setChecked(false);
                win2Stop.setChecked(false);

            }else if (w2open) {
                // do nothing
            }
        }
    };

    // Close window 2 (kitchen)
    protected View.OnClickListener win2_close = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!w2close) {
                MainActivity.sendText("Command:058336113,1,2");     // close window 2
                w2open = false;
                w2close = true;
                w2stop = false;
                stopping2 = true;
                chk2 = false;
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {

                                    windowOpening(winOpeningTime2, 2, chk2);
                        }
                        catch(Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                thread.start();
                win2Open.setChecked(false);
                win2Stop.setChecked(false);

            }else if (w2close) {
                // do nothing
            }
        }
    };

    // Stop window 2 (kitchen)
    protected View.OnClickListener win2_stop = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!w2stop) {
                MainActivity.sendText("Command:058336113,1,0");     // stop window 2
                w2open = false;
                w2close = false;
                w2stop = true;
                stopping2 = false;
                chk2 = false;
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {

                                    windowOpening(winOpeningTime2, 2, chk2);
                        }
                        catch(Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                thread.start();
                win2Close.setChecked(false);
                win2Open.setChecked(false);

            }else if (w2stop) {
                // do nothing
            }
        }
    };


    // Open window 3 (kitchen)
    protected View.OnClickListener win3_open = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!w3open) {
                MainActivity.sendText("Command:058336113,1,1");     // open window 3
                w3open = true;
                w3close = false;
                w3stop = false;
                stopping3 = true;
                chk3 = true;
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {

                                    windowOpening(winOpeningTime3, 3, chk3);

                        }
                        catch(Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                thread.start();
                win3Close.setChecked(false);
                win3Stop.setChecked(false);

            }else if (w3open) {
                // do nothing
            }
        }
    };

    // Close window 3 (kitchen)
    protected View.OnClickListener win3_close = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!w3close) {
                MainActivity.sendText("Command:058336113,1,2");     // close window 3
                w3open = false;
                w3close = true;
                w3stop = false;
                stopping3 = true;
                chk3 = false;

                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {

                            windowOpening(winOpeningTime3, 3, chk3);

                        }
                        catch(Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                thread.start();
                win3Open.setChecked(false);
                win3Stop.setChecked(false);

            }else if (w3close) {
                // do nothing
            }
        }
    };

    // Stop window 3(kitchen)
    protected View.OnClickListener win3_stop = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!w3stop) {
                MainActivity.sendText("Command:058336113,1,0");     // stop window 3
                w3open = false;
                w3close = false;
                w3stop = true;
                stopping3 = false;
                chk3 = false;
                Log.d("Stop3:", "stopping");
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                                    windowOpening(winOpeningTime3, 3, chk3);
                        }
                        catch(Exception e) {
                            e.printStackTrace();
                    }
                }
                });
                thread.start();
                win3Close.setChecked(false);
                win3Open.setChecked(false);

            }else if (w3stop) {
                // do nothing
            }
        }
    };



    public void goToHome() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    // parameters window = current degree of open/closed, ID which window, check = opening or closing?
    // Function for displaying how far each window is currently opened
    public void windowOpening(float window, final int id, boolean check) {

        for(int i = 0; i< 20; i++) {
            if((stopping1 && (id == 1)) || (stopping2 && (id == 2)) || (stopping3 && (id == 3))) {
                try {
                    //sending the actual Thread of execution to sleep X milliseconds
                    Thread.sleep(1000);
                } catch (Exception e) {
                    System.out.println("Exception : " + e.getMessage());
                }
                // if true - means the window is going to be opened or partially opened.
                if (check) {
                    window -= winOpener;
                    Log.d("opening window..", "Opening window..." + window);
                    final float tempwin = window;

                    // always updating display
                    gHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            switch(id) {
                                case 1:
                                    String temp1 = String.valueOf(tempwin + "°");
                                    if(tempwin < max) {
                                        degreeOpen1.setText(max + "°");
                                    }
                                    else degreeOpen1.setText(temp1);
                                    break;
                                case 2:
                                    String temp2 = String.valueOf(tempwin + "°");
                                    if(tempwin < max) {
                                        degreeOpen2.setText(max + "°");
                                    }
                                    else degreeOpen2.setText(temp2);
                                    break;
                                case 3:
                                    String temp3 = String.valueOf(tempwin + "°");
                                    if(tempwin < max) {
                                        degreeOpen3.setText(max + "°");
                                    }
                                    else degreeOpen3.setText(temp3);
                                    break;
                            }

                        }
                    });

                    switch(id) {
                        case 1:
                            winOpeningTime1 = window;
                            if(max > window) {
                                winOpeningTime1 = 90.0f;
                            }
                            Log.d("updating ", "variable 1 "+ winOpeningTime1);
                            break;
                        case 2:
                            winOpeningTime2 = window;
                            if(max > window) {
                                winOpeningTime1 = 90.0f;
                            }
                            Log.d("udpdating ", "variable 2 "+ winOpeningTime2);
                            break;
                        case 3:
                            winOpeningTime3 = window;
                            if(max > window) {
                                winOpeningTime1 = 90.0f;
                            }
                            Log.d("updating ", "variable 3 "+ winOpeningTime3);
                            break;
                    }

                    if(max >= window) {
                        i = 20;
                        Log.d("opening window.." , "Finished opening " + window);

                    }
                }
                // else means the window is going to be closed or partially closed
                else {
                    window += winOpener;
                    Log.d("closing window.." , "Closing Window...." + window);

                    final float tempwin = window;
                    // always updating display
                    gHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            switch(id) {
                                case 1:
                                    String temp1 = String.valueOf(tempwin + "°");
                                    if(tempwin > min) {
                                        degreeOpen1.setText(min + "°");
                                    }
                                    else degreeOpen1.setText(temp1);
                                    break;
                                case 2:
                                    String temp2 = String.valueOf(tempwin + "°");
                                    if(tempwin > min) {
                                        degreeOpen2.setText(min + "°");
                                    }
                                    else degreeOpen2.setText(temp2);
                                    break;
                                case 3:
                                    String temp3 = String.valueOf(tempwin + "°");
                                    if(tempwin > min) {
                                        degreeOpen3.setText(min + "°");
                                    }
                                    else degreeOpen3.setText(temp3);
                                    break;
                            }

                        }
                    });
                    switch(id) {
                        case 1:
                            winOpeningTime1 = window;
                            if(window > min) {
                                winOpeningTime1 = 180.0f;
                            }
                            Log.d("updating", "variable 1 "+ winOpeningTime1);
                            break;
                        case 2:
                            winOpeningTime2 = window;
                            if(window > min) {
                                winOpeningTime2 = 180.0f;
                            }
                            Log.d("updating ", "variable 2 "+ winOpeningTime2);
                            break;
                        case 3:
                            winOpeningTime3 = window;
                            if(window > min) {
                                winOpeningTime3 = 180.0f;
                            }
                            Log.d("updating ", "variable 3 "+ winOpeningTime3);
                            break;
                    }

                    if(window >= min) {
                        i = 20;        // breaking out of loop if window reaches closing point.
                        Log.d("closing window.." , "Finished closing " + window);
                    }
                    /* arithmetic seems reversed because the degree starts at 180 and goes towards 90
                    * So while actually opening the window we need to reduce the degrees*/
                }

            }
            else {
                i = 20;     // viss stopp blir trykt
            }

        }
       /* final float tempFloat = window;
        gHandler.post(new Runnable() {
            @Override
            public void run() {
                switch (id) {
                    case 1:
                        winOpeningTime1 = tempFloat;
                        float w1temp = Math.abs(winOpeningTime1 - 180);
                        String temp1 = String.valueOf(w1temp + "°");
                        degreeOpen1.setText(temp1);
                        Log.d("first window", "printing to screen" + temp1);

                        break;
                    case 2:
                        winOpeningTime2 = tempFloat;
                        float w2temp = Math.abs(winOpeningTime2 - 180);
                        String temp2 = String.valueOf(w2temp + "°");
                        degreeOpen2.setText(temp2);
                        Log.d("second window", "printing to screen" + temp2);
                        break;
                    case 3:
                        winOpeningTime3 = tempFloat;
                        //float w3temp = Math.abs(winOpeningTime3-180);
                        //String temp3 = String.valueOf(w3temp + "°");
                        String temp3 = String.valueOf(winOpeningTime3 + "°");
                        degreeOpen3.setText(temp3);
                        Log.d("third window", "printing to screen" + temp3);

                        break;
                }
            }
        }); */



    }

    public void setupGUI() {
        //home button
        homeBtn = (ImageButton) findViewById(R.id.home_button);
        homeBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goToHome();
            }
        });

        win1Open = (ToggleButton) findViewById(R.id.win1_button);
        win1Open.setOnClickListener(win1_open);
        win1Close = (ToggleButton) findViewById(R.id.win2_button);
        win1Close.setOnClickListener(win1_close);
        win1Stop = (ToggleButton) findViewById(R.id.win3_button);
        win1Stop.setOnClickListener(win1_stop);

        win2Open = (ToggleButton) findViewById(R.id.win5_button);
        win2Open.setOnClickListener(win2_open);
        win2Close = (ToggleButton) findViewById(R.id.win6_button);
        win2Close.setOnClickListener(win2_close);
        win2Stop = (ToggleButton) findViewById(R.id.win7_button);
        win2Stop.setOnClickListener(win2_stop);

        win3Open = (ToggleButton) findViewById(R.id.win9_button);
        win3Open.setOnClickListener(win3_open);
        win3Close = (ToggleButton) findViewById(R.id.win10_button);
        win3Close.setOnClickListener(win3_close);
        win3Stop = (ToggleButton) findViewById(R.id.win11_button);
        win3Stop.setOnClickListener(win3_stop);

        /* These textviews will show on the screen to indicate to what degree
         the window is opened */
        degreeOpen1 = (TextView) findViewById(R.id.win4_button);
        String temp1 = String.valueOf(winOpeningTime1 + "°");
        degreeOpen1.setText(temp1);
        degreeOpen2 = (TextView) findViewById(R.id.win8_button);
        String temp2 = String.valueOf(winOpeningTime2 + "°");
        degreeOpen2.setText(temp2);
        degreeOpen3 = (TextView) findViewById(R.id.win12_button);
        String temp3 = String.valueOf(winOpeningTime3 + "°");
        degreeOpen3.setText(temp3);
    }
}
