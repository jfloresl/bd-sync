package com.bdsync.utilities;

import com.bdsync.vo.ColumnVO;
import com.bdsync.vo.DatabaseVO;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class Util {

    public static String CreateOracleUrl(DatabaseVO databaseVO) {
        // "jdbc:oracle:thin:@localhost:1521:xe"

        return "jdbc:oracle:thin:@" + databaseVO.getHost() + ":" +
                databaseVO.getPort() + ":" + databaseVO.getService();
    }

    public static void printDb(Map<String, List<ColumnVO>> db) {
        for (String key : db.keySet()) {
            System.out.println(key);
            List<ColumnVO> values = db.get(key);
            System.out.println("\tCOLUMN_NAME,DATA_TYPE,DATA_LENGTH,NULLABLE");
            for (ColumnVO value : values) {
                System.out.println("\t" + value.getName() + " " + value.getTipo() + " " + value.getSize() + " " + value.getNullable() + " ");
            }
        }
    }


    public static void writeTablesAndColumns(Map<String, List<ColumnVO>> db, String filePath)
    {

        try(
    PrintWriter writer = new PrintWriter(new FileWriter(filePath)))

    {
        // Iterate over the map entries and write them to the file
        for (Map.Entry<String, List<ColumnVO>> entry : db.entrySet()) {
            String key = entry.getKey();
            List<ColumnVO> values = entry.getValue();

            // Write the key to the file
            writer.println("Key: " + key);

            // Write the values to the file
            for (ColumnVO column : values) {
                writer.println("Column: " + column.getName());
            }

            // Add a separator between entries
            writer.println("--------------------");
        }

        System.out.println("Map written to file successfully!");
    } catch(
    IOException e)

    {
        System.out.println("Error writing map to file: " + e.getMessage());
    }


}


    public static void writeTables(List<String> tablasNoExistEnDb, String filePath) {


        try (FileWriter writer = new FileWriter(filePath)) {
            for (String line : tablasNoExistEnDb) {
                writer.write(line);
                writer.write(System.lineSeparator()); // Add a new line after each string
            }
            System.out.println("List written to file successfully!");
        } catch (IOException e) {
            System.out.println("Error writing list to file: " + e.getMessage());
        }
    }
}
