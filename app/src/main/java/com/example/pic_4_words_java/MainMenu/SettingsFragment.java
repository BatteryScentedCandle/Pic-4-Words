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
import com.example.pic_4_words_java.Model.VolumeDetails;
import com.example.pic_4_words_java.R;

public class SettingsFragment extends androidx.fragment.app.Fragment {
    private VolumeDetails volumeDetails;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MainActivity mainActivity = (MainActivity) getActivity();
        if (mainActivity != null) {
            volumeDetails = new ViewModelProvider(mainActivity).get(VolumeDetails.class);
        }

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.settings_fragments, container, false);
        SeekBar volumeSeekBar = view.findViewById(R.id.volumeSeekBar);


        //process for after user inputs on seekbar and/or checkbox
        if(CustomMediaPlayer.getInstance().getVolume() != 0 && volumeDetails.getCurrentSeekbarProgress() !=  0){
            CustomMediaPlayer.getInstance().setVolume(volumeDetails.getCurrentSeekbarProgress());
            volumeSeekBar.setProgress(CustomMediaPlayer.getInstance().getVolume());
        }
        if(volumeDetails.getMuted()){
            volumeSeekBar.setProgress(volumeDetails.getCurrentSeekbarProgress());
        }





        volumeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                CustomMediaPlayer.getInstance().setVolume(progress);
                volumeDetails.setCurrentSeekbarProgress(progress);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });


        CheckBox muteCheckbox = view.findViewById(R.id.muteCheckbox);
        muteCheckbox.setChecked(volumeDetails.getMuted());
        muteCheckbox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){
                volumeSeekBar.setEnabled(false);
                volumeDetails.setMuted(true);
                CustomMediaPlayer.getInstance().setVolume(0);
                volumeSeekBar.setProgress(volumeDetails.getCurrentSeekbarProgress());
            }else{
                volumeSeekBar.setEnabled(true);
                volumeDetails.setMuted(false);
                CustomMediaPlayer.getInstance().setVolume(volumeDetails.getCurrentSeekbarProgress());
                volumeSeekBar.setProgress(CustomMediaPlayer.getInstance().getVolume());
            }
        });

        ImageButton closeBtn = view.findViewById(R.id.closeButton);
        closeBtn.setOnClickListener(v -> requireActivity().getSupportFragmentManager().popBackStack());

        return view;
    }


}
