package com.example.tkd.zvoting;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

public class MyElectionsViewModel extends AndroidViewModel {
    DataViewModel mDataViewModel;

    public MyElectionsViewModel(@NonNull Application application) {
        super(application);
        mDataViewModel = DataViewModel.getInstance(application);
    }
}
