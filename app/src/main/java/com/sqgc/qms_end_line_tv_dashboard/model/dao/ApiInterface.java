package com.sqgc.qms_end_line_tv_dashboard.model.dao;

import com.sqgc.qms_end_line_tv_dashboard.model.DataModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface ApiInterface {
    @Headers("Accept: application/json")
    @GET("CutBankReports")
    Call<List<DataModel>> getCutBankData();
}
