package com.sqgc.qms_tv_dashboard_sewing.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HourlyActualPcs {
    @SerializedName("hourName")
    @Expose
    private String hourName;
    @SerializedName("hourlyActualPcs")
    @Expose
    private int hourlyActualPcs;

    public HourlyActualPcs(String hourName, int hourlyActualPcs) {
        this.hourName = hourName;
        this.hourlyActualPcs = hourlyActualPcs;
    }

    public String getHourName() {
        return hourName;
    }

    public void setHourName(String hourName) {
        this.hourName = hourName;
    }

    public int getHourlyActualPcs() {
        return hourlyActualPcs;
    }

    public void setHourlyActualPcs(int hourlyActualPcs) {
        this.hourlyActualPcs = hourlyActualPcs;
    }

    @Override
    public String toString() {
        return "HourlyActualPcs{" +
                "hourName='" + hourName + '\'' +
                ", hourlyActualPcs=" + hourlyActualPcs +
                '}';
    }
}
