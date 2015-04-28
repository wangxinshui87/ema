package com.hzmc.dao;

public class User
{
	private String username;
	private String password;
	private int id;
	private int islive; //1为有效账户,0为无效账户
	
	public int getIslive()
	{
		return islive;
	}
	public void setIslive(int islive)
	{
		this.islive = islive;
	}
	public int getId()
	{
		return id;
	}
	public void setId(int id) 
	{
		this.id = id;
	}
	public String getUsername() 
	{
		return username;
	}
	public void setUsername(String username)
	{
		this.username = username;
	}
	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}

}
