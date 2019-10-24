package com.brook.ups.service.basic;

import com.brook.ups.common.BaseService;
import com.brook.ups.mapper.basic.UserMapper;
import com.brook.ups.model.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


@Service
public class UserService extends BaseService {
 
    @Resource
    private UserMapper userMapper;

    /**
     * 获得账户列表
     * @param params 参数
     */
    public List<User> selectList(Map<String, Object> params) {
        startPage(params);
        List<User> list = userMapper.selectList(params);
        return list;
    }

}