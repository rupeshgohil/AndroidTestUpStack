package rps.android_simple_retrofit;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rps.android_simple_retrofit.Adapter.MovieAdapter;
import rps.android_simple_retrofit.Connection.ApiClient;
import rps.android_simple_retrofit.Interface.MakeApiInterface;
import rps.android_simple_retrofit.Modal.MovieResponce;
import rps.android_simple_retrofit.Modal.Result;
import rps.android_simple_retrofit.Utility.Contant;
import rps.android_simple_retrofit.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding mBanding;
    MakeApiInterface makeApiInterface;
    MovieAdapter movieAdapter;
    List<Result> mResults = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBanding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        mBanding.recyclerview.setLayoutManager(new LinearLayoutManager(this));
        mBanding.recyclerview.setHasFixedSize(true);
        movieAdapter = new MovieAdapter(MainActivity.this,mResults);

        makeApiInterface = ApiClient.getClient().create(MakeApiInterface.class);
        MakeApiCall();
    }
    public void MakeApiCall(){

        Call<MovieResponce> movieResponceCall = makeApiInterface.getTopRateMovie(Contant.APIKEY);
        movieResponceCall.enqueue(new Callback<MovieResponce>() {
            @Override
            public void onResponse(Call<MovieResponce> call, Response<MovieResponce> response) {
                if(response.isSuccessful()){
                    mResults.addAll(response.body().getResults());
                    MovieResponce list = response.body();
                    Log.e("Page",String.valueOf(list.getPage()));
                    Log.e("Total_Page",String.valueOf(list.getTotalPages()));
                    Log.e("Total_result",String.valueOf(list.getTotalResults()));
                    Log.e("getResults",String.valueOf(list.getResults()));
                }
                mBanding.recyclerview.setAdapter(movieAdapter);
                movieAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<MovieResponce> call, Throwable t) {

            }
        });
    }
}
