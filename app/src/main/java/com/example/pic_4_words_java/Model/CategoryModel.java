package com.example.pic_4_words_java.Model;

import androidx.lifecycle.ViewModel;

public class CategoryModel extends ViewModel{
    private String categoryChosen;

    public String getCategoryChosen() {
        return categoryChosen;
    }

    public void setCategoryChosen(String categoryChosen) {
        this.categoryChosen = categoryChosen;
    }
}
