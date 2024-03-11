package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bean.RespsonseBean;
import com.dto.UserDto;
import com.entity.UserEntity;
import com.repositry.UserRepositry;

@RestController
public class SessionController {
	@Autowired
	private UserRepositry users;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping("signup")
	public RespsonseBean<UserEntity> signup(@RequestBody UserEntity user) {
		RespsonseBean<UserEntity> respsonse = new RespsonseBean<>();
		String hasspasword = passwordEncoder.encode(user.getPassword());
		user.setPassword(hasspasword);

		if (user != null) {
			users.save(user);
			respsonse.setData(user);
			respsonse.setMessage("user Signup");
			respsonse.setStatus(201);
		} else {
			respsonse.setMessage("Data is Missing ");
			respsonse.setStatus(200);
		}
		return respsonse;
	}

	@PostMapping("login")
	public RespsonseBean<UserEntity> login(@RequestBody UserDto usrdto) {
		UserEntity u1 = users.findByEmail(usrdto.getEmail());
		RespsonseBean<UserEntity> respsonse = new RespsonseBean<>();
		passwordEncoder.matches(u1.getPassword(), usrdto.getPassword());

		if (u1 != null) {
			// User found, now check password
			boolean passwordMatch = passwordEncoder.matches(usrdto.getPassword(), u1.getPassword());
			System.out.println(passwordMatch);
			if (passwordMatch) {
				respsonse.setData(u1);
				respsonse.setMessage("User login successful");
				respsonse.setStatus(201);
			} else {
				respsonse.setMessage("Incorrect password");
				respsonse.setStatus(401); // Unauthorized status code
			}
		} else {
			respsonse.setMessage("No User Found");
			respsonse.setStatus(404); // Not Found status code
		}
		return respsonse;

	}
	
}
