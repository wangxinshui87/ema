package com.hzmc.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.DefaultEditorKit.InsertBreakAction;

import com.hzmc.dataoperate.DeleteSqlExcutor;
import com.hzmc.dataoperate.ExcuteSql;
import com.hzmc.dataoperate.InsertSqlExcutor;
import com.hzmc.dataoperate.SelectSqlExcutor;
import com.hzmc.dataoperate.UpdateSqlExcutor;


public class UserDaoImpl implements UserDao
{
	private static int i = 0;

	public User addUser(User user) 
	{
		// TODO Auto-generated method stub
		String sqltext = new String();
		/*设置标示位和ID*/
		user.setIslive(1);
		user.setId(i);
		i++;
		sqltext = "insert into persons (id,islive,username, password)" + "values (" + user.getId() + "," + user.getIslive() + ",'" +  user.getUsername() + "','" + user.getPassword() + "')";
		System.out.println(sqltext);
		ExcuteSql sql = new InsertSqlExcutor();	
		sql.templateMethod(sqltext);
		return user;
	//	return Integer.parseInt(sql.templateMethod(sqltext).toString());
	}

	public int deleteUser(User user)
	{
		// TODO Auto-generated method stub
		String sqltext = new String();
		sqltext = "delete from persons where isLive = " + user.getIslive();
		ExcuteSql sql = new DeleteSqlExcutor();
		return Integer.parseInt(sql.templateMethod(sqltext).toString());
	}

	public int updateUser(User user)
	{
		// TODO Auto-generated method stub
		String sqltext = new String();
		ExcuteSql sql = new UpdateSqlExcutor();
		sqltext = "update persons where username = " + user.getUsername();
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
