package com.example.mapper;

import com.example.pojo.AccountInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AccountInfoMapper {
    int insert(AccountInfo accountInfo);

    AccountInfo selectByPrimaryKey(String TAAccountID);

}
