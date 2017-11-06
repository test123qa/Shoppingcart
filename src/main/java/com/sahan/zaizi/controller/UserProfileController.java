/**
 * 
 */
package com.sahan.zaizi.controller;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sahan.zaizi.domain.Role;
import com.sahan.zaizi.domain.User;
import com.sahan.zaizi.repository.UserRepository;
import com.sahan.zaizi.service.SecurityService;
import com.sahan.zaizi.service.UserService;
import com.sahan.zaizi.service.UserServiceAuth;
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
	
	@Autowired
	private UserServiceAuth userServiceAuth;
	
	@Autowired
	private SecurityService securityService;

	@RequestMapping(method = RequestMethod.POST, value = "/registerUser", consumes = "application/json", produces = "application/json")
	public void registerUser(@RequestBody User user) {
		System.out.println("In registerUser()..."+user);
		userServiceAuth.save(user);
		//userRepository.save(user);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{ip}/generateUserId")
	public String generateUserId(HttpServletRequest request, HttpServletResponse response, @PathVariable("ip") String ip) {
		System.out.println("In generateUserId()....To generate userId and write cookies for IP..."+ip);
		String userName = "";
		HttpSession session = request.getSession();
		if(request.getRemoteUser() != null && request.getRemoteUser() != ""){
			userName = request.getRemoteUser();
			User user = userService.findByUserName(userName);
			session.setAttribute("userDetails", user);
			Cookie ck=new Cookie("shoppingCart","guest,"+user.getId());
			ck.setPath("/");
			response.addCookie(ck);
			System.out.println("cookies has been updated....");
		}
		System.out.println(request.getRemoteUser());
		System.out.println(securityService.findLoggedInUsername());
		System.out.println("Session id....."+session.getId());
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
		
		String loginDetails = "{\"userName\":\""+userName+"\"}";
		
		return loginDetails;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{ip}/loginData")
	public String loginData(HttpServletRequest request, HttpServletResponse response, @PathVariable("ip") String ip) {
		System.out.println("In generateUserId()....To generate userId and write cookies for IP..."+ip);
		String userName = "";
		if(request.getRemoteUser() != null && request.getRemoteUser() != ""){
			userName = request.getRemoteUser();
			User user = userService.findByUserName(userName);
			Cookie ck=new Cookie("shoppingCart","guest,"+user.getId());
			ck.setPath("/");
			response.addCookie(ck);
			System.out.println("cookies has been written....");
		}
		System.out.println(request.getRemoteUser());
		HttpSession session = request.getSession();
		System.out.println("Session id....."+session.getId());
		String loginDetails = "{\"userName\":\""+userName+"\"}";
		return loginDetails;
	}
	
	@RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm) {
    	System.out.println("In user registration()...");
        //userValidator.validate(userForm, bindingResult);

        /*if (bindingResult.hasErrors()) {
            return "registration";
        }*/
    	Role role = new Role();
    	role.setName("user");
    	HashSet<Role> set = new HashSet();
    	set.add(role);
    	userForm.setRoles(set);
    	userServiceAuth.save(userForm);

        //securityService.autologin(userForm.getUserName(), userForm.getPassword());

        return "redirect:/index";
    }

  /* @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(Model model) {
    	System.out.println("In login controller....");
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

	   return "redirect:/index.html";
    }*/

    /*@RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String welcome(Model model) {
        return "welcome";
    }*/

}
