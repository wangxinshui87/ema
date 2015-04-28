package com.hzmc.dao;

import java.util.List;

public interface UserDao 
{
	User addUser(User user);
	int deleteUser(User user);
	int updateUser(User user);
	List<User> selectUsers(User user);
}
