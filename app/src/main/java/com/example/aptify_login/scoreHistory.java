package com.example.aptify_login;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class scoreHistory extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ScoreAdapter scoreAdapter;
    private List<Score> scoreList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_history);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        scoreList = new ArrayList<>();
        scoreAdapter = new ScoreAdapter(scoreList);
        recyclerView.setAdapter(scoreAdapter);

        String userId = FirebaseAuth.getInstance().getUid();
        Query query = FirebaseDatabase.getInstance().getReference()
                .child("Score")
                .child(userId)
                .orderByKey()
                .limitToLast(5);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                scoreList.clear();
                for (DataSnapshot testSnapshot : dataSnapshot.getChildren()) {
                    String dateTime = testSnapshot.getKey();
                    Score score = testSnapshot.getValue(Score.class);
                    if (score != null) {
                        score.setTime(dateTime);
                        scoreList.add(score);
                    }
                }
                Log.d("Scorelist", "onDataChange() called with: dataSnapshot = [" + scoreList + "]");
                Log.d("USER", "onDataChange() called with: dataSnapshot = [" + userId + "]");
                // Reverse the list to display the most recent scores first
                Collections.reverse(scoreList);
                scoreAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle any errors
            }
        });
    }
}