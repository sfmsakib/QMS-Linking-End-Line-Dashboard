package com.sqgc.qms_tv_dashboard_sewing.model.dao;

import com.sqgc.qms_tv_dashboard_sewing.model.DataModel;

import java.util.List;

public interface RestResponse {
    public void onDataFetchedSuccess(List<DataModel> dataModels);
    public void onDataFetchedFailed(String msg);
}
