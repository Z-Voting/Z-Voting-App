package com.example.tkd.zvoting;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tkd.zvoting.model.Election;
import com.example.tkd.zvoting.model.User;

import java.util.ArrayList;

public class MyElectionsFragment extends Fragment {

    private MyElectionsViewModel mViewModel;
    RecyclerView content;
    ArrayList<Election> myElections;

    public static MyElectionsFragment newInstance() {
        return new MyElectionsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.my_elections_fragment, container, false);
        content = v.findViewById(R.id.content);
        if (myElections == null)
            myElections = new ArrayList<>();
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        mViewModel.mDataViewModel.mainfragment.setValue(DataViewModel.MyElectionsFragment);

        mViewModel.liveMyElections.observe(this, elections -> {
            myElections = elections;
            mViewModel.mDataViewModel.liveUser.observe(MyElectionsFragment.this, user -> {
                content.setAdapter(new MyElectionsListAdapter(MyElectionsFragment.this, myElections, user));
            });
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MyElectionsViewModel.class);
    }

}
