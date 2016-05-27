package com.fredfama.weparty;

import android.content.Intent;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class MainActivity extends WePartyActivity {

    Button button_createParty;
    Button button_joinParty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_createParty = (Button) findViewById(R.id.button_CreateParty);
        button_joinParty = (Button) findViewById(R.id.button_JoinParty);


        button_createParty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startCreatePartyActivity();
            }
        });

        button_joinParty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startJointPartyActivity();
            }
        });

    }

    private void startCreatePartyActivity() {
        Intent intent = new Intent(this, CreatePartyActivity.class);
        startActivity(intent);
    }

    private void startJointPartyActivity() {
        Intent intent = new Intent(this, JoinPartyActivity.class);
        startActivity(intent);
    }


}