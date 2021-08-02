package com.sqgc.qms_cut_bank_dashboard;

public class CutBankModel {
    private String productionUnit;
    private String line;
    private String style;
    private String po;
    private int cutBankIn;
    private int cutBankOut;
    private int cutBankWip;

    public CutBankModel(String productionUnit, String line, String style, String po, int cutBankIn, int cutBankOut, int cutBankWip) {
        this.productionUnit = productionUnit;
        this.line = line;
        this.style = style;
        this.po = po;
        this.cutBankIn = cutBankIn;
        this.cutBankOut = cutBankOut;
        this.cutBankWip = cutBankWip;
    }

    public String getProductionUnit() {
        return productionUnit;
    }

    public void setProductionUnit(String productionUnit) {
        this.productionUnit = productionUnit;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getPo() {
        return po;
    }

    public void setPo(String po) {
        this.po = po;
    }

    public int getCutBankIn() {
        return cutBankIn;
    }

    public void setCutBankIn(int cutBankIn) {
        this.cutBankIn = cutBankIn;
    }

    public int getCutBankOut() {
        return cutBankOut;
    }

    public void setCutBankOut(int cutBankOut) {
        this.cutBankOut = cutBankOut;
    }

    public int getCutBankWip() {
        return cutBankWip;
    }

    public void setCutBankWip(int cutBankWip) {
        this.cutBankWip = cutBankWip;
    }

    @Override
    public String toString() {
        return "CutBankModel{" +
                "productionUnit='" + productionUnit + '\'' +
                ", line='" + line + '\'' +
                ", style='" + style + '\'' +
                ", po='" + po + '\'' +
                ", cutBankIn=" + cutBankIn +
                ", cutBankOut=" + cutBankOut +
                ", cutBankWip=" + cutBankWip +
                '}';
    }
}
