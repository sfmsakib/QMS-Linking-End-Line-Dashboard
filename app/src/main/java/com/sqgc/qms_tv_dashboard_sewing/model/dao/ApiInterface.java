package com.sqgc.qms_tv_dashboard_sewing.model.dao;

import com.sqgc.qms_tv_dashboard_sewing.model.DataModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface ApiInterface {
    @Headers("Accept: application/json")
    @GET("CutBankReports")
    Call<List<DataModel>> getCutBankData();
}
