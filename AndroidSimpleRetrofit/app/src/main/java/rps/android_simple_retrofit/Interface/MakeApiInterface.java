package rps.android_simple_retrofit.Interface;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rps.android_simple_retrofit.Modal.MovieResponce;
import rps.android_simple_retrofit.Utility.Contant;

public interface MakeApiInterface {

    @GET(Contant.SUBURL)
    Call<MovieResponce>getTopRateMovie(@Query(Contant.API_KEY) String key);
}
