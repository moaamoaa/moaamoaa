package com.ssafy.moamoa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ssafy.moamoa.config.security.CustomAccessDeniedHandler;
import com.ssafy.moamoa.config.security.CustomEntryPoint;
import com.ssafy.moamoa.config.security.JwtTokenProvider;
import com.ssafy.moamoa.filter.JwtAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	private final JwtTokenProvider jwtTokenProvider;
	private final CustomEntryPoint customEntryPoint;
	private final CustomAccessDeniedHandler customAccessDeniedHandler;
	private final RedisTemplate<String, String> redisTemplate;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable())
			.authorizeRequests(auth -> auth.antMatchers("/users/login", "/users/signup", "/users/reissue", "/search/*").permitAll()
				.anyRequest().permitAll())
			//로그인 확인x
			//anyRequest().permitAll())
			// .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
			.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.httpBasic(Customizer.withDefaults())
			.exceptionHandling()
			.authenticationEntryPoint(customEntryPoint)
			.accessDeniedHandler(customAccessDeniedHandler);

		http.addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider, redisTemplate),
			UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}