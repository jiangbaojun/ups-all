package com.brook.ups.mapper.system;

import com.brook.ups.common.BaseMapper;
import com.brook.ups.model.Application;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ApplicationMapper extends BaseMapper {
    List<Application> selectList(Map<String, Object> params);
    List<Application> getApplications(Map<String, Object> params);
}