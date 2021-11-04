package com.example.controller;


import com.example.mapper.AccountInfoMapper;
import com.example.pojo.AccountInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountInfoController {
    @Autowired
    private AccountInfoMapper accountInfoMapper;

    @RequestMapping(path = {"/queryAccountInfo/{id}"})
    public AccountInfo queryAccountInfo(@PathVariable("String") String TAAccountID) {
        return accountInfoMapper.selectByPrimaryKey(TAAccountID);
    }

}
