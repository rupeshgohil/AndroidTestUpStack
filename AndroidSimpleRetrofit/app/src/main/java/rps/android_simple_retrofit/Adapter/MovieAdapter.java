package rps.android_simple_retrofit.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;
import rps.android_simple_retrofit.Modal.Result;
import rps.android_simple_retrofit.R;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder>{
    public Context context;
    List<Result> mList;
    public MovieAdapter(Context mainActivity, List<Result> mResults) {
        this.context = mainActivity;
        this.mList = mResults;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.movieTitle.setText(mList.get(position).getTitle());
        holder.data.setText(mList.get(position).getReleaseDate());
        holder.movieDescription.setText(mList.get(position).getOverview());
        holder.rating.setText(mList.get(position).getVoteAverage().toString());
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView movieTitle;
        TextView data;
        TextView movieDescription;
        TextView rating;
        public MyViewHolder(View vv) {
            super(vv);
            movieTitle = (TextView) vv.findViewById(R.id.title);
            data = (TextView) vv.findViewById(R.id.subtitle);
            movieDescription = (TextView) vv.findViewById(R.id.description);
            rating = (TextView) vv.findViewById(R.id.rating);
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
