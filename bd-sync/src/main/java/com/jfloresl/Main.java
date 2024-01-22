package com.jfloresl;

import com.jfloresl.utilities.Comparer;
import com.jfloresl.vo.DatabaseVO;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

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
            //System.out.println("databaseVO[0]:"+databaseVO[0]);

            Comparer.getData(databaseVO[0]);


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