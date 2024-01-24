package com.bdsync.utilities;

import com.bdsync.vo.ColumnVO;
import com.bdsync.vo.DatabaseVO;
import com.bdsync.database.Sql;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Comparer {

    DatabaseVO[] databases;

    public static Map<String, List<ColumnVO>> getData(DatabaseVO d1){
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con1= DriverManager.getConnection(
                    Util.CreateOracleUrl(d1),d1.getUser(),d1.getPass());

            Statement stmt1=con1.createStatement();
            Map<String, List<ColumnVO>> tablas = new HashMap<>();
            ResultSet tablasDb1=stmt1.executeQuery("SELECT TABLE_NAME FROM all_tables where OWNER='"+d1.schema+"' ORDER BY TABLE_NAME");
            List<String> tablasLista=new ArrayList<>();
           // int rowCount = 0;

            while (tablasDb1.next()) { //listar tablas de db1
                tablasLista.add(tablasDb1.getString(1));
            }
            System.out.println("lista de tablas:"+tablasLista);
            List<String> tablasLista2 = new ArrayList<>(tablasLista.subList(0, 2)); //temp

            for(String t: tablasLista){
                ResultSet rs4=stmt1.executeQuery("select COLUMN_NAME,DATA_TYPE,DATA_LENGTH,NULLABLE from all_tab_columns WHERE OWNER='"+d1.schema+"' AND TABLE_NAME='"+t+"'");
                ResultSetMetaData rsmd = rs4.getMetaData();
                /*int numeroColumnas = rsmd.getColumnCount();

                while (rs4.next()) {
                    ColumnVO column=new ColumnVO();

                    for(int i = 1 ; i <= numeroColumnas; i++){// nombre columna, formato, tamaño, nullable
                      //  System.out.println(rs4.getString(i));
                        column[i]=
                    }
                    //System.out.println("========");
                }*/

                List<ColumnVO> ListaDeColumnas = new ArrayList<>();
                while (rs4.next()) {
                    String name = rs4.getString("COLUMN_NAME");
                    String type = rs4.getString("DATA_TYPE");
                    String length = rs4.getString("DATA_LENGTH");
                    String nullable = rs4.getString("NULLABLE");
                    ColumnVO column = new ColumnVO(name, type, length, nullable);
                    ListaDeColumnas.add(column);
                }

                tablas.put(t,ListaDeColumnas);

            }

            con1.close();
            return tablas;

        }catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

    public static void comparar(Map<String, List<ColumnVO>> db1, Map<String, List<ColumnVO>> db2, String nameDb1, String nameDb2) {
        // Iterate over the keys of db1

        List<String> tablasNoExistEnDb1=new ArrayList<>();
        Map<String, List<ColumnVO>> columnasNoExistEnDb1=new HashMap<>();
        List<String> tablasNoExistEnDb2=new ArrayList<>();
        Map<String, List<ColumnVO>> columnasNoExistEnDb2=new HashMap<>();

        for (String key : db1.keySet()) {
            // Check if the key is present in db2
            if (!db2.containsKey(key)) {
                tablasNoExistEnDb2.add(key);
                //System.out.println("Key " + key + " is present in"+nameDb1+" but not in "+nameDb2);
            }else{
                List<ColumnVO> listaColumnas = new ArrayList<>();
                for(ColumnVO c: db1.get(key)){
                   if(!db2.get(key).contains(c)){
                       listaColumnas.add(c);
                   }
                }
                columnasNoExistEnDb2.put(key,listaColumnas);

            }
        }
        //System.out.println("======");
        // Iterate over the keys of db2
        for (String key : db2.keySet()) {
            // Check if the key is present in db1
            if (!db1.containsKey(key)) {
                tablasNoExistEnDb1.add(key);
                //System.out.println("Key " + key + " is present in "+nameDb2+" but not in "+nameDb1);
            }else{
                List<ColumnVO> listaColumnas2 = new ArrayList<>();
                for(ColumnVO c: db2.get(key)){
                    if(!db1.get(key).contains(c)){
                        listaColumnas2.add(c);
                    }
                }
                columnasNoExistEnDb1.put(key,listaColumnas2);

            }
        }


        String filePath = "c:\\bd-sync\\db1.txt";
        Util.writeTables(tablasNoExistEnDb1,filePath);

        String filePath2 = "c:\\bd-sync\\db2.txt";
        Util.writeTables(tablasNoExistEnDb2,filePath2);

        String filePath3 = "c:\\bd-sync\\db1Columns1.txt";
        Util.writeTablesAndColumns(columnasNoExistEnDb1,filePath3);

        String filePath4 = "c:\\bd-sync\\db1Columns2.txt";
        Util.writeTablesAndColumns(columnasNoExistEnDb2,filePath4);

        Sql.generateQueriesCreateTables(tablasNoExistEnDb1,db1);
        //Sql.generateQueriesUpdateTables(tablasNoExistEnDb1,columnasNoExistEnDb1);

    }
}