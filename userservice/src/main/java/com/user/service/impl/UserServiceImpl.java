package com.user.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.user.constant.AppConstant;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.user.dto.UserDto;
import com.user.entity.Role;
import com.user.entity.User;
import com.user.exception.custom.UserNotFoundException;
import com.user.repository.RoleRepository;
import com.user.repository.UserRepository;
import com.user.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final RoleRepository roleRepository;
	private final UserRepository userRepository;
	private final ModelMapper mapper;
	private final PasswordEncoder passwordEncoder;

	@Override
	public UserDto createUser(UserDto userDto) {
		Role role = this.roleRepository.findById(AppConstant.NORMAL_USER).get();
		Set<Role> set = new HashSet<>();
		set.add(role);
		User user = this.mapper.map(userDto, User.class);
		user.setRoles(set);
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		this.userRepository.save(user);
		return this.mapper.map(this.userRepository.save(user), UserDto.class);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Long id) {
		User user = this.userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("User id " + id + " not found"));
		User user2 = this.mapper.map(userDto, User.class);
		user2.setUserId(user.getUserId());
		user2.setRoles(user.getRoles());
		return this.mapper.map(this.userRepository.save(user2), UserDto.class);
	}

	@Override
	public List<UserDto> getAllUser() {
		List<User> userList = this.userRepository.findAll();
		return userList.stream().map(u -> this.mapper.map(u, UserDto.class)).collect(Collectors.toList());
	}

	@Override
	public UserDto getUserById(Long id) {
		User user = this.userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("User id " + id + " not found"));
		return this.mapper.map(user, UserDto.class);
	}

	@Override
	public UserDto getUserByEmail(String email) {
		User user = this.userRepository.findByEmail(email)
				.orElseThrow(() -> new UserNotFoundException("User email " + email + " not found"));
		return this.mapper.map(user, UserDto.class);
	}

}
