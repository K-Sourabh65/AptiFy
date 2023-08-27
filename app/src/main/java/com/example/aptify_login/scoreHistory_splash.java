package com.example.aptify_login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class scoreHistory_splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_history_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent  i = new Intent(scoreHistory_splash.this,scoreHistory.class);
                startActivity(i);
                finish();
            }
        },3000);

    }
}