package aru.androidrecyclerviewloadmore;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    ArrayList<User> mArrayuser = new ArrayList<>();
    MyUserAdapter mAdapter;
    LinearLayoutManager layoutManager;
    Context mContext;
    public int since=1;
    int count=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MakeApiCall();
        mContext=this;
        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new MyUserAdapter(mArrayuser,mContext);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setLoadMore(new LoadMoreListener() {
            @Override
            public void onLoadMore() {
               // Log.e("OnLoadMore","OnLoadMore");
                mRecyclerView.post(new Runnable() {
                    @Override
                    public void run() {
                        since++;
                        count++;
                        LoadMoreData(since);
                      //  Log.e("setLoader","setLoader");

                    }
                });
            }
        });
    }

    private void LoadMoreData(int since) {
      //  Log.e("LoadMoreData","LoadMoreData");
        new MakeConnection(MainActivity.this,Constatnt.BASEURL,since,new ResponseListener(){

            @Override
            public void onResponse(String jobj) {
                try {
                    Log.e("response111",jobj);
                    JSONArray jarray = new JSONArray(jobj);
                   // int count = jarray.length();
                    Log.e("Count",String.valueOf(count));

                    if(count != 4){
                        for(int i=0;i<jarray.length();i++){
                            User mUser = new User();
                            JSONObject object = jarray.getJSONObject(i);
                            mUser.setLogin(object.getString("login"));
                            mUser.setImage(object.getString("avatar_url"));
                            mArrayuser.add(mUser);
                        }
                        Log.e("ArrayMainActivity1111","count1111"+count+new Gson().toJson(mArrayuser));
                        mAdapter.notifyDataSetChanged();
                        mAdapter.notifyDataChanged();
                        Log.e("CountItem",String.valueOf(mAdapter.getItemCount()));
                    }else{
                       // Log.e("ccccccccccccc","ccccccccccccccccc");
                        mAdapter.notifyDataSetChanged();
                        mAdapter.setLoader(false);
                        Log.e("CountItem3333333333",String.valueOf(mAdapter.getItemCount()));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void MakeApiCall(){
        final ProgressDialog progress = new ProgressDialog(this);
        progress.setMessage("Please wait... ");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(true);
        progress.show();
        new MakeConnection(MainActivity.this,Constatnt.BASEURL,since,new ResponseListener(){

            @Override
            public void onResponse(String jobj) {
                try {
                    //Log.e("responseMainActivity",jobj);
                    JSONArray jarray = new JSONArray(jobj);
                    for(int i=0;i<jarray.length();i++){
                        User mUser = new User();
                        JSONObject object = jarray.getJSONObject(i);
                        mUser.setLogin(object.getString("login"));
                        mUser.setImage(object.getString("avatar_url"));
                        mArrayuser.add(mUser);
                    }
                    // Log.e("ArrayMainActivity","===>"+new Gson().toJson(mArrayuser));
                     mAdapter.notifyDataSetChanged();
                    progress.dismiss();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
