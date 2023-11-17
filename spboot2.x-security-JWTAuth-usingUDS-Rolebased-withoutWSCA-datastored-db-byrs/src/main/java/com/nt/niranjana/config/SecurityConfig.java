package com.nt.niranjana.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.nt.niranjana.filter.SecurityFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig 
{

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationEntryPoint authenticationEntryPoint;
	
	@Autowired
	private SecurityFilter securityFilter;
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception 
	{
		return configuration.getAuthenticationManager();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() 
	{
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(passwordEncoder);
		provider.setUserDetailsService(userDetailsService);
		return provider;
	}
	
	@Bean
	public SecurityFilterChain configureAuth(HttpSecurity http) throws Exception
	{
		http.csrf().disable()
		.authorizeRequests().antMatchers("/user/create","/user/login","/user/tokenDetails").permitAll()
		.antMatchers(HttpMethod.GET,"/employee/fetchEmployeeAllData").hasAuthority("ADMIN")
		.antMatchers(HttpMethod.GET,"/employee/fetchEmployeeData/{id}").hasAuthority("CUSTOMER")  //use this way or use @PreAuthorize in method level,both are working
		.anyRequest().authenticated()
		.and()
		.exceptionHandling()
		.authenticationEntryPoint(authenticationEntryPoint)
		.and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
	}
	
}
//Spring 6.0 release configuration methods antMatchers(), mvcMathcers() and regexMatchers() have been removed from the API,instead u can use requestMatchers() 