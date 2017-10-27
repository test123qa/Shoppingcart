package com.sahan.zaizi.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

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
	

}
