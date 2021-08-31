package org.fangsoft.testcenter.web.access;

import java.io.Serializable;

public class SessionGuardBean implements Serializable {
    public static final String APP_SESSION_GUARD="app_session_guard";
    private int count;


    public synchronized int getCount(){
        return count;
    }
    public synchronized int add(){
        return ++count;
    }
    public synchronized int decrease(){
        return --count;
    }

}
