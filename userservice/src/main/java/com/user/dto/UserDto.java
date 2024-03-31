package com.user.dto;

import java.util.HashSet;
import java.util.Set;
import com.user.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
	private String name;

	private String email;

	private String password;

	private String gender;

	private String about;

	private String imageName;

	private Set<Role> roles = new HashSet<>();
}
