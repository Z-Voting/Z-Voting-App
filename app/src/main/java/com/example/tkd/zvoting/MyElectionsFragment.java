package com.example.tkd.zvoting;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MyElectionsFragment extends Fragment {

    private MyElectionsViewModel mViewModel;


    public static MyElectionsFragment newInstance() {
        return new MyElectionsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.my_elections_fragment, container, false);

    }

    @Override
    public void onResume() {
        super.onResume();
        mViewModel.mDataViewModel.mainfragment.setValue(DataViewModel.MyElectionsFragment);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MyElectionsViewModel.class);
    }

}
