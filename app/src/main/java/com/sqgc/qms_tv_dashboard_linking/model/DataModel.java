package com.sqgc.qms_tv_dashboard_linking.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataModel {
    @SerializedName("lineName")
    @Expose
    private String lineName;
    @SerializedName("buyerName")
    @Expose
    private String buyerName;
    @SerializedName("hourlyTarget")
    @Expose
    private int hourlyTarget;
    @SerializedName("actualPcs")
    @Expose
    private int actualPcs;
    @SerializedName("variance")
    @Expose
    private int variance;
    @SerializedName("plannedPcs")
    @Expose
    private int plannedPcs;
    @SerializedName("cumPlannedPcs")
    @Expose
    private int cumPlannedPcs;
    @SerializedName("cumActualPcs")
    @Expose
    private int cumActualPcs;
    @SerializedName("balanceToProduce")
    @Expose
    private int balanceToProduce;
    @SerializedName("cumVariance")
    @Expose
    private int cumVariance;
    @SerializedName("efficiency")
    @Expose
    private float efficiency;
    @SerializedName("hourlyActualPcsList")
    @Expose
    private List<HourlyActualPcs> hourlyActualPcsList;
    @SerializedName("defectivePcs")
    @Expose
    private int defectivePcs;
    @SerializedName("defectPercentage")
    @Expose
    private float defectPercentage;
    @SerializedName("totalDefect")
    @Expose
    private int totalDefect;
    @SerializedName("dhu")
    @Expose
    private float dhu;
    @SerializedName("topDefects")
    @Expose
    private List<TopDefect> topDefects;

    public DataModel(String lineName, String buyerName, int hourlyTarget, int actualPcs, int variance, int plannedPcs, int cumPlannedPcs, int cumActualPcs, int balanceToProduce, int cumVariance, float efficiency, List<HourlyActualPcs> hourlyActualPcsList, int defectivePcs, float defectPercentage, int totalDefect, float dhu, List<TopDefect> topDefects) {
        this.lineName = lineName;
        this.buyerName = buyerName;
        this.hourlyTarget = hourlyTarget;
        this.actualPcs = actualPcs;
        this.variance = variance;
        this.plannedPcs = plannedPcs;
        this.cumPlannedPcs = cumPlannedPcs;
        this.cumActualPcs = cumActualPcs;
        this.balanceToProduce = balanceToProduce;
        this.cumVariance = cumVariance;
        this.efficiency = efficiency;
        this.hourlyActualPcsList = hourlyActualPcsList;
        this.defectivePcs = defectivePcs;
        this.defectPercentage = defectPercentage;
        this.totalDefect = totalDefect;
        this.dhu = dhu;
        this.topDefects = topDefects;
    }

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public int getHourlyTarget() {
        return hourlyTarget;
    }

    public void setHourlyTarget(int hourlyTarget) {
        this.hourlyTarget = hourlyTarget;
    }

    public int getActualPcs() {
        return actualPcs;
    }

    public void setActualPcs(int actualPcs) {
        this.actualPcs = actualPcs;
    }

    public int getVariance() {
        return variance;
    }

    public void setVariance(int variance) {
        this.variance = variance;
    }

    public int getPlannedPcs() {
        return plannedPcs;
    }

    public void setPlannedPcs(int plannedPcs) {
        this.plannedPcs = plannedPcs;
    }

    public int getCumPlannedPcs() {
        return cumPlannedPcs;
    }

    public void setCumPlannedPcs(int cumPlannedPcs) {
        this.cumPlannedPcs = cumPlannedPcs;
    }

    public int getCumActualPcs() {
        return cumActualPcs;
    }

    public void setCumActualPcs(int cumActualPcs) {
        this.cumActualPcs = cumActualPcs;
    }

    public int getBalanceToProduce() {
        return balanceToProduce;
    }

    public void setBalanceToProduce(int balanceToProduce) {
        this.balanceToProduce = balanceToProduce;
    }

    public int getCumVariance() {
        return cumVariance;
    }

    public void setCumVariance(int cumVariance) {
        this.cumVariance = cumVariance;
    }

    public float getEfficiency() {
        return efficiency;
    }

    public void setEfficiency(float efficiency) {
        this.efficiency = efficiency;
    }

    public List<HourlyActualPcs> getHourlyActualPcsList() {
        return hourlyActualPcsList;
    }

    public void setHourlyActualPcsList(List<HourlyActualPcs> hourlyActualPcsList) {
        this.hourlyActualPcsList = hourlyActualPcsList;
    }

    public int getDefectivePcs() {
        return defectivePcs;
    }

    public void setDefectivePcs(int defectivePcs) {
        this.defectivePcs = defectivePcs;
    }

    public float getDefectPercentage() {
        return defectPercentage;
    }

    public void setDefectPercentage(float defectPercentage) {
        this.defectPercentage = defectPercentage;
    }

    public int getTotalDefect() {
        return totalDefect;
    }

    public void setTotalDefect(int totalDefect) {
        this.totalDefect = totalDefect;
    }

    public float getDhu() {
        return dhu;
    }

    public void setDhu(float dhu) {
        this.dhu = dhu;
    }

    public List<TopDefect> getTopDefects() {
        return topDefects;
    }

    public void setTopDefects(List<TopDefect> topDefects) {
        this.topDefects = topDefects;
    }

    @Override
    public String toString() {
        return "DataModel{" +
                "lineName='" + lineName + '\'' +
                ", buyerName='" + buyerName + '\'' +
                ", hourlyTarget=" + hourlyTarget +
                ", actualPcs=" + actualPcs +
                ", variance=" + variance +
                ", plannedPcs=" + plannedPcs +
                ", cumPlannedPcs=" + cumPlannedPcs +
                ", cumActualPcs=" + cumActualPcs +
                ", balanceToProduce=" + balanceToProduce +
                ", cumVariance=" + cumVariance +
                ", efficiency=" + efficiency +
                ", hourlyActualPcsList=" + hourlyActualPcsList +
                ", defectivePcs=" + defectivePcs +
                ", defectPercentage=" + defectPercentage +
                ", totalDefect=" + totalDefect +
                ", dhu=" + dhu +
                ", topDefects=" + topDefects +
                '}';
    }
}
