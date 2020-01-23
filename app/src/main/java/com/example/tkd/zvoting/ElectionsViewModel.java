package com.example.tkd.zvoting;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.tkd.zvoting.model.Election;
import com.example.tkd.zvoting.rest.ApiClient;
import com.example.tkd.zvoting.rest.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ElectionsViewModel extends AndroidViewModel {
    DataViewModel mDataViewModel;

    MutableLiveData<ArrayList<Election>> liveElections;
    ApiInterface apiInterface;

    public ElectionsViewModel(@NonNull Application application) {
        super(application);
        mDataViewModel = DataViewModel.getInstance(application);
        liveElections = new MutableLiveData<>(new ArrayList<>());

        apiInterface = ApiClient.getClient().create(ApiInterface.class);
    }

    public void getElections() {
        Call<List<Election>> call = apiInterface.getElections();
        call.enqueue(new Callback<List<Election>>() {
            @Override
            public void onResponse(Call<List<Election>> call, Response<List<Election>> response) {
                List<Election> body = response.body();

                ArrayList<Election> elections = new ArrayList<>();
                elections.addAll(body);

                liveElections.setValue(elections);
            }

            @Override
            public void onFailure(Call<List<Election>> call, Throwable t) {
                Log.e("TKD", "Error: " + t);
            }
        });
    }
}
