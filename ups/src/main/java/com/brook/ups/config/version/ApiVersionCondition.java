package com.brook.ups.config.version;

import org.springframework.lang.Nullable;
import org.springframework.web.servlet.mvc.condition.RequestCondition;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 版本url匹配器
 */
public class ApiVersionCondition implements RequestCondition<ApiVersionCondition> {
    // 路径中版本的前缀， 这里用 /v[1-9]/的形式
    private final static Pattern VERSION_PREFIX_PATTERN = Pattern.compile("v(\\d+)/");
 
    /**
     * api的版本
     */
    private int apiVersion;
 
    public ApiVersionCondition(int apiVersion) {
        this.apiVersion = apiVersion;
    }
 
    /**
     * 将不同的筛选条件合并
     */
    @Override
    public ApiVersionCondition combine(ApiVersionCondition apiVersionCondition) {
        return new ApiVersionCondition(apiVersionCondition.getApiVersion());
    }
 
    /**
     * 根据request查找匹配到的筛选条件
     * 匹配大于等于当前请求版本的condition
     */
    @Nullable
    @Override
    public ApiVersionCondition getMatchingCondition(HttpServletRequest httpServletRequest) {
        //return null;
        Matcher m = VERSION_PREFIX_PATTERN.matcher(httpServletRequest.getRequestURI());
        if(m.find()){
            Integer version = Integer.valueOf(m.group(1));
            //只允许访问相同版本.如果做向下版本兼容，可以（version >= this.apiVersion）
            if(version == this.apiVersion)
            {
                return this;
            }
        }
        return null;
    }
 
    /**
     * 当多个condition匹配时，做排序比较
     * 本例：使用高版本
     */
    @Override
    public int compareTo(ApiVersionCondition apiVersionCondition, HttpServletRequest httpServletRequest) {
        //return 0;
        // 优先匹配最新的版本号
        return apiVersionCondition.getApiVersion() - this.apiVersion;
    }
 
    public int getApiVersion() {
        return apiVersion;
    }
 
 
}