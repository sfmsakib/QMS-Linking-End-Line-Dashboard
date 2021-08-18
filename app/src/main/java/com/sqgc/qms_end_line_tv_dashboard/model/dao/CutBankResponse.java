package com.sqgc.qms_end_line_tv_dashboard.model.dao;

import com.sqgc.qms_end_line_tv_dashboard.model.DataModel;

import java.util.List;

public interface CutBankResponse {
    public void onDataFetchedSuccess(List<DataModel> cutBankModel);
    public void onDataFetchedFailed(String msg);
}