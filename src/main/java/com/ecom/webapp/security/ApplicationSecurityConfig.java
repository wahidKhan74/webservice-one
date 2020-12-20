package com.ecom.webapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public void configure(HttpSecurity http) throws Exception {		
		http.authorizeRequests()
		.antMatchers("/webapione/person/**").hasRole("admin")
		.antMatchers("/webapione/persons/**").hasRole("admin")
		.anyRequest().authenticated().and().csrf().disable().httpBasic();		
	}
	
	@Override
	@Bean
	protected UserDetailsService userDetailsService() {
		UserDetails johnSmith = User.builder().username("johnsmith")
				.password(passwordEncoder.encode("password123"))
				.roles("admin")
				.build();
		UserDetails tonyStark = User.builder().username("tonystark")
				.password(passwordEncoder.encode("password123"))
				.roles("user")
				.build();
		
		return new InMemoryUserDetailsManager(
				johnSmith,tonyStark);
	}
	
	

}
