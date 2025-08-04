package com.example.pic_4_words_java.MainMenu;

public class GameVolumeDetails extends VolumeDetails{

    private int currentSeekbarProgress;
    private boolean isMuted;

    public GameVolumeDetails() {
    }

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
