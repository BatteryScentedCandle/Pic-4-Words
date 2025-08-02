package com.example.pic_4_words_java.Game.Fragments;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.pic_4_words_java.Game.Fragments.Copies.CategoryCopy;
import com.example.pic_4_words_java.Game.Fragments.Copies.DifficultyCopy;
import com.example.pic_4_words_java.Game.Fragments.Model.QuestionAnswerModel;
import com.example.pic_4_words_java.Game.Fragments.Model.ScoreViewModel;
import com.example.pic_4_words_java.R;

import java.util.Objects;

public class Score extends Fragment {

    private ScoreViewModel scoreModel;
    private QuestionAnswerModel qaModel;
    private String categoryChosen;
    private String difficultyChosen;


    public void setAndValidateScore(TextView tvScoreNumber){
        int score = qaModel.getScore();
        Log.d("Some Score", "Score: " + score);




        if(categoryChosen.equalsIgnoreCase("Superhero")){
            if(difficultyChosen.equalsIgnoreCase("Easy")){
                scoreModel.setEsScore(score);

                Log.d("EsScore", "Score: " + scoreModel.getEsScore());

                scoreModel.setTotalScore(scoreModel.getTotalScore() + scoreModel.getEsScore());
                tvScoreNumber.setText(String.format("Score: %s", scoreModel.getEsScore()));
            }else{

                scoreModel.setHsScore(score);
                scoreModel.setTotalScore(scoreModel.getTotalScore() + scoreModel.getHsScore());
                tvScoreNumber.setText(String.format("Score: %s", scoreModel.getHsScore()));
            }
        }



        if(Objects.equals(categoryChosen, "Brainrot")){
            if(Objects.equals(difficultyChosen, "Easy")){

                scoreModel.setEbScore(score);
                tvScoreNumber.setText(String.format("Score: %s", scoreModel.getEbScore()));
                scoreModel.setTotalScore(scoreModel.getTotalScore() + scoreModel.getEbScore());
            }else{

                scoreModel.setHbScore(score);
                scoreModel.setTotalScore(scoreModel.getTotalScore() + scoreModel.getHbScore());
                tvScoreNumber.setText(String.format("Score: %s", scoreModel.getHbScore()));
            }
        }


        qaModel.setScore(0);
    }


    public void clearModel(){
        qaModel = new QuestionAnswerModel();
    }

















    //main code
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        qaModel = new ViewModelProvider(requireActivity()).get(QuestionAnswerModel.class);
        scoreModel = new ViewModelProvider(requireActivity()).get(ScoreViewModel.class);

        categoryChosen = CategoryCopy.getCategoryChosen();
        difficultyChosen = DifficultyCopy.getDifficultyChosen();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_score, container, false);
        TextView tvScoreNumber = view.findViewById(R.id.scoreNumber);

        setAndValidateScore(tvScoreNumber);


        //Switches to new instance of Category Fragment
        //Main issue of code
        ImageButton returnCategoryBtn = view.findViewById(R.id.returnToCategoryBtn);
        returnCategoryBtn.setOnClickListener(v -> {
            qaModel.resetQuestionCount();

            clearModel();
            CategoryCopy.setCategoryChosen(null);
            DifficultyCopy.setDifficultyChosen(null);

            requireActivity().getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);

            FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.flFragmentContainer, new CategoryCopy());
            transaction.commit();
        });

        return view;
    }


}