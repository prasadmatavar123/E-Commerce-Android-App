package com.demo.e_commerce.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.demo.e_commerce.UserSession;
import com.demo.e_commerce.MainActivity;
import com.demo.e_commerce.R;

public class LoginActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private UserSession session;
    EditText username,password;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);


        // first argument is the name of file and second is the mode, 0 is private mode
        sharedPreferences = getSharedPreferences("Chara_User", 0);
        // get editor to edit in file
        editor = sharedPreferences.edit();
        // User Session Manager
        session = new UserSession(getApplicationContext());


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (username.getText().toString().equals("")) {
                    Toast.makeText(LoginActivity.this, "Enter Mobile Number", Toast.LENGTH_SHORT).show();
                } else {
                    //LoginUser(mobile.getText().toString());
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    session.createUserLoginSession(username.getText().toString());
                    editor.commit();
                    startActivity(intent);
                    finish();
                }
            }
        });

    }
}