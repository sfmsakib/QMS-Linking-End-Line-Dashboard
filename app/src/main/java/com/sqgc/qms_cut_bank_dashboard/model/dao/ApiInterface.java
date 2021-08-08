package com.sqgc.qms_cut_bank_dashboard.model.dao;

import com.sqgc.qms_cut_bank_dashboard.model.CutBankModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiInterface {
    @Headers("Accept: application/json")
    @GET("CutBankReports")
    Call<List<CutBankModel>> getCutBankData();
}
