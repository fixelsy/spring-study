package com.study.springboot.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/").permitAll()
				.antMatchers("/css/**", "/js/**", "/img/**").permitAll()
				.antMatchers("/guest/**").permitAll()
				.antMatchers("/member/**").hasAnyRole("USER", "ADMIN")
				.antMatchers("/admin/**").hasRole("ADMIN")
				.anyRequest().authenticated();

		/**
		 * Spring ���� Login ������ ����ϱ�
		 */
//		http.formLogin()									//default : /login
//			.permitAll();
//
//		http.logout()
//			.permitAll();

		/**
		 * Custom Login ������ ���� ����ϱ�
		 */
		http.formLogin()
				.loginPage("/customLoginForm")				//default : /login
				.loginProcessingUrl("/j_spring_security_check")
				.failureUrl("/customLoginError")			//default : /login?error
				//.defaultSuccessUrl("/")
				.usernameParameter("j_username")			//default : j_username
				.passwordParameter("j_password")			//default : j_password
				.permitAll();

		http.logout()
				.logoutUrl("/customLogout")					//default
				.logoutSuccessUrl("/")
				.permitAll();

		//ssl�� ������� ������ true�� ���
		http.csrf().disable();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("user").password(passwordEncoder().encode("1234")).roles("USER")		//USER => ID:user, PW:1234
			.and()
			.withUser("admin").password(passwordEncoder().encode("1234")).roles("ADMIN");	//ADMIN => ID:user, PW:1234
			// ROLE_ADMIN ���� ROLE_�� �ڵ����� �ٴ´�.
	}

	// passwordEncoder() �߰�
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
