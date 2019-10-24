package com.brook.ups.service.user;

import com.brook.ups.common.BaseService;
import com.brook.ups.mapper.user.TUserMapper;
import com.brook.ups.model.user.Tuser;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class TUserService extends BaseService {
 
    @Resource
    private TUserMapper tUserMapper;

    /**
     * 获得分页数据
     * @param params
     */
    public PageInfo<Tuser> findByUsersPage(Map<String, Object> params) {
        startPage(params);
        List<Tuser> list = tUserMapper.findByUsers();
        PageInfo<Tuser> pageInfo = new PageInfo<Tuser>(list);
        return pageInfo;
    }

    /**
     * 获得所有数据
     */
    public List<Tuser> findByUsersAll() {
        return tUserMapper.findByUsers();
    }
}