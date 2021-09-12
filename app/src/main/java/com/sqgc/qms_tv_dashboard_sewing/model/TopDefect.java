package com.sqgc.qms_tv_dashboard_sewing.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TopDefect {
    @SerializedName("slNo")
    @Expose
    private int slNo;
    @SerializedName("defectName")
    @Expose
    private String defectName;
    @SerializedName("defectPercentage")
    @Expose
    private float defectPercentage;
    @SerializedName("defectPcs")
    @Expose
    private int defectPcs;

    public TopDefect(int slNo, String defectName, float defectPercentage, int defectPcs) {
        this.slNo = slNo;
        this.defectName = defectName;
        this.defectPercentage = defectPercentage;
        this.defectPcs = defectPcs;
    }

    public int getSlNo() {
        return slNo;
    }

    public void setSlNo(int slNo) {
        this.slNo = slNo;
    }

    public String getDefectName() {
        return defectName;
    }

    public void setDefectName(String defectName) {
        this.defectName = defectName;
    }

    public float getDefectPercentage() {
        return defectPercentage;
    }

    public void setDefectPercentage(float defectPercentage) {
        this.defectPercentage = defectPercentage;
    }

    public int getDefectPcs() {
        return defectPcs;
    }

    public void setDefectPcs(int defectPcs) {
        this.defectPcs = defectPcs;
    }

    @Override
    public String toString() {
        return "TopDefect{" +
                "slNo=" + slNo +
                ", defectName='" + defectName + '\'' +
                ", defectPercentage=" + defectPercentage +
                ", defectPcs=" + defectPcs +
                '}';
    }
}
