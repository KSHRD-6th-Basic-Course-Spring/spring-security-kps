package com.kshrd.demo.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private AccessDeniedHandler accessDeniedHandler;
	@Autowired
	private AuthenticationSuccessHandler successHandler;
	
	@Autowired
	private AuthenticationEntryPoint authenticationEntryPoint;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// In Memory Authentication
//		auth.inMemoryAuthentication()
//			.withUser("admin").password("admin").roles("ADMIN")
//			.and()
//			.withUser("editor").password("editor").roles("EDITOR")
//			.and()
//			.withUser("user").password("user").roles("USER");
		
		// User Detail Service Authentication
		auth.userDetailsService(userDetailsService)
			.passwordEncoder(this.bCryptPasswordEncoder());
		
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin()
			.usernameParameter("email")
			.loginPage("/login")
			.successHandler(successHandler);
		
		
//		http.csrf().disable();
		
		http.logout()
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
	
		http.exceptionHandling()
			.authenticationEntryPoint(authenticationEntryPoint);
//			.accessDeniedHandler(accessDeniedHandler);
		
		
		http.authorizeRequests()
			.antMatchers("/admin/**").hasRole("ADMIN")
			.antMatchers("/editor/**").hasAnyRole("ADMIN", "EDITOR")
			.antMatchers("/user/**").hasAnyRole("ADMIN", "EDITOR", "USER");
		
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}








