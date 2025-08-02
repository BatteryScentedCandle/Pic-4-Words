package com.example.pic_4_words_java.Game;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.pic_4_words_java.Game.Fragments.Copies.CategoryCopy;
import com.example.pic_4_words_java.Game.Fragments.Copies.DifficultyCopy;
import com.example.pic_4_words_java.R;

public class Game extends AppCompatActivity {

    public void goToCategoryCopy() {
        Fragment categoryCopy = new CategoryCopy();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.flFragmentContainer, categoryCopy).commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_game);

        goToCategoryCopy();
    }
}