package com.study.springboot.auth;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	AuthenticationFailureHandler authenticationFailureHandler;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//System.out.println("123(encode) : " + passwordEncoder().encode("123")); //$2a$10$1NdygcF51AsKqEb/H1.9zeES43tMaiOam1aj0kqerU5EUJSzhqWB.

		http.authorizeRequests()
				.antMatchers("/").permitAll()
				.antMatchers("/css/**", "/js/**", "/img/**").permitAll()
				.antMatchers("/guest/**").permitAll()
				.antMatchers("/member/**").hasAnyRole("USER", "ADMIN")
				.antMatchers("/admin/**").hasRole("ADMIN")
				.anyRequest().authenticated();

		/**
		 * Spring 제공 Login 페이지 사용하기
		 */
//		http.formLogin()									//default : /login
//			.permitAll();
//
//		http.logout()
//			.permitAll();

		/**
		 * Custom Login 페이지 만들어서 사용하기
		 */
//		http.formLogin()
//				.loginPage("/customLoginForm")				//default : /login
//				.loginProcessingUrl("/j_spring_security_check")
//				.failureUrl("/customLoginError")			//default : /login?error
//				//.defaultSuccessUrl("/")
//				.usernameParameter("j_username")			//default : j_username
//				.passwordParameter("j_password")			//default : j_password
//				.permitAll();

		/**
		 * Security Status Check하기
		 */
		http.formLogin()
			.loginPage("/sChkLoginForm")					//default : /login
			.loginProcessingUrl("/j_spring_security_check")
			//.failureUrl("/sChkLoginForm?error")			//default : /login?error -> 로그인시 에러 발생 시 로그인에러페이지가 아닌 다시 로그인 페이지로
			.failureHandler(authenticationFailureHandler)	//사용자 지정 Error 핸들러 사용하기
			.usernameParameter("j_username")				//default : j_username
			.passwordParameter("j_password")				//default : j_password
			.permitAll();

		http.logout()
				.logoutUrl("/logout")					//default
				.logoutSuccessUrl("/")
				.permitAll();

		//ssl을 사용하지 않으면 true로 사용
		http.csrf().disable();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("user").password(passwordEncoder().encode("1234")).roles("USER")		//USER => ID:user, PW:1234
			.and()
			.withUser("admin").password(passwordEncoder().encode("1234")).roles("ADMIN");	//ADMIN => ID:user, PW:1234
			// ROLE_ADMIN 에서 ROLE_는 자동으로 붙는다.
	}


	/**
	 * DataBase 연동으로 데이터 가져오기
	 */
	@Autowired
	private DataSource dataSource;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication()
			.dataSource(dataSource)
			.usersByUsernameQuery("SELECT name AS userName, password, enabled FROM user_list WHERE name = ?")
			.authoritiesByUsernameQuery("SELECT name AS userName, authority FROM user_list WHERE name = ?")
			.passwordEncoder(new BCryptPasswordEncoder());
	}

	// passwordEncoder() 추가
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
