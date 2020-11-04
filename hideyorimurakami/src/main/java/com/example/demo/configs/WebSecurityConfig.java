package com.example.demo.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	private final UserDetailsService userDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {


//		http.csrf().disable();
//		http.headers().frameOptions().disable();


		http.authorizeRequests()
		// ログイン不要ページの設定
		.antMatchers("/h2-console/**").permitAll()
				.antMatchers("/webjars/**").permitAll() //webjarsへアクセス許可
				.antMatchers("/css/**").permitAll() //cssへアクセス許可
				.antMatchers("/img/**").permitAll()
				.antMatchers("/login").permitAll() //ログインページは直リンクOK
				.antMatchers("/signup").permitAll() //ユーザー登録画面は直リンクOK
				.antMatchers("/").permitAll()
				.antMatchers("/blackjackstrategy").permitAll()
				.antMatchers("/janken").permitAll()
				.antMatchers("/roulettecalculator").permitAll()
				.antMatchers("/roulettehistoryboard").permitAll()
				.anyRequest().authenticated()
				.and()
				.formLogin()
				.loginPage("/login")
				.usernameParameter("userId")
				.passwordParameter("password")
				.and()
				.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/")
				.invalidateHttpSession(true);

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		auth
				.userDetailsService(userDetailsService)
				.passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
