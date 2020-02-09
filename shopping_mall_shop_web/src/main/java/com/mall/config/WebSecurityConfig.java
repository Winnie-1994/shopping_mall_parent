package com.mall.config;

import com.mall.service.CustomUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 注入自定义UserService，用于查库查看用户是否合法
     * @return
     */
    @Bean
    UserDetailsService customUserService() {
        return new CustomUserService();
    }

    /**
     * 查出用户的密码后跟PasswordEncoder编码后的进行比对
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserService()).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .headers().frameOptions().disable()
                .and().authorizeRequests()
                .antMatchers(
                        "/shopLogin.html",
                        "/register.html",
                        "/failed",
                        "/css/**",
                        "/img/**",
                        "/js/**",
                        "/plugins/**",
                        "/seller/add"
                ).permitAll()
                .anyRequest().authenticated()
                .and().formLogin().loginPage("/shopLogin.html")
                // alwaysUse为true，可以防止先请求别的页面登录后跳到前面请求的页面的情况
                .loginProcessingUrl("/login").defaultSuccessUrl("/admin/index.html", true)
                .and().logout().logoutSuccessUrl("/shopLogin.html").permitAll()// 退出默认跳转页面
                .and().csrf().disable();
    }


}
