package com.example.pic_4_words_java.Model;

import androidx.lifecycle.ViewModel;

public class VolumeDetails extends ViewModel {
    private int currentSeekbarProgress;
    private boolean isMuted;
    public int getCurrentSeekbarProgress() {
        return currentSeekbarProgress;
    }
    public void setCurrentSeekbarProgress(int currentSeekbarProgress) {
        this.currentSeekbarProgress = currentSeekbarProgress;
    }
    public boolean getMuted() {
        return isMuted;
    }
    public void setMuted(boolean muted) {
        isMuted = muted;
    }
}
