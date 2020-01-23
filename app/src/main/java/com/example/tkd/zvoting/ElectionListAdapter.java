package com.example.tkd.zvoting;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tkd.zvoting.model.Election;
import com.example.tkd.zvoting.model.ResponseMessage;
import com.example.tkd.zvoting.model.User;
import com.example.tkd.zvoting.rest.AddCandidateRequest;
import com.example.tkd.zvoting.rest.AddVoterRequest;
import com.example.tkd.zvoting.rest.ApiClient;
import com.example.tkd.zvoting.rest.ApiInterface;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class ElectionListAdapter extends RecyclerView.Adapter<ElectionListAdapter.ViewHolder> {

    class ViewHolder extends RecyclerView.ViewHolder {
        View v;
        TextView electionNameTxt;
        Button btnAddCandidate;
        Button btnAddVoter;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            v = itemView;
            electionNameTxt = v.findViewById(R.id.electionName);
            btnAddCandidate = v.findViewById(R.id.btnAddCandidate);
            btnAddVoter = v.findViewById(R.id.btnAddVoter);
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

    public ElectionListAdapter(Fragment context, ArrayList<Election> elections, User user) {
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
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.election_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        pos = position;
        Election election = elections.get(position);
        holder.electionNameTxt.setText(election.getName());


        holder.btnAddCandidate.setOnClickListener((v) -> {
            clickedView = v;

            if (firebaseAuth.getUid() != null)
                db.child("ownElection").child(firebaseAuth.getUid()).child(elections.get(pos).getId()).setValue(elections.get(pos));

            String imgAddress = "https://i7.pngguru.com/preview/1001/200/876/voting-election-computer-icons-electoral-symbol-politics-politics.jpg";
            addCandidateRequest = new AddCandidateRequest(user.name, user.name, imgAddress, elections.get(pos).getId());

            Call<ResponseMessage> responseMessageCall = apiInterface.addCandidate(addCandidateRequest);
            responseMessageCall.enqueue(new Callback<ResponseMessage>() {
                @Override
                public void onResponse(Call<ResponseMessage> call, Response<ResponseMessage> response) {
                    Snackbar.make(clickedView, "Candidate added", Snackbar.LENGTH_LONG).show();
                }

                @Override
                public void onFailure(Call<ResponseMessage> call, Throwable t) {
                    Snackbar.make(clickedView, "Failure: " + t, Snackbar.LENGTH_LONG).show();
                }
            });
        });

        holder.btnAddVoter.setOnClickListener((v) -> {
            clickedView = v;

            Long n = DataViewModel.n;

            long s1 = Long.parseLong(user.s1);
            String v1 = calculateV(n, s1);

            long s2 = Long.parseLong(user.s2);
            String v2 = calculateV(n, s2);

            long s3 = Long.parseLong(user.s3);
            String v3 = calculateV(n, s3);


            if (firebaseAuth.getUid() != null)
                db.child("ownElection").child(firebaseAuth.getUid()).child(elections.get(pos).getId()).setValue(elections.get(pos));

            AddVoterRequest addVoterRequest = new AddVoterRequest(user.name, user.email, v1, v2, v3, elections.get(pos).getId());

            Call<ResponseMessage> responseMessageCall = apiInterface.registerVoter(addVoterRequest);
            responseMessageCall.enqueue(new Callback<ResponseMessage>() {
                @Override
                public void onResponse(Call<ResponseMessage> call, Response<ResponseMessage> response) {
                    Snackbar.make(clickedView, "Voter Registration Successful", Snackbar.LENGTH_LONG).show();
                }

                @Override
                public void onFailure(Call<ResponseMessage> call, Throwable t) {
                    Snackbar.make(clickedView, "Failure: " + t, Snackbar.LENGTH_LONG).show();
                }
            });

        });
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
