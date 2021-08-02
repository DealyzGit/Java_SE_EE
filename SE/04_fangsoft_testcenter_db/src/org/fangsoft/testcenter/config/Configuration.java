package org.fangsoft.testcenter.config;

import org.fangsoft.testcenter.dao.DaoFactory;
import org.fangsoft.testcenter.dao.db.DBDaoFactory;
import org.fangsoft.testcenter.db.DriverManagerDataSource;

import javax.sql.DataSource;
import java.text.DateFormat;
import java.util.Locale;

public abstract class Configuration {
    private static final DateFormat dateFormat =
            DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL);
    public static final int MAX_LOGIN = 3;

    public static final String[] CHOICE_LABEL =
            {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k"};

    public static final DateFormat getDateFormat(Locale... locales) {
        if (locales == null || locales.length == 0) return dateFormat;
        return DateFormat.getDateTimeInstance(
                DateFormat.FULL, DateFormat.FULL, locales[0]);
    }

//    public static DaoFactory getDaoFactory(){
//        return PropDaoFactory.getInstance();
//    }

    private static  final DataSource oracleDataSource= new DriverManagerDataSource(
                   DBConfig.getOracleDriver(),
                   DBConfig.getOracleUser(),
                   DBConfig.getOraclePassword(),
                   DBConfig.getOracleURL());

    public static final DataSource getDataSource(){
        return oracleDataSource;
    }

    private static DaoFactory daoFactory;
    public static final DaoFactory getDaoFactory(){
        if(daoFactory==null){
            daoFactory=new DBDaoFactory(Configuration.getDataSource());
        }
        return daoFactory;


    }
}
