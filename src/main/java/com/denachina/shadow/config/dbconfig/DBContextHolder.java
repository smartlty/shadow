package com.denachina.shadow.config.dbconfig;

public class DBContextHolder {
    public static final String DB_R = "db-r";
    public static final String DB_W = "db-w";

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

    public static void setDBType(String dbType) {
        contextHolder.set(dbType);
    }

    public static String getDBType() {
        return contextHolder.get();
    }

    public static void clearDBType() {
        contextHolder.remove();
    }
}
