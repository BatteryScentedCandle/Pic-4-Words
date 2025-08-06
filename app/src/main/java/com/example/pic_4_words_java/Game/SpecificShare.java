package com.example.pic_4_words_java.Game;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.pic_4_words_java.Model.CategoryModel;
import com.example.pic_4_words_java.Model.DifficultyModel;
import com.example.pic_4_words_java.Model.QuestionAnswerModel;
import com.example.pic_4_words_java.Model.ScoreModel;
import com.example.pic_4_words_java.R;

public class SpecificShare extends Fragment {
    private QuestionAnswerModel qaModel;
    private CategoryModel categoryModel;
    private DifficultyModel difficultyModel;


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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        qaModel = new ViewModelProvider(requireActivity()).get(QuestionAnswerModel.class);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_specific_share, container, false);
        ScoreModel scoreModel = new ViewModelProvider(requireActivity()).get(ScoreModel.class);
        categoryModel = new ViewModelProvider(requireActivity()).get(CategoryModel.class);
        difficultyModel = new ViewModelProvider(requireActivity()).get(DifficultyModel.class);

        TextView tvCategoryDifficulty = view.findViewById(R.id.tvCategoryDifficulty);
        TextView tvScore = view.findViewById(R.id.tvScore);

        Log.d("Category", "Category Chosen: " + categoryModel.getCategoryChosen());
        Log.d("Difficulty", "Difficulty Chosen: " + difficultyModel.getDifficultyChosen());

        if(categoryModel.getCategoryChosen().equalsIgnoreCase("superhero")){
            if(difficultyModel.getDifficultyChosen().equalsIgnoreCase("easy")){
                tvCategoryDifficulty.setText("Superhero - Easy");
                tvScore.setText(String.valueOf(scoreModel.getEsScore()));
            }
            else{
                tvCategoryDifficulty.setText("Superhero - Hard");
                tvScore.setText(String.valueOf(scoreModel.getHsScore()));
            }
        }else if(categoryModel.getCategoryChosen().equalsIgnoreCase("brainrot")){
            if(difficultyModel.getDifficultyChosen().equalsIgnoreCase("easy")){
                tvCategoryDifficulty.setText("Brainrot - Easy");
                tvScore.setText(String.valueOf(scoreModel.getEbScore()));
            }
            else{
                tvCategoryDifficulty.setText("Brainrot - Hard");
                tvScore.setText(String.valueOf(scoreModel.getHbScore()));
            }
        }


        ImageButton closeBtn = view.findViewById(R.id.closeButton);
        closeBtn.setOnClickListener(v -> {
            reset();
            requireActivity().getSupportFragmentManager().popBackStack();
        });
        return view;
    }
}