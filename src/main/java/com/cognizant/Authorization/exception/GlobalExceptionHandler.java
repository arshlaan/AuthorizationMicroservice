package com.cognizant.Authorization.exception;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;


@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
		
	@ExceptionHandler(SignatureException.class)
	public ResponseEntity<ExceptionResponse> handleSignatureException(SignatureException e){
		//log.info("In handleSignatureException method");
		//log.error(e.getMessage());
		SimpleDateFormat date = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss:SSS");
		ExceptionResponse exceptionResponse = new ExceptionResponse(date.format(new Date()), HttpStatus.UNAUTHORIZED.name(),HttpStatus.UNAUTHORIZED.value(), "Invalid Token" );
		return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.UNAUTHORIZED);
	}
	

} 
