package com.example.tkd.zvoting;

import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class EmptyFragment extends Fragment {

    private DataViewModel mDataViewModel;
    TextView pagetitle, pagesubtitle;
    Button btnguide;
    Animation atg, atgtwo, atgthree;
    ImageView imageView3;

    public static EmptyFragment newInstance() {
        return new EmptyFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.empty_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        DataViewModelFactory factory = new DataViewModelFactory(getActivity().getApplication());
        mDataViewModel = ViewModelProviders.of(this, factory).get(DataViewModel.class);

        initializeViews(getView());

        btnguide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(getActivity(),PackageAct.class);
                startActivity(a);
            }
        });

        // pass an animation
        imageView3.startAnimation(atg);
        pagetitle.startAnimation(atgtwo);
        pagesubtitle.startAnimation(atgtwo);
        btnguide.startAnimation(atgthree);
    }

    private void initializeViews(View view) {
        //animations
        atg = AnimationUtils.loadAnimation(getContext(), R.anim.atg);
        atgtwo = AnimationUtils.loadAnimation(getContext(), R.anim.atgtwo);
        atgthree = AnimationUtils.loadAnimation(getContext(), R.anim.atgthree);

        //animation contents
        imageView3 = view.findViewById(R.id.cuteRobot);
        pagetitle = view.findViewById(R.id.pagetitle);
        pagesubtitle = view.findViewById(R.id.pagesubtitle);
        btnguide = view.findViewById(R.id.btnAddElection);
    }

}
