package com.qnkj.bean;

public class Columns extends Table {

    private String filed;
    private int length;
    private String type;
    private String comment;
    private String empty;

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    private String table;

    public String getEmpty() {
        return empty;
    }

    public void setEmpty(String empty) {
        this.empty = empty;
    }


    public String getFiled() {
        return filed;
    }

    public void setFiled(String filed) {
        this.filed = filed;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        //this.type = type;
        if (type.startsWith("varchar")) {
            int start = type.indexOf("(");
            int end = type.indexOf(")");
            if (start != 0 && end != 0) {
                try {
                    this.length = Integer.parseInt(type.substring(start + 1, end));
                    this.type = "varchar";
                } catch (Exception e) {

                }
            }
        } else {//一般为时间类型，不做处理
            this.type = type;
        }

    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getLength() {
        return length;
    }


}


