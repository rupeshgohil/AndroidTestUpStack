package rupesh.androidpracticaletwo.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import rupesh.androidpracticaletwo.R;

/**
 * Created by Aru on 17-09-2017.
 */


public class ActList extends AppCompatActivity implements View.OnClickListener {

    public Button btn1,btn2,btn3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_list);
        btn1 = (Button) findViewById(R.id.button1);
        btn1.setOnClickListener(this);
        btn2 = (Button) findViewById(R.id.button2);
        btn2.setOnClickListener(this);
        btn3 = (Button) findViewById(R.id.button3);
        btn3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.button1:
                startActivity(new Intent(getApplicationContext(), ActLoginRegister.class));
                break;
            case R.id.button2:
                startActivity(new Intent(getApplicationContext(), ActFacebookGoogleLogin.class));
                break;

        }
    }


}
