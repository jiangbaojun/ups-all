package com.brook.ups.config;

import com.brook.ups.common.enums.Version;
import com.brook.ups.config.filter.MyFilter;
import com.brook.ups.config.interceptor.AuthInnerInterceptor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * 配置
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public HandlerInterceptor myInterceptor() {
        return new AuthInnerInterceptor();
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //添加拦截器
        InterceptorRegistration interceptor = registry.addInterceptor(myInterceptor());
        String baseVersion = "/v"+Version.INNER.getValue();
        final String[] notInterceptPaths = {
            baseVersion+"/system/account/login",
            baseVersion+"/system/account/logout"
        };
        interceptor.excludePathPatterns(notInterceptPaths)
            .addPathPatterns(baseVersion+"/**");
    }

    @Bean
    public FilterRegistrationBean myFiletr(){
        //过滤器
        FilterRegistrationBean filter1 = new FilterRegistrationBean(new MyFilter());
        filter1.addUrlPatterns("/user/*");
        return filter1;
    }

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration conf = new CorsConfiguration();
        conf.addAllowedHeader("*");
        conf.addAllowedMethod("*");
        conf.addAllowedOrigin("*");
        conf.setAllowCredentials(true);
        conf.setMaxAge(3600L);
        conf.addExposedHeader("set-cookie");
        conf.addExposedHeader("access-control-allow-headers");
        conf.addExposedHeader("access-control-allow-methods");
        conf.addExposedHeader("access-control-allow-origin");
        conf.addExposedHeader("access-control-max-age");
        conf.addExposedHeader("X-Frame-Options");
        conf.addExposedHeader("X-Token");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", conf);
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(0);
        return new CorsFilter(source);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
            .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
            .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Bean
    public Docket api() {
        ApiInfo apiInfo = new ApiInfoBuilder()
            .title("exampleApi")
            .termsOfServiceUrl("www.example.com")
            .contact(new Contact("xiaoming","http://127.0.0.1:8080/","xiaoming@gmail.com"))
            .version("v1")
            .build();
        return new Docket(DocumentationType.SWAGGER_2)
            .groupName("v1")
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.brook.ups.controller.api"))
            .paths(PathSelectors.any())
            .build().apiInfo(apiInfo);
    }
}