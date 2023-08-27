package com.example.aptify_login;

import android.os.Bundle;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

public class leader_board extends AppCompatActivity {

    String quizname;
    FirebaseDatabase fd;
    DatabaseReference leaderboard_ref;

    ArrayList<LeaderBoard_score> leaderboard_list = new ArrayList<>();

    RecyclerView rv;
    RvLeaderboardAdapter rvAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_leaderboards);

        fd = FirebaseDatabase.getInstance();
        leaderboard_ref = fd.getReference("Leaderboard");

        quizname = getIntent().getStringExtra("catagory");
        getLeaderboardData(quizname);

        rv = findViewById(R.id.rv_1);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setReverseLayout(true);

        rv.setLayoutManager(llm);

        rvAdapter = new RvLeaderboardAdapter(this, leaderboard_list);
        rv.setAdapter(rvAdapter);
    }

    void getLeaderboardData(String quizname) {
        leaderboard_ref.child(quizname).orderByChild("score").limitToLast(10).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                leaderboard_list.clear();
                HashMap<String, LeaderBoard_score> uniqueUsers = new HashMap<>();

                for (DataSnapshot all : snapshot.getChildren()) {
                    LeaderBoard_score data = all.getValue(LeaderBoard_score.class);
                    String username = data.getUsername();

                    if (!uniqueUsers.containsKey(username)) {

                        uniqueUsers.put(username, data);
                    } else {
                        LeaderBoard_score existingData = uniqueUsers.get(username);
                        if (existingData.getScore() < data.getScore()) {

                            existingData.setScore(data.getScore());
                        }
                    }
                }

                List<LeaderBoard_score> scores = new ArrayList<>(uniqueUsers.values());

                Collections.sort(scores, new Comparator<LeaderBoard_score>() {
                    @Override
                    public int compare(LeaderBoard_score o1, LeaderBoard_score o2) {
                        return Integer.compare(o2.getScore(), o1.getScore());
                    }
                });

                int count = 0;
                for (LeaderBoard_score score : scores) {
                    if (count >= 10) {
                        break;
                    }
                    leaderboard_list.add(score);
                    count++;
                }
                Collections.reverse(leaderboard_list);
                Log.d("Score leaders", "onDataChange() called with: snapshot = [" + leaderboard_list + "]");
                rvAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }



}
