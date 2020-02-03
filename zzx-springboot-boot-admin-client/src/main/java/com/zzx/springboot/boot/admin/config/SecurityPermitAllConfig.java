package com.zzx.springboot.boot.admin.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @Copyright All Rights Reserved
 * @Company 叮当快药科技集团有限公司
 * @Author Zhou / zzx
 * @Date 2019-05-28 21:50
 **/
@Configuration
public class SecurityPermitAllConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        http.authorizeRequests().anyRequest().permitAll()
            .and().csrf().disable();
    }
}
