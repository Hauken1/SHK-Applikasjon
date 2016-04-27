package com.weebly.smarthusgruppen.shk_applikasjon.Arbeidsrom;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.weebly.smarthusgruppen.shk_applikasjon.R;

public class LightingFlat extends AppCompatActivity {

    public static final String savedLight = "SavedLightingFlat_1";
    public static final String savedTemp = "1SavedTemperature_3";
    public static final String savedColor = "SavedBackgroundColor_3";

    SharedPreferences sharedpreferences;
    public SharedPreferences tempSetting;
    public SharedPreferences lightSettings;

    String sMode;
    int iMode;

    public static final int row1 = 1;
    public static final int row2 = 2;
    public static final int row3 = 3;
    public static final int row4 = 4;
    public static final int row5 = 5;
    public static final int row6 = 6;
    public static final int rowall = 7;

    public static final int lightOff = 0;
    public static final int lightMin = 1;
    public static final int lightMed = 2;
    public static final int lightMax = 3;

    public static final String DAY = "Dag";
    public static final String NIGHT = "Natt";
    public static final String AWAY = "Borte";
    public static final String HOLIDAY = "Ferie";

    public static final int iDAY = 2;
    public static final int iNIGHT = 3;
    public static final int iAWAY = 4;
    public static final int iHOLIDAY = 1;

    TextView mode_View;

    boolean lmin = false;
    boolean lmed = false;
    boolean lmax = false;

    boolean lmin1 = false;
    boolean lmed1 = false;
    boolean lmax1 = false;

    boolean lmin2 = false;
    boolean lmed2 = false;
    boolean lmax2 = false;

    boolean lmin3 = false;
    boolean lmed3 = false;
    boolean lmax3 = false;

    boolean lmin4 = false;
    boolean lmed4 = false;
    boolean lmax4 = false;

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

    ToggleButton lightOffBtn2;
    ToggleButton lightMinBtn2;
    ToggleButton lightMedBtn2;
    ToggleButton lightMaxBtn2;

    ToggleButton lightOffBtn3;
    ToggleButton lightMinBtn3;
    ToggleButton lightMedBtn3;
    ToggleButton lightMaxBtn3;

    ToggleButton lightOffBtn4;
    ToggleButton lightMinBtn4;
    ToggleButton lightMedBtn4;
    ToggleButton lightMaxBtn4;

    ToggleButton lightOffBtn6;
    ToggleButton lightMinBtn6;
    ToggleButton lightMaxBtn6;
    ToggleButton lightMedBtn6;
    ImageButton homeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lighting_kitchen);

        setupGUI();
    }

    // all lights off
    protected View.OnClickListener light_all__off_Listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (lmin || lmed || lmax) {
                MainActivity_3.sendText("Command:000002117,1,0");
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
                MainActivity_3.sendText("Command:000002117,1,1");     // all lights minimum
                lmin = true;
                lmed = false;
                lmax = false;

                lightMedBtn.setChecked(false);
                lightMaxBtn.setChecked(false);
                lightOffBtn.setChecked(false);
            }

            else if (lmin) {
                MainActivity_3.sendText("Command:000002117,1,0");     // all lights off
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
                MainActivity_3.sendText("Command:000002117,1,2");     // all lights medium
                lmin = false;
                lmed = true;
                lmax = false;

                lightMinBtn.setChecked(false);
                lightMaxBtn.setChecked(false);
                lightOffBtn.setChecked(false);
            }

            else if (lmed) {
                MainActivity_3.sendText("Command:000002117,1,0"); // all lights off
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
                MainActivity_3.sendText("Command:000002117,1,3"); // all lights to max
                lmin = false;
                lmed = false;
                lmax = true;

                lightMinBtn.setChecked(false);
                lightMedBtn.setChecked(false);
                lightOffBtn.setChecked(false);
            }

            else if (lmax) {
                MainActivity_3.sendText("Command:000002117,1,0");     // all lights off
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

    public void allBoolFalse()  {
        lmin = false;
        lmed = false;
        lmax = false;
    }
    public void goToHome() {
        Intent intent = new Intent(this, MainActivity_3.class);
        startActivity(intent);
    }


    /* next 2nd row */

    // all lights off
    protected View.OnClickListener light_all__off_Listener1 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (lmin1 || lmed1 || lmax1) {
                MainActivity_3.sendText("Command:000002117,1,0");
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
                MainActivity_3.sendText("Command:000002117,1,1");     // all lights minimum
                lmin1 = true;
                lmed1 = false;
                lmax1 = false;

                lightMedBtn1.setChecked(false);
                lightMaxBtn1.setChecked(false);
                lightOffBtn1.setChecked(false);
            } else if (lmin1) {
                MainActivity_3.sendText("Command:000002117,1,0");     // all lights off
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
                MainActivity_3.sendText("Command:000002117,1,2");     // all lights medium
                lmin1 = false;
                lmed1 = true;
                lmax1 = false;

                lightMinBtn1.setChecked(false);
                lightMaxBtn1.setChecked(false);
                lightOffBtn1.setChecked(false);
            }
            else if (lmed1) {
                MainActivity_3.sendText("Command:000002117,1,0"); // all lights off
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
                MainActivity_3.sendText("Command:000002117,1,3"); // all lights to max
                lmin1 = false;
                lmed1 = false;
                lmax1 = true;

                lightMinBtn1.setChecked(false);
                lightMedBtn1.setChecked(false);
                lightOffBtn1.setChecked(false);
            }
            else if (lmax1) {
                MainActivity_3.sendText("Command:000002117,1,0");     // all lights off
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

    public void allBoolFalse1()  {
        lmin1 = false;
        lmed1 = false;
        lmax1 = false;
    }

    /* next 3rd row */

    // all lights off
    protected View.OnClickListener light_all__off_Listener2 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (lmin2 || lmed2 || lmax2) {
                MainActivity_3.sendText("Command:000002117,1,0");
                lmin2 = false;
                lmed2 = false;
                lmax2 = false;


                lightMinBtn2.setChecked(false);
                lightMedBtn2.setChecked(false);
                lightMaxBtn2.setChecked(false);


            }
        }
    };

    // all lights on minimum
    protected View.OnClickListener light_all_min2 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!lmin2) {
                MainActivity_3.sendText("Command:000002117,1,1");     // all lights minimum
                lmin2 = true;
                lmed2 = false;
                lmax2 = false;

                lightMedBtn2.setChecked(false);
                lightMaxBtn2.setChecked(false);
                lightOffBtn2.setChecked(false);
            } else if (lmin2) {
                MainActivity_3.sendText("Command:000002117,1,0");     // all lights off
                allBoolFalse2();
                allButtonsOff2();
                lightOffBtn2.setChecked(true);

            }
        }
    };

    // all lights on medium
    protected View.OnClickListener light_all_med2 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!lmed1) {
                MainActivity_3.sendText("Command:000002117,1,2");     // all lights medium
                lmin2 = false;
                lmed2 = true;
                lmax2 = false;

                lightMinBtn2.setChecked(false);
                lightMaxBtn2.setChecked(false);
                lightOffBtn2.setChecked(false);
            }
            else if (lmed2) {
                MainActivity_3.sendText("Command:000002117,1,0"); // all lights off
                allBoolFalse2();
                allButtonsOff2();
                lightOffBtn2.setChecked(true);
            }
        }
    };

    // all lights on max
    protected View.OnClickListener light_all_Listener2 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!lmax2) {        //this method should only be called if there is a connection.
                MainActivity_3.sendText("Command:000002117,1,3"); // all lights to max
                lmin2 = false;
                lmed2 = false;
                lmax2 = true;

                lightMinBtn2.setChecked(false);
                lightMedBtn2.setChecked(false);
                lightOffBtn2.setChecked(false);
            }
            else if (lmax2) {
                MainActivity_3.sendText("Command:000002117,1,0");     // all lights off
                allBoolFalse2();
                allButtonsOff2();
                lightOffBtn2.setChecked(true);
            }
        }
    };

    public void allButtonsOff2() {
        lightMinBtn2.setChecked(false);
        lightMedBtn2.setChecked(false);
        lightMaxBtn2.setChecked(false);
        lightOffBtn2.setChecked(false);
    }

    public void allBoolFalse2()  {
        lmin2 = false;
        lmed2 = false;
        lmax2 = false;
    }

     /* next 4th row */

    // all lights off
    protected View.OnClickListener light_all__off_Listener3 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (lmin3 || lmed3 || lmax3) {
                MainActivity_3.sendText("Command:000002117,1,0");
                lmin3 = false;
                lmed3 = false;
                lmax3 = false;

                lightMinBtn3.setChecked(false);
                lightMedBtn3.setChecked(false);
                lightMaxBtn3.setChecked(false);

            }
        }
    };

    // all lights on minimum
    protected View.OnClickListener light_all_min3 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!lmin3) {
                MainActivity_3.sendText("Command:000002117,1,1");     // all lights minimum
                lmin3 = true;
                lmed3 = false;
                lmax3 = false;

                lightMedBtn3.setChecked(false);
                lightMaxBtn3.setChecked(false);
                lightOffBtn3.setChecked(false);
            } else if (lmin3) {
                MainActivity_3.sendText("Command:000002117,1,0");     // all lights off
                allBoolFalse3();
                allButtonsOff3();
                lightOffBtn3.setChecked(true);

            }
        }
    };

    // all lights on medium
    protected View.OnClickListener light_all_med3 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!lmed3) {
                MainActivity_3.sendText("Command:000002117,1,2");     // all lights medium
                lmin3 = false;
                lmed3 = true;
                lmax3 = false;

                lightMinBtn3.setChecked(false);
                lightMaxBtn3.setChecked(false);
                lightOffBtn3.setChecked(false);
            }
            else if (lmed3) {
                MainActivity_3.sendText("Command:000002117,1,0"); // all lights off
                allBoolFalse3();
                allButtonsOff3();
                lightOffBtn3.setChecked(true);
            }
        }
    };

    // all lights on max
    protected View.OnClickListener light_all_Listener3 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!lmax3) {        //this method should only be called if there is a connection.
                MainActivity_3.sendText("Command:000002117,1,3"); // all lights to max
                lmin3 = false;
                lmed3 = false;
                lmax3 = true;

                lightMinBtn3.setChecked(false);
                lightMedBtn3.setChecked(false);
                lightOffBtn3.setChecked(false);
            }
            else if (lmax3) {
                MainActivity_3.sendText("Command:000002117,1,0");     // all lights off
                allBoolFalse3();
                allButtonsOff3();
                lightOffBtn3.setChecked(true);
            }
        }
    };

    public void allButtonsOff3() {
        lightMinBtn3.setChecked(false);
        lightMedBtn3.setChecked(false);
        lightMaxBtn3.setChecked(false);
        lightOffBtn3.setChecked(false);
    }

    public void allBoolFalse3()  {
        lmin3 = false;
        lmed3 = false;
        lmax3 = false;
    }

      /* next 5th row */

    // all lights off
    protected View.OnClickListener light_all__off_Listener4 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (lmin4 || lmed4 || lmax4) {
                MainActivity_3.sendText("Command:000002117,1,0");
                lmin4 = false;
                lmed4 = false;
                lmax4 = false;

                lightMinBtn4.setChecked(false);
                lightMedBtn4.setChecked(false);
                lightMaxBtn4.setChecked(false);

            }
        }
    };

    // all lights on minimum
    protected View.OnClickListener light_all_min4 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!lmin4) {
                MainActivity_3.sendText("Command:000002117,1,1");     // all lights minimum
                lmin4 = true;
                lmed4 = false;
                lmax4 = false;

                lightMedBtn4.setChecked(false);
                lightMaxBtn4.setChecked(false);
                lightOffBtn4.setChecked(false);
            } else if (lmin4) {
                MainActivity_3.sendText("Command:000002117,1,0");     // all lights off
                allBoolFalse4();
                allButtonsOff4();
                lightOffBtn4.setChecked(true);

            }
        }
    };

    // all lights on medium
    protected View.OnClickListener light_all_med4 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!lmed4) {
                MainActivity_3.sendText("Command:000002117,1,2");     // all lights medium
                lmin4 = false;
                lmed4 = true;
                lmax4 = false;

                lightMinBtn4.setChecked(false);
                lightMaxBtn4.setChecked(false);
                lightOffBtn4.setChecked(false);
            }
            else if (lmed4) {
                MainActivity_3.sendText("Command:000002117,1,0"); // all lights off
                allBoolFalse4();
                allButtonsOff4();
                lightOffBtn4.setChecked(true);
            }
        }
    };

    // all lights on max
    protected View.OnClickListener light_all_Listener4 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!lmax4) {        //this method should only be called if there is a connection.
                MainActivity_3.sendText("Command:000002117,1,3"); // all lights to max
                lmin4 = false;
                lmed4 = false;
                lmax4 = true;

                lightMinBtn4.setChecked(false);
                lightMedBtn4.setChecked(false);
                lightOffBtn4.setChecked(false);
            }
            else if (lmax4) {
                MainActivity_3.sendText("Command:000002117,1,0");     // all lights off
                allBoolFalse4();
                allButtonsOff4();
                lightOffBtn4.setChecked(true);
            }
        }
    };

    public void allButtonsOff4() {
        lightMinBtn4.setChecked(false);
        lightMedBtn4.setChecked(false);
        lightMaxBtn4.setChecked(false);
        lightOffBtn4.setChecked(false);
    }

    public void allBoolFalse4()  {
        lmin4 = false;
        lmed4 = false;
        lmax4 = false;
    }


          /* next 6th row CONTROL ALL ABOVE LIGHTS */

    // all lights off
    protected View.OnClickListener light_all__off_Listener6 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

                MainActivity_3.sendText("Command:000002117,1,0");
                lmin6 = false;
                lmed6 = false;
                lmax6 = false;
                loff6 = true;

                lightMinBtn6.setChecked(false);
                lightMedBtn6.setChecked(false);
                lightMaxBtn6.setChecked(false);
                allButtonOffKill();
                offSwitchoff();


            if(loff6){
                lightOffBtn6.setChecked(true);
            }
        }
    };

    // all lights on minimum
    protected View.OnClickListener light_all_min6 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!lmin6) {
                MainActivity_3.sendText("Command:000002117,1,1");     // all lights minimum
                lmin6 = true;
                lmed6 = false;
                lmax6 = false;

                lightMedBtn6.setChecked(false);
                lightMaxBtn6.setChecked(false);
                lightOffBtn6.setChecked(false);

                allButtonOffKill();

                lightMinBtn.setChecked(true);
                lightMinBtn1.setChecked(true);
                lightMinBtn2.setChecked(true);
                lightMinBtn3.setChecked(true);
                lightMinBtn4.setChecked(true);

                lmin = true;
                lmin1 = true;
                lmin2 = true;
                lmin3 = true;
                lmin4 = true;



            } else if (lmin6) {
                MainActivity_3.sendText("Command:000002117,1,0");     // all lights off
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
                MainActivity_3.sendText("Command:000002117,1,2");     // all lights medium
                lmin6 = false;
                lmed6 = true;
                lmax6 = false;

                lightMinBtn6.setChecked(false);
                lightMaxBtn6.setChecked(false);
                lightOffBtn6.setChecked(false);

                allButtonOffKill();
                lightMedBtn.setChecked(true);
                lightMedBtn1.setChecked(true);
                lightMedBtn2.setChecked(true);
                lightMedBtn3.setChecked(true);
                lightMedBtn4.setChecked(true);

                lmed = true;
                lmed1 = true;
                lmed2 = true;
                lmed3 = true;
                lmed4 = true;

            }
            else if (lmed6) {
                MainActivity_3.sendText("Command:000002117,1,0"); // all lights off
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
                MainActivity_3.sendText("Command:000002117,1,3"); // all lights to max
                lmin6 = false;
                lmed6 = false;
                lmax6 = true;

                lightMinBtn6.setChecked(false);
                lightMedBtn6.setChecked(false);
                lightOffBtn6.setChecked(false);

                allButtonOffKill();
                lightMaxBtn.setChecked(true);
                lightMaxBtn1.setChecked(true);
                lightMaxBtn2.setChecked(true);
                lightMaxBtn3.setChecked(true);
                lightMaxBtn4.setChecked(true);

                lmax = true;
                lmax1 = true;
                lmax2 = true;
                lmax3 = true;
                lmax4 = true;

            }
            else if (lmax6) {
                MainActivity_3.sendText("Command:000002117,1,0");     // all lights off
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

    public void allBoolFalse6()  {
        lmin6 = false;
        lmed6 = false;
        lmax6 = false;
    }

    public void allButtonOffKill() {
        allButtonsOff();
        allButtonsOff1();
        allButtonsOff2();
        allButtonsOff3();
        allButtonsOff4();

        allBoolFalse();
        allBoolFalse1();
        allBoolFalse2();
        allBoolFalse3();
        allBoolFalse4();
    }

    public void offSwitchoff() {
        lightOffBtn.setChecked(true);
        lightOffBtn1.setChecked(true);
        lightOffBtn2.setChecked(true);
        lightOffBtn3.setChecked(true);
        lightOffBtn4.setChecked(true);
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


        lightOffBtn2 = (ToggleButton) findViewById(R.id.toggle_02);
        lightOffBtn2.setOnClickListener(light_all__off_Listener2);

        lightMinBtn2 = (ToggleButton) findViewById(R.id.toggle_302);
        lightMinBtn2.setOnClickListener(light_all_min2);

        lightMedBtn2 = (ToggleButton) findViewById(R.id.toggle_702);
        lightMedBtn2.setOnClickListener(light_all_med2);

        lightMaxBtn2 = (ToggleButton) findViewById(R.id.toggle_1002);
        lightMaxBtn2.setOnClickListener(light_all_Listener2);


        lightOffBtn3 = (ToggleButton) findViewById(R.id.toggle_03);
        lightOffBtn3.setOnClickListener(light_all__off_Listener3);

        lightMinBtn3 = (ToggleButton) findViewById(R.id.toggle_303);
        lightMinBtn3.setOnClickListener(light_all_min3);

        lightMedBtn3 = (ToggleButton) findViewById(R.id.toggle_703);
        lightMedBtn3.setOnClickListener(light_all_med3);

        lightMaxBtn3 = (ToggleButton) findViewById(R.id.toggle_1003);
        lightMaxBtn3.setOnClickListener(light_all_Listener3);


        lightOffBtn4 = (ToggleButton) findViewById(R.id.toggle_04);
        lightOffBtn4.setOnClickListener(light_all__off_Listener4);

        lightMinBtn4 = (ToggleButton) findViewById(R.id.toggle_304);
        lightMinBtn4.setOnClickListener(light_all_min4);

        lightMedBtn4 = (ToggleButton) findViewById(R.id.toggle_704);
        lightMedBtn4.setOnClickListener(light_all_med4);

        lightMaxBtn4 = (ToggleButton) findViewById(R.id.toggle_1004);
        lightMaxBtn4.setOnClickListener(light_all_Listener4);



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
            public void onClick(View v)  {
                goToHome();
            }
        });
        mode_View = (TextView) findViewById(R.id.mode_view);

        try {
            tempSetting = getSharedPreferences(savedTemp, 0);
            String mode = tempSetting.getString("mode", "2");
            int imode = Integer.parseInt(mode);
            switch (imode) {
                case iHOLIDAY:
                    mode = HOLIDAY;
                    break;
                case iDAY:
                    mode = DAY;
                    break;
                case iNIGHT:
                    mode = NIGHT;
                    break;
                case iAWAY:
                    mode = AWAY;
                    break;
                default:
                    mode = DAY;
                    break;
            }
            mode_View.setGravity(Gravity.CENTER);
            mode_View.setText("Modus: " + mode);
        }catch (Exception e){
            e.printStackTrace();
        }
        sharedpreferences = getSharedPreferences(savedColor, Context.MODE_PRIVATE);

        int value1 = sharedpreferences.getInt("value1", 0);
        int value2 = sharedpreferences.getInt("value2", 0);
        int value3 = sharedpreferences.getInt("value3", 0);
        int value4 = sharedpreferences.getInt("set", 0);
        if(value4 != 0){
            View v = findViewById(R.id.ScrollView01);
            v.setBackgroundColor(Color.rgb(value1, value3, value2));
            setContentView(v);
        }
    }
}
