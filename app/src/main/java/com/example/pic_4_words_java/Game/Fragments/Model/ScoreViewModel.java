package com.example.pic_4_words_java.Game.Fragments.Model;

import androidx.lifecycle.ViewModel;

public class ScoreViewModel extends ViewModel {

    private int esScore;
    private int hsScore;
    private int hbScore;
    private int ebScore;

    public int getEbScore() {
        return ebScore;
    }

    public void setEbScore(int ebScore) {
        this.ebScore = ebScore;
    }

    public int getEsScore() {
        return esScore;
    }

    public void setEsScore(int esScore) {
        this.esScore = esScore;
    }

    public int getHsScore() {
        return hsScore;
    }

    public void setHsScore(int hsScore) {
        this.hsScore = hsScore;
    }

    public int getHbScore() {
        return hbScore;
    }

    public void setHbScore(int hbScore) {
        this.hbScore = hbScore;
    }
}