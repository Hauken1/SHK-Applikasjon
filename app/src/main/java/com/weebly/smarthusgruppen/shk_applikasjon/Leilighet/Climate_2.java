package com.weebly.smarthusgruppen.shk_applikasjon.Leilighet;

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

import com.weebly.smarthusgruppen.shk_applikasjon.R;

/**
 * Interface to send the user to climate view to enter temperature and ventilation pages
 */
public class Climate_2 extends AppCompatActivity {
    ImageButton tempBtn;
    ImageButton ventBtn;
    ImageButton homeBtn;

    public static final String DAY = "Dag";
    public static final String NIGHT = "Natt";
    public static final String AWAY = "Borte";
    public static final String HOLIDAY = "Ferie";

    public static final int iDAY = 2;
    public static final int iNIGHT = 3;
    public static final int iAWAY = 4;
    public static final int iHOLIDAY = 1;

    public static final String savedTemp = "1SavedTemperature_2";
    public static final String savedColor = "SavedBackgroundColor_2";

    SharedPreferences sharedpreferences;
    public SharedPreferences tempSetting;

    TextView mode_View;

    /**
     * loads GUI settings on startup
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_climate_2);
        setupGUI();


    }
    /**
     * Setting up GUI. sets up all onClickListener buttons. Sets background depending on user
     * settings. SharedPreferences for each mode.
     */
    public void setupGUI() {

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
            View v = findViewById(R.id.climate_id2);
            v.setBackgroundColor(Color.rgb(value1, value3, value2));
            setContentView(v);
        }

        // temp button
        tempBtn = (ImageButton) findViewById(R.id.heating_view_button);
        tempBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goToTemperatureView();
            }
        });

        // vent button
        ventBtn = (ImageButton) findViewById(R.id.ventilation_view_button);
        ventBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goToVentilationView();
            }
        });

        //home button
        homeBtn = (ImageButton) findViewById(R.id.home_button);
        homeBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)  {
                goToHome();
            }
        });

    }

    /**
     * sends the user to the temperature view menu
     */
    public void goToTemperatureView() {
        Intent intent = new Intent(this, Temperature_2.class);
        startActivity(intent);
    }
    /**
     * sends the user to the ventilation view menu
     */
    public void goToVentilationView() {
        Intent intent = new Intent(this, Ventilation_2.class);
        startActivity(intent);
    }
    /**
     * sends the user to the mainActivity view menu
     */
    public void goToHome() {
        Intent intent = new Intent(this, MainActivity_2.class);
        startActivity(intent);
    }



}
