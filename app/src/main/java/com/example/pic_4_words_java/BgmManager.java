package com.example.pic_4_words_java;



import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;

public class BgmManager {
    private static BgmManager Instance;
    private static int audioFileName;
    private static float volume = 1.0f;
    MediaPlayer mediaPlayer;




    public int getAudioFile() {
        return audioFileName;
    }

    public int getVolume() {
        return (int) (volume * 100);
    }

    public void setVolume(float volumePercent) {
        volume = volumePercent / 100.0f;
        mediaPlayer.setVolume(volume, volume);
    }



    //creates new instance if it does not exist
    //returns existing instance if exists
    public static BgmManager getInstance(){
        if(Instance == null){
            return Instance = new BgmManager();
        }
        return Instance;
    }

    public void playLoopingAudio(Context c, int audioFile){
        mediaPlayer = MediaPlayer.create(c, audioFile);
        audioFileName = audioFile;
        Log.d("Audio", "Audio file: " + audioFileName);
        mediaPlayer.setOnPreparedListener(MediaPlayer::start);
        mediaPlayer.setLooping(true);
    }

    public void playAudio(Context c, int audioFile){
        audioFileName = audioFile;
        mediaPlayer = MediaPlayer.create(c, audioFile);
        mediaPlayer.setOnPreparedListener(MediaPlayer::start);
    }

    public void stopAudio(){
        if(mediaPlayer != null){
            mediaPlayer.stop();
        }
    }
}
