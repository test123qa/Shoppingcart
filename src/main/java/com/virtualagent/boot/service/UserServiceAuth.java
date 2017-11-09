package com.virtualagent.boot.service;

import com.virtualagent.boot.domain.User;

public interface UserServiceAuth {

	void save(User user);

    User findByUsername(String username);
}
