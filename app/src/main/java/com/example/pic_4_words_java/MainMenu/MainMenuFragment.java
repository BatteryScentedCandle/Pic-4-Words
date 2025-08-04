package com.example.pic_4_words_java.MainMenu;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.pic_4_words_java.Game.GameActivity;
import com.example.pic_4_words_java.Model.VolumeDetails;
import com.example.pic_4_words_java.R;

public class MainMenuFragment extends Fragment {

    //helper methods
    private void moveToSettings(){
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.popup_enter, 0)
                .replace(android.R.id.content, new SettingsFragment())
                .addToBackStack(null)
                .commit();
    }

    private void moveToGameActivity(){
        Intent intent = new Intent(requireActivity(), GameActivity.class);

        VolumeDetails volumeDetails = new ViewModelProvider(requireActivity()).get(VolumeDetails.class);
        if(volumeDetails.getCurrentSeekbarProgress() == 0){
            volumeDetails.setCurrentSeekbarProgress(100);
        }

        intent.putExtra("progress", volumeDetails.getCurrentSeekbarProgress());
        intent.putExtra("muted", volumeDetails.getMuted());

        startActivity(intent);
    }




    //main codes
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main_menu, container, false);

        //SYSTEM WINDOW INSETS PADDING
        ViewCompat.setOnApplyWindowInsetsListener(view.findViewById(R.id.main_menu), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });



        //SETTINGS BUTTON LOGIC
        ImageButton settingsButton = view.findViewById(R.id.settingsButton);
        settingsButton.setOnClickListener(v -> { moveToSettings(); });

        ImageButton playButton = view.findViewById(R.id.playButton);
        playButton.setOnClickListener(v -> { moveToGameActivity(); });

        return view;
    }

}