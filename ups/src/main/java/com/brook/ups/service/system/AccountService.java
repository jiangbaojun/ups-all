package com.brook.ups.service.system;

import com.brook.ups.common.BaseService;
import com.brook.ups.common.ServiceInterface;
import com.brook.ups.mapper.system.AccountMapper;
import com.brook.ups.model.Account;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


@Service
public class AccountService extends BaseService implements ServiceInterface {
 
    @Resource
    private AccountMapper accountMapper;

    /**
     * 获得账户列表
     * @param params 参数
     */
    public List<Account> selectList(Map<String, Object> params) {
        startPage(params);
        List<Account> list = accountMapper.selectList(params);
        return list;
    }

    /**
     * 添加账户
     * @param params
     * @return
     */
    public int add(Map<String, Object> params) {
        return accountMapper.add(params);
    }

    /**
     * 修改账户
     * @param params
     * @return
     */
    public int update(Map<String, Object> params) {
        return accountMapper.update(params);
    }

    /**
     * 删除账户
     * @param params
     * @return
     */
    public int delete(Map<String, Object> params) {
        return accountMapper.delete(params);
    }

    /**
     * 根据id获得账户
     * @param params
     * @return
     */
    public Account getAccountById(Map<String, Object> params) {
        return accountMapper.getAccountById(params);
    }

    /**
     * 根据精确条件获得账户列表
     * @param params
     * @return
     */
    public List<Account> getAccounts(Map<String, Object> params) {
        return accountMapper.getAccounts(params);
    }

    /**
     * 根据账户名，返回数量
     * @param params
     */
    public int getNameCount(Map<String, Object> params) {
        return accountMapper.getNameCount(params);
    }
}