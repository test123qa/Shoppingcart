package com.sahan.zaizi.service;

import com.sahan.zaizi.domain.User;

public interface UserServiceAuth {

	void save(User user);

    User findByUsername(String username);
}
