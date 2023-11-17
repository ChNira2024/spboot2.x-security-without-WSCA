package com.nt.niranjana.security.config; //WSCA has removed from spring 2.7.it will work upto 2.6.
                                          //if u r using 2.6 then use WSCA and override two configure method and do secure else use below code..

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig 
{


	//1.In Authentication(InMemory)
	@Bean
	public UserDetailsService userDetailsService() 
	{
		UserDetails user =
			 User.withDefaultPasswordEncoder()
			 	.username("admin")
			 	
				.password("admin")
				.roles("ADMIN") //here i didn't use roles.if u un-comment and execute then u will get seFilterChain error
				.build();

		return new InMemoryUserDetailsManager(user);
	}
	
	//2.Authorization

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception 
	{
		http
			.authorizeHttpRequests((requests) -> requests
				.antMatchers("/", "/home").permitAll()
				.anyRequest().authenticated()
			)
			.formLogin((form) -> form
				.loginPage("/login")
				.permitAll()
			)
			.logout((logout) -> logout.permitAll());

		return http.build();
	}
}