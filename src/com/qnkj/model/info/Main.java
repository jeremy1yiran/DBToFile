package com.qnkj.model.info;

import com.qnkj.bean.Columns;
import com.qnkj.bean.Table;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {


    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://192.168.2.27:3306/model";
    private static final String USERNAME = "qnkj";
    private static final String PASSWORD = "qnkj20201217";

    private static final String SQL = "SELECT * FROM ";// 数据库操作

    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {

        }
    }


    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {

        }
    }

    /**
     * 获取数据库连接
     *
     * @return
     */
    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {

        }
        return conn;
    }

    /**
     * 关闭数据库连接
     *
     * @param conn
     */
    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {

            }
        }
    }

    /**
     * 获取数据库下的所有表名
     */
    public static List<Table> getTableNames() {
        List<Table> tableNames = new ArrayList<>();
        Connection conn = getConnection();
        ResultSet rs = null;
        try {
            //获取数据库的元数据
            DatabaseMetaData db = conn.getMetaData();

            //从元数据中获取到所有的表名
            rs = db.getTables(null, null, null, new String[]{"TABLE"});
            while (rs.next()) {
                String tablename = rs.getString(3);
                if (tablename.startsWith("dwd_hr_")) {
                    //tableNames.add(rs.getString(3));
                    String tableobj = rs.getString(3);
                    Table table = new Table();
                    table.setTablecode(tableobj);
                    table.setTalbename(tableobj);
                    table.setTabledescrip(tableobj);
                    tableNames.add(table);
                }
            }
        } catch (SQLException e) {

        } finally {
            try {
                rs.close();
                closeConnection(conn);
            } catch (SQLException e) {

            }
        }
        return tableNames;
    }

    /**
     * 获取表中所有字段名称
     *
     * @param tableName 表名
     * @return
     */
    public static List<String> getColumnNames(String tableName) {
        List<String> columnNames = new ArrayList<>();
        //与数据库的连接
        Connection conn = getConnection();
        PreparedStatement pStemt = null;
        String tableSql = SQL + tableName;
        try {
            pStemt = conn.prepareStatement(tableSql);
            //结果集元数据
            ResultSetMetaData rsmd = pStemt.getMetaData();
            //表列数
            int size = rsmd.getColumnCount();
            System.out.println(size);
            for (int i = 0; i < size; i++) {
                String columnName = rsmd.getColumnName(i + 1);
                String columnType = rsmd.getColumnTypeName(i + 1);
                System.out.println(columnName);
                int presicsion = rsmd.getPrecision(i + 1);
                System.out.println(presicsion);
                columnNames.add(rsmd.getColumnName(i + 1));


            }


        } catch (SQLException e) {

        } finally {
            if (pStemt != null) {
                try {
                    pStemt.close();
                    closeConnection(conn);
                } catch (SQLException e) {

                }
            }
        }
        return columnNames;
    }

    /**
     * 获取表中所有字段类型
     *
     * @param tableName
     * @return
     */
    public static List<String> getColumnTypes(String tableName) {
        List<String> columnTypes = new ArrayList<>();
        //与数据库的连接
        Connection conn = getConnection();
        PreparedStatement pStemt = null;
        String tableSql = SQL + tableName;
        try {
            pStemt = conn.prepareStatement(tableSql);
            //结果集元数据
            ResultSetMetaData rsmd = pStemt.getMetaData();
            //表列数
            int size = rsmd.getColumnCount();
            for (int i = 0; i < size; i++) {
                columnTypes.add(rsmd.getColumnTypeName(i + 1));
            }
        } catch (SQLException e) {

        } finally {
            if (pStemt != null) {
                try {
                    pStemt.close();
                    closeConnection(conn);
                } catch (SQLException e) {

                }
            }
        }
        return columnTypes;
    }

    /**
     * 获取表中字段的所有注释
     *

     * @return
     */
    public static List<Columns> getColumnComments(List<Table> tableList) {
        //List<String> columnTypes = new ArrayList<>();//类型集合
        //List<String> columnFiled = new ArrayList<>();//columnname 集合
        List<Columns> columns = new ArrayList<>();//列名注释集合

        String comments = "";
        String filed = "";
        String type = "";
        //与数据库的连接
        Connection conn = getConnection();
        PreparedStatement pStemt = null;

        ResultSet rs = null;
        try {
            for (Table table : tableList) {

                String tablename = table.getTalbename();
                String tableSql = SQL + tablename;

                pStemt = conn.prepareStatement(tableSql);
                rs = pStemt.executeQuery("show full columns from " + tablename);

                while (rs.next()) {
                    Columns clo = new Columns();
                    clo.setTalbename(tablename);
                    clo.setTablecode(tablename);
                    clo.setTabledescrip(tablename);
                    clo.setComment(rs.getString("Comment"));
                    clo.setFiled(rs.getString("Field"));
                    clo.setType(rs.getString("Type"));
                    clo.setEmpty(rs.getString("Null"));
                    columns.add(clo);
                }

            }
            return columns;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                    closeConnection(conn);
                } catch (SQLException e) {

                }
            }
        }
        //}


        return columns;
    }


}