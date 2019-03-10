package com.sap.csc.gateway;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/*
 * @author I326950
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.
			// Disable the frame options
			headers().frameOptions().disable().
			and().authorizeRequests().antMatchers("/resources/**").permitAll();
//		http
//				.authorizeRequests()
//					.antMatchers("/css/**", "/index").permitAll()
//					.antMatchers("/user/**").hasRole("USER")
//					.and()
//				.formLogin().loginPage("/login").failureUrl("/login-error");
	}

}
