package com.example.pic_4_words_java.Game.Fragments.Model;

import androidx.lifecycle.ViewModel;

public class MainModel extends ViewModel {
    private int currentQuestionCount ;
    private static int score;



    //getters and setters
    public static int getScore() { return score; }
    public static void setScore(int score) { MainModel.score = score; }

    public int getCurrentQuestionCount() { return currentQuestionCount; }
    public void setCurrentQuestionCount(int currentQuestionCount) { this.currentQuestionCount = currentQuestionCount; }
}
