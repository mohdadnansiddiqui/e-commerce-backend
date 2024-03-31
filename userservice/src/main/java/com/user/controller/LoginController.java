package com.user.controller;

import com.user.dto.JwtResponse;
import com.user.dto.LoginDto;
import com.user.dto.UserDto;
import com.user.entity.User;
import com.user.exception.custom.BadCredentialException;
import com.user.repository.UserRepository;
import com.user.utils.JwtTokenUtil;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/api/user/")
@CrossOrigin("*")
public class LoginController {

	private final UserDetailsService userDetailsService;
	private final JwtTokenUtil jwtTokenUtil;
	private final AuthenticationManager authenticationManager;
	private final UserRepository userRepository;
	private final ModelMapper modelMapper;

	@PostMapping("login")
	@ResponseStatus(value = HttpStatus.OK)
	private JwtResponse loginUser(@RequestBody LoginDto loginDto) {
		authnicate(loginDto.getUsername(), loginDto.getPassword());
		UserDetails userDetails = userDetailsService.loadUserByUsername(loginDto.getUsername());
		String generateToken = jwtTokenUtil.generateToken(userDetails);
		User user = userRepository.findByEmail(loginDto.getUsername()).get();
		return new JwtResponse(generateToken, this.modelMapper.map(user, UserDto.class));
	}

	private void authnicate(String username, String password) {

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

		} catch (Exception e) {
			throw new BadCredentialException("Bad Crienditial!!");
		}
	}

}
