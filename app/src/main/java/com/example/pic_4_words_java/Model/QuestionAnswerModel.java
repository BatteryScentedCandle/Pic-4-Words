package com.example.pic_4_words_java.Model;

//contains variables, setters and getters for answers and images

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class QuestionAnswerModel extends ViewModel {

    private int currentQuestionCount;
    private List<String > answer;
    private LinkedHashMap<String, List<String>> images = new LinkedHashMap<>();
    private int score;

    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }
    //setters and getters
    public List<String> getAnswer() { return answer; }
    public void setAnswer(List<String> answer) { this.answer = answer; }

    public void setImages(LinkedHashMap<String, List<String>> sourceMap){
        this.images.putAll(sourceMap);
    }

    public void incrementQuestionCount(){ ++ currentQuestionCount;}

    public void resetQuestionCount(){ currentQuestionCount = 0; };

    public int getCurrentQuestionCount(){
        return currentQuestionCount;
    }

    public LinkedHashMap<String, List<String>> getImages() {
        return images;
    }



    public void resetQAModel(){
        currentQuestionCount = 0;
        answer = new ArrayList<>();
        images  = new LinkedHashMap<>();
    }
}
