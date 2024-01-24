package com.bdsync.database;

import com.bdsync.vo.ColumnVO;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Sql {

    public static void generateQueriesCreateTables(List<String> tablas, Map<String, List<ColumnVO>> columnas) {
        for (String tabla : tablas) {
            List<ColumnVO> columnList = columnas.get(tabla);
            if (columnList != null) {
                StringBuilder queryBuilder = new StringBuilder();
                queryBuilder.append("CREATE TABLE ").append(tabla).append(" (");
                for (ColumnVO column : columnList) {
                    queryBuilder.append(column.getName()).append(" ").append(column.getTipo());
                    if (column.getSize() != null) {
                        queryBuilder.append("(").append(column.getSize()).append(")");
                    }
                    if (Objects.equals(column.getNullable(), "Y")) {
                        queryBuilder.append(" NOT NULL");
                    }
                    queryBuilder.append(", ");
                }

                queryBuilder.setLength(queryBuilder.length() - 2); // Remove the last comma and space
                queryBuilder.append(")");

                String query = queryBuilder.toString();

                try {
                    FileWriter writer = new FileWriter("C:\\bd-sync\\file1.sql", true);
                    writer.write(query);
                    writer.write(System.lineSeparator());
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void generateQueriesUpdateTables(List<String> tablas, Map<String, List<ColumnVO>> columnas) {
        for (String tabla : tablas) {
            List<ColumnVO> columnList = columnas.get(tabla);
            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder.append("ALTER TABLE ").append(tabla).append(" ");

            for (ColumnVO column : columnList) {
                queryBuilder.append("MODIFY ").append(column.getName()).append(" ");
                queryBuilder.append(column.getTipo());
                if (column.getSize() != null) {
                    queryBuilder.append("(").append(column.getSize()).append(")");
                }
                if (Objects.equals(column.getNullable(), "Y")) {
                    queryBuilder.append(" NOT NULL");
                }
                queryBuilder.append(", ");
            }

            queryBuilder.setLength(queryBuilder.length() - 2); // Remove the last comma and space

            String query = queryBuilder.toString();
          //  System.out.println(query);

            try {
                FileWriter writer = new FileWriter("C:\\bd-sync\\file2.sql", true);
                writer.write(query);
                writer.write(System.lineSeparator());
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
