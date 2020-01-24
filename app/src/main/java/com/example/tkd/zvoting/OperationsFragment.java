package com.example.tkd.zvoting;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.tkd.zvoting.model.Election;
import com.example.tkd.zvoting.rest.ApiClient;
import com.example.tkd.zvoting.rest.ApiInterface;
import com.example.tkd.zvoting.rest.CreateElectionRequest;
import com.example.tkd.zvoting.rest.CreateElectionResponse;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OperationsFragment extends Fragment {

    private OperationsViewModel mViewModel;
    AlertDialog alertDialog;
    DataViewModel mDataViewModel;

    ApiInterface apiInterface;
    View root;
    private RecyclerView list;

    public static OperationsFragment newInstance() {
        return new OperationsFragment();
    }

    private void displayAddElectionDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        View layout = inflater.inflate(R.layout.add_election_layout, null);
        EditText editElectionName = (EditText) layout.findViewById(R.id.editElectionName);
        EditText editElectionDuration = (EditText) layout.findViewById(R.id.editElectionDuration);

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(layout)
                // Add action buttons
                .setPositiveButton("Create Election", (dialog, id) -> {
                    String electionName=editElectionName.getText().toString();
                    String electionDuration=editElectionDuration.getText().toString();

                    ProgressDialog progress = new ProgressDialog(getContext());
                    progress.setTitle("Loading");
                    progress.setMessage("Wait while loading...");
                    progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
                    progress.show();

                    apiInterface.createElection(new CreateElectionRequest(electionName, electionDuration)).enqueue(new Callback<CreateElectionResponse>() {
                        @Override
                        public void onResponse(Call<CreateElectionResponse> call, Response<CreateElectionResponse> response) {
                            Snackbar.make(root, "Election Created", Snackbar.LENGTH_LONG).show();
                            progress.dismiss();
                        }

                        @Override
                        public void onFailure(Call<CreateElectionResponse> call, Throwable t) {
                            Snackbar.make(root, "Error: " + t, Snackbar.LENGTH_LONG).show();
                            progress.dismiss();
                        }
                    });
                })
                .setNegativeButton("Cancel", (dialog, id) -> {
                    OperationsFragment.this.alertDialog.cancel();
                });

        alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.operations_fragment, container, false);

        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        list = root.findViewById(R.id.list);

        root.findViewById(R.id.btnAddElection).setOnClickListener(v -> displayAddElectionDialog());
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        mDataViewModel.mainfragment.setValue(DataViewModel.ElectionResultsFragment);


        mViewModel.liveElections.observe(this, new Observer<ArrayList<Election>>() {
            @Override
            public void onChanged(ArrayList<Election> elections) {
                list.setAdapter(new OperationsAdapter(OperationsFragment.this, elections, mViewModel.mDataViewModel.liveUser.getValue()));
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(OperationsViewModel.class);
        mDataViewModel = DataViewModel.getInstance(null);
    }

}
