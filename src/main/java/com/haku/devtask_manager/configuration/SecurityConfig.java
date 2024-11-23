package com.haku.devtask_manager.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfigurationSource;

import javax.crypto.spec.SecretKeySpec;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final String[]  PUBLIC_ENDPOINTS = {"/user","/login","/account/**","/roles/**","/rolesdetail/**",
            "/department/**","/departmentdetail/**","/category/**","/information/**","/degree/**",
            "/degreedetail/**","/specializationdetail/**","specialization/**"};
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(request ->
                request.requestMatchers(HttpMethod.POST,
                                PUBLIC_ENDPOINTS).permitAll()
                        .requestMatchers(HttpMethod.GET,
                                PUBLIC_ENDPOINTS).permitAll()
                        .requestMatchers(HttpMethod.PUT,
                                PUBLIC_ENDPOINTS).permitAll()
                        .requestMatchers(HttpMethod.DELETE,
                                PUBLIC_ENDPOINTS).permitAll()

                        //phân quyền project
                        .requestMatchers(HttpMethod.POST,
                                "/project/**").hasRole("MN")
                        .requestMatchers(HttpMethod.GET,
                                "/project/**").hasRole("MN")
                        .requestMatchers(HttpMethod.PUT,
                                "/project/**").hasRole("MN")
                        .requestMatchers(HttpMethod.DELETE,
                                "/project/**").hasRole("MN")
//                        .requestMatchers(HttpMethod.GET,"/category/**").hasRole("category")
//                        .requestMatchers(HttpMethod.POST,"/category/**").hasRole("category")
                        .anyRequest().authenticated());

        httpSecurity.oauth2ResourceServer(oauth2 ->
                oauth2.jwt(jwtConfigurer -> jwtConfigurer.decoder(jwtDecoder())
                        .jwtAuthenticationConverter(jwtAuthenticationConverter()))
        );

        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        return httpSecurity.build();
    }

    @Bean
    JwtDecoder jwtDecoder(){
        SecretKeySpec secretKeySpec = new SecretKeySpec("jk6lzqp2tghGPcTCCODK5Jql2qpYmKcMnvBzgg43KccOSusywDLnTgkms83lbsWm".getBytes(),"HS512");
        return NimbusJwtDecoder
                .withSecretKey(secretKeySpec)
                .macAlgorithm(MacAlgorithm.HS512)
                .build();
    };
    @Bean
    JwtAuthenticationConverter jwtAuthenticationConverter(){
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix("ROLE_");
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
        return  jwtAuthenticationConverter;
    }


    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(10) {
        };
    }
}
