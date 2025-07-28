package com.example.pic_4_words_java.Game.Fragments;

import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.pic_4_words_java.Game.Fragments.Model.MainModel;
import com.example.pic_4_words_java.Game.Fragments.Model.ScoreViewModel;
import com.example.pic_4_words_java.R;

import java.util.Objects;

public class Score extends Fragment {

    private ScoreViewModel scoreModel;


    public void setAndValidateScore(ScoreViewModel scoreModel, TextView tvScoreNumber){
        int score = MainModel.getScore();


        if(Objects.equals(CategoryFragment.getCategoryChosen(), "Superhero")){
            if(Objects.equals(DifficultyFragment.getDifficutlyChosen(), "Easy")){
                scoreModel.setEsScore(score);
                tvScoreNumber.setText(String.format("Score: %s", scoreModel.getEsScore()));
            }else{

                scoreModel.setHsScore(score);
                tvScoreNumber.setText(String.format("Score: %s", scoreModel.getHsScore()));
            }
        }



        if(Objects.equals(CategoryFragment.getCategoryChosen(), "Brainrot")){
            if(Objects.equals(DifficultyFragment.getDifficutlyChosen(), "Easy")){

                scoreModel.setEbScore(score);
                tvScoreNumber.setText(String.format("Score: %s", scoreModel.getEbScore()));
            }else{

                scoreModel.setHbScore(score);
                tvScoreNumber.setText(String.format("Score: %s", scoreModel.getHbScore()));
            }
        }


        MainModel.setScore(0);
    }



    //idk what this one is for
    public static Score newInstance() {
        return new Score();
    }















    //main code
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        scoreModel = new ViewModelProvider(requireActivity()).get(ScoreViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_score, container, false);
        TextView tvScoreNumber = view.findViewById(R.id.scoreNumber);

        setAndValidateScore(scoreModel, tvScoreNumber);


        //Switches to new instance of Category Fragment
        //Main issue of code
        ImageButton returnCategoryBtn = view.findViewById(R.id.returnToCategoryBtn);
        returnCategoryBtn.setOnClickListener(v -> {
            FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.flFragmentContainer, new CategoryFragment());
            transaction.commit();
        });

        return view;
    }


}