package com.fredfama.weparty;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by root on 12/05/16.
 */
public class WePartyActivity extends AppCompatActivity {

    protected int WeParty_partyId = 0;
    protected String WeParty_partyName = "";
    protected int WeParty_userId = 0;
    protected String WeParty_userName = "";
    protected String WeParty_userEmail = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // NECESSÁRIO PARA COMUNICAÇÃO WEBSERVICE
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }


    }


}
