package com.ssafy.moamoa.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

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
		http.cors().configurationSource(corsConfigurationSource())
			.and()
			.csrf(csrf -> csrf.disable())
			.authorizeRequests(auth -> auth
				.antMatchers(HttpMethod.OPTIONS, "/**/*").permitAll()
				.antMatchers("/api/users/login", "/api/users/signup", "/api/users/reissue", "/api/users/nickname",
					"/api/search/**", "/api/projects/detail", "/api/swagger-ui", "/api/users/email", "/swagger-ui/**")
				.permitAll()
				.antMatchers("/swagger-resources/**", "/swagger-ui/**", "/v2/api-docs").permitAll()
				.antMatchers(HttpMethod.GET, "/api/profile/{\\d+}", "/api/profile/review")
				.permitAll()
				.anyRequest()
				.authenticated())
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


	@Bean
	public CorsConfigurationSource corsConfigurationSource(){
		CorsConfiguration configuration = new CorsConfiguration();

		configuration.addAllowedOrigin("http://localhost:3000");
		configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE"));
		configuration.setAllowedHeaders(List.of("*"));
		configuration.setAllowCredentials(true); // 자격증명과 함께 요청 여부 (Authorization로 사용자 인증 사용 시 true)
		//configuration.setMaxAge(3600L);

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**",configuration);
		return source;
	}
}