package com.example.tkd.zvoting;

import android.app.Dialog;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tkd.zvoting.model.Candidate;
import com.example.tkd.zvoting.model.Election;
import com.example.tkd.zvoting.model.LoginChallenge;
import com.example.tkd.zvoting.model.User;
import com.example.tkd.zvoting.rest.AddCandidateRequest;
import com.example.tkd.zvoting.rest.ApiClient;
import com.example.tkd.zvoting.rest.ApiInterface;
import com.example.tkd.zvoting.rest.GetCandidatesRequest;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class MyElectionsListAdapter extends RecyclerView.Adapter<MyElectionsListAdapter.ViewHolder> {

    class ViewHolder extends RecyclerView.ViewHolder {
        View v;
        TextView electionNameTxt;
        TextView txtElectionStatus;
        Button btnVote;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            v = itemView;
            electionNameTxt = v.findViewById(R.id.txtElectionName);
            txtElectionStatus = v.findViewById(R.id.txtElectionStatus);
            btnVote = v.findViewById(R.id.btnVote);
        }
    }

    User user;
    ArrayList<Election> elections;
    Fragment context;
    ApiInterface apiInterface;
    DataViewModel mDataViewModel;
    AddCandidateRequest addCandidateRequest;
    View clickedView;
    DatabaseReference db;
    FirebaseAuth firebaseAuth;
    int pos;

    public MyElectionsListAdapter(Fragment context, ArrayList<Election> elections, User user) {
        this.context = context;
        this.elections = elections;
        this.user = user;
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        mDataViewModel = DataViewModel.getInstance(null);
        db = FirebaseDatabase.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();
    }

    private int checkedItem;
    private String[] candidateData;
    Dialog alertDialog = null;


    private void displayResultDialog(String[] data) {
        candidateData = data;

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context.getActivity());

        dialogBuilder.setTitle("Whom do you want to vote for?");

        dialogBuilder.setSingleChoiceItems(candidateData, checkedItem,
                (dialogInterface, which) -> {
                    checkedItem = which;
                });
        dialogBuilder.setPositiveButton("Vote Now", (dialog, which) -> castVote());
        dialogBuilder.setNegativeButton("Cancel", (dialog, which) -> cancelDialog());

        alertDialog = dialogBuilder.create();
        alertDialog.show();
    }

    private void displayVoteDialog(ArrayList<Candidate> data) {
        String[] names = new String[data.size()];
        for (int i = 0; i < data.size(); i++) {
            names[i] = data.get(i).getName();
        }
        candidateData = names;

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context.getActivity());

        dialogBuilder.setTitle("Whom do you want to vote for?");

        dialogBuilder.setSingleChoiceItems(candidateData, checkedItem,
                (dialogInterface, which) -> {
                    checkedItem = which;
                });
        dialogBuilder.setPositiveButton("Vote Now", (dialog, which) -> castVote());
        dialogBuilder.setNegativeButton("Cancel", (dialog, which) -> cancelDialog());

        alertDialog = dialogBuilder.create();
        alertDialog.show();
    }

    private void cancelDialog() {
        //Toast.makeText(context.getActivity(), "You selected: " + candidateData[checkedItem], Toast.LENGTH_SHORT).show();
    }

    private void castVote() {
        apiInterface.getLoginChallenge().enqueue(new Callback<LoginChallenge>() {
            @Override
            public void onResponse(Call<LoginChallenge> call, Response<LoginChallenge> response) {
                LoginChallenge loginChallenge = response.body();
                long a1 = loginChallenge.getA1();
                long a2 = loginChallenge.getA2();
                long a3 = loginChallenge.getA3();


            }

            @Override
            public void onFailure(Call<LoginChallenge> call, Throwable t) {

            }
        });
        Toast.makeText(context.getActivity(), "You selected: " + candidateData[checkedItem], Toast.LENGTH_SHORT).show();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.my_election_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        pos = position;
        Election election = elections.get(position);
        holder.electionNameTxt.setText(election.getName());


        holder.btnVote.setOnClickListener(v -> {
            if (election.isOver()) {
                String[] arr = {"baal", "saal", "maal"};
                displayResultDialog(arr);
            } else if (election.isRunning()) {

                Call<List<Candidate>> call = apiInterface.getCandidates(new GetCandidatesRequest(election.getId()));
                call.enqueue(new Callback<List<Candidate>>() {
                    @Override
                    public void onResponse(Call<List<Candidate>> call, Response<List<Candidate>> response) {
                        ArrayList<Candidate> candidates = new ArrayList<>(response.body());
                        displayVoteDialog(candidates);
                    }

                    @Override
                    public void onFailure(Call<List<Candidate>> call, Throwable t) {

                    }
                });
            }
        });


        if (election.isFresh()) {
            holder.btnVote.setText("Coming!!!");
            holder.btnVote.setEnabled(false);
            holder.txtElectionStatus.setText("Election hasn't started yet...");
        }

        if (election.isOver()) {
            holder.btnVote.setText("View Result");
            holder.btnVote.setEnabled(true);
            holder.txtElectionStatus.setText("Election is over...");
        }

        if (election.isRunning()) {
            holder.btnVote.setText("Result");
            holder.btnVote.setEnabled(true);
            final TextView electionStatus = holder.txtElectionStatus;
            String statusTxt = "Election Running...";
            electionStatus.setText(statusTxt);


            long timeRemaining = election.getRemainingTime();

            new CountDownTimer(timeRemaining * 1000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    long seconds = millisUntilFinished / 1000;

                    int day = (int) TimeUnit.SECONDS.toDays(seconds);
                    long hours = TimeUnit.SECONDS.toHours(seconds) - (day * 24);
                    long minute = TimeUnit.SECONDS.toMinutes(seconds) - (TimeUnit.SECONDS.toHours(seconds) * 60);
                    long second = TimeUnit.SECONDS.toSeconds(seconds) - (TimeUnit.SECONDS.toMinutes(seconds) * 60);

                    String statusTxt = "Election Running. " + day + " days " + hours + ":" + minute + ":" + second + " remaining";

                    electionStatus.setText(statusTxt);
                }

                @Override
                public void onFinish() {
                    electionStatus.setText("Finished");
                }
            }.start();
        }
    }

    private static String calculateV(Long n, long s1) {
        s1 %= n;
        s1 += n;
        s1 %= n;

        long v1 = (s1 * s1) % n;
        return String.valueOf(v1);
    }

    @Override
    public int getItemCount() {
        return elections.size();
    }


}
