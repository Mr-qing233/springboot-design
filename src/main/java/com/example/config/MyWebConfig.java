package com.example.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * <p>
 * 跨域问题解决方案[后端]
 * 所有请求都允许跨域，使用这种配置就不需要在interceptor中配置header了
 * </p>
 *
 * @author Gin
 * @since 2023-06-10
 */
@SpringBootConfiguration
public class MyWebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry corsRegistry){
        corsRegistry.addMapping("/**")
                .allowCredentials(true)
                .allowedOriginPatterns("*")
                // 不知道为什么这个不行
                // .allowedOrigins("http://localhost:8080")
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
                .allowedHeaders("*")
                .maxAge(3600);
    }
}