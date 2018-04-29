package rps.android_simple_retrofit.Connection;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rps.android_simple_retrofit.Utility.Contant;

public class ApiClient {

    private static Retrofit retrofit;

    public static Retrofit getClient() {
        if(retrofit == null){
            Log.e("BaseUrl",Contant.BASEURL);
            OkHttpClient httpClient = new OkHttpClient.Builder()
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS).build();
            retrofit = new Retrofit.Builder()
                    .baseUrl(Contant.BASEURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient).build();
        }
        return retrofit;
    }
}
