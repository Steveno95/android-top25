package com.example.helloworld;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.json.JSONArray;

public class Top25AlbumsActivity extends AppCompatActivity {

    private static final String TAG = "Top25AlbumsActivity";

    // variables
    private JSONArray data = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_25_albums);

        Intent intent = getIntent();
        String url = intent.getExtras().getString("REQUEST_STRING");


        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading data...");
        progressDialog.show();
        new NetworkManager().getData(url, new NetworkManager.Callback() {
            @Override
            public void complete(final JSONArray data) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Top25AlbumsActivity.this.data = data;
                        initRecyclerView();
                        progressDialog.dismiss();

                    }
                });
            }
        });
    }

    private void initRecyclerView() {
        Log.d(TAG, "initRecyclerView: init recyclerview");
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, data);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
