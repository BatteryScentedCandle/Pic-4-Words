package com.example.pic_4_words_java.Game.Fragments.Copies;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.pic_4_words_java.R;

public class CategoryCopy extends Fragment {
    private static String categoryChosen;

    public static String getCategoryChosen() {
        return categoryChosen;
    }
    public static void setCategoryChosen(String categoryChosen) {
        CategoryCopy.categoryChosen = categoryChosen;
    }

    public void goToDifficulty() {
        Fragment difficultyFragment = new DifficultyCopy();
        FragmentTransaction fragmentTransaction = requireActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.flFragmentContainer, difficultyFragment).addToBackStack(null).commit();
    }


    //main codes
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);



        //Button clicks
        Button superheroBtn = view.findViewById(R.id.superheroCategoryBtn);
        superheroBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryChosen = "superhero";
                goToDifficulty();
            }
        });

        Button brainrotBtn = view.findViewById(R.id.brainrotCategoryBtn);
        brainrotBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryChosen = "brainrot";
                goToDifficulty();
            }
        });



        return view;
    }
}
