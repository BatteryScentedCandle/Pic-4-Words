package com.example.pic_4_words_java.Game.Fragments;

import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.pic_4_words_java.Game.Fragments.Model.MainModel;
import com.example.pic_4_words_java.Game.Fragments.Model.QuestionTemplateViewModel;
import com.example.pic_4_words_java.R;

public class QuestionTemplate extends Fragment {

    private QuestionTemplateViewModel questionModel;
    private MainModel brainrotModel;

    private int baseScore = 100;
    private int newScore = baseScore;


    public static QuestionTemplate newInstance() {
        return new QuestionTemplate();
    }




    private void checkAnswer(TextView outputResult, EditText userInput, ImageButton nextQuestionBtn){

        userInput.setOnEditorActionListener((v, actionId, event) -> {

            if(event != null && event.getAction() == KeyEvent.ACTION_DOWN){
                if(event.getKeyCode() == KeyEvent.KEYCODE_ENTER){
                    if(userInput.getText().toString().equalsIgnoreCase(questionModel.getAnswer())){
                        brainrotModel.setScore(brainrotModel.getScore() + newScore);

                        userInput.setEnabled(false);

                        outputResult.setText(R.string.correctAnswer);
                        outputResult.setVisibility(View.VISIBLE);

                        nextQuestionBtn.setVisibility(View.VISIBLE);
                        nextQuestionBtn.setClickable(true);


                        Log.d("Replace", "Question Score: " + newScore);
                        Log.d("Total Replace", "Total Score: " + brainrotModel.getScore());
                    }
                    else{
                        if(newScore != 50){
                            newScore -= 10;
                        }
                        userInput.setText("");

                        outputResult.setText(R.string.wrongAnswer);
                        outputResult.setVisibility(View.VISIBLE);
                    }return true;
                }
            }return false;

        });
    }


    private void moveToNextQuestion(ImageButton nextQuestionBtn){
        nextQuestionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(brainrotModel.getCurrentQuestionCount() <= 3 ){
                    brainrotModel.setCurrentQuestionCount(brainrotModel.getCurrentQuestionCount() + 1);
                }
                FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.flFragmentContainer, new CategoryFragment());
                transaction.commit();
            }
        });
    }




    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        questionModel = new ViewModelProvider(requireActivity()).get(QuestionTemplateViewModel.class);
        brainrotModel = new ViewModelProvider(requireActivity()).get(MainModel.class);
        Log.d("CurrentQuestionCount", "Question Frag Current Question: " + brainrotModel.getCurrentQuestionCount());
        Log.d("Answer", "Answer in View Model: " + questionModel.getAnswer());
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.category_level_generic, container, false);

        TextView tvDifficultyCategory = view.findViewById(R.id.tvDifficultyCategory);
        tvDifficultyCategory.setText(questionModel.getTvDifficultyCategoryContent());

        TextView tvLevel = view.findViewById(R.id.tvLevel);
        tvLevel.setText(questionModel.getTvLevelContent());

        ImageView topLeft = view.findViewById(R.id.topLeft);
        ImageView topRight = view.findViewById(R.id.topRight);
        ImageView botLeft = view.findViewById(R.id.botLeft);
        ImageView botRight = view.findViewById(R.id.botRight);


        Glide.with(this).load(questionModel.getImageURL(0)).placeholder(R.drawable.ic_launcher_background).into(topLeft);
        Glide.with(this).load(questionModel.getImageURL(1)).placeholder(R.drawable.ic_launcher_foreground).into(topRight);
        Glide.with(this).load(questionModel.getImageURL(2)).placeholder(R.drawable.ic_launcher_background).into(botLeft);
        Glide.with(this).load(questionModel.getImageURL(3)).placeholder(R.drawable.ic_launcher_foreground).into(botRight);

        TextView outputResult = view.findViewById(R.id.outputResult);
        EditText userInput = view.findViewById(R.id.easyBrainrotUserInput);
        ImageButton nextQuestionBtn = view.findViewById(R.id.nextQuestionBtn);
        checkAnswer(outputResult, userInput, nextQuestionBtn);
        moveToNextQuestion(nextQuestionBtn);

        return view;
    }


}