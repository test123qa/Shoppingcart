/**
 * 
 */
package com.virtualagent.boot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.virtualagent.boot.domain.User;
import com.virtualagent.boot.repository.UserRepository;
import com.virtualagent.boot.service.LoginService;

/**
 * @author nisum
 *
 */

@RestController
public class loginController {

	@Autowired
	private LoginService loginService;

	@Autowired
	private UserRepository userRepository;

	/*
	 * @RequestMapping(value = "/login", method = RequestMethod.POST) public
	 * ResponseEntity<?> showLoginForm(@RequestBody User user) { boolean status =
	 * false;
	 * 
	 * try { status = loginService.isUserExist(user); if (status == true) { return
	 * new ResponseEntity<>(loginService.isUserExist(user), HttpStatus.OK); } else {
	 * return new ResponseEntity<>("User Doesn't Exist with these Credentials",
	 * HttpStatus.UNAUTHORIZED); } } catch (Exception e) { return new
	 * ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST); }
	 * 
	 * }
	 */

	/*@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String checkUser(@RequestBody User user) {
System.out.println(user);
		for (User usr : userRepository.findAll()) {
			if (usr.getEmail().equals(user.getEmail()) && usr.getPwd().equals(user.getPwd())) {
				return "http://localhost:9100/shoppingcart/app/views/login.html";
			}
		}
		return null;
	}*/

	@RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "/getUsers")
	public List<User> getAllUsers() {

		return (List<User>) userRepository.findAll();

	}

}
