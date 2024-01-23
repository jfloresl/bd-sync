package com.bdsync.utilities;

import com.bdsync.vo.ColumnVO;
import com.bdsync.vo.DatabaseVO;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.util.List;
import java.util.Map;

public class Util {

    public static String CreateOracleUrl(DatabaseVO databaseVO){
       // "jdbc:oracle:thin:@localhost:1521:xe"

        return "jdbc:oracle:thin:@"+databaseVO.getHost()+":"+
                databaseVO.getPort()+":"+databaseVO.getService();
    }

    public static void printDb(Map<String, List<ColumnVO>> db){
        for (String key : db.keySet()) {
            System.out.println(key);
            List<ColumnVO> values = db.get(key);
            System.out.println("\tCOLUMN_NAME,DATA_TYPE,DATA_LENGTH,NULLABLE");
            for (ColumnVO value : values) {
                System.out.println("\t" + value.getName()+" "+ value.getTipo()+" "+ value.getSize()+" "+ value.getNullable()+" ");
            }
        }
    }
}
