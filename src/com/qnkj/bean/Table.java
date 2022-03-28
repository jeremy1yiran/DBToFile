package com.qnkj.bean;

public class Table extends Model {

    private String talbename;
    private String tablecode;
    private String tabledescrip;

    private String tablecomment;


    public String getTalbename() {
        return talbename;
    }

    public void setTalbename(String talbename) {
        this.talbename = talbename;
    }

    public String getTablecode() {
        return tablecode;
    }

    public void setTablecode(String tablecode) {
        this.tablecode = tablecode;
    }

    public String getTabledescrip() {
        return tabledescrip;
    }

    public void setTabledescrip(String tabledescrip) {
        this.tabledescrip = tabledescrip;
    }

    public String getTablecomment() {
        return tablecomment;
    }

    public void setTablecomment(String tablecomment) {
        this.tablecomment = tablecomment;
    }
}
