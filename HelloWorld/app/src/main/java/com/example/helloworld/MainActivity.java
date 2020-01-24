package com.example.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickTopAlbums (View view) {
        onClickMenuItem(view, "https://rss.itunes.apple.com/api/v1/us/apple-music/top-albums/all/25/explicit.json");
    }

    public void onClickTopSongs (View view) {
        onClickMenuItem(view, "https://rss.itunes.apple.com/api/v1/us/apple-music/top-songs/all/25/explicit.json");
    }

    public void onClickMenuItem(View view, String reqStr) {
        Intent intent = new Intent(this, Top25AlbumsActivity.class);
        intent.putExtra("REQUEST_STRING", reqStr);
        startActivity(intent);
    }
}
