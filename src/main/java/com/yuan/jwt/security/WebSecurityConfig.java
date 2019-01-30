package com.yuan.jwt.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

/**
 * @author: mac
 * @date: 2019-01-30
 * @description:
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // Disable CSRF (cross site request forgery)
        http.csrf().disable();

        // No session will be created or used by spring security
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // Entry points
        http.authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS).permitAll() //配合@CrossOrigin 解决跨域问题
                .antMatchers("/test/**").permitAll() // test/**无需websecurity认证
                // Disallow everything else..
                .anyRequest().authenticated(); // 其他请求都需要认证

        http.apply(new JwtTokenFilterConfigurer(jwtTokenProvider));

    }

}
