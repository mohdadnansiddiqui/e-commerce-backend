package com.user.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	@NotBlank(message = "Name cannot be blank")
	@Size(min = 5,max = 30,message = "name should be 5 to 30 char")
	private String name;
	@NotBlank(message = "email cannot be blank")
	private String email;
	@NotBlank(message = "password cannot be blank")
	@Size(min = 8,message = "password should be 8 char")
	private String password;
	@NotBlank(message = "gender cannot be blank")
	private String gender;
	@NotBlank(message = "about cannot be blank")
	@Size(min = 10,max = 1000,message = "about should be 10 to 1000 char")
	private String about;

	private String imageName;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(joinColumns = @JoinColumn(name = "user", referencedColumnName = "userId"), inverseJoinColumns = @JoinColumn(name = "role", referencedColumnName = "roleId"))
	private Set<Role> roles = new HashSet<>();

}
