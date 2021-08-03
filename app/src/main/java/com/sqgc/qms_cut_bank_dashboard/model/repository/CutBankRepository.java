package com.sqgc.qms_cut_bank_dashboard.model.repository;

import android.os.Handler;

import com.sqgc.qms_cut_bank_dashboard.model.CutBankModel;
import com.sqgc.qms_cut_bank_dashboard.model.dao.ApiInterface;
import com.sqgc.qms_cut_bank_dashboard.model.dao.CutBankResponse;
import com.sqgc.qms_cut_bank_dashboard.model.network.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CutBankRepository {
    ApiInterface apiInterface;
    public CutBankRepository() {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
    }
    public void getCutBankData(CutBankResponse cutBankResponse){
        ArrayList<CutBankModel> cutBankModelArrayListDemo = new ArrayList<>();
        cutBankModelArrayListDemo.add(new CutBankModel("Unit1", "Line77","4817-Hileg", "12313124124-TBA-941234", 455,535,1534523));
        cutBankModelArrayListDemo.add(new CutBankModel("Unit1", "Line88","4817-Hileg", "12313124124-TBA-933113", 5435,452,12533));
        cutBankModelArrayListDemo.add(new CutBankModel("Unit2", "Line99","4817-Hileg", "12313124124-TBA-954234", 245,3443,3535));
        cutBankModelArrayListDemo.add(new CutBankModel("Unit2", "Line55","4817-Hileg", "12313124124-TBA-935153", 24545,4543,63645));
        cutBankResponse.onDataFetchedSuccess(cutBankModelArrayListDemo);


        //callAPIWithDelay(cutBankResponse);


    }
    private void callAPIWithDelay(CutBankResponse cutBankResponse){
        Handler handler =new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                callApi(cutBankResponse);
                callAPIWithDelay(cutBankResponse);
            }
        }, 50000);
    }

    private void callApi(CutBankResponse cutBankResponse) {
        Call<List<CutBankModel>> getData = apiInterface.getCutBankData();
        getData.enqueue(new Callback<List<CutBankModel>>() {
            @Override
            public void onResponse(Call<List<CutBankModel>> call, Response<List<CutBankModel>> response) {
                if (response.isSuccessful()){
                    if (response.body() != null){
                        cutBankResponse.onDataFetchedSuccess(response.body());
                    }else {
                        cutBankResponse.onDataFetchedFailed("Failed to fetched data : Body null");
                    }
                }else {
                    cutBankResponse.onDataFetchedFailed("Failed to fetched data : Response fail");
                }
            }
            @Override
            public void onFailure(Call<List<CutBankModel>> call, Throwable t) {
                cutBankResponse.onDataFetchedFailed("Failed to fetched data :"+t.getMessage().toString());

            }
        });
    }
}
