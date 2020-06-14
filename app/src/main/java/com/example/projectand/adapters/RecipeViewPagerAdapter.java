package com.example.projectand.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

public class RecipeViewPagerAdapter extends FragmentStateAdapter {

    private ArrayList<Fragment> listF = new ArrayList<>();

    public RecipeViewPagerAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return listF.get(position);
    }

    @Override
    public int getItemCount() {
        return listF.size();
    }

    public void addFragment(Fragment frag){
        listF.add(frag);

    }
}
