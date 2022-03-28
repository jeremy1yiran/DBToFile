package com.qnkj.file.excel;

import com.qnkj.bean.Columns;
import com.qnkj.bean.Model;
import com.qnkj.bean.Table;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class AddExcel {

    /**
     * 向Excel中追加内容
     * @throws Exception
     */
    public static void add(List<Columns> list, Model model) throws Exception {


        FileInputStream in = new FileInputStream("D:\\data_hr.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(in);
        XSSFSheet sheet = workbook.getSheetAt(0);
        XSSFRow row = sheet.getRow(1);

        FileOutputStream out = new FileOutputStream("D:\\data_hr.xlsx");
        //从第二行开始追加列
        /*row=sheet.getRow(1);
        row.createCell(3).setCellValue("AAA");
        row.createCell(4).setCellValue("BBB");*/

        /**追加列数据
         for(int i=0;i<stuList2.size();i++){
         Student student = stuList2.get(i);
         row = sheet.getRow(i+1);
         row.createCell(3).setCellValue(student.getAge());
         row.createCell(4).setCellValue(student.getName());
         row.createCell(5).setCellValue(student.getAddress());
         }**/

        /*//追加行数据*/
        //for (int i = 0; i < list.size(); i++) {
        for (Columns columns : list) {
            row = sheet.createRow((short) (sheet.getLastRowNum() + 1)); //在现有行号后追加数据
            row.createCell(0).setCellValue(model.getType());
            row.createCell(1).setCellValue(model.getModelname());
            row.createCell(2).setCellValue(model.getDatabase());
            row.createCell(3).setCellValue(model.getDomain());
            row.createCell(4).setCellValue(model.getSubdomain());
            row.createCell(5).setCellValue("能源");
            row.createCell(6).setCellValue(columns.getTalbename());
            row.createCell(7).setCellValue(columns.getTalbename());
            row.createCell(8).setCellValue(columns.getTalbename());
            row.createCell(9).setCellValue(columns.getFiled());
            row.createCell(10).setCellValue(columns.getFiled());
            row.createCell(11).setCellValue(columns.getType());
            row.createCell(12).setCellValue(columns.getEmpty());
            row.createCell(13).setCellValue(columns.getLength());
            row.createCell(14).setCellValue(columns.getComment());

        }
        try {
            out.flush();
            workbook.write(out);
            out.close();
        } catch (
                Exception e) {
            e.printStackTrace();
        }
    }
}