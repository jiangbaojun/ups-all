package com.brook.ups.mapper.system;

import com.brook.ups.common.BaseMapper;
import com.brook.ups.model.Account;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface AccountMapper extends BaseMapper {
    List<Account> selectList(Map<String, Object> params);

    Account getAccountById(Map<String, Object> params);

    List<Account> getAccounts(Map<String, Object> params);
}