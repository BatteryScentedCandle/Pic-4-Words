package com.example.pic_4_words_java.MainMenu;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class MainMenuFragmentStateAdapter extends FragmentStateAdapter {

    public MainMenuFragmentStateAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new MainMenuFragment();

            case 1:
                return new LeaderboardFragment();

            default:
                return new MainMenuFragment();
        }
    }

}
