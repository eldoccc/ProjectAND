package com.example.projectand.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.projectand.R;
import com.example.projectand.adapters.RecipeViewPagerAdapter;
import com.example.projectand.fragments.subC.AllFragment;
import com.example.projectand.fragments.subC.DayFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

/**
 * A simple {@link Fragment} subclass.
 */
public class CommunityFragment extends Fragment {

    public CommunityFragment() {
    }

    public static CommunityFragment newInstance(){
        return new CommunityFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_community, container, false);

        TabLayout tbL = rootView.findViewById(R.id.tabL);
        ViewPager2 vp = rootView.findViewById(R.id.pager);
        RecipeViewPagerAdapter rvpa = new RecipeViewPagerAdapter(this);
        rvpa.addFragment(AllFragment.newInstance());
        rvpa.addFragment(DayFragment.newInstance());
        vp.setAdapter(rvpa);
        new TabLayoutMediator(tbL, vp,
                new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override
                    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                        tab.setText("OBJECT " + (position + 1));
                    }
                }
        ).attach();
        return rootView;
    }
}
