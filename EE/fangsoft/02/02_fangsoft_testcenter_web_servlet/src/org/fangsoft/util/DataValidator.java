package org.fangsoft.util;

public class DataValidator {
    public static String validate(String str){
        if(str==null)return "";
        return str.trim();
    }
}
