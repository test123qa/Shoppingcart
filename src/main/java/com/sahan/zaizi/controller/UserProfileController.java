/**
 * 
 */
package com.sahan.zaizi.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sahan.zaizi.domain.User;
import com.sahan.zaizi.repository.UserRepository;
import com.sahan.zaizi.service.UserService;
import com.sahan.zaizi.util.ShoppingCartUtil;

/**
 * @author nisum
 *
 */

@RestController
public class UserProfileController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ShoppingCartUtil shoppingCartUtil;
	
	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.POST, value = "/profile", consumes = "application/json", produces = "application/json")
	public void addUser(@RequestBody User user) {
		userRepository.save(user);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{ip}/generateUserId")
	public void generateUserId(HttpServletRequest request, HttpServletResponse response, @PathVariable("ip") String ip) {
		System.out.println("In generateUserId()....To generate userId and write cookies for IP..."+ip);
		Cookie cookie = shoppingCartUtil.getShoppingCartCookie(request, "shoppingCart");
		String userId = ip.replace(".", "*");
		if(cookie == null){
			System.out.println(ip+"---ip---userid----"+userId);
			User user = userService.saveUserDetails(ip, userId, request);
			Cookie ck=new Cookie("shoppingCart","guest,"+user.getId());
			ck.setPath("/");
			response.addCookie(ck);
			System.out.println("cookies has been written....");
		}else{
			System.out.println("Cookies has already written.");
		}
	}

}
