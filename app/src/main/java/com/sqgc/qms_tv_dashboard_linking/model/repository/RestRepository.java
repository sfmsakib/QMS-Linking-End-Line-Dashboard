package com.sqgc.qms_tv_dashboard_linking.model.repository;

import android.os.Handler;
import android.util.Log;

import com.sqgc.qms_tv_dashboard_linking.model.DataModel;
import com.sqgc.qms_tv_dashboard_linking.model.dao.ApiInterface;
import com.sqgc.qms_tv_dashboard_linking.model.dao.RestResponse;
import com.sqgc.qms_tv_dashboard_linking.model.network.ApiClient;

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
//        ArrayList<DataModel> dataModelArrayList = new ArrayList<>();
//        ArrayList<HourlyActualPcs> hourlyActualPcs = new ArrayList<>();
//        hourlyActualPcs.add(new HourlyActualPcs("9",200));
//        hourlyActualPcs.add(new HourlyActualPcs("10",320));
//        hourlyActualPcs.add(new HourlyActualPcs("11",190));
//        hourlyActualPcs.add(new HourlyActualPcs("12",400));
//        hourlyActualPcs.add(new HourlyActualPcs("1",300));
//        hourlyActualPcs.add(new HourlyActualPcs("2",0));
//        hourlyActualPcs.add(new HourlyActualPcs("3",400));
//        hourlyActualPcs.add(new HourlyActualPcs("4",120));
//        hourlyActualPcs.add(new HourlyActualPcs("5",70));
//        hourlyActualPcs.add(new HourlyActualPcs("6",302));
//        hourlyActualPcs.add(new HourlyActualPcs("7",250));
//        hourlyActualPcs.add(new HourlyActualPcs("8",450));
//
//        ArrayList<HourlyActualPcs> hourlyActualPcs1 = new ArrayList<>();
//        hourlyActualPcs1.add(new HourlyActualPcs("9",300));
//        hourlyActualPcs1.add(new HourlyActualPcs("10",420));
//        hourlyActualPcs1.add(new HourlyActualPcs("11",590));
//        hourlyActualPcs1.add(new HourlyActualPcs("12",100));
//        hourlyActualPcs1.add(new HourlyActualPcs("1",150));
//        hourlyActualPcs1.add(new HourlyActualPcs("2",0));
//        hourlyActualPcs1.add(new HourlyActualPcs("3",200));
//        hourlyActualPcs1.add(new HourlyActualPcs("4",250));
//        hourlyActualPcs1.add(new HourlyActualPcs("5",350));
//        hourlyActualPcs1.add(new HourlyActualPcs("6",200));
//        hourlyActualPcs1.add(new HourlyActualPcs("7",150));
//        hourlyActualPcs1.add(new HourlyActualPcs("8",450));
//
//        ArrayList<TopDefect> topDefects = new ArrayList<>();
//        topDefects.add(new TopDefect(1,"Up Down",10,230));
//        topDefects.add(new TopDefect(2,"Wavy",7,180));
//        topDefects.add(new TopDefect(3,"Uncut thread",5,120));
//        topDefects.add(new TopDefect(4,"Stretch",3,54));
//        topDefects.add(new TopDefect(5,"Needle drop",1,13));
//
//        ArrayList<TopDefect> topDefects1 = new ArrayList<>();
//        topDefects1.add(new TopDefect(1,"Wavy",8,234));
//        topDefects1.add(new TopDefect(2,"Up Down",6,156));
//        topDefects1.add(new TopDefect(3,"Stretch",4,80));
//        topDefects1.add(new TopDefect(4,"Uncut thread",2,44));
//        topDefects1.add(new TopDefect(5,"Needle drop",1,11));
//
//        dataModelArrayList.add(new DataModel("Line-3","H&M",120,80,40,1100,700,560,540,140,76.43f,hourlyActualPcs,3200,5.06f,264,10.2f,topDefects));
//        dataModelArrayList.add(new DataModel("Line-6","Calvin Klein",140,30,110,1200,800,660,540,140,66.43f,hourlyActualPcs1,4200,4.06f,262,12.2f,topDefects1));
//


        //restResponse.onDataFetchedSuccess(dataModelArrayList);

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
                        Log.i("DASHBOARD_LOG","Failed to fetched data : Body null"+response.message());
                    }
                }else {
                    restResponse.onDataFetchedFailed("Failed to fetched data : Response fail");
                    Log.i("DASHBOARD_LOG"," Failed to fetched data : Response fail"+response.message());
                }
            }
            @Override
            public void onFailure(Call<List<DataModel>> call, Throwable t) {
                Log.i("DASHBOARD_LOG","Failed to fetched data :"+t.getMessage().toString());
                restResponse.onDataFetchedFailed("Failed to fetched data :"+t.getMessage().toString());

            }
        });
    }
}
