package com.qnkj.file.excel;

import com.qnkj.bean.Columns;
import com.qnkj.bean.Model;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class OptExcel {




    //判断文件是否存在
    public static boolean fileExist(String filePath){
        boolean flag = false;
        File file = new File(filePath);
        flag = file.exists();
        return flag;
    }

    //向Excel中写数据
    public static void writeExcel(List<Columns> list , String filePath, Model model){
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("student");
        XSSFRow firstRow = sheet.createRow(0);//第一行表头
        XSSFCell cells[] = new XSSFCell[3];

        String[] titles = new String[]{"age","name","address"};
        //循环设置表头信息
        for (int i=0;i<3;i++){
            cells[0]=firstRow.createCell(i);
            cells[0].setCellValue(titles[i]);
        }

        //遍历list,将数据写入Excel中
        for (int i=0;i<list.size();i++){
            XSSFRow row = sheet.createRow(i+1);
            Columns columns = list.get(i);
            XSSFCell cell = row.createCell(0); //第一列
            //cell.setCellValue();
            //cell=row.createCell(1); //第二列
            //cell.setCellValue(student.getName());
            //cell=row.createCell(2); //第三列
            //cell.setCellValue(student.getAddress());
        }
        OutputStream out = null;
        try {
            out = new FileOutputStream(filePath);
            workbook.write(out);
            out.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}