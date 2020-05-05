package com.brook.ups.mapper.basic;

import com.brook.ups.common.BaseMapper;
import com.brook.ups.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper extends BaseMapper {
    List<User> selectList(Map<String, Object> params);
}