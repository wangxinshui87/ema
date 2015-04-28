package com.hzmc.ema;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.hzmc.dao.User;
import com.hzmc.dao.UserDao;
import com.hzmc.dao.UserDaoImpl;
import com.hzmc.service.UserService;
import com.hzmc.service.UserServiceImpl;

public class NormalLogin 
{
	//initialize()初始化容器中的成员
	private String m_username;
	private String m_password;
	private List<User>m_users; //存放用户消息的容器
	private UserService userService = new UserServiceImpl();
	/*初始化从数据库中获取IsLive为1的用户消息*/
	public void Initalize()
	{
		//执行相关sql语句从表格里获取islive = 1 的账户的信息
		User user = new User();
//		m_users = userDao.selectUsers(user);
		for (User user1 : m_users)
		{
			System.out.println(user1.getUsername() + ":" + user1.getPassword());
		}
	}
	public void addUser()
	{
		User user = new User();
		user.setUsername("zs");
		user.setPassword("123456");
		user = userService.addUser(user);
		System.out.println(user.getUsername() + ":" + user.getPassword() + ":" + user.getId() + ":" + user.getIslive());
		
	}
	public void Update()
	{
		//每隔一段时间更新一次容器
		//或者把这个函数注册到维护的类中
	}
	/*构造函数*/
	public NormalLogin()
	{
		// TODO Auto-generated constructor stub
	}
	/*判断登陆是否合法*/
	public Boolean IsLegal(String username, String password)
	{
		// TODO 
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		Map<String, Object> result;
		result = userService.login(user);
		if (result.get("isSuccess").equals(true))
		{
			System.out.println("登陆成功");
		}
		else
		{
			System.out.println("登陆失败  : " + result.get("errorMsg").toString());
		}
		
		return true;
	}
	
	public static void main(String[] args)
	{
		NormalLogin login = new NormalLogin();
		//login.addUser();
		login.IsLegal("zs", "123456");
	}
}
