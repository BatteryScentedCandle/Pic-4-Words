package com.example.pic_4_words_java.Game;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.pic_4_words_java.Game.Fragments.Category;
import com.example.pic_4_words_java.MainMenu.GameVolumeDetails;
import com.example.pic_4_words_java.MainMenu.VolumeDetails;
import com.example.pic_4_words_java.R;

public class Game extends AppCompatActivity {

    public void goToCategoryCopy() {
        Fragment categoryCopy = new Category();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.flFragmentContainer, categoryCopy).commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_game);

        int progress = getIntent().getIntExtra("progress", 100);
        boolean isMuted = getIntent().getBooleanExtra("muted", false);

        GameVolumeDetails volumeDetails = new ViewModelProvider(this).get(GameVolumeDetails.class);
        volumeDetails.setCurrentSeekbarProgress(progress);
        volumeDetails.setMuted(isMuted);

        Log.d("Game", "Progress: " + progress);
        Log.d("Game", "Muted: " + isMuted);

        goToCategoryCopy();
    }
}