package com.example.triparrangersfyp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.window.SplashScreen;

import com.airbnb.lottie.LottieAnimationView;
import com.example.triparrangersfyp.Admin.Admin_Dashboard;
import com.example.triparrangersfyp.Tourist.Users_Dashboard;
import com.example.triparrangersfyp.TravelAgency.TA_Dashboard;
import com.example.triparrangersfyp.util.TinyDB;

public class MainActivity extends AppCompatActivity {
    TinyDB tinyDB;
    private static final int counter = 5000;

    TextView tv;
    LottieAnimationView lottieAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        tv=findViewById(R.id.triparrangers);
        lottieAnimationView=findViewById(R.id.Lottie);
        tinyDB = new TinyDB(this);
        tv.animate().translationY(-1300).setDuration(2700).setStartDelay(900);
        //lottieAnimationView.animate().translationX(0).setDuration(2000).setStartDelay(2900);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (tinyDB.getInt("LOGIN_PREF") == 1) {
                    Intent intent = new Intent(MainActivity.this, Users_Dashboard.class);
                    startActivity(intent);
                    finish();
                }
                else if (tinyDB.getInt("LOGIN_PREF") == 2) {
                    Intent intent = new Intent(MainActivity.this, TA_Dashboard.class);
                    startActivity(intent);
                    finish();
                }
                else if (tinyDB.getInt("LOGIN_PREF") == 3){
                    Intent intent = new Intent(MainActivity.this, Admin_Dashboard.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Intent intent = new Intent(MainActivity.this, Login.class);
                    startActivity(intent);
                    finish();
                }

            }
        }, counter);
    }
}




