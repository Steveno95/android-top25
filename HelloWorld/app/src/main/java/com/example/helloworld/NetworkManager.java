package com.example.helloworld;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NetworkManager {

    interface Callback {
        public void complete(JSONArray data);
    }

    public void getData(String url, final Callback callback) {
        Request request = new Request.Builder().url(url).build();
        OkHttpClient client = new OkHttpClient();


        client.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected code " + response);
                } else {

                    try {
                        JSONArray data = new JSONObject(response.body().string()).getJSONObject("feed").getJSONArray("results");
                        callback.complete(data);
                        Log.d("Network Manager", "onResponse: " + data);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
