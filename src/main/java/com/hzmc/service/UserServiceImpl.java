package com.hzmc.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hzmc.dao.User;
import com.hzmc.dao.UserDao;
import com.hzmc.dao.UserDaoImpl;

public class UserServiceImpl implements UserService
{

	private UserDao userDao =  new UserDaoImpl();
	public Map<String, Object> login(User user)
	{
		Map<String, Object> result = new HashMap<String, Object>();
		List<User> userList = new ArrayList<User>();
	    userList = userDao.selectUsers(user);
	    result.put("isSuccess", false);
	    if (userList.isEmpty())
	    {
	    	result.put("errorMsg", "there is no this user");
	    }
	    else if (!user.getPassword().equalsIgnoreCase(userList.get(0).getPassword()))
	    {
			result.put("errorMsg", "password is error");
		}
	    else if (userList.get(0).getIslive() != 1)
	    {
	    	result.put("errorMsg", "use has alreadly unlive");
	    }
	    else
	    {
	    	result.put("isSuccess", true);
		}

	    return result;
	}

	public User addUser(User user)
	{
		user = userDao.addUser(user);
		return user;
	}

	public Map<String, Object> deleteUser(User user)
	{
		Map<String, Object> result = new HashMap<String, Object>();
		List<User> userList = new ArrayList<User>();
	    userList = userDao.selectUsers(user);
	    result.put("isSuccess", false);
	    if (userList.isEmpty())
	    {
	    	result.put("errorMsg", "there is no this user");
	    }
	    else if (!user.getPassword().equalsIgnoreCase(userList.get(0).getPassword()))
	    {
			result.put("errorMsg", "password is error");
		}
	    else if (userList.get(0).getIslive() != 1)
	    {
	    	result.put("errorMsg", "use has alreadly unlive");
	    }
	    else
	    {
	    	/*此时存在该用户以及该用户还是活跃的,要更新把islive字段置为0*/
	    	userDao.deleteUser(user);
	    	result.put("isSuccess", true);
		}

	    return result;
	}
	
}
