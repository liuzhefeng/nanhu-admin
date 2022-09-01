package com.nanhu.admin.config.security;

import com.nanhu.admin.config.security.handler.CustomAccessDeineHandler;
import com.nanhu.admin.config.security.handler.CustomizeAuthenticationHandler;
import com.nanhu.admin.config.security.handler.LoginFailureHandler;
import com.nanhu.admin.config.security.handler.LoginSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author zfliu
 * @date 2022/8/29
 * @desc: Spring Security配置类
 */
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private CustomerUserDetailsService customerUserDetailsService;
    @Autowired
    private CustomizeAuthenticationHandler customizeAuthenticationHandler;
    @Autowired
    private CustomAccessDeineHandler customAccessDeineHandler;


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 解决 无法直接注入 AuthenticationManager
     *
     * @return
     * @throws Exception
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    /**
     * 配置权限资源
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //.formLogin()
                //.loginProcessingUrl("/api/user/login")
                //// 自定义的登录验证成功或失败后的去向
                //.successHandler(loginSuccessHandler)
                //.failureHandler(loginFailureHandler)
                //// 禁用csrf防御机制(跨域请求伪造)，这么做在测试和开发会比较方便。
                //.and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/api/user/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(customizeAuthenticationHandler)
                .accessDeniedHandler(customAccessDeineHandler);
    }

    /**
     * 配置认证处理器
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customerUserDetailsService);
    }
}
