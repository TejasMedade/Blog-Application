package com.masai.spring.security.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.masai.JWT.security.configurations.JWTAuthenticationEntryPoint;
import com.masai.JWT.security.configurations.JWTAuthenticationFilter;

/**
 * @author tejas
 *
 */

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration {

	public static final String[] PUBLIC_URLS = {"/blog/auth/signup/user", "/blog/auth/signin", "/blog/auth/signout" ,"/v3/api-docs", "/v2/api-docs",
			"/swagger-resources/**", "/swagger-ui/**", "/webjars/**" };
	
	public static final String[] USER_ADMIN_URLS = {"/blog/category/**","/blog/comment/**","/blog/post/**","/blog/user/**"};
	
	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JWTAuthenticationEntryPoint authenticationEntryPoint;

	// Basic Authentication for API
	// Needs a Better Way, as we need to enter UserName & Password from
	// application.properties
	// every time we fire a request.
	// So JWT Token came in to picture.

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.cors().and().csrf().disable().exceptionHandling().authenticationEntryPoint(authenticationEntryPoint).and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
				.antMatchers(PUBLIC_URLS).permitAll()
				.antMatchers(HttpMethod.GET).permitAll()
				.antMatchers(USER_ADMIN_URLS).hasAnyRole("USER","ADMIN")
				.anyRequest().authenticated();

		http.authenticationProvider(authenticationProvider());

		http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

	// Basic Authentication for API
	// Entering Username & Password from Database
	// Build UserDetails By Extending User
	// Build UserServiceDetails Implementation

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

		authProvider.setUserDetailsService(userDetailsService);

		authProvider.setPasswordEncoder(passwordEncoder());

		return authProvider;
	}

	// Password Endcoder Required

	@Bean
	public PasswordEncoder passwordEncoder() {

		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}

	@Bean
	public JWTAuthenticationFilter authenticationJwtTokenFilter() {
		return new JWTAuthenticationFilter();
	}

}
