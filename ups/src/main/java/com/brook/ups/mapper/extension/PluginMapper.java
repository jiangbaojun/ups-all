package com.brook.ups.mapper.extension;

import com.brook.ups.common.BaseMapper;
import com.brook.ups.model.FormPlugin;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface PluginMapper extends BaseMapper {

    List<FormPlugin> selectList(Map<String, Object> params);

    FormPlugin getPlugInById(Map<String, Object> params);

    List<Map<String, Object>> getPlugInRef(Map<String, Object> params);
}