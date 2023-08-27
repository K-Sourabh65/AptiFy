package com.example.aptify_login;

import static com.example.aptify_login.Quiz_Splash.listOfQ;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Quiz_dashboard extends AppCompatActivity {

    List<Model> list  = new ArrayList<>();;
    Model mod;
    int index=0;

    String quizcatagory;
    int correctCount = 0;
    int totalCount = 5;
    int wrongCount = 0;

    TextView card_question,card_opt1,card_opt2,card_opt3,card_opt4;
    CardView cardOA,cardOB,cardOC,cardOD;

    Button nextBtn1;
    TextView exitbtn;
    ImageView i1;


    CountDownTimer countDownTimer;
    int timerValue = 40;

    ProgressBar progressBar;

    private void Hooks(){
        card_question = findViewById(R.id.card_que);
        card_opt1 = findViewById(R.id.card_opt1);
        card_opt2 = findViewById(R.id.card_opt2);
        card_opt3 = findViewById(R.id.card_opt3);
        card_opt4 = findViewById(R.id.card_opt4);

        cardOA = findViewById(R.id.cardOA);
        cardOB = findViewById(R.id.cardOB);
        cardOC = findViewById(R.id.cardOC);
        cardOD = findViewById(R.id.cardOD);

        nextBtn1 = findViewById(R.id.nextBtn1);

        exitbtn = findViewById(R.id.exit);
        i1 = findViewById(R.id.ic_back);
    }

    private void setAllData() {
        card_question.setText(mod.getQuestion());
        card_opt1.setText(mod.getoA());
        card_opt2.setText(mod.getoB());
        card_opt3.setText(mod.getoC());
        card_opt4.setText(mod.getoD());

    }

    public void correct(CardView cardView){
//        cardView.setBackgroundColor(getResources().getColor(R.color.green));
        //System.out.println("Working!!!");


        Log.d("size", String.valueOf(listOfQ.size()));
        nextBtn1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
//                cardView.setBackgroundColor(getResources().getColor(R.color.black));
                nextBtn1.setClickable(false);
                correctCount++;
                index++;
                enableButton();
                mod = list.get(index);
                setAllData();
                resetColor();

            }
        });



    }

    public void wrong(CardView cardOA){

//        cardOA.setBackgroundColor(getResources().getColor(R.color.red));


        nextBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextBtn1.setClickable(false);
                wrongCount++;
                if(index < list.size()-1){
                    index++;
                    enableButton();
                    mod = list.get(index);
                    setAllData();
                    resetColor();
                }else{
                    gameWon();
                }
            }
        });
    }

    private void gameWon() {
//        Toast.makeText(this, "time's UP!!", Toast.LENGTH_SHORT).show();
        int res = totalCount-wrongCount;
        Intent inet = new Intent(Quiz_dashboard.this,Won.class);
        inet.putExtra("correct",res);
        inet.putExtra("wrong",wrongCount);
        inet.putExtra("round",quizcatagory);
        startActivity(inet);
        finish();
    }

    public void enableButton(){
        cardOA.setClickable(true);
        cardOB.setClickable(true);
        cardOC.setClickable(true);
        cardOD.setClickable(true);
    }

    public void disableButton(){
        cardOA.setClickable(false);
        cardOB.setClickable(false);
        cardOC.setClickable(false);
        cardOD.setClickable(false);

    }

    public void resetColor(){
        cardOA.setBackgroundColor(getResources().getColor(R.color.white));
        cardOB.setBackgroundColor(getResources().getColor(R.color.white));
        cardOC.setBackgroundColor(getResources().getColor(R.color.white));
        cardOD.setBackgroundColor(getResources().getColor(R.color.white));
    }

    public void optionaClick(View view){
        nextBtn1.setClickable(true);
        disableButton();
        cardOA.setBackgroundColor(getResources().getColor(R.color.blue));
        if(mod.getoA().equals(mod.getAns())){


            if(index <= list.size()-1){


                correct(cardOA);

            }else{
                gameWon();
            }

        }else{
            wrong(cardOA);
        }
    }

    public void optionbClick(View view){

        nextBtn1.setClickable(true);
        disableButton();
        cardOB.setBackgroundColor(getResources().getColor(R.color.blue));
        if(mod.getoB().equals(mod.getAns())){
//            cardOB.setBackgroundColor(getResources().getColor(R.color.green));

            if(index < list.size()-1){
                correct(cardOB);
            }else{
                gameWon();
            }

        }else{
            wrong(cardOB);
        }
    }

    public void optioncClick(View view){

        nextBtn1.setClickable(true);
        disableButton();
        cardOC.setBackgroundColor(getResources().getColor(R.color.blue));
        if(mod.getoC().equals(mod.getAns())){
//            cardOC.setBackgroundColor(getResources().getColor(R.color.green));

            if(index < list.size()-1){
                correct(cardOC);
            }else{
                gameWon();
            }

        }else{
            wrong(cardOC);
        }
    }

    public void optiondClick(View view){

        nextBtn1.setClickable(true);
        disableButton();
        cardOD.setBackgroundColor(getResources().getColor(R.color.blue));
        if(mod.getoD().equals(mod.getAns())){
//            cardOD.setBackgroundColor(getResources().getColor(R.color.green));

            if(index < list.size()-1){
                correct(cardOD);
            }else{
                gameWon();
            }

        }else{
            wrong(cardOD);
        }
    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_dashboard);

        quizcatagory = getIntent().getStringExtra("category");

        Hooks();

        list = listOfQ;
//        list.add(new Model("sai","s","a","i","r","s"));
//        list.add(new Model("raj","r","a","j","t","r"));
//        list.add(new Model("raut","r","a","u","t","r"));

        Collections.shuffle(list);
        mod = list.get(index);


        cardOA.setBackgroundColor(getResources().getColor(R.color.white));
        cardOB.setBackgroundColor(getResources().getColor(R.color.white));
        cardOC.setBackgroundColor(getResources().getColor(R.color.white));
        cardOD.setBackgroundColor(getResources().getColor(R.color.white));

        nextBtn1.setClickable(false);

        setAllData();

        progressBar = findViewById(R.id.progressBar);
        countDownTimer = new CountDownTimer(40000,1000) {
            @Override
            public void onTick(long l) {
                timerValue = timerValue-1;
                progressBar.setProgress(timerValue);
            }

            @Override
            public void onFinish() {
                Intent i = new Intent(Quiz_dashboard.this,Won.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.putExtra("EXIT",true);
                startActivity(i);
            }
        }.start();

        exitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Quiz_dashboard.this,homePage.class);
                startActivity(in);

            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(Quiz_dashboard.this,homePage.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.putExtra("EXIT",true);
        startActivity(i);
    }
}