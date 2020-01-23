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

public class ElectionsFragment extends Fragment {

    private ElectionsViewModel mViewModel;
    User user;

    public static ElectionsFragment newInstance() {
        return new ElectionsFragment();
    }

    RecyclerView electionList;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.elections_fragment, container, false);
        initializeViews(view);

        return view;
    }

    private void initializeViews(View root) {
        electionList = root.findViewById(R.id.electionList);
    }

    @Override
    public void onResume() {
        super.onResume();

        mViewModel.mDataViewModel.mainfragment.setValue(DataViewModel.ElectionsFragment);

        mViewModel.getElections();

        mViewModel.mDataViewModel.liveUser.observe(this, new Observer<User>() {
            @Override
            public void onChanged(User currentUser) {
                ElectionsFragment.this.user = currentUser;

                mViewModel.liveElections.observe(ElectionsFragment.this, new Observer<ArrayList<Election>>() {
                    @Override
                    public void onChanged(ArrayList<Election> elections) {
                        electionList.setAdapter(new ElectionListAdapter(ElectionsFragment.this, elections, user));
                    }
                });
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ElectionsViewModel.class);
    }

}
