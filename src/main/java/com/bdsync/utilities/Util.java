package com.bdsync.utilities;

import com.bdsync.vo.DatabaseVO;

public class Util {

    public static String CreateOracleUrl(DatabaseVO databaseVO){
       // "jdbc:oracle:thin:@localhost:1521:xe"

        return "jdbc:oracle:thin:@"+databaseVO.getHost()+":"+
                databaseVO.getPort()+":"+databaseVO.getService();
    }
}
