package com.example.tkd.zvoting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;

public class AuthActivity extends AppCompatActivity {
    FragmentManager fragmentManager;
    FirebaseAuth firebaseAuth;
    private DataViewModel mDataViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        DataViewModelFactory factory = new DataViewModelFactory(getApplication());
        mDataViewModel = ViewModelProviders.of(this, factory).get(DataViewModel.class);

        mDataViewModel.activity.setValue("AuthActivity");

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.addAuthStateListener(new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser()!=null) {
                    startActivity(new Intent(AuthActivity.this, MasterActivity.class));
                    finish();
                }
            }
        });

        fragmentManager = getSupportFragmentManager();

        Log.e("TKD", "" + getSupportFragmentManager().getBackStackEntryCount());

        loadLoginFragment();
//        loadRegisterFragment();
    }

    public void loadLoginFragment() {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.animator.fade_appear, R.animator.fade_disappear,R.animator.fade_appear, R.animator.fade_disappear);

        Fragment loginFragment = new LoginFragment();
        fragmentTransaction.replace(android.R.id.content, loginFragment);
        fragmentTransaction.addToBackStack("Login");
        fragmentTransaction.commit();
    }


    public void loadRegisterFragment() {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.animator.fade_appear, R.animator.fade_disappear,R.animator.fade_appear, R.animator.fade_disappear);

        Fragment loginFragment = RegisterFragment.newInstance();
        fragmentTransaction.replace(android.R.id.content, loginFragment);
        fragmentTransaction.addToBackStack("Register");

        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        int count = getSupportFragmentManager().getBackStackEntryCount();
        if (count == 1) {
            finish();
        } else {
            getSupportFragmentManager().popBackStack();
        }

    }
}
