package com.example.tkd.zvoting;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tkd.zvoting.model.Election;
import com.example.tkd.zvoting.model.User;
import com.example.tkd.zvoting.rest.AddCandidateRequest;
import com.example.tkd.zvoting.rest.ApiClient;
import com.example.tkd.zvoting.rest.ApiInterface;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

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

        if(election.isFresh()) {
            holder.btnVote.setText("Coming!!!");
            holder.btnVote.setEnabled(false);
            holder.txtElectionStatus.setText("Election hasn't started yet...");
        }

        if(election.isOver()) {
            holder.btnVote.setText("View Result");
            holder.btnVote.setEnabled(true);
            holder.txtElectionStatus.setText("Election is over...");
        }

        if(election.isRunning()) {
            holder.btnVote.setText("Result");
            holder.btnVote.setEnabled(true);
            final TextView electionStatus = holder.txtElectionStatus;
            String statusTxt = "Election Running...";
            electionStatus.setText(statusTxt);


            long timeRemaining = election.getRemainingTime();

            new CountDownTimer(timeRemaining*1000,1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    long seconds = millisUntilFinished/1000;

                    int day = (int)TimeUnit.SECONDS.toDays(seconds);
                    long hours = TimeUnit.SECONDS.toHours(seconds) - (day *24);
                    long minute = TimeUnit.SECONDS.toMinutes(seconds) - (TimeUnit.SECONDS.toHours(seconds)* 60);
                    long second = TimeUnit.SECONDS.toSeconds(seconds) - (TimeUnit.SECONDS.toMinutes(seconds) *60);

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
