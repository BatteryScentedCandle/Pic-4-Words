package com.example.pic_4_words_java.MainMenu.Leaderboard;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pic_4_words_java.R;

import java.util.List;

public class LeaderboardAdapter extends RecyclerView.Adapter<LeaderboardAdapter.ViewHolder> {

    private List<UserScore> scores;

    public LeaderboardAdapter(List<UserScore> scores){
        this.scores = scores;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_score_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        UserScore score = scores.get(position);
        holder.rank.setText(String.valueOf(position + 1));
        holder.username.setText(score.getUsername());
        holder.points.setText(String.valueOf(score.getScore()));
    }

    @Override
    public int getItemCount() {
        return scores.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView rank, username, points;

        public ViewHolder(View itemView) {
            super(itemView);
            rank = itemView.findViewById(R.id.rankTextView);
            username = itemView.findViewById(R.id.usernameTextView);
            points = itemView.findViewById(R.id.scoreTextView);
        }
    }
}
