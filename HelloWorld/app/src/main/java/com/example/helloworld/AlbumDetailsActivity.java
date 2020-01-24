package com.example.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class AlbumDetailsActivity extends AppCompatActivity {

    private static final String TAG = "AlbumDetailsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_details);

        Intent intent = getIntent();
        JSONObject data = null;
        String artist = null;
        String title = null;
        String genre = null;
        String artworkURL = null;
        String release = null;
        String copyright = null;

        try {
            data = new JSONObject(intent.getExtras().getString("ALBUM_DETAILS"));
            artist = data.getString("artistName");
            title = data.getString("name");
            genre = data.getJSONArray("genres").getJSONObject(0).getString("name");
            artworkURL = data.getString("artworkUrl100");
            release = data.getString("releaseDate");
            copyright = data.getString("copyright");

            TextView artistView = findViewById(R.id.artist_view);
            TextView titleView = findViewById(R.id.title_view);
            TextView genreView = findViewById(R.id.genre_view);
            ImageView artworkView = findViewById(R.id.artwork_view);
            TextView releaseView = findViewById(R.id.release_view);
            TextView copyrightView = findViewById(R.id.copyright_view);

            artistView.setText(artist);
            titleView.setText(title);
            genreView.setText(genre);
            new DownloadImageTask(artworkView).execute(artworkURL);
            releaseView.setText(release);
            copyrightView.setText(copyright);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d(TAG, "onCreate: " + data);
    }
}
