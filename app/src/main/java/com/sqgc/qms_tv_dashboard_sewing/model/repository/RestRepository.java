package com.sqgc.qms_tv_dashboard_sewing.model.repository;

import android.os.Handler;
import android.util.Log;

import com.sqgc.qms_tv_dashboard_sewing.model.DataModel;
import com.sqgc.qms_tv_dashboard_sewing.model.dao.ApiInterface;
import com.sqgc.qms_tv_dashboard_sewing.model.dao.RestResponse;
import com.sqgc.qms_tv_dashboard_sewing.model.network.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestRepository {
    ApiInterface apiInterface;
    public RestRepository() {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
    }

    public void getDashboardDataModel(RestResponse restResponse){
//        ArrayList<CutBankModel> cutBankModelArrayListDemo = new ArrayList<>();
//        cutBankModelArrayListDemo.add(new CutBankModel("Unit1", "Line77","4817-Hileg", "12313124124-TBA-941234", 455,535,1534523));
//        cutBankModelArrayListDemo.add(new CutBankModel("Unit1", "Line88","4817-Hileg", "12313124124-TBA-933113", 5435,452,12533));
//        cutBankModelArrayListDemo.add(new CutBankModel("Unit2", "Line99","4817-Hileg", "12313124124-TBA-954234", 245,3443,3535));
//        cutBankModelArrayListDemo.add(new CutBankModel("Unit2", "Line55","4817-Hileg", "12313124124-TBA-935153", 24545,4543,63645));
//        cutBankResponse.onDataFetchedSuccess(cutBankModelArrayListDemo);


        callApi(restResponse);
        callAPIWithDelay(restResponse);


    }
    private void callAPIWithDelay(RestResponse restResponse){
        Handler handler =new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                callApi(restResponse);
                callAPIWithDelay(restResponse);
            }
        }, 50000);
    }

    private void callApi(RestResponse restResponse) {
        Call<List<DataModel>> getData = apiInterface.getDashboardData();
        getData.enqueue(new Callback<List<DataModel>>() {
            @Override
            public void onResponse(Call<List<DataModel>> call, Response<List<DataModel>> response) {
                if (response.isSuccessful()){
                    if (response.body() != null){
                        restResponse.onDataFetchedSuccess(response.body());
                    }else {
                        restResponse.onDataFetchedFailed("Failed to fetched data : Body null");
                        Log.i("CUT_BANK_DASHBOARD","Failed to fetched data : Body null"+response.message());
                    }
                }else {
                    restResponse.onDataFetchedFailed("Failed to fetched data : Response fail");
                    Log.i("CUT_BANK_DASHBOARD"," Failed to fetched data : Response fail"+response.message());
                }
            }
            @Override
            public void onFailure(Call<List<DataModel>> call, Throwable t) {
                Log.i("CUT_BANK_DASHBOARD","Failed to fetched data :"+t.getMessage().toString());
                restResponse.onDataFetchedFailed("Failed to fetched data :"+t.getMessage().toString());

            }
        });
    }
}
