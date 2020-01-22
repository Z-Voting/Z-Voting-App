package com.example.tkd.zvoting;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tkd.zvoting.model.User;
import com.google.firebase.auth.FirebaseAuth;

public class MasterActivity extends AppCompatActivity {

    TextView nameUser, emailUser, review, network, plugins, myapps, mainmenus;

    private FirebaseAuth firebaseAuth;
    private DataViewModel mDataViewModel;
    View linkLogout;

    private void initializeViews() {
        //header section
        nameUser = findViewById(R.id.nameuser);
        emailUser = findViewById(R.id.walletuser);

        //main menu
        linkLogout = findViewById(R.id.linkLogout);

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
                if(firebaseAuth.getCurrentUser()==null) {
                    startActivity(new Intent(MasterActivity.this, AuthActivity.class));
                    finish();
                }
            }
        });

        initializeViews();

        mDataViewModel.liveUser.observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                nameUser.setText(user.Name);
                emailUser.setText(user.Email);
            }
        });


        linkLogout.setOnClickListener((v)->{
            mDataViewModel.firebaseAuth.signOut();
        });
    }
}
