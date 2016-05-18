package com.fredfama.weparty;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by root on 09/05/16.
 */
public class WebServiceJSON {

    private static OkHttpClient client;
    private static Request request;
    private static Response response = null;

    private static String baseURL = "http://10.1.0.10/we_party/";




    // TODO: POST JSONOBJECT, http://stackoverflow.com/questions/34179922/okhttp-post-body-as-json
    public static JSONObject post(String action) {
        return post(action, null);
    }

    public static JSONObject post(String action, JSONObject jsonPost) {

        JSONObject jsonResponse = null;
        String jsonString = "";

        if(jsonPost != null) {
            jsonString = jsonPost.toString();
        }

        client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(mediaType, jsonString);
        request = new Request.Builder()
                .url(baseURL+action)
                .post(body)
                .build();

        try {

            response = client.newCall(request).execute();

            try {
                String responseData = response.body().string();
                jsonResponse = new JSONObject(responseData);
                //Log.i("opa json = ", json.toString());
                //Log.i("opa json2 = ", json.getString("send"));


            } catch (JSONException e) {
                Log.i("Exception", "JSONException postJSONObject");
                e.printStackTrace();
            }

        } catch (IOException e) {
            Log.i("Exception", "IOException postJSONObject");
            e.printStackTrace();
        }

        return jsonResponse;

    }


}
