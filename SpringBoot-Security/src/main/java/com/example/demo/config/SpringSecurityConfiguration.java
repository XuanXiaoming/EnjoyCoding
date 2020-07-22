package com.example.demo.config;

import com.example.demo.security.handler.MyAuthenticationFailureHandler;
import com.example.demo.security.handler.MyAuthenticationSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final MyAuthenticationSuccessHandler authenticationSuccessHandler;

    private final MyAuthenticationFailureHandler authenticationFailureHandler;

    private static final String[] AUTH_LIST = {
            "/v*/api-docs",
            "/configuration/ui",
            "/swagger-resources/**",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            "/swagger*/index.html",
            "/swagger*/**",
            "/authentication/require",
            "/login.html",
            "/css/**",
            "/js/**"
    };

    public SpringSecurityConfiguration(MyAuthenticationSuccessHandler authenticationSuccessHandler, MyAuthenticationFailureHandler authenticationFailureHandler) {
        this.authenticationSuccessHandler = authenticationSuccessHandler;
        this.authenticationFailureHandler = authenticationFailureHandler;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin() // 表单登录
                .loginPage("/authentication/require") // 登录跳转 URL
                .loginProcessingUrl("/login") // 处理表单登录 URL
                .successHandler(authenticationSuccessHandler) // 处理登录成功
                .failureHandler(authenticationFailureHandler) // 处理登录失败
                .and()
                .authorizeRequests() // 授权配置
                .antMatchers(AUTH_LIST).permitAll() // 登录跳转 URL 无需认证
                .anyRequest()  // 所有请求
                .authenticated() // 都需要认证
                .and().csrf().disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
