package top.suyiiyii.guavalearn.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import top.suyiiyii.guavalearn.utils.RbacHandler;

@Configuration
public class InterceptorsConfig implements WebMvcConfigurer {


    RbacHandler rbacHandler;

    @Autowired
    public InterceptorsConfig(RbacHandler rbacHandler) {
        this.rbacHandler = rbacHandler;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(rbacHandler);
    }
}
