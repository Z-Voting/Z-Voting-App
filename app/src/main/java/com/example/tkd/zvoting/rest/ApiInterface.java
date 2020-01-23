package com.example.tkd.zvoting.rest;

import com.example.tkd.zvoting.model.Candidate;
import com.example.tkd.zvoting.model.Election;
import com.example.tkd.zvoting.model.LoginChallenge;
import com.example.tkd.zvoting.model.ResponseMessage;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {

    @GET("/getElections")
    Call<List<Election>> getElections();

    @POST("/addCandidate")
    Call<ResponseMessage> addCandidate(@Body AddCandidateRequest addCandidateRequest);

    @POST("/registerVoter")
    Call<ResponseMessage> registerVoter(@Body AddVoterRequest addVoterRequest);

    @POST("/getCandidates")
    Call<List<Candidate>> getCandidates(@Body GetCandidatesRequest getCandidatesRequest);

    @POST("/getLoginChallenge")
    Call<LoginChallenge> getLoginChallenge();
}
