package com.weebly.smarthusgruppen.shk_applikasjon.Arbeidsrom;
// THIS ROOMLIST FILE IS FOR LIGHTS ONLY !!!!

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.weebly.smarthusgruppen.shk_applikasjon.R;
/**
 * Roomlist covers all the rooms in the apartment. Lets the user choose which room to change
 * the lights in
 */
public class RoomList_3 extends AppCompatActivity {

    Button bathroomBtn;
    Button kitchenBtn;
    ImageButton homeBtn;

    TextView mode_View;
    public static final String DAY = "Dag";
    public static final String NIGHT = "Natt";
    public static final String AWAY = "Borte";
    public static final String HOLIDAY = "Ferie";

    public static final int iDAY = 2;
    public static final int iNIGHT = 3;
    public static final int iAWAY = 4;
    public static final int iHOLIDAY = 1;

    public static final String savedTemp = "1SavedTemperature_3";
    public static final String savedColor = "SavedBackgroundColor_3";

    SharedPreferences sharedpreferences;
    public SharedPreferences tempSetting;

    @Override
    /**
     * loads GUI settings on startup
     * @param savedInstanceState
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_list_3);
        setupGUI();
        }
    /**
     * Sets up onClickListeners for all buttons. loads settings from sharedpreferences based on
     * which mode the house is in. Sets background color depending on user.
     */
    public void setupGUI(){
        // kitchen button
        kitchenBtn = (Button) findViewById(R.id.room1_button);
        kitchenBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goToKitchenLights();
            }
        });

        // bathroom button
        bathroomBtn = (Button) findViewById(R.id.room2_button);
        bathroomBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goToBathroomLights();
            }
        });

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
            View v = findViewById(R.id.roomlist_id_3);
            v.setBackgroundColor(Color.rgb(value1, value3, value2));
            setContentView(v);
        }
    }

    /**
     * sends the user to the temperature view menu
     */
    public void goToKitchenLights() {
        Intent intent = new Intent(this, LightingFlat.class);
        startActivity(intent);
    }
    /**
     * sends the user to the ventilation view menu
     */
    public void goToBathroomLights() {
        Intent intent = new Intent(this, LightingBathroom_3.class);
        startActivity(intent);
    }
    /**
     * sends the user to the mainActivity view menu
     */
    public void goToHome() {
        Intent intent = new Intent(this, MainActivity_3.class);
        startActivity(intent);
    }
}