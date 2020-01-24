package com.example.tkd.zvoting;

import android.app.Application;
import android.os.CountDownTimer;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tkd.zvoting.model.Election;
import com.example.tkd.zvoting.rest.ApiClient;
import com.example.tkd.zvoting.rest.ApiInterface;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ElectionResultsViewModel extends AndroidViewModel {
    DataViewModel mDataViewModel;
    ApiInterface apiInterface;
    MutableLiveData<ArrayList<Election>> liveElections;

    MutableLiveData<ArrayList<Election>> liveMyElections;

    DatabaseReference db;
    FirebaseAuth firebaseAuth;
    MutableLiveData<ArrayList<String>> liveMyElectionIds;

    public ElectionResultsViewModel(@NonNull Application application) {
        super(application);
        db = FirebaseDatabase.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();

        mDataViewModel = DataViewModel.getInstance(application);

        liveElections = new MutableLiveData<>(new ArrayList<>());
        liveMyElections = new MutableLiveData<>(new ArrayList<>());
        liveMyElectionIds = new MutableLiveData<>(new ArrayList<>());

        mDataViewModel = DataViewModel.getInstance(application);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);


        getElections();

        db.child("ownedElection").child(firebaseAuth.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<String> myElectionIds = new ArrayList<>();

                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    myElectionIds.add(child.getKey());
                }

                liveMyElectionIds.setValue(myElectionIds);

                if (liveElections.getValue() != null && liveElections.getValue().size() > 0) {
                    ArrayList<Election> myElections = new ArrayList<>();

                    for (Election election : liveElections.getValue()) {
                        if (myElectionIds.contains(election.getId())) {
                            myElections.add(election);
                        }
                    }
                    liveMyElections.setValue(myElections);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void getElections() {
        Call<List<Election>> call = apiInterface.getElections();
        call.enqueue(new Callback<List<Election>>() {
            @Override
            public void onResponse(Call<List<Election>> call, Response<List<Election>> response) {
                List<Election> body = response.body();

                ArrayList<Election> elections = new ArrayList<>();
                elections.addAll(body);

                if (!elections.equals(liveElections.getValue())) {
                    liveElections.setValue(elections);
                }


                if (liveMyElectionIds.getValue() != null && liveMyElectionIds.getValue().size() > 0) {
                    ArrayList<String> myElectionIds = liveMyElectionIds.getValue();
                    ArrayList<Election> myElections = new ArrayList<>();

                    for (Election election : elections) {
                        if (myElectionIds.contains(election.getId())) {
                            myElections.add(election);
                        }
                    }
                    liveMyElections.setValue(myElections);

                }
            }

            @Override
            public void onFailure(Call<List<Election>> call, Throwable t) {
                Log.e("TKD", "Error: " + t);
            }
        });

        new CountDownTimer(10000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                getElections();
            }
        }.start();
    }
}
