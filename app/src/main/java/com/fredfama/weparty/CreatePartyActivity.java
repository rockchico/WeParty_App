package com.fredfama.weparty;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

public class CreatePartyActivity extends WePartyActivity {

    Button button_create;
    EditText editText_PartyName;
    EditText editText_PartyLocation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_party);

        button_create = (Button) findViewById(R.id.button_Create);
        editText_PartyName = (EditText) findViewById(R.id.editText_PartyName);
        editText_PartyLocation = (EditText) findViewById(R.id.editText_PartyLocation);


        button_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                JSONObject jsonResponse;

                try {


                    //WeParty_userId = 1;

                    JSONObject jsonPost = new JSONObject();
                    jsonPost.put("partyName", editText_PartyName.getText());
                    jsonPost.put("partyLocation",   editText_PartyLocation.getText());
                    jsonPost.put("partyUserId", WeParty_userId);


                    // envia um post contendo o json e obtem resposta
                    jsonResponse = WebServiceJSON.post("parties/create", jsonPost);

                    Log.i("parties/create: ", jsonResponse.toString());

                    WeParty_partyId = jsonResponse.getJSONObject("jsonResponse").getInt("lastInsertId");

                    Log.i("WeParty_partyId: ", Integer.toString(WeParty_partyId));


                } catch (JSONException e) {
                    Log.i("opa", "JSONException");
                    e.printStackTrace();
                }
            }
        });



    }
}
