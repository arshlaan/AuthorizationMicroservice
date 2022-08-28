package com.cognizant.Authorization.config;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtTokenUtil {
			
	public static final long JWT_TOKEN_VALIDITY_IN_MIN = 30;

	@Value("${jwt.secret}")
	private String secret;

	//To extract username from jwt token
	public String getUsernameFromToken(String token) {
	////("In getUsernameFromToken method");
	return getClaimFromToken(token, Claims::getSubject);
	}

	//retrieve expiration date from jwt token
	public Date getExpirationDateFromToken(String token) {
		////("In getExpirationDateFromToken method");
	return getClaimFromToken(token, Claims::getExpiration);
	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
	final Claims claims = getAllClaimsFromToken(token);
	return claimsResolver.apply(claims);
	}
	//for retrieveing any information from token we will need the secret key
	private Claims getAllClaimsFromToken(String token) {
	return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}

	//check if the token has expired
	private Boolean isTokenExpired(String token) {
		////("In isTokenExpired method");
	final Date expiration = getExpirationDateFromToken(token);
	return expiration.before(new Date());
	}

	//generate token for user
	public String generateToken(UserDetails userDetails) {
	Map<String, Object> claims = new HashMap<>();
	return doGenerateToken(claims, userDetails.getUsername());
	}

	//To generate token
	private String doGenerateToken(Map<String, Object> claims, String subject) {
	//("In doGenerateToken method");
	return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
	.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY_IN_MIN * 1000 * 60))
	.signWith(SignatureAlgorithm.HS512, secret).compact();
	}

	//To validate token
	public Boolean validateToken(String token, UserDetails userDetails) {
	//("In validateToken method");
	final String username = getUsernameFromToken(token);
	return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
	
	public long getExpirationDuration() {
		return JWT_TOKEN_VALIDITY_IN_MIN * 60;
	}

}
