package org.fangsoft.testcenter.config;

import org.fangsoft.testcenter.dao.DaoFactory;
import org.fangsoft.testcenter.dao.memory.MemDaoFactory;

public abstract class Configuration {
    private static DaoFactory daoFactory;
    public static final DaoFactory getDaoFactory(){
        if(daoFactory==null){
            daoFactory=new MemDaoFactory();
        }
        return daoFactory;
    }

    public static final String[] CHOICE_LABEL =
            {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
}
