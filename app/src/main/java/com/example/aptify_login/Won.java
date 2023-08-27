package com.example.aptify_login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Won extends AppCompatActivity {

    CircularProgressBar circularProgressBar;
    TextView resultText,exit;
    int correct,wrong;
    String category;
    String quizCatagory;
    double accuracy;
    FirebaseInsert insert;

    LinearLayout btnShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_won);



        correct = getIntent().getIntExtra("correct",0);
        wrong = getIntent().getIntExtra("wrong",0);
        category = getIntent().getStringExtra("round");

        circularProgressBar = findViewById(R.id.circularProgressBar);
        resultText = findViewById(R.id.resultText);
        btnShare = findViewById(R.id.btnShare);
        exit = findViewById(R.id.exit);

        circularProgressBar.setProgress(correct);
        resultText.setText(correct+"/5");

        addScoreToHistory(correct);

        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My application name");
                    String shareMessage= "\ni got"+correct + "out of 20 you can also try";
                    shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID +"\n\n";
                    shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                    startActivity(Intent.createChooser(shareIntent, "choose one"));
                } catch(Exception e) {
                    //e.toString();
                }
            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Won.this,homePage.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.putExtra("EXIT",true);
                startActivity(i);
            }
        });
    }


    private void addScoreToHistory(int scoreVal) {
        quizCatagory = category;
        accuracy = (double) scoreVal/5;
        SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        String currentDate = date.format(new Date());
        insert=new FirebaseInsert();
        Toast.makeText(this, ""+category, Toast.LENGTH_SHORT).show();
        insert.insert(quizCatagory, new Score(scoreVal, accuracy, insert.getDate(),quizCatagory), this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(Won.this,homePage.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.putExtra("EXIT",true);
        startActivity(i);
    }
}