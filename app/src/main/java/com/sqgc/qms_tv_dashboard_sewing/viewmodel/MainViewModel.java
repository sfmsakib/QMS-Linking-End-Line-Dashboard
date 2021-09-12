package com.sqgc.qms_tv_dashboard_sewing.viewmodel;

import android.app.Application;
import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.sqgc.qms_tv_dashboard_sewing.model.DataModel;
import com.sqgc.qms_tv_dashboard_sewing.model.dao.RestResponse;
import com.sqgc.qms_tv_dashboard_sewing.model.repository.RestRepository;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainViewModel extends AndroidViewModel {

    RestRepository restRepository;
    public MainViewModel(@NonNull @NotNull Application application) {
        super(application);
        restRepository = new RestRepository();
    }


    public LiveData<List<DataModel>> getDashboardData(){
        MutableLiveData<List<DataModel>> dataMutableLiveData = new MutableLiveData<>();

        restRepository.getDashboardDataModel(new RestResponse() {
            @Override
            public void onDataFetchedSuccess(List<DataModel> dataModelList) {
                dataMutableLiveData.setValue(dataModelList);
            }

            @Override
            public void onDataFetchedFailed(String msg) {
                dataMutableLiveData.setValue(null);
            }
        });
        return dataMutableLiveData;
    }
    MutableLiveData<String> dateLive = new MutableLiveData<>();
    public LiveData<String> getDateTime(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                setTime();
            }

        }, 1000);



        return dateLive;
    }
    private void setTime() {
        //String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        //String finalValue = String.valueOf("Date: "+currentDate+" | Time: "+currentTime);

        String currentDateTime = new SimpleDateFormat("hh:mm aaa", Locale.getDefault()).format(new Date());
        dateLive.setValue(currentDateTime);
        getDateTime();
    }

}
