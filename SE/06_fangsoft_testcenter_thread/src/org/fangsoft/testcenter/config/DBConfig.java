package org.fangsoft.testcenter.config;

public class DBConfig {
    //    jdbc:oracle:thin:@localhost:1521:fangsoft
    public static final String ORACLE_DRIVER = "oracle.jdbc.OracleDriver";
//    public static final String ORACLE_DRIVER = "jdbc.oracle.thin";

    public static final String ORACLE_USER = "system";
    public static final String ORACLE_PASSWORD = "123";
    public static final String ORACLE_HOST = "localhost";
    public static final String ORACLE_PORT = "1521";
    public static final String ORACLE_DATABASE = "fangsoft";

    public static String getOracleDriver() {
        return ORACLE_DRIVER;
    }

    public static String getOracleUser() {
        return ORACLE_USER;
    }

    public static String getOraclePassword() {
        return ORACLE_PASSWORD;
    }

    public static String getOracleURL() {
        return "jdbc:oracle:thin:@" + ORACLE_HOST + ":" + ORACLE_PORT + ":"
                + ORACLE_DATABASE;
    }
}

