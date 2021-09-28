package com.sqgc.qms_tv_dashboard_linking.model.dao;

import com.sqgc.qms_tv_dashboard_linking.model.DataModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface ApiInterface {
    @Headers("Accept: application/json")
    @GET("GetDashboardDataForLinkIn")
    Call<List<DataModel>> getDashboardData();
}
