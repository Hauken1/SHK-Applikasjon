package com.weebly.smarthusgruppen.shk_applikasjon;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class RoomListTemp extends AppCompatActivity {

    Button bathroomBtn;
    Button bed1Btn;
    Button bed2Btn;
    Button bed3Btn;
    Button hallwayBtn;
    Button kitchenBtn;
    Button officeBtn;
    Button livingRoomBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_list);

        // kitchen button
        kitchenBtn = (Button) findViewById(R.id.room1_button);
        kitchenBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goToKitchenTemp();
            }
        });
        // bathroom button
        bathroomBtn = (Button) findViewById(R.id.room2_button);
        bathroomBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goToBathroomTemp();
            }
        });


        // Living room button
        livingRoomBtn = (Button) findViewById(R.id.room3_button);
        livingRoomBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)  {
                goToLivingRoomTemp();
            }
        });

        // bedroom 1 button
        bed1Btn = (Button) findViewById(R.id.room4_button);
        bed1Btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)  {
                goToBed1Temp();
            }
        });

        // bedroom 2 button
        bed2Btn = (Button) findViewById(R.id.room5_button);
        bed2Btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)  {
                goToBed2Temp();
            }
        });

        // bedroom 3 button
        bed3Btn = (Button) findViewById(R.id.room6_button);
        bed3Btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)  {
                goToBed3Temp();
            }
        });

        // office button
        officeBtn = (Button) findViewById(R.id.room7_button);
        officeBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)  {
                goToOfficeTemp();
            }
        });

        // hallway button
        hallwayBtn = (Button) findViewById(R.id.room8_button);
        hallwayBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)  {
                goToHallwayTemp();
            }
        });



    }
    public void goToKitchenTemp() {
        Intent intent = new Intent(this, TempKitchen.class);
        startActivity(intent);
    }
    public void goToBathroomTemp() {
        Intent intent = new Intent(this, TempBathroom.class);
        startActivity(intent);
    }
    public void goToLivingRoomTemp() {
        Intent intent = new Intent(this, TempLivingRoom.class);
        startActivity(intent);
    }
    public void goToBed1Temp() {
        Intent intent = new Intent(this, TempBedroom1.class);
        startActivity(intent);
    }
    public void goToBed2Temp() {
        Intent intent = new Intent(this, TempBedroom2.class);
        startActivity(intent);
    }
    public void goToBed3Temp() {
        Intent intent = new Intent(this, TempBedroom3.class);
        startActivity(intent);
    }
    public void goToOfficeTemp() {
        Intent intent = new Intent(this, TempOffice.class);
        startActivity(intent);
    }
    public void goToHallwayTemp() {
        Intent intent = new Intent(this, TempHallway.class);
        startActivity(intent);
    }

}
