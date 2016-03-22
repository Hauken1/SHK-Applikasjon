package com.weebly.smarthusgruppen.shk_applikasjon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LightingKitchen extends AppCompatActivity {

    boolean connected;

    Button light_all_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lighting_kitchen);

        light_all_button = (Button) findViewById(R.id.connectButton);
        light_all_button.setOnClickListener(light_all_Listener);
    }

    protected View.OnClickListener light_all_Listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            connected = true;
            if (connected) {        //this method should only be called if there is a connection.
                MainActivity.sendText("Command:000002117,1,3");
            }
        }
    };
}
