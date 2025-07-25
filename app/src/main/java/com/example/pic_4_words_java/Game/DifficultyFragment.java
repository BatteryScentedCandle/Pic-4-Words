package com.example.pic_4_words_java.Game;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.pic_4_words_java.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DifficultyFragment extends Fragment {

    private static String difficutlyChosen;

    public static String getDifficutlyChosen() {
        return difficutlyChosen;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_difficulty, container, false);

        Button easyBtn = view.findViewById(R.id.easyBtn);
        easyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                difficutlyChosen = "Easy";

                Fragment categoryFragment = new CategoryFragment();
                FragmentTransaction diffFragment= requireActivity().getSupportFragmentManager().beginTransaction();
                diffFragment.replace(R.id.flFragmentContainer, categoryFragment).commit();

            }
        });


        Button hardBtn = view.findViewById(R.id.hardBtn);
        hardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                difficutlyChosen = "Hard";

                Fragment categoryFragment = new CategoryFragment();
                FragmentTransaction diffFragment= requireActivity().getSupportFragmentManager().beginTransaction();
                diffFragment.replace(R.id.flFragmentContainer, categoryFragment).commit();
            }
        });


        return view;
    }
}