package aru.androidrecyclerviewloadmore;

import android.content.Context;
import android.util.Base64;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MakeConnection {
    JSONObject jobj,jobjre;
    private RequestQueue requestQueue;
    private CustomJsonObject callApi;
    private  JsonObjectRequest mJsonObjectRequest;
    public StringRequest mStringRequest;
    public Context mContext;
    public String url;
    int since;
    ResponseListener mResponseListener;

    public MakeConnection(MainActivity mainActivity, String baseurl,int mIndex, ResponseListener responseListener) {
        this.mContext = mainActivity;
        this.url = baseurl;
        this.mResponseListener = responseListener;
        this.since = mIndex;
         /* Map<String, String> json = new HashMap<>();
           json.put("api_key","7e8f60e325cd06e164799af1e317d7a7");
           json.put("page","1");
           Log.e("json==", ":==" + json);

           callApi = new CustomJsonObject(Request.Method.POST, BASEURLMOVIE, new JSONObject(json), new Response.Listener<JSONObject>() {
               @Override
               public void onResponse(JSONObject response) {
                   Log.e("json==json", "Response:==" + response);
               }
           }, new Response.ErrorListener() {
               @Override
               public void onErrorResponse(VolleyError error) {
                   Log.e("Error:", error.toString());
               }
           });*/
           Log.e("Baseurl:",url+since);
            mStringRequest = new StringRequest(Request.Method.GET, url+since, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("MakeConnectionResponse",response);
                    mResponseListener.onResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue = Volley.newRequestQueue(mContext);
        requestQueue.add(mStringRequest);
        mStringRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 5, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

    }

}
