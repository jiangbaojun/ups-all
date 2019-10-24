package com.brook.ups.config.version;

import com.brook.ups.common.annotation.ApiVersion;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.condition.RequestCondition;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;

/**
 * 自定义请求映射
 */
public class ApiRequestMappingHandlerMapping extends RequestMappingHandlerMapping {
    private static final String VERSION_FLAG = "{version}";

    /**
     * 自定义含有ApiVersion注解和{version}的pathvar映射处理
     */
    private static RequestCondition<ApiVersionCondition> createCondition(Class<?> clazz) {
        RequestMapping classRequestMapping = clazz.getAnnotation(RequestMapping.class);
        if (classRequestMapping == null) {
            return null;
        }
        StringBuilder mappingUrlBuilder = new StringBuilder();
        if (classRequestMapping.value().length > 0) {
            mappingUrlBuilder.append(classRequestMapping.value()[0]);
        }
        String mappingUrl = mappingUrlBuilder.toString();
        if (!mappingUrl.contains(VERSION_FLAG)) {
            return null;
        }
        ApiVersion apiVersion = clazz.getAnnotation(ApiVersion.class);
        if(apiVersion==null || apiVersion.value()==null){
            return null;
        }
        return new ApiVersionCondition(apiVersion.value().getValue());
    }

    @Override
    protected RequestCondition<?> getCustomMethodCondition(Method method) {
        RequestCondition<ApiVersionCondition> apiRequestCondition = createCondition(method.getClass());
        if(apiRequestCondition!=null){
            return apiRequestCondition;
        }
        return super.getCustomMethodCondition(method);
    }

    @Override
    protected RequestCondition<?> getCustomTypeCondition(Class<?> handlerType) {
        RequestCondition<ApiVersionCondition> apiRequestCondition = createCondition(handlerType);
        if(apiRequestCondition!=null){
            return apiRequestCondition;
        }
        return super.getCustomTypeCondition(handlerType);
    }
}