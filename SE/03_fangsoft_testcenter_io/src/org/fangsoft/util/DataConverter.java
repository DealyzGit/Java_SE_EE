package org.fangsoft.util;

import org.fangsoft.testcenter.config.Configuration;

import java.text.ParseException;
import java.util.Date;

public class DataConverter {
    public static Date str2Date(String val) {
    try {
        return Configuration.getDateFormat().parse(val);
    } catch (ParseException e) {
        e.printStackTrace();
    }
    return new Date();
}
    public static int str2Int(String val) {
        if (val == null) return 0;
        try {
            return Integer.parseInt(val);
        } catch (Exception ex) {
        }
        return 0;
    }


}
