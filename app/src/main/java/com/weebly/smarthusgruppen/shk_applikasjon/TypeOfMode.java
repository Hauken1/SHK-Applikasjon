package com.weebly.smarthusgruppen.shk_applikasjon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ToggleButton;

public class TypeOfMode extends AppCompatActivity {
    ImageButton homeBtn;
    ToggleButton dayBtn;
    ToggleButton nightBtn;
    ToggleButton awayBtn;
    ToggleButton holidayBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_of_mode);

        //home button
        homeBtn = (ImageButton) findViewById(R.id.home_button);
        homeBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goToHome();
            }
        });

        dayBtn = (ToggleButton) findViewById(R.id.toggle_day);
        dayBtn.setOnClickListener(light_all__off_Listener);

        nightBtn = (ToggleButton) findViewById(R.id.toggle_night);
        nightBtn.setOnClickListener(light_all__off_Listener);

        awayBtn = (ToggleButton) findViewById(R.id.toggle_away);
        awayBtn.setOnClickListener(light_all__off_Listener);

        holidayBtn = (ToggleButton) findViewById(R.id.toggle_holiday);
        holidayBtn.setOnClickListener(light_all__off_Listener);
    }
    public void goToHome() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
