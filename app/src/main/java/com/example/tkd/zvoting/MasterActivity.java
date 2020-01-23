package com.example.tkd.zvoting;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tkd.zvoting.dummy.DummyContent;
import com.example.tkd.zvoting.model.User;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class MasterActivity extends AppCompatActivity implements ElectionResultsFragment.OnListFragmentInteractionListener {

    TextView nameUser, emailUser, review, network, plugins, myapps, mainmenus;

    private FirebaseAuth firebaseAuth;
    private DataViewModel mDataViewModel;
    ImageView logoutIcon;
    View linkLogout, linkElections, linkMyElections, linkElectionResults;
    private ImageView electionsIcon, myElectionsIcon, electionResultsIcon;
    private TextView electionsText, myElectionsText, electionResultsText;

    ViewPager viewPager;
    MasterPageAdapter pageAdapter;

    private void initializeViews() {
        //header section
        nameUser = findViewById(R.id.nameuser);
        emailUser = findViewById(R.id.walletuser);

        //main menu
        linkElections = findViewById(R.id.linkElections);
        electionsIcon = findViewById(R.id.electionsIcon);
        electionsText = findViewById(R.id.electionsText);

        linkMyElections = findViewById(R.id.linkMyElections);
        myElectionsIcon = findViewById(R.id.myElectionsIcon);
        myElectionsText = findViewById(R.id.myElectionsText);

        linkElectionResults = findViewById(R.id.linkElectionResults);
        electionResultsIcon = findViewById(R.id.electionResultsIcon);
        electionResultsText = findViewById(R.id.electionResultsText);

        linkLogout = findViewById(R.id.linkLogout);
        logoutIcon = findViewById(R.id.logoutIcon);

        viewPager = findViewById(R.id.viewPager);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master);

        DataViewModelFactory factory = new DataViewModelFactory(getApplication());
        mDataViewModel = ViewModelProviders.of(this, factory).get(DataViewModel.class);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.addAuthStateListener(new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() == null) {
                    startActivity(new Intent(MasterActivity.this, AuthActivity.class));
                    finish();
                }
            }
        });

        mDataViewModel.activity.setValue("MasterActivity");
        mDataViewModel.mainfragment.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Log.e("TKD", s);
                electionsText.setTypeface(null, (s.equals(DataViewModel.ElectionsFragment)? Typeface.BOLD:Typeface.NORMAL));
                myElectionsText.setTypeface(null, (s.equals(DataViewModel.MyElectionsFragment)? Typeface.BOLD:Typeface.NORMAL));
                electionResultsText.setTypeface(null, (s.equals(DataViewModel.ElectionResultsFragment)? Typeface.BOLD:Typeface.NORMAL));
            }
        });

        initializeViews();

        List<Fragment> fragments= createFragments();
        pageAdapter = new MasterPageAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(pageAdapter);

        viewPager.setCurrentItem(0);

        mDataViewModel.liveUser.observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                nameUser.setText(user.name);
                emailUser.setText(user.email);
            }
        });


        linkElections.setOnClickListener(v->viewPager.setCurrentItem(0, true));
        linkMyElections.setOnClickListener(v->viewPager.setCurrentItem(1, true));
        linkElectionResults.setOnClickListener(v->viewPager.setCurrentItem(2, true));

        linkLogout.setOnClickListener((v) -> {
            mDataViewModel.firebaseAuth.signOut();
        });
    }

    private List<Fragment> createFragments() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(ElectionsFragment.newInstance());
        fragments.add(MyElectionsFragment.newInstance());
        fragments.add(ElectionResultsFragment.newInstance(2));
        return fragments;
    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }
}
