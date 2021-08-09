package com.sqgc.qms_cut_bank_dashboard.viewmodel;

import android.app.Application;
import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.sqgc.qms_cut_bank_dashboard.model.CutBankModel;
import com.sqgc.qms_cut_bank_dashboard.model.dao.ApiInterface;
import com.sqgc.qms_cut_bank_dashboard.model.dao.CutBankResponse;
import com.sqgc.qms_cut_bank_dashboard.model.network.ApiClient;
import com.sqgc.qms_cut_bank_dashboard.model.repository.CutBankRepository;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CutBankViewModel extends AndroidViewModel {

    CutBankRepository cutBankRepository;
    public CutBankViewModel(@NonNull @NotNull Application application) {
        super(application);
        cutBankRepository = new CutBankRepository();
    }


    public LiveData<List<CutBankModel>> getCutBankData(){
        MutableLiveData<List<CutBankModel>> cutBankModelLiveData = new MutableLiveData<>();

        cutBankRepository.getCutBankData(new CutBankResponse() {
            @Override
            public void onDataFetchedSuccess(List<CutBankModel> cutBankModel) {
                cutBankModelLiveData.setValue(cutBankModel);
            }

            @Override
            public void onDataFetchedFailed(String msg) {
                cutBankModelLiveData.setValue(null);
            }
        });
        return cutBankModelLiveData;
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

        String currentDateTime = new SimpleDateFormat("'Date:' yyyy-MM-dd '| Time:' HH:mm:ss aaa", Locale.getDefault()).format(new Date());
        dateLive.setValue(currentDateTime);
        getDateTime();
    }

}
