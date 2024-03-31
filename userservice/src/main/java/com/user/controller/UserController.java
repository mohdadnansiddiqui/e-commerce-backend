package com.user.controller;

import java.util.List;

import com.user.dto.UserDto;
import com.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/user")
@CrossOrigin("*")
public class UserController {
	private final UserService userService;

	@PostMapping("/create-user")
	private ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
		return new ResponseEntity<>(this.userService.createUser(userDto), HttpStatus.CREATED);
	}

	@PutMapping("/update-user/{id}")
	private ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto, @PathVariable Long id) {
		return new ResponseEntity<>(this.userService.updateUser(userDto, id), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	private ResponseEntity<UserDto> getUserByID(@PathVariable Long id) {
		return new ResponseEntity<>(this.userService.getUserById(id), HttpStatus.OK);

	}

	@GetMapping("/")
	private ResponseEntity<List<UserDto>> getAllUsers() {
		return new ResponseEntity<>(this.userService.getAllUser(), HttpStatus.OK);
	}

	@GetMapping("/email/{email}")
	private ResponseEntity<UserDto> getUserByEmail(@PathVariable String email) {
		return new ResponseEntity<>(this.userService.getUserByEmail(email), HttpStatus.OK);
	}
}
