package com.example.pic_4_words_java.Game.Fragments;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.pic_4_words_java.Game.Fragments.Model.ScoreViewModel;
import com.example.pic_4_words_java.Game.Fragments.Model.QuestionTemplateViewModel;
import com.example.pic_4_words_java.Game.Fragments.Model.MainModel;
import com.example.pic_4_words_java.R;

import java.util.Arrays;
import java.util.Objects;

public class CategoryFragment extends Fragment {
    private MainModel mainModel;
    private QuestionTemplateViewModel questionModel;
    private ScoreViewModel scoreModel;
    private Boolean questionsAnswered = false;
    public static String categoryChosen;

    public static String getCategoryChosen() {
        return categoryChosen;
    }


    //handle superhero
    private void easySuperheroQuestionHandler(FragmentTransaction fm){
        Fragment questionFragment;
        switch (mainModel.getCurrentQuestionCount()) {
            case 4:
                mainModel.setCurrentQuestionCount(0);
                questionFragment = new Score();
                break;
            case 3:
                questionModel.setTvLevelContent("Level 3");
                questionModel.setAnswer("Thor");
                questionModel.setImageURL(Arrays.asList(
                        "https://i.imgur.com/PmwvNFa.jpeg",
                        "https://i.imgur.com/7HldHuZ.jpeg",
                        "https://i.imgur.com/9WxETQY.jpeg",
                        "https://i.imgur.com/ycM0Qqm.jpeg"
                ));
                questionFragment = new QuestionTemplate();
                break;
            case 2:
                questionModel.setTvLevelContent("Level 2");
                questionModel.setAnswer("Spider-Man");
                questionModel.setImageURL(Arrays.asList(
                        "https://i.imgur.com/dtvXILx.jpeg",
                        "https://i.imgur.com/TgqIfsH.jpeg",
                        "https://i.imgur.com/dnXwJW7.jpeg",
                        ""
                ));
                questionFragment = new QuestionTemplate();
                break;
            default:
                questionModel.setTvLevelContent("Level 1");
                questionModel.setAnswer("Batman");
                questionModel.setImageURL(Arrays.asList(
                        "https://i.imgur.com/JJS0gFv.jpeg",
                        "https://i.imgur.com/pbWu92c.jpeg",
                        "",
                        ""
                ));
                questionFragment = new QuestionTemplate();
                break;
        }

        fm.replace(R.id.flFragmentContainer, questionFragment)
                .addToBackStack("category")
                .commit();
    }
    private void hardSuperheroQuestionHandler(FragmentTransaction fm){
        Fragment questionFragment;
        switch (mainModel.getCurrentQuestionCount()) {
            case 4:
                mainModel.setCurrentQuestionCount(0);
                questionFragment = new Score();
                break;
            case 3:
                questionModel.setTvLevelContent("Level 3");
                questionModel.setAnswer("Daredevil");
                questionModel.setImageURL(Arrays.asList(
                        "https://i.imgur.com/OVvd8ai.jpeg",
                        "https://i.imgur.com/uPyXma7.gif",
                        "https://i.imgur.com/UmGMJVI.jpeg",
                        ""
                ));
                questionFragment = new QuestionTemplate();
                break;
            case 2:
                questionModel.setTvLevelContent("Level 2");
                questionModel.setAnswer("Shazam");
                questionModel.setImageURL(Arrays.asList(
                        "https://i.imgur.com/VhfKji4.jpeg",
                        "https://i.imgur.com/PUJDSXu.jpeg",
                        "https://i.imgur.com/4W6mIUk.png",
                        "https://i.imgur.com/JTMCuiX.jpeg"
                ));
                questionFragment = new QuestionTemplate();
                break;
            default:
                questionModel.setTvLevelContent("Level 1");
                questionModel.setAnswer("Deadpool");
                questionModel.setImageURL(Arrays.asList(
                        "https://i.imgur.com/CrtAR4i.jpeg",
                        "https://i.imgur.com/OAjzPa3.jpeg",
                        "",
                        ""
                ));
                questionFragment = new QuestionTemplate();
                break;
        }

        fm.replace(R.id.flFragmentContainer, questionFragment)
                .addToBackStack("category")
                .commit();
    }

    private void superheroHandler(View v, MainModel brainrotModel, QuestionTemplateViewModel questionModel){
        categoryChosen = "Superhero";
        if(brainrotModel.getCurrentQuestionCount() == 0){
            brainrotModel.setCurrentQuestionCount(1);
        }

        FragmentTransaction fm= requireActivity().getSupportFragmentManager().beginTransaction();

        if(Objects.equals(DifficultyFragment.getDifficutlyChosen(), "Easy")){
            questionModel.setTvDifficultyCategoryContent("Easy - Superhero");
            easySuperheroQuestionHandler(fm);
        }


        if(Objects.equals(DifficultyFragment.getDifficutlyChosen(), "Hard")){
            questionModel.setTvDifficultyCategoryContent("Hard - Superhero");
            hardSuperheroQuestionHandler(fm);
        }
    }









    //handle brainrot
    private void easyBrainrotQuestionHandler(FragmentTransaction fm){
        Fragment questionFragment;
        switch (mainModel.getCurrentQuestionCount()) {
            case 4:
                mainModel.setCurrentQuestionCount(0);
                questionFragment = new Score();
                break;
            case 3:
                questionModel.setTvLevelContent("Level 3");
                questionModel.setAnswer("Grimace");
                questionModel.setImageURL(Arrays.asList(
                        "https://i.imgur.com/Nd66UdA.jpeg",
                        "https://i.imgur.com/B30vAyg.jpeg",
                        "https://i.imgur.com/qXbjXkJ.jpeg",
                        ""
                ));
                questionFragment = new QuestionTemplate();
                break;
            case 2:
                questionModel.setTvLevelContent("Level 2");
                questionModel.setAnswer("Sus");
                questionModel.setImageURL(Arrays.asList(
                        "https://i.imgur.com/5imhCMs.jpeg",
                        "https://i.imgur.com/w6wdCz9.png",
                        "",
                        ""
                ));
                questionFragment = new QuestionTemplate();
                break;
            default:
                questionModel.setTvLevelContent("Level 1");
                questionModel.setAnswer("Tung Tung Tung Sahur");
                questionModel.setImageURL(Arrays.asList(
                        "https://i.imgur.com/QDwm0TK.jpeg",
                        "https://i.imgur.com/SaKRwon.jpeg",
                        "https://i.imgur.com/7BK86Eu.png",
                        "https://i.imgur.com/QrqdTSB.jpeg"
                ));
                questionFragment = new QuestionTemplate();
                break;
        }

        fm.replace(R.id.flFragmentContainer, questionFragment)
                .addToBackStack("category")
                .commit();
    }


    private void hardBrainrotQuestionHandler(FragmentTransaction fm){
        Fragment questionFragment;
        switch (mainModel.getCurrentQuestionCount()) {
            case 4:
                mainModel.setCurrentQuestionCount(0);

                questionFragment = new Score();
                break;
            case 3:
                questionModel.setTvLevelContent("Level 3");
                questionModel.setAnswer("Mukbang");
                questionModel.setImageURL(Arrays.asList(
                        "https://i.imgur.com/IM2ptmw.jpeg",
                        "https://i.imgur.com/Snbu7oQ.jpeg",
                        "https://i.imgur.com/egoBmKK.jpeg",
                        ""
                ));
                questionFragment = new QuestionTemplate();
                break;
            case 2:
                questionModel.setTvLevelContent("Level 2");
                questionModel.setAnswer("Crashout");
                questionModel.setImageURL(Arrays.asList(
                        "https://i.imgur.com/WUzXtfF.jpeg",
                        "https://i.imgur.com/M9tBDXP.jpeg",
                        "https://i.imgur.com/oipSzGB.jpeg",
                        ""
                ));
                questionFragment = new QuestionTemplate();
                break;
            default:
                questionModel.setTvLevelContent("Level 1");
                questionModel.setAnswer("Sigma");
                questionModel.setImageURL(Arrays.asList(
                        "https://i.imgur.com/ngcAxu0.jpeg",
                        "https://i.imgur.com/ptoZqL0.jpeg",
                        "https://i.imgur.com/aDr7GF0.jpeg",
                        ""
                ));
                questionFragment = new QuestionTemplate();
                break;
        }

        fm.replace(R.id.flFragmentContainer, questionFragment)
                .addToBackStack("category")
                .commit();
    }

    private void brainrotHandler(View v, MainModel brainrotModel, QuestionTemplateViewModel questionModel){
        categoryChosen = "Brainrot";

        if(brainrotModel.getCurrentQuestionCount() == 0){
            brainrotModel.setCurrentQuestionCount(1);
        }

        FragmentTransaction fm= requireActivity().getSupportFragmentManager().beginTransaction();

        if(Objects.equals(DifficultyFragment.getDifficutlyChosen(), "Easy")){
            Log.d("Difficulty", "Difficulty chosen: " + DifficultyFragment.getDifficutlyChosen());
            questionModel.setTvDifficultyCategoryContent("Easy - Brainrot");
            easyBrainrotQuestionHandler(fm);
        }


        if(Objects.equals(DifficultyFragment.getDifficutlyChosen(), "Hard")){
            Log.d("Difficulty", "Difficulty chosen: " + DifficultyFragment.getDifficutlyChosen());
            questionModel.setTvDifficultyCategoryContent("Hard - Brainrot");
            hardBrainrotQuestionHandler(fm);
        }
    }

















    //main codes
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //initialize models
        mainModel = new ViewModelProvider(requireActivity()).get(MainModel.class);
        questionModel = new ViewModelProvider(requireActivity()).get(QuestionTemplateViewModel.class);
        scoreModel = new ViewModelProvider(requireActivity()).get(ScoreViewModel.class);


        //may need to omit as this does not work properly
        if(questionsAnswered == true){

            OnBackPressedCallback callback = new OnBackPressedCallback(true) {
                @Override
                public void handleOnBackPressed() {
                    FragmentManager fm = requireActivity().getSupportFragmentManager();

                    // For example, if you want to go back to DifficultyFragment and clear stack:
                    fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);

                    fm.beginTransaction()
                            .replace(R.id.flFragmentContainer, new DifficultyFragment())
                            .commit();
                }
            };
            requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);

        TextView tvDifficultyChosen = view.findViewById(R.id.tvDifficultyChosen);
        tvDifficultyChosen.setText(DifficultyFragment.getDifficutlyChosen());



        //Button clicks
        Button superheroBtn = view.findViewById(R.id.superheroCategoryBtn);
        superheroBtn.setOnClickListener(v -> superheroHandler(v, mainModel, questionModel));

        Button brainrotBtn = view.findViewById(R.id.brainrotCategoryBtn);
        brainrotBtn.setOnClickListener(v -> brainrotHandler(v, mainModel, questionModel));



        //main process
        if (mainModel.getCurrentQuestionCount() > 1 &&
                (DifficultyFragment.getDifficutlyChosen().equals("Easy") || DifficultyFragment.getDifficutlyChosen().equals("Hard"))) {


            if(categoryChosen.equalsIgnoreCase("superhero")){

                view.post(() -> superheroHandler(view, mainModel, questionModel));
                questionsAnswered = true;
            }
            if(categoryChosen.equalsIgnoreCase("brainrot")){

                view.post(() -> brainrotHandler(view, mainModel, questionModel));
                questionsAnswered = true;
            }
        }

        return view;
    }

}

