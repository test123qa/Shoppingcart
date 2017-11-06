package com.sahan.zaizi.service;


public interface SecurityService {

	String findLoggedInUsername();

    void autologin(String username, String password);
}
