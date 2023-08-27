package com.example.aptify_login;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ScoreAdapter extends RecyclerView.Adapter<ScoreAdapter.ScoreViewHolder> {
    private List<Score> scoreList;

    public ScoreAdapter(List<Score> scoreList) {
        this.scoreList = scoreList;
    }

    @NonNull
    @Override
    public ScoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_score, parent, false);
        return new ScoreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScoreViewHolder holder, int position) {
        Score score = scoreList.get(position);
        holder.bind(score);
    }

    @Override
    public int getItemCount() {
        return scoreList.size();
    }

    static class ScoreViewHolder extends RecyclerView.ViewHolder {
        private TextView textScore;
        private TextView textAccuracy;
        private TextView textTimeOfQuiz;

        private  TextView textCatagory;

        ScoreViewHolder(@NonNull View itemView) {
            super(itemView);
            textScore = itemView.findViewById(R.id.text_score);
            textAccuracy = itemView.findViewById(R.id.text_accuracy);
            textTimeOfQuiz = itemView.findViewById(R.id.text_time_of_quiz);
            textCatagory = itemView.findViewById(R.id.text_catagory);
        }

        void bind(Score score) {
            textScore.setText("Score: " + score.getScore());
            textAccuracy.setText("Accuracy: " + score.getAccuracy());
            textTimeOfQuiz.setText("Time of Quiz: " + score.getTime());
            textCatagory.setText("Quiz Catagory: " + score.getQuizcatagory());
        }
    }
}
