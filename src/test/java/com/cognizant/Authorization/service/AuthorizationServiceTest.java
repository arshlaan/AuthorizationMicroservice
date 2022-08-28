package com.cognizant.Authorization.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.cognizant.Authorization.model.User;
import com.cognizant.Authorization.repository.UserDetailsDAO;

@SpringBootTest(classes = AuthorizationServiceTest.class)
public class AuthorizationServiceTest {

	@InjectMocks
	AuthorizationService authService;
	
	@Mock
	UserDetailsDAO userDao;
	
	@Test
	public void loadUserByUsernameTest() {
		Mockito.when(userDao.findByUsername("root")).thenReturn(new User(1,"root","password"));
		assertNotNull(authService.loadUserByUsername("root"));
	}
}
