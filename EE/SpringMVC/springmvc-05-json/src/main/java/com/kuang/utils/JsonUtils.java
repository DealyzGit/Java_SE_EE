package com.kuang.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import java.text.SimpleDateFormat;

public class JsonUtils {
    @SneakyThrows
    public static String getJson(Object object, String DataFormat){
        ObjectMapper mapper =new ObjectMapper();
        SimpleDateFormat sdf = new SimpleDateFormat(DataFormat);
        mapper.setDateFormat(sdf);
        String s = mapper.writeValueAsString(object);
        return s;
    }


    @SneakyThrows
    public static String getJson(Object object){
        return getJson(object,"yyyy-MM-dd HH:mm:ss");
    }

}
