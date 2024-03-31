package com.user.service;

import java.util.List;

import com.user.dto.UserDto;



public interface UserService {
	UserDto createUser(UserDto userDto);

	UserDto updateUser(UserDto userDto, Long id);

	List<UserDto> getAllUser();

	UserDto getUserById(Long id);

	UserDto getUserByEmail(String email);

}
