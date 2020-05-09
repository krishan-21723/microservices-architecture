package com.xyz.userservice;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.xyz.userservice.exception.GenericException;
import com.xyz.userservice.model.User;
import com.xyz.userservice.request.AddUserRequest;
import com.xyz.userservice.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserServiceApplicationTests {

	@MockBean
	private UserService userService;

	@Test
	void saveUserTest() throws GenericException {

		Integer userId = 123;
		String name = "test";
		String email = "test12345@dummy.com";
		Mockito.when(userService.saveUser(AddUserRequest.builder().name(name).email(email).build())).thenReturn(userId);

		assertThat(userService.saveUser(AddUserRequest.builder().name(name).email(email).build()) > userId);

	}

	@Test
	void getUserTest() throws Exception {

		Integer userId = 123;
		String name = "test";
		String email = "test12345@dummy.com";
		User user = User.builder().id(userId).name(name).email(email).build();
		Mockito.when(userService.getUser(userId)).thenReturn(user);

		assertThat(userService.getUser(userId).getId()).isEqualTo(userId);

	}

}
