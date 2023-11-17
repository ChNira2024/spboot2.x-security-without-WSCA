package com.nt.niranjana.security.config;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig 
{
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http
			.authorizeHttpRequests((requests) -> requests
				.antMatchers("/", "/home").permitAll()
				.antMatchers("/customer").hasAuthority("CUSTOMER")
				.antMatchers("/admin").hasAuthority("ADMIN")
				.anyRequest().authenticated()
			)
			.formLogin((form) -> form
				.loginPage("/login")
				.permitAll()
			)
			.logout((logout) -> logout.permitAll());

		return http.build();
	}

	@Bean
	public UserDetailsService userDetailsService(DataSource datasource)
	{
			
		UserDetails user1 = User.builder()
				.username("sam1")
				.password("$2a$10$VkwJAtBbi/i8cHt5Wg3Y1.YDRm62ONmMirK9v9hGcWIi/RWXLgJzC")
				.authorities("ADMIN")
				.build();
		
		UserDetails user2 = User.builder()
				.username("ram1")
				.password("$2a$10$SPxorfdCX0TeKTuKQStMS.9EO69hBb6KtpYcj4.G67fsRNfc1yytC")
				.authorities("CUSTOMER")
				.build();
		
		UserDetails user3 = User.builder()
				.username("sisu1")
				.password("$2a$10$c8DFPTQZI2qvam932Q.WguLw6ti7u/sYP6J2Q8wc.BDObiPv.lUuC")
				.authorities("ADMIN")
				.build();

		JdbcUserDetailsManager users = new JdbcUserDetailsManager(datasource);//JdbcUserDetailsManager internally execute the insert query
		users.createUser(user1);
		users.createUser(user2);
		users.createUser(user3);
		return users;
	}
}