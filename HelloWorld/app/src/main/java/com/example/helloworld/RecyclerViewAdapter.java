package com.example.helloworld;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";

    private Context context;
    private JSONArray mData = new JSONArray();

    public RecyclerViewAdapter(Context context, JSONArray mData) {
        this.context = context;
        this.mData = mData;
        Log.d(TAG, "RecyclerViewAdapter: " + mData);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_top_25_albums_listitem, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        Log.d(TAG, "onBindViewHolder: called");
        JSONObject currentItem = null;
        String currentAlbum = null;
        String currentArtist = null;

        try {
            currentItem = mData.getJSONObject(i);
            currentAlbum = currentItem.getString("name");
            currentArtist = currentItem.getString("artistName");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        viewHolder.rank.setText(Integer.toString(i + 1));
        viewHolder.album.setText(currentAlbum);
        viewHolder.artist.setText(currentArtist);

        viewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked on ");


                try {
                    Intent intent = new Intent(context, AlbumDetailsActivity.class);
                    intent.putExtra("ALBUM_DETAILS", mData.getJSONObject(i).toString());
                    context.startActivity(intent);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mData != null) {
            return mData.length();
        } else {
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView rank, album, artist;
        LinearLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            rank = itemView.findViewById(R.id.rank);
            album = itemView.findViewById(R.id.album);
            artist = itemView.findViewById(R.id.artist);
            parentLayout = itemView.findViewById(R.id.list_item_layout);
        }
    }
}

