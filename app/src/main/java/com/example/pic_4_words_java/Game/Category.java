package com.example.pic_4_words_java.Game;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.pic_4_words_java.Model.CategoryModel;
import com.example.pic_4_words_java.R;

public class Category extends Fragment {
    private CategoryModel categoryModel;

    public void goToDifficulty() {
        Fragment difficultyFragment = new Difficulty();
        FragmentTransaction fragmentTransaction = requireActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.flFragmentContainer, difficultyFragment).addToBackStack(null).commit();
    }


    //main codes
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        categoryModel = new ViewModelProvider(requireActivity()).get(CategoryModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);



        //Button clicks
        Button superheroBtn = view.findViewById(R.id.superheroCategoryBtn);
        superheroBtn.setOnClickListener(v -> {
            categoryModel.setCategoryChosen("Superhero");
            goToDifficulty();
        });

        Button brainrotBtn = view.findViewById(R.id.brainrotCategoryBtn);
        brainrotBtn.setOnClickListener(v -> {
            categoryModel.setCategoryChosen("Brainrot");
            goToDifficulty();
        });



        return view;
    }
}
