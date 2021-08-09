package org.fangsoft.util;


public class DataConverter {
    public static int str2Int(String val) {
        try {
            return Integer.parseInt(DataValidator.validate(val));
        } catch (Exception ex) {
        }
        return 0;
    }
}
