package com.example.pic_4_words_java;

import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import com.example.pic_4_words_java.MainMenu.MainMenuFragmentStateAdapter;

public class MainActivity extends AppCompatActivity {







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
//        CustomMediaPlayer.getInstance().mediaPlayer.setVolume(volume, volume);

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        CustomMediaPlayer.getInstance().stopAudio();
        CustomMediaPlayer.getInstance().mediaPlayer.release();
    }





}