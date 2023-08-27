package com.example.aptify_login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Quiz_Splash extends AppCompatActivity {
    public static ArrayList<Model> listOfQ;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_splash);

        // Fetch data from Firebase
        listOfQ = new ArrayList<>();
//        databaseReference = FirebaseDatabase.getInstance().getReference("Question");
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
//                    Model model = dataSnapshot.getValue(Model.class);
//                    listOfQ.add(model);
//                }
//                startCountdown();
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

        final String selectTopic=getIntent().getStringExtra("text");
        Log.d("name",selectTopic);

        databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://aptify-4401d-default-rtdb.firebaseio.com/");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.child(selectTopic).getChildren()) {
                    final String getQuestion = dataSnapshot.child("Question").getValue(String.class);

                    final String getOption1 = dataSnapshot.child("oA").getValue(String.class);
                    final String getOption2 = dataSnapshot.child("oB").getValue(String.class);
                    final String getOption3 = dataSnapshot.child("oC").getValue(String.class);
                    final String getOption4 = dataSnapshot.child("oD").getValue(String.class);
                    final String getAnswer = dataSnapshot.child("ans").getValue(String.class);

                    Model mod = new Model(getQuestion, getOption1, getOption2, getOption3, getOption4, getAnswer);
                    listOfQ.add(mod);
//                    Model model = dataSnapshot.getValue(Model.class);
//                    listOfQ.add(model);
//
                    Log.d("LIST", "onDataChange() called with: snapshot = [" + listOfQ + "]");
                    Intent inet = new Intent(Quiz_Splash.this,Quiz_dashboard.class);
                    inet.putExtra("category",selectTopic);
                    startActivity(inet);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
