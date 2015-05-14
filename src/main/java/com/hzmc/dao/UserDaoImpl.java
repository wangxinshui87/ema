package com.hzmc.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.hzmc.dataoperate.ExcuteSql;
import com.hzmc.dataoperate.InsertSqlExcutor;
import com.hzmc.dataoperate.SelectSqlExcutor;
import com.hzmc.dataoperate.UpdateSqlExcutor;


public class UserDaoImpl implements UserDao
{
	public User addUser(User user) 
	{
		try
		{
			String sqltext = new String();
			/*设置标示位*/
			user.setIslive(1);
			/*设置userID*/
			sqltext = "select count(*) from persons";
			ExcuteSql sqlSelect = new SelectSqlExcutor();
			ResultSet rs = (ResultSet) sqlSelect.templateMethod(sqltext);
			rs.next();
			user.setId(rs.getInt(1));
		//	System.out.println("after select count" + sqltext);
			sqltext = "insert into persons (id,islive,username, password)" + "values (" + user.getId() + "," + user.getIslive() + ",'" +  user.getUsername() + "','" + user.getPassword() + "')";
			
			ExcuteSql sql = new InsertSqlExcutor();	
			sql.templateMethod(sqltext);
		}
		catch(Exception e)
		{
			System.out.println("user: adduser" + e.getMessage());
		}
		return user;
	}

	public int deleteUser(User user)
	{
		return updateUser(user);
//		// TODO Auto-generated method stub
//		String sqltext = new String();
//		sqltext = "delete from persons where isLive = " + user.getIslive();
//		ExcuteSql sql = new DeleteSqlExcutor();
//		return Integer.parseInt(sql.templateMethod(sqltext).toString());
	}

	public int updateUser(User user)
	{
		// TODO Auto-generated method stub
		String sqltext = new String();
		ExcuteSql sql = new UpdateSqlExcutor();
		sqltext = "update persons set islive = 0 where username = '" + user.getUsername() +"'";
	//	System.out.println("updateUser : " + sqltext);
		return Integer.parseInt(sql.templateMethod(sqltext).toString());
	}

	public List<User> selectUsers(User user)
	{
		List<User> userList = new ArrayList<User>();
		try 
		{
			String sqltext = "select * from persons where username = '" + user.getUsername() + "'";
			ExcuteSql sql = new SelectSqlExcutor();
			ResultSet rs = (ResultSet) sql.templateMethod(sqltext);
			while(rs.next())
			{
				User tempUser = new User();
				tempUser.setUsername(rs.getString("username"));
				tempUser.setPassword(rs.getString("password"));
				tempUser.setId(rs.getInt("id"));
				tempUser.setIslive(rs.getInt("isLive"));
				userList.add(tempUser);
			}
		} 
		catch (Exception e) 
		{
			// TODO: handle exception
			System.out.println("selectUsers failed"+e.getMessage());
		}
		
		return userList;
	}
	
}
