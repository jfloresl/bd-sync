package com.bdsync;

import com.bdsync.utilities.Comparer;
import com.bdsync.vo.ColumnVO;
import com.bdsync.vo.DatabaseVO;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.bdsync.utilities.Util.printDb;

public class Main {
    public static void main(String[] args) {
        String folderPath = "c:\\bd-sync";
        String filePath = folderPath + "\\bd.properties";

        // Check if the file exists
        Path path = Paths.get(filePath);
        if (Files.exists(path)) {
            // File exists, open and get data
            System.out.println("existe");
            List<String> dbs = PropertiesReader.findDb();
            DatabaseVO[] databaseVO = new DatabaseVO[dbs.size()];
            int i = 0;
            for (String d : dbs) {
                databaseVO[i] = PropertiesReader.read(d);
                i++;
            }
            System.out.println(databaseVO[0].getName());
            System.out.println(databaseVO[1].getName());
            System.out.println(databaseVO[2].getName());
            System.out.println(databaseVO[3].getName());
            System.out.println(databaseVO[4].getName());

            Map<String, List<ColumnVO>> uat5 = Comparer.getData(databaseVO[0]);
           // Map<String, List<ColumnVO>> cert = Comparer.getData(databaseVO[1]);
            // Map<String, List<ColumnVO>> buat = Comparer.getData(databaseVO[2]);
             Map<String, List<ColumnVO>> local = Comparer.getData(databaseVO[4]);


            // Map<String, List<ColumnVO>> db1 = Comparer.getData(databaseVO[0]);
            //printDb(Objects.requireNonNull(Comparer.getData(databaseVO[0])));
            //System.out.println(Comparer.getData(databaseVO[0]));


            Comparer.comparar(uat5,local,databaseVO[0].getName(),databaseVO[4].getName());

        } else {
            // File doesn't exist, create folder and file
            try {
                Files.createDirectories(Paths.get(folderPath));
                Files.createFile(path);
                System.out.println("archivo creado cargue credenciales y vuelva ejecutar");

            } catch (IOException e) {
                System.err.println("Error creating folder and file: " + e.getMessage());
            }
        }

    }
}