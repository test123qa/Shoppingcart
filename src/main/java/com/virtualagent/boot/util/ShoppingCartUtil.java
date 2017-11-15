package com.virtualagent.boot.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.virtualagent.boot.domain.User;

@Component
public class ShoppingCartUtil {
	
	
	
	public Cookie getShoppingCartCookie(HttpServletRequest request, String cookieName){
		System.out.println("In getShoppingCartCookie()......");
		if(request.getCookies() != null){
			Cookie[] cookies  = request.getCookies();
			System.out.println("Cookies list.........");
			for (Cookie cookie : cookies) {
				System.out.println(cookie.getName()+"-->>---"+cookie.getValue());
				if(cookie.getName().equals(cookieName)){
					return cookie;
				}
			}
		}
		
		return null;
	}
	
	public String getIPInfo(String ip){
		System.out.println("In ShoppingCartUtil..getIPInfo() ..method");
		BufferedReader reader = null;
		String ipInfo = null;
		try{
			URL url = new URL("http://freegeoip.net/json/" + ip);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.connect();

			InputStream is = connection.getInputStream();
			int status = connection.getResponseCode();
			if (status != 200) {
				System.out.println("Error.......");
				return null;
			}

			reader = new BufferedReader(new InputStreamReader(is));
			if((ipInfo = reader.readLine()) != null){
				System.out.println("....."+ipInfo);
			}

		}catch(Exception e){
			e.printStackTrace();
		}
		finally {
			try {
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return ipInfo;

	}

	public void generateRandomTokenId(HttpServletRequest request, HttpServletResponse response, User user){
		System.out.println("In generateRandomTokenId()......");

		Cookie[] cookies = request.getCookies();
		if (cookies != null){
			Cookie cookie = new Cookie("userId", "");

			for (Cookie ck : cookies) {
				if ("guestTokenID".equals(ck.getName())) {
					//if such a cookie is available, override it.
					cookie = ck;            	  
				}
			}

			//Check if the value in cookie is available or not.
			if(cookie.getValue() == null || cookie.getValue() =="") {
				//Set an auto-generated number as value if not present
				String uniqueID = UUID.randomUUID().toString();
				Cookie ck1=new Cookie("guestTokenID",uniqueID);
				ck1.setPath("/");
				response.addCookie(ck1);
			}
			else {
				//Value present. Save to DB.
				System.out.println(" Saving filter values to DB");
			}
		}
	}

}
