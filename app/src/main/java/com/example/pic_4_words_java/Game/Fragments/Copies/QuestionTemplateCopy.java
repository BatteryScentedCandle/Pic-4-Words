package com.example.pic_4_words_java.Game.Fragments.Copies;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.pic_4_words_java.Game.Fragments.Model.QuestionAnswerModel;
import com.example.pic_4_words_java.Game.Fragments.Model.QuestionTemplateViewModel;
import com.example.pic_4_words_java.Game.Fragments.QuestionAnswerBuilder;
import com.example.pic_4_words_java.Game.Fragments.Score;
import com.example.pic_4_words_java.R;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;

public class QuestionTemplateCopy extends Fragment {

    private QuestionTemplateViewModel questionModel;
    private QuestionAnswerModel qaModel;

    private final int baseScore = 100;
    private int newScore = baseScore;

    private String categoryChosen;
    private String difficultyChosen;

    private List<String> answers;
    private LinkedHashMap<String, List<String>> allImages;
    private List<String> questionImages;
    private List<String> allImageKeys;
    private int currentQuestion;





    //helper functions

    public void handleCorrectAnswer(TextView outputResult, EditText userInput, ImageButton nextQuestionBtn){

        //update score
        qaModel.setScore(qaModel.getScore() + newScore);


        //disable input
        userInput.setEnabled(false);


        //make invisible palettes appear
        outputResult.setText(R.string.correctAnswer);
        outputResult.setVisibility(View.VISIBLE);

        nextQuestionBtn.setVisibility(View.VISIBLE);
        nextQuestionBtn.setClickable(true);

        moveToNextFragment(nextQuestionBtn);


        //debugging
        Log.d("New Score", "Question Score: " + newScore);
        Log.d("Total Score", "Total Score: " + qaModel.getScore());
    }



    public void handleWrongAnswer(TextView outputResult, EditText userInput){
        if(newScore != 50){
            newScore -= 10;
        }
        userInput.setText("");

        outputResult.setText(R.string.wrongAnswer);
        outputResult.setVisibility(View.VISIBLE);
    }

    public void isCorrectAnswer(TextView outputResult, EditText userInput, ImageButton nextQuestionBtn){
        String userInputAnswer = userInput.getText().toString();
        String currentAnswer = answers.get(currentQuestion);


        if(userInputAnswer.equalsIgnoreCase(currentAnswer) && currentAnswer != null){
            handleCorrectAnswer(outputResult, userInput, nextQuestionBtn);
        }
        else if (currentAnswer != null){
            handleWrongAnswer( outputResult, userInput);
        }else{
            Log.d("Current Answer", "Answer is null");
        }
    }



    public void checkAnswer(TextView outputResult, EditText userInput, ImageButton nextQuestionBtn){
//        int currentQuestion = qaModel.getCurrentQuestionCount();

        userInput.setOnEditorActionListener((v, actionId, event) -> {

            if(event != null && event.getAction() == KeyEvent.ACTION_DOWN){
                if(event.getKeyCode() == KeyEvent.KEYCODE_ENTER){

                    isCorrectAnswer(outputResult, userInput, nextQuestionBtn);

                    return true;
                }
            }return false;

        });
    }







    private void moveToNextFragment(ImageButton nextQuestionBtn){
        nextQuestionBtn.setOnClickListener(v -> {
            if(qaModel.getCurrentQuestionCount() < 2){
                qaModel.incrementQuestionCount();
                FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.flFragmentContainer, new QuestionTemplateCopy());
                transaction.commit();
            }else{
                FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.flFragmentContainer, new Score());
                transaction.commit();
            }

        });
    }





    public void logQuestionImages(List<String> questionImages){
        if(questionImages != null){
            Log.d("Question Images", "Images set");
        }else{
            Log.d("Question Images", "Question Images are null");
        }
    }

    public void populateAnswerImages() {
        answers = new ArrayList<>(qaModel.getAnswer());
        allImages = new LinkedHashMap<>(qaModel.getImages());
        allImageKeys = new ArrayList<>(allImages.keySet());
    }















    //handlers

    public void currentQuestionHandler(int question, ImageButton nextQuestionBtn) {
        try {
            if (allImages != null && allImageKeys != null && question >= 0 && question < allImageKeys.size()) {
                questionImages = allImages.get(allImageKeys.get(question));
                if (questionImages == null) {
                    Log.e("CurrentQuestion", "No images found for question: " + question);
                    // Handle the error case
                }
            } else {
                Log.e("CurrentQuestion", "Invalid question index or null data. Question: " + question + 
                      ", allImageKeys: " + (allImageKeys != null ? allImageKeys.size() : "null") +
                      ", allImages: " + (allImages != null ? allImages.size() : "null"));
                moveToNextFragment(nextQuestionBtn);
            }
        } catch (Exception e) {
            Log.e("CurrentQuestion", "Error in currentQuestionHandler: " + e.getMessage());
            e.printStackTrace();
            moveToNextFragment(nextQuestionBtn);
        }
    }

    public void handlerLogic(ImageButton nextQuestionBtn){
        if (qaModel != null) {

            populateAnswerImages();

            //gets image list of key at specific question number
            currentQuestionHandler(currentQuestion, nextQuestionBtn);

            //debugging
            logQuestionImages(questionImages);

        }else{
            Log.d("Model Error","Model Error: No answers initialized in model");
        }
    }


    public void easyBrainrotQuestionHandler(ImageButton nextQuestionBtn){

        if(QuestionAnswerBuilder.easyBrainrotQA() != null){

            QuestionAnswerModel builder = QuestionAnswerBuilder.easyBrainrotQA();
            qaModel.setAnswer(builder.getAnswer());
            qaModel.setImages(builder.getImages());
        }else{
            Log.d("Model Error","Model Error: No answers initialized in model");
            return;
        }

        handlerLogic(nextQuestionBtn);

    }



    //still empty
    public void hardBrainrotQuestionHandler(ImageButton nextQuestionBtn){

        if(QuestionAnswerBuilder.hardBrainrot() != null){

            QuestionAnswerModel builder = QuestionAnswerBuilder.hardBrainrot();
            qaModel.setAnswer(builder.getAnswer());
            qaModel.setImages(builder.getImages());
        }else{
            Log.d("Model Error","Model Error: No answers initialized in model");
            return;
        }

        handlerLogic(nextQuestionBtn);

    }

    public void brainrotHandler(QuestionTemplateViewModel questionModel, ImageButton nextQuestionBtn){

        if(Objects.equals(difficultyChosen, "Easy")){
            Log.d("Difficulty", "Difficulty chosen: " + difficultyChosen);
            questionModel.setTvDifficultyCategoryContent("Easy - Brainrot");
            easyBrainrotQuestionHandler(nextQuestionBtn);

        }

        if(Objects.equals(difficultyChosen, "Hard")){
            Log.d("Difficulty", "Difficulty chosen: " + difficultyChosen);
            questionModel.setTvDifficultyCategoryContent("Hard - Brainrot");
            hardBrainrotQuestionHandler(nextQuestionBtn);
        }

    }





    public void easySuperheroQuestionHandler(ImageButton nextQuestionBtn) {
        try {
            // Get the question data from the builder
            QuestionAnswerModel builder = QuestionAnswerBuilder.easySuperhero();
            
            // Set the answer and images
            if (builder != null && builder.getAnswer() != null && builder.getImages() != null) {
                qaModel.setAnswer(builder.getAnswer());
                qaModel.setImages(builder.getImages());
                handlerLogic(nextQuestionBtn);
            } else {
                Log.e("EasySuperhero", "Failed to load question data");
                // Handle the error case, maybe show a message to the user
            }
        } catch (Exception e) {
            Log.e("EasySuperhero", "Error in easySuperheroQuestionHandler: " + e.getMessage());
            e.printStackTrace();
            // Handle the error case, maybe show a message to the user
        }
    }



    //still empty
    public void hardSuperheroQuestionHandler(ImageButton nextQuestionBtn){

        qaModel = new QuestionAnswerModel();
        if(QuestionAnswerBuilder.hardSuperhero() != null){

            QuestionAnswerModel builder = QuestionAnswerBuilder.hardSuperhero();
            qaModel.setAnswer(builder.getAnswer());
            qaModel.setImages(builder.getImages());
        }else{
            Log.d("Model Error","Model Error: No answers initialized in model");
            return;
        }

        handlerLogic(nextQuestionBtn);

    }

    public void superheroHandler(QuestionTemplateViewModel questionModel, ImageButton nextQuestionBtn){

        if(Objects.equals(difficultyChosen, "Easy")){
            Log.d("Difficulty", "Difficulty chosen: " + difficultyChosen);
            questionModel.setTvDifficultyCategoryContent("Easy - Superhero");
            easySuperheroQuestionHandler(nextQuestionBtn);

        }

        if(Objects.equals(difficultyChosen, "Hard")){
            Log.d("Difficulty", "Difficulty chosen: " + difficultyChosen);
            questionModel.setTvDifficultyCategoryContent("Hard - Superhero");
            hardSuperheroQuestionHandler(   nextQuestionBtn);
        }

    }















    //main code
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       //initialize models
        questionModel = new ViewModelProvider(requireActivity()).get(QuestionTemplateViewModel.class);
        qaModel = new QuestionAnswerModel();  // Initialize qaModel

        categoryChosen = CategoryCopy.getCategoryChosen();
        difficultyChosen = DifficultyCopy.getDifficultyChosen();
        currentQuestion = qaModel.getCurrentQuestionCount();



        //add code here for superhero


        //debug
        Log.d("CurrentQuestionCount", "Question Frag Current Question: " + qaModel.getCurrentQuestionCount());
    }

    //very self-explanatory code
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

        TextView outputResult = view.findViewById(R.id.outputResult);
        EditText userInput = view.findViewById(R.id.easyBrainrotUserInput);
        ImageButton nextQuestionBtn = view.findViewById(R.id.nextQuestionBtn);


        if(categoryChosen != null && categoryChosen.equalsIgnoreCase("brainrot")){
            brainrotHandler(questionModel, nextQuestionBtn);
            checkAnswer(outputResult, userInput, nextQuestionBtn);
        }
        if(categoryChosen != null && categoryChosen.equalsIgnoreCase("superhero")){
            superheroHandler(questionModel, nextQuestionBtn);
            checkAnswer(outputResult, userInput, nextQuestionBtn);
        }

        for(int i = 0; questionImages.size() < 3; ++i) {
            if(questionImages.get(i) == null){
                Log.d("Image null", "Image null: " + questionImages.get(i));
            }
        }
        Glide.with(this).load(questionImages.get(0)).override(300, 300).placeholder(R.drawable.ic_launcher_background).into(topLeft);
        Glide.with(this).load(questionImages.get(1)).override(300, 300).placeholder(R.drawable.ic_launcher_foreground).into(topRight);
        Glide.with(this).load(questionImages.get(2)).override(300, 300).placeholder(R.drawable.ic_launcher_background).into(botLeft);
        Glide.with(this).load(questionImages.get(3)).override(300, 300).placeholder(R.drawable.ic_launcher_foreground).into(botRight);



        return view;
    }


}
