package com.brook.ups.common;

import java.util.Map;

public interface BaseMapper {
    int delete(Map<String, Object> params);

    int update(Map<String, Object> params);

    int add(Map<String, Object> params);

    int getNameCount(Map<String, Object> params);
}