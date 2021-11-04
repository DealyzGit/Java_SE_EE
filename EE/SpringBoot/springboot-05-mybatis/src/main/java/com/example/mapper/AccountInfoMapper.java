package com.example.mapper;

import com.example.pojo.AccountInfo;
import com.example.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AccountInfoMapper {
    int insert(AccountInfo accountInfo);

    List<AccountInfo> queryAccountInfoList();

    AccountInfo selectByPrimaryKey(String TAAccountID);

}
