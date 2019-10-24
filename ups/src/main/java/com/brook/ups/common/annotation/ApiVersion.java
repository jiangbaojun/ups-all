package com.brook.ups.common.annotation;

import com.brook.ups.common.enums.Version;
import org.springframework.web.bind.annotation.Mapping;

import java.lang.annotation.*;

/**
 * 版本控制
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Mapping
public @interface ApiVersion {
    /** 标识版本号 */
    Version value();
}