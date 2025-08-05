package com.example.pic_4_words_java.Game;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.pic_4_words_java.Model.DifficultyModel;
import com.example.pic_4_words_java.R;

public class Difficulty extends Fragment {

    private DifficultyModel difficultyModel;

    public void goToQuestionFragment(){
        Fragment questionFragment = new QuestionTemplate() ;
        FragmentTransaction diffFragment= requireActivity().getSupportFragmentManager().beginTransaction();
        diffFragment.replace(R.id.flFragmentContainer, questionFragment).addToBackStack(null).commit();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        difficultyModel = new ViewModelProvider(requireActivity()).get(DifficultyModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_difficulty, container, false);


        //Button clicks
        Button easyBtn = view.findViewById(R.id.easyDifficultyBtn);
        easyBtn.setOnClickListener(v -> {
            difficultyModel.setDifficultyChosen("Easy");
            goToQuestionFragment();
        });
        Button hardBtn = view.findViewById(R.id.hardDifficultyBtn);
        hardBtn.setOnClickListener(v -> {
            difficultyModel.setDifficultyChosen("Hard");
            goToQuestionFragment();

        });


        return view;
    }
}
