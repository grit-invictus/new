package com.example.EmployeeFetch;
import org.springdoc.webmvc.ui.SwaggerWebMvcConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springdoc.core.GroupedOpenApi;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi defaultApi() {
        return GroupedOpenApi.builder()
                .group("default")
                .packagesToScan("com.example.EmployeeFetch")
                .build();
    }

//    @Bean
//    public WebMvcConfigurer webMvcConfigurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addResourceHandlers(ResourceHandlerRegistry registry) {
//                registry.addResourceHandler("/swagger-ui/**")
//                        .addResourceLocations("classpath:/META-INF/resources/webjars/springdoc-openapi-ui/")
//                        .resourceChain(false);
//            }
//        };
//    }

    @Bean
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

