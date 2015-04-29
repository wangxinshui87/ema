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
	
	
	/**
	 * 
	 * @param user
	 * @return
	 * 增加用户
	 */
	User addUser(User user);
	
	
	/**
	 * 传入user,当用户名和密码都正确的时候,把该用户记录ISLIVE字段置为0
	 * @param user
	 * @return
	 * key:isSuccess/errorMsg
	 * value:删除成功key为isSuccess,value的值为true,失败key为errorMsg,value值存放失败的原因(包括用户不存在,密码错误)
	 */
	Map<String, Object>deleteUser(User user);
}
