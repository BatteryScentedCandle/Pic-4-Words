package com.example.pic_4_words_java.Game;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
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

import com.example.pic_4_words_java.CustomMediaPlayer;
import com.example.pic_4_words_java.Model.CategoryModel;
import com.example.pic_4_words_java.Model.DifficultyModel;
import com.example.pic_4_words_java.Model.QuestionAnswerModel;
import com.example.pic_4_words_java.Model.ScoreModel;
import com.example.pic_4_words_java.MainActivity;
import com.example.pic_4_words_java.Model.VolumeDetailsCopy;
import com.example.pic_4_words_java.R;

public class Score extends Fragment {

    private ScoreModel scoreModel;
    private QuestionAnswerModel qaModel;
    private CategoryModel categoryModel;
    private DifficultyModel difficultyModel;
    private String categoryChosen;
    private String difficultyChosen;



    //helper methods
    private String getScoreField(String categoryChosen, String difficultyChosen){

        //returns which score field (category + difficulty) to update
        if(categoryChosen.equalsIgnoreCase("Superhero")){
            return difficultyChosen.equalsIgnoreCase("Easy") ? "esScore" : "hsScore";
        }else if(categoryChosen.equalsIgnoreCase("Brainrot")){
            return difficultyChosen.equalsIgnoreCase("Easy") ? "ebScore" : "hbScore";
        }

        //just in case
        return "";
    }


    private void setScoreFieldValue(ScoreModel scoreModel, String scoreField, int score){
        if(scoreField.equalsIgnoreCase("esScore")) scoreModel.setEsScore(score);
        if(scoreField.equalsIgnoreCase("hsScore")) scoreModel.setHsScore(score);
        if(scoreField.equalsIgnoreCase("ebScore")) scoreModel.setEbScore(score);
        if(scoreField.equalsIgnoreCase("hbScore")) scoreModel.setHbScore(score);
    }

    private void setAndValidateScore(TextView tvScoreNumber){
        int score = qaModel.getScore();

        String scoreField = getScoreField(categoryChosen, difficultyChosen);
        setScoreFieldValue(scoreModel, scoreField, score);

        scoreModel.setTotalScore(scoreModel.getTotalScore() + score);
        tvScoreNumber.setText(String.format("Score: %s", score));

        qaModel.setScore(0);
    }


    public void clearModel(){
        qaModel = new QuestionAnswerModel();
    }


    public void reset(){
        qaModel.resetQuestionCount();

        clearModel();
        categoryModel.setCategoryChosen("");
        difficultyModel.setDifficultyChosen("");

        requireActivity().getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    public void moveToCategory(){
        CustomMediaPlayer.getInstance().stopAudio();
        CustomMediaPlayer.getInstance().playLoopingAudio(this.getContext(), R.raw.m_b4);
        FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.flFragmentContainer, new Category());
        transaction.commit();
    }

    public void moveToMainMenu(){
        CustomMediaPlayer.getInstance().stopAudio();
        Intent intent = new Intent(requireActivity(), MainActivity.class);
        startActivity(intent);
    }

















    //main code
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        qaModel = new ViewModelProvider(requireActivity()).get(QuestionAnswerModel.class);
        scoreModel = new ViewModelProvider(requireActivity()).get(ScoreModel.class);
        categoryModel = new ViewModelProvider(requireActivity()).get(CategoryModel.class);
        difficultyModel = new ViewModelProvider(requireActivity()).get(DifficultyModel.class);

        categoryChosen = categoryModel.getCategoryChosen();
        difficultyChosen = difficultyModel.getDifficultyChosen();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_score, container, false);
        TextView tvScoreNumber = view.findViewById(R.id.scoreNumber);

        setAndValidateScore(tvScoreNumber);
        Log.d("Score", "esScore: " + scoreModel.getEsScore());
        Log.d("Score", "hsScore: " + scoreModel.getHsScore());
        Log.d("Score", "ebScore: " + scoreModel.getEbScore());
        Log.d("Score", "hbScore: " + scoreModel.getHbScore());
        Log.d("Score", "Total Score: " + scoreModel.getTotalScore());


        //Switches to new instance of Category Fragment
        //Main issue of code
        ImageButton returnCategoryBtn = view.findViewById(R.id.returnToCategoryBtn);
        returnCategoryBtn.setOnClickListener(v -> {
            reset();
            moveToCategory();
        });

        ImageButton mainMenuBtn = view.findViewById(R.id.returnToMainMenuBtn);
        mainMenuBtn.setOnClickListener(v -> {
            reset();
            moveToMainMenu();
        });

        ImageButton shareBtn = view.findViewById(R.id.shareBtn);
        //enter logic here

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        VolumeDetailsCopy volumeDetails = new ViewModelProvider(requireActivity()).get(VolumeDetailsCopy.class);
        CustomMediaPlayer.getInstance().playAudio(this.getContext(), R.raw.score_audio);
        CustomMediaPlayer.getInstance().setVolume(volumeDetails.getCurrentSeekbarProgress());
    }
}