/**
 * 
 */
package com.virtualagent.boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.virtualagent.boot.domain.User;
import com.virtualagent.boot.repository.UserRepository;

/**
 * @author nisum
 *
 */

@Component
public class LoginService {

	@Autowired
	private UserRepository userRepository;

//	public boolean isUserExist(User usr) {
//
//		return userRepository.isUserExist(usr.getName(), usr.getPwd());
//
//	}

}
