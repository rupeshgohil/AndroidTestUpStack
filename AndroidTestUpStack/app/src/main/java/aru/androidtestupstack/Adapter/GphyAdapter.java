package aru.androidtestupstack.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;

import java.io.IOException;
import java.util.ArrayList;

import aru.androidtestupstack.ExosPlayVideo;
import aru.androidtestupstack.Interface.OnClickPrasenter;
import aru.androidtestupstack.MainActivity;
import aru.androidtestupstack.Modal.GiphyImageInfo;
import aru.androidtestupstack.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifDrawableBuilder;
import pl.droidsonroids.gif.GifImageView;

public class GphyAdapter extends RecyclerView.Adapter<GphyAdapter.GphyViewHolder>{
    ArrayList<GiphyImageInfo> arrayList;
    View v;
    Context mContext;
    public OnClickPrasenter mOnClickPrasenter;
    public GphyAdapter(Context mainActivity, ArrayList<GiphyImageInfo> mGphyArray, OnClickPrasenter mOnClick) {
        this.mContext = mainActivity;
        this.arrayList = mGphyArray;
        this.mOnClickPrasenter = mOnClick;
    }

    @NonNull
    @Override
    public GphyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_row, parent, false);
        return new GphyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull GphyViewHolder holder, int position) {
        GiphyImageInfo giphyImageInfo = arrayList.get(position);
        Log.e("GIF",giphyImageInfo.getGifurl());
        Glide
                .with(mContext)
                .load(giphyImageInfo.getGifurl())
                 .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE).override(200, 200))
                .into(holder.gifImageView);
        holder.gifImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(mContext,ExosPlayVideo.class);
                mIntent.putExtra("mp4",giphyImageInfo.getUrl());
                mContext.startActivity(mIntent);
            }
        });
        holder.imgLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnClickPrasenter.clickLicke(position);
            }
        });
        holder.imgUnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnClickPrasenter.clickDisLicke(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public void add(GiphyImageInfo giphyImageInfo) {
        arrayList.add(giphyImageInfo);
    }

    public static class GphyViewHolder extends RecyclerView.ViewHolder{
         @BindView(R.id.gif_image)
         ImageView gifImageView;
        @BindView(R.id.imgLike)
        ImageView imgLike;
        @BindView(R.id.imgunLike)
        ImageView imgUnLike;
        @BindView(R.id.txtlike)
        TextView tvLike;
        @BindView(R.id.txtunlike)
        TextView tvUnlike;
        public GphyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
