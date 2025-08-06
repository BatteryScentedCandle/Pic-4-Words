package com.example.pic_4_words_java.Model;

import androidx.lifecycle.ViewModel;

public class DifficultyModel extends ViewModel {
    private String difficultyChosen;

    public String getDifficultyChosen() {
        return difficultyChosen;
    }

    public void setDifficultyChosen(String difficultyChosen) {
        this.difficultyChosen = difficultyChosen;
    }
}
