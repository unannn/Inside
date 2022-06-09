package unannn.inside.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import unannn.inside.web.filter.LoginValidationFilter;

import javax.servlet.Filter;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public FilterRegistrationBean<Filter> LoginValidationFilter() {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new LoginValidationFilter());
        filterRegistrationBean.setOrder(-101);
        filterRegistrationBean.addUrlPatterns("/login");
        return filterRegistrationBean;
    }
}
