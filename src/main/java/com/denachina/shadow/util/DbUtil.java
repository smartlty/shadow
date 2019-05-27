package com.denachina.shadow.util;

import com.denachina.shadow.config.dbconfig.DBContextHolder;

public class DbUtil {

    public static final void setDbR() {
        DBContextHolder.setDBType(DBContextHolder.DB_R);
    }

    public static final void setDbW() {
        DBContextHolder.setDBType(DBContextHolder.DB_W);
    }
}
