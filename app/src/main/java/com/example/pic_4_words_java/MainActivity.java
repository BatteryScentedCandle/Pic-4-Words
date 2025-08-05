package com.example.pic_4_words_java;



import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;
import com.example.pic_4_words_java.MainMenu.MainMenuFragmentStateAdapter;
import com.example.pic_4_words_java.Model.ScoreModelCopy;

public class MainActivity extends AppCompatActivity {

    private void populateScoreModelCopy(ScoreModelCopy scoreModelCopy){
        int esScore = getIntent().getIntExtra("esScore", 0);
        int hsScore = getIntent().getIntExtra("hsScore", 0);
        int hbScore = getIntent().getIntExtra("hbScore", 0);
        int ebScore = getIntent().getIntExtra("ebScore", 0);
        int totalScore = getIntent().getIntExtra("totalScore", 0);
        scoreModelCopy.setEsScore(esScore);
        scoreModelCopy.setHsScore(hsScore);
        scoreModelCopy.setHbScore(hbScore);
        scoreModelCopy.setEbScore(ebScore);
        scoreModelCopy.setTotalScore(totalScore);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewPager2 viewPager = findViewById(R.id.viewpager);
        MainMenuFragmentStateAdapter adapter = new MainMenuFragmentStateAdapter(this);
        viewPager.setAdapter(adapter);


        CustomMediaPlayer.getInstance().stopAudio();
        CustomMediaPlayer.getInstance().playLoopingAudio(MainActivity.this, R.raw.m_b4);
        CustomMediaPlayer.getInstance().setVolume(100);

        ScoreModelCopy scoreModelCopy = new ViewModelProvider(this).get(ScoreModelCopy.class);
        populateScoreModelCopy(scoreModelCopy);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        CustomMediaPlayer.getInstance().stopAudio();
        CustomMediaPlayer.getInstance().mediaPlayer.release();
    }
}