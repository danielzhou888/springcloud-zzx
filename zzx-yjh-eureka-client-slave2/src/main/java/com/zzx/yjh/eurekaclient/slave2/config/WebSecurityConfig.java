package com.zzx.yjh.eurekaclient.slave2.config;//package com.zzx.yjh.eureka.client.slaveone.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
//
///**
// * @Copyright All Rights Reserved
// * @Company 创盛视联数码科技（北京）有限公司   https://www.bokecc.com/
// * @Author Daniel Zhou / zzx
// * @Date 2019-08-04 21:58
// **/
//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        super.configure(auth);
//        auth.inMemoryAuthentication().passwordEncoder(new SCryptPasswordEncoder())
//                .withUser("eureka-client").password("123456").roles("EUREKA_CLIENT");
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http);
//        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER);
//        http.csrf().disable();
//        //注意：为了可以使用 http://${user}:${password}@${host}:${port}/eureka/ 这种方式登录,所以必须是httpBasic,如果是form方式,不能使用url格式登录
//        http.authorizeRequests().anyRequest().authenticated().and().httpBasic();
//    }
//}
