package com.example.a4p1w

import  androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.fragment.app.FragmentActivity
import com.example.a4p1w.MainMenu.LeaderboardFragment
import com.example.a4p1w.MainMenu.MainMenuFragment

class MainMenuFragmentStateAdapter(fragmentActivity: FragmentActivity) :FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = 2;
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> MainMenuFragment()
            1 -> LeaderboardFragment()
            else -> MainMenuFragment()
        }
    }
}

//The createFragment() method returns the Fragment at the specified position.
//So first will be MainMenuFragment and upon swiping left, we will get LeaderboardFragment
//
//The getItemCount() method returns the number of Fragments there are to be displayed, which, in this case, is two.