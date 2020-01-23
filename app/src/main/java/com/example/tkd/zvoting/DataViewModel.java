package com.example.tkd.zvoting;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.tkd.zvoting.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


class DataViewModelFactory implements ViewModelProvider.Factory {

    private Application application;

    public DataViewModelFactory(Application application) {
        this.application = application;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) DataViewModel.getInstance(application);
    }
}

public class DataViewModel extends AndroidViewModel {

    public static long n = 1000000007;
    public static String URL="http://52.191.210.249:3000";
    public static String MyElectionsFragment="MyElectionsFragment";
    public static String ElectionResultsFragment="ElectionResultsFragment";
    public static String ElectionsFragment="ElectionsFragment";
    public String userEmailStr;
    public String userNameStr;
    public FirebaseAuth firebaseAuth;
    public MutableLiveData<User> liveUser;
    public DatabaseReference databaseReference;

    public MutableLiveData<String> activity, mainfragment;


    private static  DataViewModel instance;

    public static DataViewModel getInstance(@NonNull Application application) {
        if(instance==null) instance = new DataViewModel(application);
        return instance;
    }

     DataViewModel(@NonNull Application application) {
        super(application);
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        liveUser = new MutableLiveData<>();
        liveUser.setValue( new User(userNameStr, userEmailStr) );
        activity = new MutableLiveData<>();
        mainfragment = new MutableLiveData<>();


        firebaseAuth.addAuthStateListener(new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser()!=null) {
                    String uid = firebaseAuth.getCurrentUser().getUid();
                    databaseReference.child("user").child(uid).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            User user = (User) dataSnapshot.getValue(User.class);
                            liveUser.setValue( user );
                            Log.e("TKD", "USER FOUND");
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
            }
        });
    }

}
