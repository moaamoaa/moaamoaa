package com.ssafy.moamoa.config.security;

import java.util.Base64;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Component
@Slf4j
public class JwtTokenProvider {

	@Value("${security.jwt.secret-key}")
	private String secretKey;

	private long accessTokenExpireTime = 1000L * 60 * 30;

	private long refreshTokenExpireTime = 1000L * 60 * 60 * 24 * 14;

	private final UserDetailsService userDetailsService;

	@PostConstruct
	protected void init() {
		secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
	}

	public String createAccessToken(String email, String userNickname) {
		Date now = new Date();
		Date validity = new Date(now.getTime() + accessTokenExpireTime);

		return Jwts.builder()
			.setSubject("access-token")
			.claim("nickname", userNickname)
			.claim("email", email)
			.setIssuedAt(now)
			.setExpiration(validity)
			.signWith(SignatureAlgorithm.HS256, secretKey)
			.compact();
	}

	public String createRefreshToken() {
		Date now = new Date();
		Date validity = new Date(now.getTime() + refreshTokenExpireTime);

		return Jwts.builder()
			.setSubject("refresh-token")
			.setIssuedAt(now)
			.setExpiration(validity)
			.signWith(SignatureAlgorithm.HS256, secretKey)//
			.compact();
	}

	public Authentication getAuthentication(String token) {
		UserDetails userDetails = userDetailsService.loadUserByUsername(getUserEmail(token));
		return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
	}

	public String getUserEmail(String token) {
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().get("email").toString();
	}

	public String resolveToken(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");
		if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7);
		}
		return null;
	}

	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
			return true;
		} catch (SignatureException ex) {
			log.error("Invalid JWT signature");
		} catch (MalformedJwtException ex) {
			log.error("Invalid JWT token");
		} catch (ExpiredJwtException ex) {
			log.error("Expired JWT token");
		} catch (UnsupportedJwtException ex) {
			log.error("Unsupported JWT token");
		} catch (IllegalArgumentException ex) {
			log.error("JWT claims string is empty.");
		} catch (NullPointerException ex) {
			log.error("JWT RefreshToken is empty");
		}
		// catch (JwtException ex) {
		// 	throw new UnAuthorizedException("만료되었거나 검증되지 않은 토큰입니다.");
		// }
		return false;
	}
}
