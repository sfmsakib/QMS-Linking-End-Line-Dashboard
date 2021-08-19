package com.sqgc.qms_tv_dashboard_sewing.model.dao;

import com.sqgc.qms_tv_dashboard_sewing.model.DataModel;

import java.util.List;

public interface CutBankResponse {
    public void onDataFetchedSuccess(List<DataModel> cutBankModel);
    public void onDataFetchedFailed(String msg);
}
