package com.haku.devtask_manager.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.CorsConfigurationSource;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
public class CORSConfig {


    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:5173")); // Cho phép nguồn gốc mà bạn muốn
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS")); // Cho phép các phương thức HTTP
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true); // Cho phép gửi cookie và các thông tin xác thực

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}

