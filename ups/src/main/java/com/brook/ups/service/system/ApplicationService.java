package com.brook.ups.service.system;

import com.brook.ups.common.BaseService;
import com.brook.ups.common.ServiceInterface;
import com.brook.ups.mapper.system.ApplicationMapper;
import com.brook.ups.model.Application;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class ApplicationService extends BaseService implements ServiceInterface {
 
    @Resource
    private ApplicationMapper applicationMapper;

    /**
     * 获得应用列表
     * @param params 参数
     */
    public List<Application> selectList(Map<String, Object> params) {
        startPage(params);
        List<Application> list = applicationMapper.selectList(params);
        return list;
    }

    /**
     * 添加应用
     * @param params
     * @return
     */
    public int add(Map<String, Object> params) {
        return applicationMapper.add(params);
    }

    /**
     * 修改应用
     * @param params
     * @return
     */
    public int update(Map<String, Object> params) {
        return applicationMapper.update(params);
    }

    /**
     * 删除应用
     * @param params
     * @return
     */
    public int delete(Map<String, Object> params) {
        return applicationMapper.delete(params);
    }

    /**
     * 根据条件精确匹配，获得应用有效列表
     * @param params
     * @return
     */
    public List<Application> getApplications(Map<String, Object> params) {
        return applicationMapper.getApplications(params);
    }

    /**
     * 按照应用名查找数量
     * @param params
     * @return
     */
    public int getNameCount(Map<String, Object> params) {
        return applicationMapper.getNameCount(params);
    }
}