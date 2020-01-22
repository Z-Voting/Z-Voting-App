package com.example.tkd.zvoting;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.example.tkd.zvoting.model.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class RegisterFragment extends Fragment {
    private FirebaseAuth firebaseAuth;
    private EditText editEmail;
    private EditText editPassword;
    private EditText editName;
    private Button btnRegister;
    private String email;
    private String password;
    private String name;

    DatabaseReference db;
    private DataViewModel mDataViewModel;


    public static RegisterFragment newInstance() {
        return new RegisterFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        view.findViewById(R.id.linkLogin).setOnClickListener((v)->{
            if(getActivity() instanceof AuthActivity) {
                ((AuthActivity) getActivity()).loadLoginFragment();
            }
        });

        firebaseAuth = FirebaseAuth.getInstance();

        editName = view.findViewById(R.id.editName);
        editEmail = view.findViewById(R.id.editEmail);
        editPassword = view.findViewById(R.id.editPassword);
        btnRegister = view.findViewById(R.id.btnRegister);

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

        mDataViewModel.mainfragment.setValue("RegisterFragment");


        btnRegister.setOnClickListener((v)->{
            name = editName.getText().toString();
            email = editEmail.getText().toString();
            password = editPassword.getText().toString();

            firebaseAuth.createUserWithEmailAndPassword(email, password).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Snackbar.make(v, "Login Error: "+e.getMessage(), Snackbar.LENGTH_LONG).show();
                }
            }).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    String uid = authResult.getUser().getUid();
                    User user = new User(name, email);
                    user.S1 = ( String.valueOf((uid+"1").hashCode()).replaceAll("-", "1") );
                    user.S2 = ( String.valueOf((uid+"2").hashCode()).replaceAll("-", "2") );
                    user.S3 = ( String.valueOf((uid+"3").hashCode()).replaceAll("-","3") );

                    db = FirebaseDatabase.getInstance().getReference();
                    db.child("liveUser").child(uid).setValue(user);
                }
            });

        });
    }
}
