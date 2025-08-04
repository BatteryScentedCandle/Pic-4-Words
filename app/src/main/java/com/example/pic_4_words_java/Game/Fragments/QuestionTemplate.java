package com.example.pic_4_words_java.Game.Fragments;



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
import com.example.pic_4_words_java.CustomMediaPlayer;
import com.example.pic_4_words_java.Game.Fragments.Model.CategoryModel;
import com.example.pic_4_words_java.Game.Fragments.Model.DifficultyModel;
import com.example.pic_4_words_java.Game.Fragments.Model.QuestionAnswerBuilder;
import com.example.pic_4_words_java.Game.Fragments.Model.QuestionAnswerModel;
import com.example.pic_4_words_java.Game.Fragments.Model.QuestionViewModel;
//import com.example.pic_4_words_java.MainMenu.VolumeDetails;
import com.example.pic_4_words_java.MainMenu.GameVolumeDetails;
import com.example.pic_4_words_java.MainMenu.VolumeDetails;
import com.example.pic_4_words_java.R;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class QuestionTemplate extends Fragment {

    private QuestionViewModel qViewModel;
    private QuestionAnswerModel qaModel;
    private CategoryModel categoryModel;
    private DifficultyModel difficultyModel;
    private GameVolumeDetails gameVolumeDetails;
//    private VolumeDetails volumeDetails;









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


        if(userInputAnswer.equalsIgnoreCase(currentAnswer)){
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
            FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();

            if(qaModel.getCurrentQuestionCount() < 2){
                qaModel.incrementQuestionCount();
                transaction.replace(R.id.flFragmentContainer, new QuestionTemplate()).commit();
            }else{

                //reset models to ensure no overlapping, move to scre
                CustomMediaPlayer.getInstance().stopAudio();
                qViewModel.resetViewModel();
                qaModel.resetQAModel();
                transaction.replace(R.id.flFragmentContainer, new Score()).commit();
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

    public void isModelInitialized(Object model ) {
        if (model != null) {
            Log.d("Model Initialize", "Model initialized");
        } else {
            Log.d("Model Initialize", "Model not initialized");
        }
    }












    //handlers

    public void currentQuestionHandler(int question, ImageButton nextQuestionBtn) {
        try {
            if (allImages != null && allImageKeys != null && question >= 0 && question < allImageKeys.size()) {
                questionImages = allImages.get(allImageKeys.get(question));
                if (questionImages == null) {
                    Log.d("CurrentQuestion", "No images found for question: " + question);
                    // Handle the error case
                }
            } else {
                Log.d("CurrentQuestion", "Invalid question index or null data. Question: " + question +
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

    public void handleQuestionDifficulty(ImageButton nextQuestionBtn, QuestionAnswerModel builder){
        if (builder == null ) {
            Log.e("QuestionHandler", "Failed to load question data");
            return; // Optionally show an error to the user
        }

        qaModel.setAnswer(builder.getAnswer());
        qaModel.setImages(builder.getImages());
        handlerLogic(nextQuestionBtn);
    }

    public void brainrotQuestionHandler(ImageButton nextQuestionBtn) {
        QuestionAnswerModel builder = null;

        if(difficultyChosen.equalsIgnoreCase("easy")){
            builder = QuestionAnswerBuilder.easyBrainrotQA();
        }
        else{
            builder = QuestionAnswerBuilder.hardBrainrot();
        }

        if(builder != null){
            handleQuestionDifficulty(nextQuestionBtn, builder);
        }
        else{
            Log.d("Builder", "Builder is null");
        }
    }



    public void superheroQuestionHandler(ImageButton nextQuestionBtn) {
        QuestionAnswerModel builder = null;

        if(difficultyChosen.equalsIgnoreCase("easy")){
            builder = QuestionAnswerBuilder.easySuperhero();
        }
        else{
            builder = QuestionAnswerBuilder.hardSuperhero();
        }

        if(builder != null){
            handleQuestionDifficulty(nextQuestionBtn, builder);
        }
        else{
            Log.d("Builder", "Builder is null");
        }
    }


    public void mainHandler(TextView outputResult, EditText userInput, QuestionViewModel qViewModel, ImageButton nextQuestionBtn){
        if(categoryChosen == null){
            Log.d("Category", "Category is null");
            return;
        }

        if(categoryChosen.equalsIgnoreCase("brainrot")){
            Log.d("Category", "Category chosen: " + categoryChosen);
            qViewModel.setTvDifficultyCategoryContent("Brainrot");
            brainrotQuestionHandler(nextQuestionBtn);
        }

        if(categoryChosen.equalsIgnoreCase("superhero")){
            Log.d("Category", "Category chosen: " + categoryChosen);
            qViewModel.setTvDifficultyCategoryContent("Superhero");
            superheroQuestionHandler(nextQuestionBtn);
        }

        checkAnswer(outputResult, userInput, nextQuestionBtn);
    }


















    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        int menuAudio = R.raw.m_b4;

        //checks if CustomMediaPlayer is playing m_b4
        if(CustomMediaPlayer.getInstance().getAudioFile() == menuAudio){
            CustomMediaPlayer.getInstance().stopAudio();
            CustomMediaPlayer.getInstance().playLoopingAudio(this.getContext(), R.raw.m_b1);
            CustomMediaPlayer.getInstance().setVolume(gameVolumeDetails.getCurrentSeekbarProgress());
        }
    }
    //main code
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       //initialize models
        qViewModel = new ViewModelProvider(requireActivity()).get(QuestionViewModel.class);
        qaModel = new ViewModelProvider(requireActivity()).get(QuestionAnswerModel.class);
        categoryModel = new ViewModelProvider(requireActivity()).get(CategoryModel.class);
        difficultyModel = new ViewModelProvider(requireActivity()).get(DifficultyModel.class);
        gameVolumeDetails = new ViewModelProvider(requireActivity()).get(GameVolumeDetails.class);
        Category category = new Category();
        Difficulty difficulty = new Difficulty();

        //check if models are initialized
        isModelInitialized(qViewModel);
        isModelInitialized(qaModel);
        isModelInitialized(category);
        isModelInitialized(difficulty);


        //initialize variables
        categoryChosen = categoryModel.getCategoryChosen();
        difficultyChosen = difficultyModel.getDifficultyChosen();
        currentQuestion = qaModel.getCurrentQuestionCount();

        //debugging
        Log.d("Category", "Category chosen: " + categoryChosen);
        Log.d("Difficulty", "Difficulty chosen: " + difficultyChosen);
    }

    //very self-explanatory code
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.category_level_generic, container, false);

        //process
        TextView outputResult = view.findViewById(R.id.outputResult);
        EditText userInput = view.findViewById(R.id.easyBrainrotUserInput);
        ImageButton nextQuestionBtn = view.findViewById(R.id.nextQuestionBtn);
        mainHandler(outputResult, userInput, qViewModel, nextQuestionBtn);

        //views
        TextView tvDifficultyCategory = view.findViewById(R.id.tvDifficultyCategory);
        tvDifficultyCategory.setText(qViewModel.getTvDifficultyCategoryContent());

        TextView tvLevel = view.findViewById(R.id.tvLevel);
        tvLevel.setText(qViewModel.getTvLevelContent());


        //Images
        ImageView topLeft = view.findViewById(R.id.topLeft);
        ImageView topRight = view.findViewById(R.id.topRight);
        ImageView botLeft = view.findViewById(R.id.botLeft);
        ImageView botRight = view.findViewById(R.id.botRight);

        Glide.with(this).load(questionImages.get(0)).override(300, 300).centerCrop().placeholder(R.drawable.ic_launcher_background).into(topLeft);
        Glide.with(this).load(questionImages.get(1)).override(300, 300).centerCrop().placeholder(R.drawable.ic_launcher_foreground).into(topRight);
        Glide.with(this).load(questionImages.get(2)).override(300, 300).centerCrop().placeholder(R.drawable.ic_launcher_background).into(botLeft);
        Glide.with(this).load(questionImages.get(3)).override(300, 300).centerCrop().placeholder(R.drawable.ic_launcher_foreground).into(botRight);



        return view;
    }

}
