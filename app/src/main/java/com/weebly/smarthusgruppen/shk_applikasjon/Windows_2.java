package com.weebly.smarthusgruppen.shk_applikasjon;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ToggleButton;

public class Windows_2 extends AppCompatActivity {
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

                win3Close.setChecked(false);
                win3Open.setChecked(false);

            }else if (w3stop) {
                // do nothing
            }
        }
    };



    public void goToHome() {
        Intent intent = new Intent(this, MainActivity_2.class);
        startActivity(intent);
    }
}
