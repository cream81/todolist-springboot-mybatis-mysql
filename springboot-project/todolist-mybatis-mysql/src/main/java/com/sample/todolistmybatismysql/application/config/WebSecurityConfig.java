package com.sample.todolistmybatismysql.application.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.sample.todolistmybatismysql.domain.service.login.ReservationLoginDetailsService;

@Configuration // コンフィグレーションファイル
@EnableGlobalMethodSecurity(prePostEnabled = true) // メソッドセキュリティを有効
@EnableWebSecurity // Spring Securityを使うための設定
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
    private ReservationLoginDetailsService reservationLoginDetailsService;

    @Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .authorizeRequests()
        .antMatchers("/css/**")
        .permitAll()
        .and()
        .authorizeRequests()
        .anyRequest()
        .authenticated()
        .and()
      .formLogin()
          .defaultSuccessUrl("/todos", true)
          .permitAll()
          .and()
      .logout()
          .permitAll();
    }

    @Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(reservationLoginDetailsService).passwordEncoder(passwordEncoder());
	}
}