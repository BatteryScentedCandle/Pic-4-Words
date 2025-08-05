package com.example.pic_4_words_java.MainMenu;



import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.SeekBar;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.example.pic_4_words_java.CustomMediaPlayer;
import com.example.pic_4_words_java.MainActivity;
import com.example.pic_4_words_java.Model.BGMSettings;
import com.example.pic_4_words_java.R;

public class SettingsFragment extends androidx.fragment.app.Fragment {
    private BGMSettings BGMSettings;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MainActivity mainActivity = (MainActivity) getActivity();
        if (mainActivity != null) {
            BGMSettings = new ViewModelProvider(mainActivity).get(BGMSettings.class);
        }

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.settings_fragments, container, false);
        SeekBar volumeSeekBar = view.findViewById(R.id.volumeSeekBar);


        //process for after user inputs on seekbar and/or checkbox
        if(CustomMediaPlayer.getInstance().getVolume() != 0 && BGMSettings.getCurrentSeekbarProgress() !=  0){
            CustomMediaPlayer.getInstance().setVolume(BGMSettings.getCurrentSeekbarProgress());
            volumeSeekBar.setProgress(CustomMediaPlayer.getInstance().getVolume());
        }
        if(BGMSettings.getMuted()){
            volumeSeekBar.setProgress(BGMSettings.getCurrentSeekbarProgress());
        }





        volumeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                CustomMediaPlayer.getInstance().setVolume(progress);
                BGMSettings.setCurrentSeekbarProgress(progress);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });


        CheckBox muteCheckbox = view.findViewById(R.id.muteCheckbox);
        muteCheckbox.setChecked(BGMSettings.getMuted());
        muteCheckbox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){
                volumeSeekBar.setEnabled(false);
                BGMSettings.setMuted(true);
                CustomMediaPlayer.getInstance().setVolume(0);
                volumeSeekBar.setProgress(BGMSettings.getCurrentSeekbarProgress());
            }else{
                volumeSeekBar.setEnabled(true);
                BGMSettings.setMuted(false);
                CustomMediaPlayer.getInstance().setVolume(BGMSettings.getCurrentSeekbarProgress());
                volumeSeekBar.setProgress(CustomMediaPlayer.getInstance().getVolume());
            }
        });

        ImageButton closeBtn = view.findViewById(R.id.closeButton);
        closeBtn.setOnClickListener(v -> requireActivity().getSupportFragmentManager().popBackStack());

        return view;
    }


}
