package com.qnkj.model.info;

import com.qnkj.bean.Columns;
import com.qnkj.bean.Model;
import com.qnkj.bean.Table;
import com.qnkj.file.excel.AddExcel;

import java.util.ArrayList;
import java.util.List;

class MainTest {

    @org.junit.jupiter.api.Test
    void getConnection() {
    }

    @org.junit.jupiter.api.Test
    void closeConnection() {
    }

    @org.junit.jupiter.api.Test
    void getTableNames() {
    }

    @org.junit.jupiter.api.Test
    void getColumnNames() {
        Main.getColumnNames("dwd_ast_allotrequestnote");

    }

    @org.junit.jupiter.api.Test
    void getColumnTypes() {
    }

    @org.junit.jupiter.api.Test
    void getColumnComments() throws Exception {

        List<Table> tableList = new ArrayList<Table>();
        tableList = Main.getTableNames();


        List<Columns>  clos =Main.getColumnComments(tableList);
        System.out.println(clos.size());
        Model model = new Model();
        model.setDatabase("mysql");
        model.setDomain("人员域");
        model.setSubdomain("资产信息");
        model.setIndustry("能源");
        model.setType("物理模型");
        model.setModelname("物理模型-hr");
        AddExcel.add(clos,model);



    }
}