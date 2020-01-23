package com.example.tkd.zvoting;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

class MasterPageAdapter extends FragmentPagerAdapter {
    List<Fragment> fragments;
    DataViewModel mDataViewModel;

    public MasterPageAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.fragments = fragments;
        this.mDataViewModel = DataViewModel.getInstance(null);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: mDataViewModel.mainfragment.setValue(DataViewModel.ElectionsFragment); break;
            case 1: mDataViewModel.mainfragment.setValue(DataViewModel.MyElectionsFragment); break;
            case 2: mDataViewModel.mainfragment.setValue(DataViewModel.ElectionResultsFragment); break;
        }

        return this.fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
