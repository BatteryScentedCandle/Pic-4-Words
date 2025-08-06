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
import android.widget.Toast;

import com.example.pic_4_words_java.MainMenu.Leaderboard.LeaderboardAdapter;
import com.example.pic_4_words_java.MainMenu.Leaderboard.UserScore;
import com.example.pic_4_words_java.R;

import java.util.ArrayList;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.auth.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LeaderboardFragment extends Fragment {

    private FirebaseFirestore db;
    List<UserScore> userScores = new ArrayList<>();
    LeaderboardAdapter adapter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_leaderboard, container, false);

        db = FirebaseFirestore.getInstance();

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new LeaderboardAdapter(userScores);
        recyclerView.setAdapter(adapter);

        loadLeaderboard();

        return view;
    }

    //loads scores from firebase
    private void loadLeaderboard(){
        db.collection("userscores")
                .get()
                .addOnSuccessListener(querySnapshot ->{
                    userScores.clear();
                    for (DocumentSnapshot doc : querySnapshot.getDocuments()){
                        UserScore score = doc.toObject(UserScore.class);
                        if (score != null) {
                            userScores.add(score);
                        }
                    }
                    Collections.sort(userScores, (a, b) -> Integer.compare(b.getScore(), a.getScore()));
                    adapter.notifyDataSetChanged();
                })
                .addOnFailureListener(e ->{
                    e.printStackTrace();
                });
    }
}