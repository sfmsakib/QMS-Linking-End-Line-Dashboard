package com.sqgc.qms_cut_bank_dashboard.model.dao;

import com.sqgc.qms_cut_bank_dashboard.model.CutBankModel;

import java.util.List;

public interface CutBankResponse {
    public void onDataFetchedSuccess(List<CutBankModel> cutBankModel);
    public void onDataFetchedFailed(String msg);
}
