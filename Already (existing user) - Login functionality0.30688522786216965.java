

package com.sacral.ai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sacral.ai.model.User;
import com.sacral.ai.service.UserService;

@RestController
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping
	public boolean login(@RequestBody User user) {
		return userService.login(user);
	}
}

package com.sacral.ai.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sacral.ai.model.User;
import com.sacral.ai.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public boolean login(User user) {
		
		User existingUser = userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
		
		if(existingUser == null) {
			return false;
		}
		
		return true;
	}
}

package com.sacral.ai.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sacral.ai.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	public User findByUsernameAndPassword(String username, String password);

}