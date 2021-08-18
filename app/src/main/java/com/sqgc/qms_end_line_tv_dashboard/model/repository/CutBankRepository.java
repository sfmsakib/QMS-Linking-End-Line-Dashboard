package com.sqgc.qms_end_line_tv_dashboard.model.repository;

import android.os.Handler;
import android.util.Log;

import com.sqgc.qms_end_line_tv_dashboard.model.DataModel;
import com.sqgc.qms_end_line_tv_dashboard.model.dao.ApiInterface;
import com.sqgc.qms_end_line_tv_dashboard.model.dao.CutBankResponse;
import com.sqgc.qms_end_line_tv_dashboard.model.network.ApiClient;

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
//        ArrayList<CutBankModel> cutBankModelArrayListDemo = new ArrayList<>();
//        cutBankModelArrayListDemo.add(new CutBankModel("Unit1", "Line77","4817-Hileg", "12313124124-TBA-941234", 455,535,1534523));
//        cutBankModelArrayListDemo.add(new CutBankModel("Unit1", "Line88","4817-Hileg", "12313124124-TBA-933113", 5435,452,12533));
//        cutBankModelArrayListDemo.add(new CutBankModel("Unit2", "Line99","4817-Hileg", "12313124124-TBA-954234", 245,3443,3535));
//        cutBankModelArrayListDemo.add(new CutBankModel("Unit2", "Line55","4817-Hileg", "12313124124-TBA-935153", 24545,4543,63645));
//        cutBankResponse.onDataFetchedSuccess(cutBankModelArrayListDemo);


        callApi(cutBankResponse);
        callAPIWithDelay(cutBankResponse);


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
        Call<List<DataModel>> getData = apiInterface.getCutBankData();
        getData.enqueue(new Callback<List<DataModel>>() {
            @Override
            public void onResponse(Call<List<DataModel>> call, Response<List<DataModel>> response) {
                if (response.isSuccessful()){
                    if (response.body() != null){
                        cutBankResponse.onDataFetchedSuccess(response.body());
                    }else {
                        cutBankResponse.onDataFetchedFailed("Failed to fetched data : Body null");
                        Log.i("CUT_BANK_DASHBOARD","Failed to fetched data : Body null"+response.message());
                    }
                }else {
                    cutBankResponse.onDataFetchedFailed("Failed to fetched data : Response fail");
                    Log.i("CUT_BANK_DASHBOARD"," Failed to fetched data : Response fail"+response.message());
                }
            }
            @Override
            public void onFailure(Call<List<DataModel>> call, Throwable t) {
                Log.i("CUT_BANK_DASHBOARD","Failed to fetched data :"+t.getMessage().toString());
                cutBankResponse.onDataFetchedFailed("Failed to fetched data :"+t.getMessage().toString());

            }
        });
    }
}
