package com.dynamic.dataConvert.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class SwaggerConfig {

    @Bean
    public GroupedOpenApi defaultApi() {
        return GroupedOpenApi.builder()
                .group("default")
                .packagesToScan("com.dynamic.dataConvert")
                .build();
    }
    public WebMvcConfigurer webMvcConfigurer()
    {
        return new SwaggerWebMvcConfigurer();
    }
    private static class SwaggerWebMvcConfigurer implements WebMvcConfigurer{
        public void addResourceHandler(ResourceHandlerRegistry registry)
        {
            registry.addResourceHandler("/swagger-ui/**").addResourceLocations("classpath:/META-INF/resources/webjars/springdoc-openapi-ui/");
        }

    }



}
