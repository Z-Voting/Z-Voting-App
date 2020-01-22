package com.example.tkd.zvoting;

import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginFragment extends Fragment {

    private DataViewModel mDataViewModel;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    EditText editEmail, editPassword;
    Button btnLogin;
    FirebaseAuth firebaseAuth;
    View linkRegister;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_fragment, container, false);

        linkRegister = view.findViewById(R.id.linkRegister);

        firebaseAuth = FirebaseAuth.getInstance();

        editEmail = view.findViewById(R.id.editEmail);
        editPassword = view.findViewById(R.id.editPassword);
        btnLogin = view.findViewById(R.id.btnLogin);


        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        DataViewModelFactory factory = new DataViewModelFactory(getActivity().getApplication());
        mDataViewModel = ViewModelProviders.of(this, factory).get(DataViewModel.class);

        btnLogin.setOnClickListener((v)->{
            String email = editEmail.getText().toString();
            String password = editPassword.getText().toString();

            firebaseAuth.signInWithEmailAndPassword(email, password).addOnFailureListener(e -> {
                Snackbar.make(v, "Login Error: "+e.getMessage(), Snackbar.LENGTH_LONG).show();
            });
        });

        mDataViewModel.mainfragment.setValue("LoginFragment");


        linkRegister.setOnClickListener((v)->{
            if( getActivity() instanceof AuthActivity) {
                AuthActivity authActivity = (AuthActivity) getActivity();
                authActivity.loadRegisterFragment();
            }
        });
    }

}
