package com.example.pic_4_words_java.Game.Fragments;

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

public class DifficultyFragment extends Fragment {
    private static String difficutlyChosen;
    public static String getDifficutlyChosen() {
        return difficutlyChosen;
    }


    public void switchToCategoryFrag(){
        Fragment categoryFragment = new CategoryFragment();
        FragmentTransaction diffFragment= requireActivity().getSupportFragmentManager().beginTransaction();
        diffFragment.replace(R.id.flFragmentContainer, categoryFragment).addToBackStack(null).commit();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //resets difficulty
        difficutlyChosen = null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_difficulty, container, false);


        //Button clicks
        Button easyBtn = view.findViewById(R.id.easyBtn);
        easyBtn.setOnClickListener(v -> {

            difficutlyChosen = "Easy";
            switchToCategoryFrag();

        });
        Button hardBtn = view.findViewById(R.id.hardBtn);
        hardBtn.setOnClickListener(v -> {

            difficutlyChosen = "Hard";
            switchToCategoryFrag();

        });


        return view;
    }
}