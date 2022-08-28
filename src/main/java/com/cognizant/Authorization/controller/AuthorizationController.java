package com.cognizant.Authorization.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.Authorization.config.JwtTokenUtil;
import com.cognizant.Authorization.model.AuthResponse;
import com.cognizant.Authorization.model.User;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin
@Slf4j
public class AuthorizationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserDetailsService userDetailsService;


	// To create JWT token if successfully authenticated
	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody User authenticationRequest)
			throws DisabledException, BadCredentialsException, Exception {
		
	

		//("In createAuthenticationToken method");

		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
				authenticationRequest.getPassword()));

		//("After calling authenticate method in createAuthenticationToken method");

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);
		//("Token successfully generated");
		AuthResponse authResponse = new AuthResponse(token, jwtTokenUtil.getExpirationDuration());
		return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.OK);
	}

	// To authorize based on the input token provided in header
	@GetMapping("/authorize")
	public ResponseEntity<Boolean> authorize(@RequestHeader("Authorization") String header) {
		//("In authorize method");

		String token = header.substring(7);

		UserDetails userDetails = userDetailsService.loadUserByUsername(jwtTokenUtil.getUsernameFromToken(token));

		if (jwtTokenUtil.validateToken(token, userDetails)) {
			//("Token is valid");
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		} else {
			//("Token is Invalid");
			return new ResponseEntity<Boolean>(false, HttpStatus.FORBIDDEN);
		}
	}

}
