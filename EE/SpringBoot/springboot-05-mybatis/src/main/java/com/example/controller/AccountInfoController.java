package com.example.controller;


import com.example.mapper.AccountInfoMapper;
import com.example.pojo.AccountInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccountInfoController {
    @Autowired
    private AccountInfoMapper accountInfoMapper;

    @RequestMapping(path = {"/queryAccountInfoList"})
    public List<AccountInfo> queryAccountInfoList() {
        return accountInfoMapper.queryAccountInfoList();
    }

}
