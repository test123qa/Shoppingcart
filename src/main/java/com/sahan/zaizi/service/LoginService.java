/**
 * 
 */
package com.sahan.zaizi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sahan.zaizi.domain.User;
import com.sahan.zaizi.repository.UserRepository;

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
