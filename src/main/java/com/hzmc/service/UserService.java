package com.hzmc.service;

import java.util.Map;

import com.hzmc.dao.User;

public interface UserService
{
	Map<String, Object> login(User user);
	User addUser(User user);
}
