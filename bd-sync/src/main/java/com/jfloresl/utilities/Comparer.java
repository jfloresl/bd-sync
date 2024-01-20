package com.jfloresl.utilities;

import com.jfloresl.vo.DatabaseVO;

import java.sql.*;

import oracle.jdbc.driver.OracleDriver;

public class Comparer {

    DatabaseVO[] databases;

    public static void comparer(DatabaseVO d1,DatabaseVO d2){
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con= DriverManager.getConnection(
                    Util.CreateOracleUrl(d1),d1.getUser(),d1.getPass());
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("SELECT TABLE_NAME FROM all_tables where OWNER='"+d1.schema+"'");
            ResultSetMetaData rsmd = null;

            rsmd = rs.getMetaData();

            int colCount = rsmd.getColumnCount();
            int rowCount = 0;
            while (rs.next()) {
                rowCount++;
                System.out.println(rs.getString(1));

            }

            con.close();

        }catch(Exception e){ System.out.println(e);}

    }


}
