package com.example.pic_4_words_java.Game.Fragments.Model;

import androidx.lifecycle.ViewModel;
import java.util.List;

public class QuestionTemplateViewModel extends ViewModel {
    private String answer;
    private List<String> imageURL;
    private String tvLevelContent;
    private String tvDifficultyCategoryContent;

    public String getTvDifficultyCategoryContent() {
        return tvDifficultyCategoryContent;
    }

    public void setTvDifficultyCategoryContent(String tvDifficultyCategoryContent) {
        this.tvDifficultyCategoryContent = tvDifficultyCategoryContent;
    }

    public String getAnswer() { return answer; }
    public void setAnswer(String answer) { this.answer = answer; }

    public String getImageURL(int index) { return imageURL.get(index); }
    public void setImageURL(List<String> imageURL) { this.imageURL = imageURL; }

    public String getTvLevelContent() { return tvLevelContent; }
    public void setTvLevelContent(String tvLevelContent) { this.tvLevelContent= tvLevelContent; }
}