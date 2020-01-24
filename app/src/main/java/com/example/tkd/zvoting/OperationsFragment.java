package com.example.tkd.zvoting;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class OperationsFragment extends Fragment {

    private OperationsViewModel mViewModel;
    AlertDialog alertDialog;
    DataViewModel mDataViewModel;

    public static OperationsFragment newInstance() {
        return new OperationsFragment();
    }

    private void displayAddElectionDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        View layout = inflater.inflate(R.layout.add_election_layout, null);
        EditText editElectionName = (EditText) layout.findViewById(R.id.editElectionName);

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(layout)
                // Add action buttons
                .setPositiveButton("Create Election", (dialog, id) -> {

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
        View root = inflater.inflate(R.layout.operations_fragment, container, false);

        root.findViewById(R.id.btnAddElection).setOnClickListener(v -> displayAddElectionDialog());
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        mDataViewModel.mainfragment.setValue(DataViewModel.ElectionResultsFragment);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(OperationsViewModel.class);
        mDataViewModel = DataViewModel.getInstance(null);
    }

}
