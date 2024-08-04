package com.springboot.shopbubu.security;


import com.springboot.shopbubu.constant.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    private static final String[] WHITE_LIST = {"/api/v1/auth/**"};


    private final JwtAuthenticationFilter jwtRequestFilter;
    private final UserDetailsService userDetailsService;
    private final CustomAccessDeniedHandler accessDeniedHandler;
    private final CustomAuthenticationEntryPoint authenticationEntryPoint;

    @Lazy
    public SecurityConfiguration(JwtAuthenticationFilter jwtRequestFilter,
                                 UserDetailsService userDetailsService,
                                 CustomAccessDeniedHandler accessDeniedHandler, CustomAuthenticationEntryPoint authenticationEntryPoint) {
        this.jwtRequestFilter = jwtRequestFilter;
        this.userDetailsService = userDetailsService;
        this.accessDeniedHandler = accessDeniedHandler;
        this.authenticationEntryPoint = authenticationEntryPoint;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfiguration) throws Exception {
        return authConfiguration.getAuthenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    // ma hoa password

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auths -> auths
                        .requestMatchers(WHITE_LIST).permitAll()
                        .requestMatchers("/api/v1/cart/**").hasAnyAuthority(Role.USER.name(), Role.ADMIN.name())
                        .requestMatchers("/api/v1/category/findAll").hasAnyAuthority(Role.USER.name(), Role.ADMIN.name())  // cho phép USER xem tất cả các danh mục
                        .requestMatchers("/api/v1/category/findById/{id}").hasAnyAuthority(Role.USER.name(), Role.ADMIN.name())
                        .requestMatchers("/api/v1/category/create").hasAuthority(Role.ADMIN.name())  // chỉ ADMIN có thể tạo danh mục
                        .requestMatchers("/api/v1/category/update").hasAuthority(Role.ADMIN.name())  // chỉ ADMIN có thể cập nhật danh mục
                        .requestMatchers("/api/v1/category/delete/{id}").hasAuthority(Role.ADMIN.name())  // chỉ ADMIN có thể xóa danh mục
                        .requestMatchers("/api/v1/customer/**").hasAnyAuthority(Role.USER.name(), Role.ADMIN.name())
                        .requestMatchers("/api/v1/order/**").hasAnyAuthority(Role.USER.name(), Role.ADMIN.name())
                        .requestMatchers("/api/v1/product/findAll").hasAnyAuthority(Role.USER.name(), Role.ADMIN.name())
                        .requestMatchers("/api/v1/product/findById/{id}").hasAnyAuthority(Role.USER.name(), Role.ADMIN.name())
                        .requestMatchers("/api/v1/product/create").hasAuthority(Role.ADMIN.name())
                        .requestMatchers("/api/v1/product/update").hasAuthority(Role.ADMIN.name())
                        .requestMatchers("/api/v1/product/delete/{id}").hasAuthority(Role.ADMIN.name())
                        .requestMatchers("/api/v1/productReview/**").hasAnyAuthority(Role.USER.name(), Role.ADMIN.name())
                        .anyRequest().authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(handler -> handler
                        .accessDeniedHandler(accessDeniedHandler)
                        .authenticationEntryPoint(authenticationEntryPoint));
        return http.build();
    }
}
