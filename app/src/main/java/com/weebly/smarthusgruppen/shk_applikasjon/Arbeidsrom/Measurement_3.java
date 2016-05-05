package com.weebly.smarthusgruppen.shk_applikasjon.Arbeidsrom;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.weebly.smarthusgruppen.shk_applikasjon.Leilighet.MainActivity_2;
import com.weebly.smarthusgruppen.shk_applikasjon.R;
import com.weebly.smarthusgruppen.shk_applikasjon.Toppleilighet.MainActivity;

public class Measurement_3 extends AppCompatActivity {
    ImageButton homeBtn;
    public static final String savedColor = "SavedBackgroundColor_3";

    SharedPreferences sharedpreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_measurement_3);

        setupGUI();

    }

    public void setupGUI(){
        //home button
        homeBtn = (ImageButton) findViewById(R.id.home_button);
        homeBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goToHome();
            }
        });

        sharedpreferences = getSharedPreferences(savedColor, Context.MODE_PRIVATE);

        int value1 = sharedpreferences.getInt("value1", 0);
        int value2 = sharedpreferences.getInt("value2", 0);
        int value3 = sharedpreferences.getInt("value3", 0);
        int value4 = sharedpreferences.getInt("set", 0);
        if(value4 != 0){
            View v = findViewById(R.id.measure_id_3);
            v.setBackgroundColor(Color.rgb(value1, value3, value2));
            setContentView(v);
        }
    }

    public void goToHome() {
        Intent intent = new Intent(this, MainActivity_3.class);
        startActivity(intent);
    }
}
