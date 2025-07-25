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
import android.widget.TextView;

import com.example.pic_4_words_java.Game.BrainrotQuestions.EasyBrainrotFragment;
import com.example.pic_4_words_java.Game.BrainrotQuestions.HardBrainrotFragment;
import com.example.pic_4_words_java.Game.SuperheroQuestions.EasySuperheroFragment;
import com.example.pic_4_words_java.Game.SuperheroQuestions.HardSuperheroFragment;
import com.example.pic_4_words_java.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Objects;

public class CategoryFragment extends Fragment {

    public CategoryFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);

        TextView tvDifficultyChosen = view.findViewById(R.id.tvDifficultyChosen);
        tvDifficultyChosen.setText(DifficultyFragment.getDifficutlyChosen());

        Button superheroBtn = view.findViewById(R.id.superheroCategoryBtn);
        superheroBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Objects.equals(DifficultyFragment.getDifficutlyChosen(), "Easy")){

                    Fragment easySuperheroFrag = new EasySuperheroFragment();
                    FragmentTransaction fm= requireActivity().getSupportFragmentManager().beginTransaction();
                    fm.replace(R.id.flFragmentContainer, easySuperheroFrag).commit();
                }


                if(Objects.equals(DifficultyFragment.getDifficutlyChosen(), "Hard")){

                    Fragment hardSuperheroFrag = new HardSuperheroFragment();
                    FragmentTransaction fm= requireActivity().getSupportFragmentManager().beginTransaction();
                    fm.replace(R.id.flFragmentContainer, hardSuperheroFrag).commit();
                }
            }
        });

        Button brainrotBtn = view.findViewById(R.id.brainrotCategoryBtn);
        brainrotBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Objects.equals(DifficultyFragment.getDifficutlyChosen(), "Easy")){

                    Fragment easyBrainrotFragment = new EasyBrainrotFragment();
                    FragmentTransaction fm= requireActivity().getSupportFragmentManager().beginTransaction();
                    fm.replace(R.id.flFragmentContainer, easyBrainrotFragment).commit();
                }


                if(Objects.equals(DifficultyFragment.getDifficutlyChosen(), "Hard")){

                    Fragment hardBrainrotFragment = new HardBrainrotFragment();
                    FragmentTransaction fm= requireActivity().getSupportFragmentManager().beginTransaction();
                    fm.replace(R.id.flFragmentContainer, hardBrainrotFragment).commit();
                }
            }
        });

        FloatingActionButton goBackBtn = view.findViewById(R.id.backButton);
        goBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment difficultyFragment = new DifficultyFragment();
                FragmentTransaction fm = requireActivity().getSupportFragmentManager().beginTransaction();
                fm.replace(R.id.flFragmentContainer, difficultyFragment).commit();
            }
        });

        return view;
    }

}

