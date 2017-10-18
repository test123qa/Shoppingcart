/**
 * 
 */
package com.sahan.zaizi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sahan.zaizi.domain.User;
import com.sahan.zaizi.repository.UserRepository;

/**
 * @author nisum
 *
 */

@RestController
public class UserProfileController {

	@Autowired
	private UserRepository userRepository;

	@RequestMapping(method = RequestMethod.POST, value = "/profile", consumes = "application/json", produces = "application/json")
	public void addUser(@RequestBody User user) {

		userRepository.save(user);

	}

}
