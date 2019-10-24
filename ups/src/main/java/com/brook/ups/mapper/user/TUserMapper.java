package com.brook.ups.mapper.user;

import com.brook.ups.model.user.Tuser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TUserMapper {
    List<Tuser> findByUsers();
}