package com.jfloresl.utilities;

import com.jfloresl.vo.DatabaseVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Comparer {

    DatabaseVO[] databases;

    public static List<String> getData(DatabaseVO d1){
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con1= DriverManager.getConnection(
                    Util.CreateOracleUrl(d1),d1.getUser(),d1.getPass());

            Statement stmt1=con1.createStatement();
            Map<String, List<String>> tablas1 = new HashMap<>();
            ResultSet tablasDb1=stmt1.executeQuery("SELECT TABLE_NAME FROM all_tables where OWNER='"+d1.schema+"' ORDER BY TABLE_NAME");
            List<String> tablasLista=new ArrayList<>();
           // int rowCount = 0;

            while (tablasDb1.next()) { //listar tablas de db1
                tablasLista.add(tablasDb1.getString(1));
            }
            System.out.println("lista de tablas:"+tablasLista);

            for(String t: tablasLista){
                ResultSet rs4=stmt1.executeQuery("select COLUMN_NAME,DATA_TYPE,DATA_LENGTH,NULLABLE from all_tab_columns WHERE OWNER='"+d1.schema+"' AND TABLE_NAME='"+t+"'");
                ResultSetMetaData rsmd = rs4.getMetaData();
                int numeroColumnas = rsmd.getColumnCount();

                while (rs4.next()) {
                    for(int i = 1 ; i <= numeroColumnas; i++){// nombre columna, formato, tamaño, nullable
                        System.out.println(rs4.getString(i));
                    }
                    System.out.println("========");
                }
            }

            con1.close();
            return tablasLista;

        }catch(Exception e){
            System.out.println(e);
            return null;
        }
    }
}