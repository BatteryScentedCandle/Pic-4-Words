package com.example.pic_4_words_java.Game;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.pic_4_words_java.Model.BGMSettings;
import com.example.pic_4_words_java.Model.ScoreModel;
import com.example.pic_4_words_java.R;

public class GameActivity extends AppCompatActivity {

    public void goToCategoryCopy() {
        Fragment categoryCopy = new Category();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.flFragmentContainer, categoryCopy).commit();
    }



    private void populateScoreModel(ScoreModel scoreModel){
        int esScore = getIntent().getIntExtra("esScore", 0);
        int hsScore = getIntent().getIntExtra("hsScore", 0);
        int hbScore = getIntent().getIntExtra("hbScore", 0);
        int ebScore = getIntent().getIntExtra("ebScore", 0);
        int totalScore = getIntent().getIntExtra("totalScore", 0);
        scoreModel.setEsScore(esScore);
        scoreModel.setHsScore(hsScore);
        scoreModel.setHbScore(hbScore);
        scoreModel.setEbScore(ebScore);
        scoreModel.setTotalScore(totalScore);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_game);

        int progress = getIntent().getIntExtra("progress", 100);
        boolean isMuted = getIntent().getBooleanExtra("muted", false);

        BGMSettings BGMSettings = new ViewModelProvider(this).get(BGMSettings.class);
        BGMSettings.setCurrentSeekbarProgress(progress);
        BGMSettings.setMuted(isMuted);

        ScoreModel scoreModel = new ViewModelProvider(this).get(ScoreModel.class);
        populateScoreModel(scoreModel);

        Log.d("GameActivity", "Progress: " + progress);
        Log.d("GameActivity", "Muted: " + isMuted);

        goToCategoryCopy();
    }
}