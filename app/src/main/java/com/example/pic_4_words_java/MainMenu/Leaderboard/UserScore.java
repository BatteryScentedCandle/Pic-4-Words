package com.example.pic_4_words_java.MainMenu.Leaderboard;

public class UserScore {
    private String username;
    private int score;


    public UserScore(String username, int score) {
        this.username = username;
        this.score = score;
    }

    public String getUsername() {
        return username;
    }

    public int getScore() {
        return score;
    }
}