package com.hzmc.ema;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.elasticsearch.ElasticsearchException;

import com.hzmc.dao.User;
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
	
	/*增加用户*/
	public void addUser()
	{
		User user = new User();
		user.setUsername("ls");
		user.setPassword("123456");
		user = userService.addUser(user);
		System.out.println(user.getUsername() + ":" + user.getPassword() + ":" + user.getId() + ":" + user.getIslive());
		
	}
	/*构造函数*/
	public NormalLogin()
	{
		// TODO Auto-generated constructor stub
	}
	
	/*登陆用户,判断登陆是否合法*/
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
			return false;
		}
		
		return true;
	}
	
	public Boolean deleteUser(String username, String password)
	{
		/*只有用户名密码都正确的时候才能删除用户,删除用户就是把用户的ISLIVE 字段设置为0*/
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		Map<String, Object>result;
		result = userService.deleteUser(user);
		if (result.get("isSuccess").equals(true))
		{
			System.out.println("删除成功");
		}
		else
		{
			System.out.println("删除失败" + result.get("errorMsg").toString());
		}
		return true;
	}

}