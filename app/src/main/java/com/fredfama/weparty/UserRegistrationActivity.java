package com.fredfama.weparty;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class UserRegistrationActivity extends WePartyActivity {

    Button button_UserRegister;
    EditText editText_UserName;
    EditText editText_UserEmail;
    EditText editText_UserPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);


        button_UserRegister = (Button) findViewById(R.id.button_UserRegister);
        editText_UserName = (EditText) findViewById(R.id.editText_UserName);
        editText_UserEmail = (EditText) findViewById(R.id.editText_UserEmail);
        editText_UserPassword = (EditText) findViewById(R.id.editText_UserPassword);


        button_UserRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                JSONObject jsonResponse;

                try {

                    String userName = editText_UserName.getText().toString().trim();
                    String userEmail = editText_UserEmail.getText().toString().toLowerCase().trim();


                    JSONObject jsonPost = new JSONObject();
                    jsonPost.put("userName", userName);
                    jsonPost.put("userEmail",   userEmail);
                    jsonPost.put("userPassword", editText_UserPassword.getText());


                    // envia um post contendo o json e obtem resposta
                    jsonResponse = WebServiceJSON.post("users/registration", jsonPost);

                    Log.i("users/registration: ", jsonResponse.toString());

                    Context context = getApplicationContext();
                    CharSequence text;
                    int duration = Toast.LENGTH_LONG;


                    if(jsonResponse.getJSONObject("jsonResponse").getString("success").equals("yes")) {

                        WeParty_userId = jsonResponse.getJSONObject("jsonResponse").getInt("lastInsertId");
                        WeParty_userName = userName;
                        WeParty_userEmail = userEmail;

                        Log.i("WeParty_userId: ", Integer.toString(WeParty_userId));

                        text = "Usuário cadastrado com sucesso!";
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();

                        startLoginActivity();


                    } else {

                        String info = jsonResponse.getJSONObject("jsonResponse").getString("info");
                        text = "Não foi possível cadastrar o usuário, "+info;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();


                    }


                } catch (JSONException e) {
                    Log.i("opa", "JSONException");
                    e.printStackTrace();
                }
            }
        });




    }


    private void startLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }



}
