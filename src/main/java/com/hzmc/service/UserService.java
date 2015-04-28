package com.hzmc.service;

import java.util.Map;

import com.hzmc.dao.User;

public interface UserService
{
	/**
	 * 
	 * @param user
	 * 传入 一个user,user中需要设置好用户名和密码
	 * @return
	 * 返回一个map<String,Object>来表示登陆成功或者失败,以及失败原因
	 *key:isSuccess/errorMsg
	 *value:登陆成功则key为 isSuccess的value值为true,失败则key为 errorMsg的value值存放失败的原因
	 */
	Map<String, Object> login(User user);
	User addUser(User user);
}
