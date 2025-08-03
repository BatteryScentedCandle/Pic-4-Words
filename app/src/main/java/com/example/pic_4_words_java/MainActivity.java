package com.example.pic_4_words_java;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import com.example.pic_4_words_java.MainMenu.MainMenuFragmentStateAdapter;
import com.example.pic_4_words_java.MainMenu.SettingsFragment;

public class MainActivity extends AppCompatActivity {


    MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewPager2 viewPager = findViewById(R.id.viewpager);
        MainMenuFragmentStateAdapter adapter = new MainMenuFragmentStateAdapter(this);
        viewPager.setAdapter(adapter);

        player = MediaPlayer.create(MainActivity.this, R.raw.m_b4);
        player.setLooping(true);
        player.setVolume(100, 100);
        player.start();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        player.stop();
        player.release();
    }




    //these functions are not used
    public int onStartCommand(Intent intent, int flags, int startId) {
        player.start();
        return startId;
    }

    public void onStart(Intent intent, int startId) {
    }

}