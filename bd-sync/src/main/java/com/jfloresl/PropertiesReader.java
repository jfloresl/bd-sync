package com.jfloresl;

import com.jfloresl.vo.DatabaseVO;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PropertiesReader {
    public static List<String> findDb(){

        try {
            BufferedReader br = new BufferedReader(new FileReader("c:\\bd-sync\\bd.properties"));
            String line;
            List<String> dbs = new ArrayList<>();

            while ((line = br.readLine()) != null) {
                if (line.matches("^\\w+.*")) { // Match
                    String firstWord = line.substring(0, line.indexOf('.'));
                    if (!dbs.contains(firstWord)){
                        dbs.add(firstWord);
                    }
                }
            }
            return dbs;
        } catch (Exception ex) {
            System.out.println("error leer archivos findDb ex:"+ex.getMessage());
            throw new RuntimeException(ex.getMessage());
        }
    }
    public static DatabaseVO read(String name) {
        Properties properties = new Properties();
        DatabaseVO databaseVO =new DatabaseVO();
        try {
            FileInputStream input = new FileInputStream("c:\\bd-sync\\bd.properties");
            properties.load(input);
                // Accessing individual properties
            databaseVO.setName(name);
            databaseVO.setHost(properties.getProperty(name+".hostName"));
            databaseVO.setPort(properties.getProperty(name+".port"));
            databaseVO.setService(properties.getProperty(name+".serviceName"));
            databaseVO.setSchema(properties.getProperty(name+".schema"));
            databaseVO.setUser(properties.getProperty(name+".user"));
            databaseVO.setPass(properties.getProperty(name+".password"));
            return databaseVO;

        } catch (IOException ex) {
            System.out.println("error leer archivos ex:"+ex.getMessage());
            throw new RuntimeException(ex.getMessage());
        }
    }
}
