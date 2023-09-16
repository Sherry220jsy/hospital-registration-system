package com.sherry.config;


import com.sherry.interceptor.JwtTokenAdminInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.*;


import java.util.List;

/**
 * 配置类，注册web层相关组件
 */
@Configuration
@Slf4j
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Autowired
    private JwtTokenAdminInterceptor jwtTokenAdminInterceptor;

    /**
     * 注册自定义拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        log.info("开始注册自定义拦截器...");
        registry.addInterceptor(jwtTokenAdminInterceptor)
                .excludePathPatterns("/login")
                .excludePathPatterns("/patient/signup")
                .excludePathPatterns("/patient/logout")
                .excludePathPatterns("/doctor/logout");
    }



    /**
     * 设置静态资源映射
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
            // 设置允许跨域的路径
            registry.addMapping("/**")
                    // 设置允许跨域请求的域名
                    .allowedOriginPatterns("*")
                    // 是否允许cookie
                    .allowCredentials(true)
                    // 设置允许的请求方式
                    .allowedMethods("GET", "POST", "DELETE", "PUT","OPTIONS")
                    // 设置允许的header属性
                    .allowedHeaders("*")
                    // 跨域允许时间
                    .maxAge(3600);
    }


}
