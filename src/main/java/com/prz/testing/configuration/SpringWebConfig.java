package com.prz.testing.configuration;

import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.util.Log4jConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.io.FileNotFoundException;

/**
 * Created by Roman on 25.08.2015.
 */
@Configuration
@EnableWebMvc
@PropertySource({"classpath:application.properties", "classpath:log4j.properties"})
@ComponentScan(basePackages = "com.prz.testing")
public class SpringWebConfig extends WebMvcConfigurerAdapter{

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        System.out.println("startup");
        registry.addResourceHandler("/resources*//**").addResourceLocations("/resources/");
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer appProperties(){
        return new PropertySourcesPlaceholderConfigurer();
    }

  /*  @Bean
    public void initLog4j() throws FileNotFoundException{
        Log4jConfigurer.initLogging("classpath:log4j.properties");
    }*/

}
