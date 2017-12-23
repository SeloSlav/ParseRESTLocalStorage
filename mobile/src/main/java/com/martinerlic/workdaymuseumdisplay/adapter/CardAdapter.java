package com.martinerlic.workdaymuseumdisplay.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.martinerlic.workdaymuseumdisplay.R;
import com.martinerlic.workdaymuseumdisplay.model.MediaImage;

import java.util.ArrayList;
import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {

    private List<MediaImage> mItems;
    private Context mContext;

    public CardAdapter(Context applicationContext) {
        super();
        mItems = new ArrayList<>();
        mContext = applicationContext;
    }

    public void addData(List<MediaImage> mediaIds) {
        mItems.addAll(mediaIds);
        notifyDataSetChanged();
    }

    public void clear() {
        mItems.clear();
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycler_view, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        String mediaId = mItems.get(i).getTitle();

        // TODO: Assuming mediaIds are URLs, we could load them into each imageView with Picasso
        /*Picasso.with(mContext)
                .load(photo.getMedia().get(0).getUrl())
                .placeholder(R.color.colorAccent)
                .into(viewHolder.imageView);*/

        /*viewHolder.imageView.setOnClickListener(view -> {
            Toast.makeText(mContext, mediaId, Toast.LENGTH_LONG).show();
        });*/

    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        // TextView textView;
        ImageView imageView;

        ViewHolder(View itemView) {
            super(itemView);

            // textView = itemView.findViewById(R.id.textView);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}