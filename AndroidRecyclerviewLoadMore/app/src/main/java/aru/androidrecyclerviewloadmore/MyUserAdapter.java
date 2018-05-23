package aru.androidrecyclerviewloadmore;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Aru on 02-04-2018.
 */

public class MyUserAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    ArrayList<User> arrayUser;
    public Context mContext;
    private View itemview;
    private static final int ITEM = 0;
    private static final int LOADING = 1;
    LoadMoreListener MoreListener;
    private boolean isAddedLoad = false;
   boolean isMoreDataAvailable = true;
   boolean isLoading = false;
    public MyUserAdapter(ArrayList<User> articaleArrayList, Context mContext) {
        this.arrayUser = articaleArrayList;
        this.mContext = mContext;

    }

    public void setLoadMore(LoadMoreListener loadMoreListener) {
        this.MoreListener = loadMoreListener;
    }


    @Override
    public int getItemCount() {
        return arrayUser.size();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view_item = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_articleitem_row,parent,false);
        View load_item = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemloadprogress,parent,false);
        if(viewType == ITEM){
           // Log.e("Item","Item");
            return new MyItemViewHolder(view_item);
        }else {
           // Log.e("LoadMore","LoadMore");
            return new MyLoadItemViewHolder(load_item);
        }

    }

    public void setLoader(boolean isMoredata) {
       // Log.e("aaaaaaaaaaa","bbbbbbbbbbbbb");
        isMoreDataAvailable=isMoredata;
        notifyDataSetChanged();
    }
    public void notifyDataChanged(){
       // Log.e("aaaaaaaaaaa","aaaaaaaaaaaa");
        isLoading = false;
        notifyDataSetChanged();
    }

    public class MyLoadItemViewHolder extends RecyclerView.ViewHolder{
            ProgressBar mProgressBar;
        public MyLoadItemViewHolder(View itemview){
            super(itemview);
            mProgressBar = (ProgressBar)itemview.findViewById(R.id.loadmore_progress);
        }
    }
    public class MyItemViewHolder extends RecyclerView.ViewHolder{
            TextView tv_title;
            ImageView imageView;

        public MyItemViewHolder(View itemView) {
            super(itemView);
            tv_title = (TextView)itemView.findViewById(R.id.txt_login);
            imageView = (ImageView)itemView.findViewById(R.id.profile_image);

        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            if (getItemViewType(position) == ITEM){
                User mUser = arrayUser.get(position);
                bind((MyItemViewHolder) holder,mUser);
               // Log.e("onBindViewHolder","onBindViewHolder");
            }else{
                if(position>=getItemCount()-1 && isMoreDataAvailable && !isLoading && MoreListener!=null){
                  //  Log.e("isLoading","isLoading");
                    isLoading = true;
                    MoreListener.onLoadMore();
                }
            }
    }

    private void bind(final MyItemViewHolder holder, User mUser) {
        holder.tv_title.setText(mUser.getLogin());
        Picasso.with(mContext).load(mUser.getImage())
                .into(holder.imageView);
    }


    @Override
    public int getItemViewType(int position) {
      //  Log.e("getItemViewType",String.valueOf(position == arrayUser.size()-1));
       // return (position == arrayUser.size()-1 && isAddedLoad)?LOADING:ITEM;
        if(!isMoreDataAvailable){
            Log.e("isMoreDataAvailable","isMoreDataAvailable");
            return ITEM;
        }else{
            if(position == arrayUser.size()-1){
                //Log.e("lodingtur","lodingtrue");
                return LOADING;
            }else{
               // Log.e("itemtur","itemtrue");
                return ITEM;
            }
        }

    }
}
