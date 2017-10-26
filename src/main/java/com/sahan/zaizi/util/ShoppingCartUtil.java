package com.sahan.zaizi.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

@Component
public class ShoppingCartUtil {
	
	
	
	public Cookie getShoppingCartCookie(HttpServletRequest request, String cookieName){
		if(request.getCookies() != null){
			Cookie[] cookies  = request.getCookies();
			for (Cookie cookie : cookies) {
				if(cookie.getName().equals(cookieName)){
					return cookie;
				}
			}
		}
		
		return null;
	}
	

}
