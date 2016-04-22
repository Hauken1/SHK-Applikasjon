package com.weebly.smarthusgruppen.shk_applikasjon.Leilighet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ToggleButton;

import com.weebly.smarthusgruppen.shk_applikasjon.R;

public class LightingHallway_2 extends AppCompatActivity {
    boolean lmin = false;
    boolean lmed = false;
    boolean lmax = false;

    boolean lmin1 = false;
    boolean lmed1 = false;
    boolean lmax1 = false;

    boolean lmin6 = false;
    boolean lmed6 = false;
    boolean lmax6 = false;
    boolean loff6 = false;

    ToggleButton lightOffBtn;
    ToggleButton lightMinBtn;
    ToggleButton lightMedBtn;
    ToggleButton lightMaxBtn;

    ToggleButton lightOffBtn1;
    ToggleButton lightMinBtn1;
    ToggleButton lightMedBtn1;
    ToggleButton lightMaxBtn1;

    ToggleButton lightOffBtn6;
    ToggleButton lightMinBtn6;
    ToggleButton lightMaxBtn6;
    ToggleButton lightMedBtn6;
    ImageButton homeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lighting_hallway_2);

        setupGUI();
    }


    // all lights off
    protected View.OnClickListener light_all__off_Listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (lmin || lmed || lmax) {
                MainActivity_2.sendText("Command:000002117,1,0");
                lmin = false;
                lmed = false;
                lmax = false;

                lightMinBtn.setChecked(false);
                lightMedBtn.setChecked(false);
                lightMaxBtn.setChecked(false);
            }
        }
    };

    // all lights on minimum
    protected View.OnClickListener light_all_min = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!lmin) {
                MainActivity_2.sendText("Command:000002117,1,1");     // all lights minimum
                lmin = true;
                lmed = false;
                lmax = false;

                lightMedBtn.setChecked(false);
                lightMaxBtn.setChecked(false);
                lightOffBtn.setChecked(false);
            } else if (lmin) {
                MainActivity_2.sendText("Command:000002117,1,0");     // all lights off
                allBoolFalse();
                allButtonsOff();
                lightOffBtn.setChecked(true);

            }
        }
    };

    // all lights on medium
    protected View.OnClickListener light_all_med = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!lmed) {
                MainActivity_2.sendText("Command:000002117,1,2");     // all lights medium
                lmin = false;
                lmed = true;
                lmax = false;

                lightMinBtn.setChecked(false);
                lightMaxBtn.setChecked(false);
                lightOffBtn.setChecked(false);
            } else if (lmed) {
                MainActivity_2.sendText("Command:000002117,1,0"); // all lights off
                allBoolFalse();
                allButtonsOff();
                lightOffBtn.setChecked(true);
            }
        }
    };

    // all lights on max
    protected View.OnClickListener light_all_Listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!lmax) {        //this method should only be called if there is a connection.
                MainActivity_2.sendText("Command:000002117,1,3"); // all lights to max
                lmin = false;
                lmed = false;
                lmax = true;

                lightMinBtn.setChecked(false);
                lightMedBtn.setChecked(false);
                lightOffBtn.setChecked(false);
            } else if (lmax) {
                MainActivity_2.sendText("Command:000002117,1,0");     // all lights off
                allBoolFalse();
                allButtonsOff();
                lightOffBtn.setChecked(true);
            }
        }
    };

    public void allButtonsOff() {
        lightMinBtn.setChecked(false);
        lightMedBtn.setChecked(false);
        lightMaxBtn.setChecked(false);
        lightOffBtn.setChecked(false);
    }

    public void allBoolFalse() {
        lmin = false;
        lmed = false;
        lmax = false;
    }

    public void goToHome() {
        Intent intent = new Intent(this, MainActivity_2.class);
        startActivity(intent);
    }


    /* next 2nd row */

    // all lights off
    protected View.OnClickListener light_all__off_Listener1 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (lmin1 || lmed1 || lmax1) {
                MainActivity_2.sendText("Command:000002117,1,0");
                lmin1 = false;
                lmed1 = false;
                lmax1 = false;

                lightMinBtn1.setChecked(false);
                lightMedBtn1.setChecked(false);
                lightMaxBtn1.setChecked(false);
            }
        }
    };

    // all lights on minimum
    protected View.OnClickListener light_all_min1 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!lmin1) {
                MainActivity_2.sendText("Command:000002117,1,1");     // all lights minimum
                lmin1 = true;
                lmed1 = false;
                lmax1 = false;

                lightMedBtn1.setChecked(false);
                lightMaxBtn1.setChecked(false);
                lightOffBtn1.setChecked(false);
            } else if (lmin1) {
                MainActivity_2.sendText("Command:000002117,1,0");     // all lights off
                allBoolFalse1();
                allButtonsOff1();
                lightOffBtn1.setChecked(true);

            }
        }
    };

    // all lights on medium
    protected View.OnClickListener light_all_med1 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!lmed1) {
                MainActivity_2.sendText("Command:000002117,1,2");     // all lights medium
                lmin1 = false;
                lmed1 = true;
                lmax1 = false;

                lightMinBtn1.setChecked(false);
                lightMaxBtn1.setChecked(false);
                lightOffBtn1.setChecked(false);
            } else if (lmed1) {
                MainActivity_2.sendText("Command:000002117,1,0"); // all lights off
                allBoolFalse1();
                allButtonsOff1();
                lightOffBtn1.setChecked(true);
            }
        }
    };

    // all lights on max
    protected View.OnClickListener light_all_Listener1 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!lmax1) {        //this method should only be called if there is a connection.
                MainActivity_2.sendText("Command:000002117,1,3"); // all lights to max
                lmin1 = false;
                lmed1 = false;
                lmax1 = true;

                lightMinBtn1.setChecked(false);
                lightMedBtn1.setChecked(false);
                lightOffBtn1.setChecked(false);
            } else if (lmax1) {
                MainActivity_2.sendText("Command:000002117,1,0");     // all lights off
                allBoolFalse1();
                allButtonsOff1();
                lightOffBtn1.setChecked(true);
            }
        }
    };

    public void allButtonsOff1() {
        lightMinBtn1.setChecked(false);
        lightMedBtn1.setChecked(false);
        lightMaxBtn1.setChecked(false);
        lightOffBtn1.setChecked(false);
    }

    public void allBoolFalse1() {
        lmin1 = false;
        lmed1 = false;
        lmax1 = false;
    }



          /* next 6th row CONTROL ALL ABOVE LIGHTS */

    // all lights off
    protected View.OnClickListener light_all__off_Listener6 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            MainActivity_2.sendText("Command:000002117,1,0");
            lmin6 = false;
            lmed6 = false;
            lmax6 = false;
            loff6 = true;

            lightMinBtn6.setChecked(false);
            lightMedBtn6.setChecked(false);
            lightMaxBtn6.setChecked(false);
            allButtonOffKill();
            offSwitchoff();

            if (loff6) {
                lightOffBtn6.setChecked(true);
            }

        }
    };

    // all lights on minimum
    protected View.OnClickListener light_all_min6 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!lmin6) {
                MainActivity_2.sendText("Command:000002117,1,1");     // all lights minimum
                lmin6 = true;
                lmed6 = false;
                lmax6 = false;

                lightMedBtn6.setChecked(false);
                lightMaxBtn6.setChecked(false);
                lightOffBtn6.setChecked(false);

                allButtonOffKill();

                lightMinBtn.setChecked(true);
                lightMinBtn1.setChecked(true);

                lmin = true;
                lmin1 = true;


            } else if (lmin6) {
                MainActivity_2.sendText("Command:000002117,1,0");     // all lights off
                allBoolFalse6();
                allButtonsOff6();
                allButtonOffKill();
                offSwitchoff();
            }
        }
    };

    // all lights on medium
    protected View.OnClickListener light_all_med6 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!lmed6) {
                MainActivity_2.sendText("Command:000002117,1,2");     // all lights medium
                lmin6 = false;
                lmed6 = true;
                lmax6 = false;

                lightMinBtn6.setChecked(false);
                lightMaxBtn6.setChecked(false);
                lightOffBtn6.setChecked(false);

                allButtonOffKill();
                lightMedBtn.setChecked(true);
                lightMedBtn1.setChecked(true);

                lmed = true;
                lmed1 = true;

            } else if (lmed6) {
                MainActivity_2.sendText("Command:000002117,1,0"); // all lights off
                allBoolFalse6();
                allButtonsOff6();
                allButtonOffKill();
                offSwitchoff();
            }
        }
    };

    // all lights on max
    protected View.OnClickListener light_all_Listener6 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!lmax6) {        //this method should only be called if there is a connection.
                MainActivity_2.sendText("Command:000002117,1,3"); // all lights to max
                lmin6 = false;
                lmed6 = false;
                lmax6 = true;

                lightMinBtn6.setChecked(false);
                lightMedBtn6.setChecked(false);
                lightOffBtn6.setChecked(false);

                allButtonOffKill();
                lightMaxBtn.setChecked(true);
                lightMaxBtn1.setChecked(true);

                lmax = true;
                lmax1 = true;

            } else if (lmax6) {
                MainActivity_2.sendText("Command:000002117,1,0");     // all lights off
                allBoolFalse6();

                allButtonsOff6();
                allButtonOffKill();
                offSwitchoff();
            }
        }
    };

    public void allButtonsOff6() {
        lightMinBtn6.setChecked(false);
        lightMedBtn6.setChecked(false);
        lightMaxBtn6.setChecked(false);
        lightOffBtn6.setChecked(false);
    }

    public void allBoolFalse6() {
        lmin6 = false;
        lmed6 = false;
        lmax6 = false;
    }

    public void allButtonOffKill() {
        allButtonsOff();
        allButtonsOff1();

        allBoolFalse();
        allBoolFalse1();
    }

    public void offSwitchoff() {
        lightOffBtn.setChecked(true);
        lightOffBtn1.setChecked(true);


    }

    public void setupGUI() {
        lightOffBtn = (ToggleButton) findViewById(R.id.toggle_0);
        lightOffBtn.setOnClickListener(light_all__off_Listener);

        lightMinBtn = (ToggleButton) findViewById(R.id.toggle_30);
        lightMinBtn.setOnClickListener(light_all_min);

        lightMedBtn = (ToggleButton) findViewById(R.id.toggle_70);
        lightMedBtn.setOnClickListener(light_all_med);

        lightMaxBtn = (ToggleButton) findViewById(R.id.toggle_100);
        lightMaxBtn.setOnClickListener(light_all_Listener);

        lightOffBtn1 = (ToggleButton) findViewById(R.id.toggle_01);
        lightOffBtn1.setOnClickListener(light_all__off_Listener1);

        lightMinBtn1 = (ToggleButton) findViewById(R.id.toggle_301);
        lightMinBtn1.setOnClickListener(light_all_min1);

        lightMedBtn1 = (ToggleButton) findViewById(R.id.toggle_701);
        lightMedBtn1.setOnClickListener(light_all_med1);

        lightMaxBtn1 = (ToggleButton) findViewById(R.id.toggle_1001);
        lightMaxBtn1.setOnClickListener(light_all_Listener1);




        lightOffBtn6 = (ToggleButton) findViewById(R.id.toggle_06);
        lightOffBtn6.setOnClickListener(light_all__off_Listener6);

        lightMinBtn6 = (ToggleButton) findViewById(R.id.toggle_306);
        lightMinBtn6.setOnClickListener(light_all_min6);

        lightMedBtn6 = (ToggleButton) findViewById(R.id.toggle_706);
        lightMedBtn6.setOnClickListener(light_all_med6);

        lightMaxBtn6 = (ToggleButton) findViewById(R.id.toggle_1006);
        lightMaxBtn6.setOnClickListener(light_all_Listener6);

        //home button
        homeBtn = (ImageButton) findViewById(R.id.home_button);
        homeBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goToHome();
            }
        });
    }
}