package com.fredfama.weparty;

import android.content.Context;
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

        button_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                JSONObject jsonResponse;

                try {

                    JSONObject jsonPost = new JSONObject();
                    jsonPost.put("userEmail",   editText_UserEmail.getText());
                    jsonPost.put("userPassword", editText_UserPassword.getText());


                    // envia um post contendo o json e obtem resposta
                    jsonResponse = WebServiceJSON.post("users/login", jsonPost);

                    Log.i("users/login: ", jsonResponse.toString());

                    Context context = getApplicationContext();
                    CharSequence text;
                    int duration = Toast.LENGTH_LONG;


                    if(jsonResponse.getJSONObject("jsonResponse").getString("success").equals("yes")) {

                        text = "Login efetuado com sucesso!";
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();


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


    }
}
