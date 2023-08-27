package com.example.aptify_login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class Activity_lederboard_loader extends AppCompatActivity {

    private FrameLayout num,ver,log, crit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lederboard_loader);

        num = findViewById(R.id.lead1);
        log = findViewById(R.id.lead3);
        ver = findViewById(R.id.lead2);
        crit = findViewById(R.id.lead4);

        num.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Activity_lederboard_loader.this,leader_board.class);
                i.putExtra("catagory","Numeric Round");
                startActivity(i);
            }
        });
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Activity_lederboard_loader.this,leader_board.class);
                i.putExtra("catagory","Logical Round");
                startActivity(i);
            }
        });
        ver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Activity_lederboard_loader.this,leader_board.class);
                i.putExtra("catagory","Verbal Round");
                startActivity(i);
            }
        });
        crit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Activity_lederboard_loader.this,leader_board.class);
                i.putExtra("catagory","Critical Round");
                startActivity(i);
            }
        });
    }
}