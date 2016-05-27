package com.fredfama.weparty;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends WePartyActivity {

    Button button_Login;
    EditText editText_UserEmail;
    EditText editText_UserPassword;
    TextView textView_CreateAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        button_Login = (Button) findViewById(R.id.button_Login);
        textView_CreateAccount = (TextView) findViewById(R.id.textView_CreateAccount);
        editText_UserEmail = (EditText) findViewById(R.id.editText_UserEmail);
        editText_UserPassword = (EditText) findViewById(R.id.editText_UserPassword);


        if(!WeParty_userName.equals("")) {

            Log.i("sim WeParty_userName: ", WeParty_userName);

        } else {
            Log.i("nao WeParty_userName: ", "");
        }

        button_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                JSONObject jsonResponse;

                try {

                    JSONObject jsonPost = new JSONObject();
                    jsonPost.put("userEmail",   editText_UserEmail.getText().toString().toLowerCase().trim());
                    jsonPost.put("userPassword", editText_UserPassword.getText().toString());


                    // envia um post contendo o json e obtem resposta
                    jsonResponse = WebServiceJSON.post("users/login", jsonPost);

                    Log.i("users/login: ", jsonResponse.toString());

                    Context context = getApplicationContext();
                    CharSequence text;
                    int duration = Toast.LENGTH_LONG;


                    if(jsonResponse.getJSONObject("jsonResponse").getString("success").equals("yes")) {

                        WeParty_userId = jsonResponse.getJSONObject("jsonResponse").getInt("userId");
                        WeParty_userName = jsonResponse.getJSONObject("jsonResponse").getString("userName");


                        text = "Login efetuado com sucesso! valeu "+WeParty_userName;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();

                        startMainActivity();


                    } else {

                        text = "Não foi possível efetuar login, email ou senha inválidos";
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();


                    }


                } catch (JSONException e) {
                    Log.i("opa", "JSONException");
                    e.printStackTrace();
                }
            }
        });

        textView_CreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startUserRegistrationActivity();


            }
        });


    }

    private void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void startUserRegistrationActivity() {
        Intent intent = new Intent(this, UserRegistrationActivity.class);
        startActivity(intent);
    }
}
