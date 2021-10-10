package com.sqgc.qms_tv_dashboard_linking.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HourlyActualPcs {
    @SerializedName("hourName")
    @Expose
    private String hourName;
    @SerializedName("hourlyActualPcs")
    @Expose
    private int hourlyActualPcs;

    @SerializedName("hourlyTargetPcs")
    @Expose
    private int hourlyTargetPcs;

    public HourlyActualPcs(String hourName, int hourlyActualPcs, int hourlyTargetPcs) {
        this.hourName = hourName;
        this.hourlyActualPcs = hourlyActualPcs;
        this.hourlyTargetPcs = hourlyTargetPcs;
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

    public int getHourlyTargetPcs() {
        return hourlyTargetPcs;
    }

    public void setHourlyTargetPcs(int hourlyTargetPcs) {
        this.hourlyTargetPcs = hourlyTargetPcs;
    }

    @Override
    public String toString() {
        return "HourlyActualPcs{" +
                "hourName='" + hourName + '\'' +
                ", hourlyActualPcs=" + hourlyActualPcs +
                ", hourlyTargetPcs=" + hourlyTargetPcs +
                '}';
    }
}
