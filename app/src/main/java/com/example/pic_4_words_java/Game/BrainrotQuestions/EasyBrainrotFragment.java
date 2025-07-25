package com.example.pic_4_words_java.Game.BrainrotQuestions;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pic_4_words_java.Game.CategoryFragment;
import com.example.pic_4_words_java.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EasyBrainrotFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_easy_brainrot, container, false);

        FloatingActionButton goBackBtn = view.findViewById(R.id.backButton);
        goBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment categoryFragment = new CategoryFragment();
                FragmentTransaction fm = requireActivity().getSupportFragmentManager().beginTransaction();
                fm.replace(R.id.flFragmentContainer, categoryFragment).commit();
            }
        });

        return view;
    }
}