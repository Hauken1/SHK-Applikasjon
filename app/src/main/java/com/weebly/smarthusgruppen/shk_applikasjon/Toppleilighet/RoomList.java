package com.weebly.smarthusgruppen.shk_applikasjon.Toppleilighet;
// THIS ROOMLIST FILE IS FOR LIGHTS ONLY !!!!

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.widget.ImageButton;

import com.weebly.smarthusgruppen.shk_applikasjon.R;

public class RoomList extends AppCompatActivity {

    Button bathroomBtn;
    Button bed1Btn;
    Button bed2Btn;
    Button bed3Btn;
    Button hallwayBtn;
    Button kitchenBtn;
    Button officeBtn;
    Button livingRoomBtn;
    TextView mode_View;
    ImageButton homeBtn;

    public static final String DAY = "Dag";
    public static final String NIGHT = "Natt";
    public static final String AWAY = "Borte";
    public static final String HOLIDAY = "Ferie";

    public static final int iDAY = 2;
    public static final int iNIGHT = 3;
    public static final int iAWAY = 4;
    public static final int iHOLIDAY = 1;

    public static final String savedTemp = "1SavedTemperature_1";
    public static final String savedColor = "SavedBackgroundColor_1";

    SharedPreferences sharedpreferences;
    public SharedPreferences tempSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_list);
        setupGUI();

        }

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


        // Living room button
        livingRoomBtn = (Button) findViewById(R.id.room3_button);
        livingRoomBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)  {
                goToLivingRoomLights();
            }
        });

        // bedroom 1 button
        bed1Btn = (Button) findViewById(R.id.room4_button);
        bed1Btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)  {
                goToBed1Lights();
            }
        });

        // bedroom 2 button
        bed2Btn = (Button) findViewById(R.id.room5_button);
        bed2Btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)  {
                goToBed2Lights();
            }
        });


        // office button
        officeBtn = (Button) findViewById(R.id.room7_button);
        officeBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)  {
                goToOfficeLights();
            }
        });

        // hallway button
        hallwayBtn = (Button) findViewById(R.id.room8_button);
        hallwayBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)  {
                goToHallwayLights();
            }
        });

        //home button
        homeBtn = (ImageButton) findViewById(R.id.home_button);
        homeBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)  {
                goToHome();
            }
        });

        sharedpreferences = getSharedPreferences(savedColor, Context.MODE_PRIVATE);

        int value1 = sharedpreferences.getInt("value1", 0);
        int value2 = sharedpreferences.getInt("value2", 0);
        int value3 = sharedpreferences.getInt("value3", 0);
        int value4 = sharedpreferences.getInt("set", 0);
        if(value4 != 0){
            View v = findViewById(R.id.roomlist_id);
            v.setBackgroundColor(Color.rgb(value1, value3, value2));
            setContentView(v);
        }
    }

    public void goToKitchenLights() {
        Intent intent = new Intent(this, LightingKitchen.class);
        startActivity(intent);
    }
    public void goToBathroomLights() {
        Intent intent = new Intent(this, LightingBathroom.class);
        startActivity(intent);
    }
    public void goToLivingRoomLights() {
        Intent intent = new Intent(this, LightingLivingRoom.class);
        startActivity(intent);
    }
    public void goToBed1Lights() {
        Intent intent = new Intent(this, LightingBedroom1.class);
        startActivity(intent);
    }
    public void goToBed2Lights() {
        Intent intent = new Intent(this, LightingBedroom2.class);
        startActivity(intent);
    }


    public void goToOfficeLights() {
        Intent intent = new Intent(this, LightingOffice.class);
        startActivity(intent);
    }
    public void goToHallwayLights() {
        Intent intent = new Intent(this, LightingHallway.class);
        startActivity(intent);
    }
    public void goToHome() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
