package com.demo.e_commerce.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.demo.e_commerce.MainActivity;
import com.demo.e_commerce.UserSession;
import com.demo.e_commerce.R;

public class SplashActivity extends AppCompatActivity {

    private static final long SPLASH_SCREEN_TIME_OUT = 10;
    UserSession session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash); // this will bind your MainActivity.class file with activity_main.

        session = new UserSession(getApplicationContext());
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Intent is used to switch from one activity to another.
                if(session.isUserLoggedIn()) {
                    //Toast.makeText(SplashScreen.this, "Login", Toast.LENGTH_SHORT).show();
                    // user is not logged in redirect him to Login Activity
                    Intent i = new Intent(SplashActivity.this, MainActivity.class);

                    // Closing all the Activities from stack
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                    // Add new Flag to start new Activity
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    i.setAction(Intent.ACTION_MAIN);
                    i.addCategory(Intent.CATEGORY_HOME);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    // Staring Login Activity
                    startActivity(i);
                    finish();
                }
                else {
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                    // HomeActivity.class is the activity to go after showing the splash screen.
                }// the current activity will get finished.
            }
        }, SPLASH_SCREEN_TIME_OUT);

    }
}