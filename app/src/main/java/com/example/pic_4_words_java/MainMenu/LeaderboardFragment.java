package com.example.pic_4_words_java.MainMenu;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pic_4_words_java.Game.Fragments.Score;
import com.example.pic_4_words_java.MainMenu.Leaderboard.LeaderboardAdapter;
import com.example.pic_4_words_java.MainMenu.Leaderboard.UserScore;
import com.example.pic_4_words_java.R;

import java.util.ArrayList;
import java.util.List;

public class LeaderboardFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_leaderboard, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);

        List<UserScore> userScores = new ArrayList<>();

        userScores.add(new UserScore("Squiggly Miggly", 15));
        userScores.add(new UserScore("Micropachycephalosaurus", 12));

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        LeaderboardAdapter adapter = new LeaderboardAdapter(userScores);
        recyclerView.setAdapter(adapter);
        return view;
    }
}