package com.kuang.controller;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kuang.pojo.User;
import com.kuang.utils.JsonUtils;
import lombok.SneakyThrows;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class UserController {


    @SneakyThrows
    @ResponseBody
    @RequestMapping("/j1")
    public String json1() {

        ObjectMapper mapper = new ObjectMapper();
        User user1 = new User("童慧林", 3, "man");

        String str = mapper.writeValueAsString(user1);

        return str;
    }

    @SneakyThrows
    @ResponseBody
    @RequestMapping("/j2")
    public String json2() {
        ObjectMapper mapper = new ObjectMapper();
        User user1 = new User("童慧林", 3, "man");
        User user2 = new User("童慧林", 3, "man");
        User user3 = new User("童慧林", 3, "man");
        User user4 = new User("童慧林", 3, "man");
        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        userList.add(user4);
//        String str = mapper.writeValueAsString(userList);
        String str = JsonUtils.getJson(userList);
        return str;
    }


    @SneakyThrows
    @ResponseBody
    @RequestMapping("/j3")
    public String json3() {


//        ObjectMapper mapper = new ObjectMapper();
//        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,false);

        Date date = new Date();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        mapper.setDateFormat(sdf);
//        String str = mapper.writeValueAsString(date);

        return JsonUtils.getJson(date, "yyyy-MM-dd HH:mm:ss");
    }



    @SneakyThrows
    @ResponseBody
    @RequestMapping("/j4")
    public String json4() {

        User user1 = new User("童慧林1", 1, "man");
        User user2 = new User("童慧林2", 2, "man");
        User user3 = new User("童慧林3", 3, "man");
        User user4 = new User("童慧林4", 4, "man");
        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        userList.add(user4);

        String string = JSON.toJSONString(userList);
        return string;
    }

}
