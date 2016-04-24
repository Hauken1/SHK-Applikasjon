package com.weebly.smarthusgruppen.shk_applikasjon.Leilighet;
// THIS ROOMLIST FILE IS FOR LIGHTS ONLY !!!!

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.weebly.smarthusgruppen.shk_applikasjon.R;

public class RoomList_2 extends AppCompatActivity {

    Button bathroomBtn;
    Button bed1Btn;
    Button bed2Btn;
    Button hallwayBtn;
    Button kitchenBtn;
    Button officeBtn;
    ImageButton homeBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_list_2);


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



        }


    public void goToKitchenLights() {
        Intent intent = new Intent(this, LightingKitchen_2.class);
        startActivity(intent);
    }
    public void goToBathroomLights() {
        Intent intent = new Intent(this, LightingBathroom_2.class);
        startActivity(intent);
    }
    public void goToBed1Lights() {
        Intent intent = new Intent(this, LightingBedroom1_2.class);
        startActivity(intent);
    }
    public void goToBed2Lights() {
        Intent intent = new Intent(this, LightingBedroom2_2.class);
        startActivity(intent);
    }


    public void goToOfficeLights() {
        Intent intent = new Intent(this, LightingOffice_2.class);
        startActivity(intent);
    }
    public void goToHallwayLights() {
        Intent intent = new Intent(this, LightingHallway_2.class);
        startActivity(intent);
    }
    public void goToHome() {
        Intent intent = new Intent(this, MainActivity_2.class);
        startActivity(intent);
    }
}
