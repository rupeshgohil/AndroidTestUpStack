package rps.androidsqliteexample;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import rps.androidsqliteexample.databinding.ActivityMainBinding;
import rps.androidsqliteexample.databinding.ActivitySignupBinding;

public class ActivitySignup extends BaseActivity {
    public static ActivitySignupBinding mActivitySignupBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       mActivitySignupBinding = DataBindingUtil.setContentView(this, R.layout.activity_signup);
       mActivitySignupBinding.btnsubmit.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if(isValid("ActivitySignup")){
                   Log.e("call","call");
               }
           }
       });
    }
}