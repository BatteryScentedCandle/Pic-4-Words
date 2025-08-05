package com.example.pic_4_words_java;



import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;
import com.example.pic_4_words_java.MainMenu.MainMenuFragmentStateAdapter;
import com.example.pic_4_words_java.Model.BGMSettings;
import com.example.pic_4_words_java.Model.ScoreModel;

public class MainActivity extends AppCompatActivity {

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

        Log.d("MainActivity", "esScore: " + esScore);
        Log.d("MainActivity", "hsScore: " + hsScore);
        Log.d("MainActivity", "hbScore: " + hbScore);
        Log.d("MainActivity", "ebScore: " + ebScore);
        Log.d("MainActivity", "totalScore: " + totalScore);
    }

    private void populateBgmSettings(BGMSettings bgmSettings){
        int progress = getIntent().getIntExtra("progress", 100);
        boolean isMuted = getIntent().getBooleanExtra("muted", false);
        bgmSettings.setCurrentSeekbarProgress(progress);
        bgmSettings.setMuted(isMuted);
        Log.d("MainActivity", "Progress: " + progress);
        Log.d("MainActivity", "Muted: " + isMuted);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewPager2 viewPager = findViewById(R.id.viewpager);
        MainMenuFragmentStateAdapter adapter = new MainMenuFragmentStateAdapter(this);
        viewPager.setAdapter(adapter);

        ScoreModel scoreModel = new ViewModelProvider(this).get(ScoreModel.class);
        populateScoreModel(scoreModel);

        BGMSettings bgmSettings = new ViewModelProvider(this).get(BGMSettings.class);
        populateBgmSettings(bgmSettings);

        com.example.pic_4_words_java.BgmManager.getInstance().stopAudio();
        com.example.pic_4_words_java.BgmManager.getInstance().playLoopingAudio(MainActivity.this, R.raw.m_b4);
        if(bgmSettings.getCurrentSeekbarProgress() != 100 || bgmSettings.getMuted() == false){
            com.example.pic_4_words_java.BgmManager.getInstance().setVolume(bgmSettings.getCurrentSeekbarProgress());
        } else if (bgmSettings.getCurrentSeekbarProgress() != 100 && bgmSettings.getMuted() == true) {
            com.example.pic_4_words_java.BgmManager.getInstance().setVolume(0);
        }else{
            com.example.pic_4_words_java.BgmManager.getInstance().setVolume(100);
        }

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        com.example.pic_4_words_java.BgmManager.getInstance().stopAudio();
        com.example.pic_4_words_java.BgmManager.getInstance().mediaPlayer.release();
    }
}