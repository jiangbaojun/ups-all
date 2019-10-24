package com.brook.ups.common;

import java.util.Map;

public interface ServiceInterface {

    int getNameCount(Map<String, Object> params);

    int add(Map<String, Object> params);

    int update(Map<String, Object> params);

    int delete(Map<String, Object> params);
}
