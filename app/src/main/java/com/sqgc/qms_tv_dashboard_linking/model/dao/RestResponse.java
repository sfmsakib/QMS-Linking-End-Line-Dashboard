package com.sqgc.qms_tv_dashboard_linking.model.dao;

import com.sqgc.qms_tv_dashboard_linking.model.DataModel;

import java.util.List;

public interface RestResponse {
    public void onDataFetchedSuccess(List<DataModel> dataModels);
    public void onDataFetchedFailed(String msg);
}
