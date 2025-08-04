package com.example.pic_4_words_java.Game;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.pic_4_words_java.Model.VolumeDetailsCopy;
import com.example.pic_4_words_java.R;

public class GameActivity extends AppCompatActivity {

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

        VolumeDetailsCopy volumeDetails = new ViewModelProvider(this).get(VolumeDetailsCopy.class);
        volumeDetails.setCurrentSeekbarProgress(progress);
        volumeDetails.setMuted(isMuted);

        Log.d("GameActivity", "Progress: " + progress);
        Log.d("GameActivity", "Muted: " + isMuted);

        goToCategoryCopy();
    }
}