package com.hzmc.ema;

public class NormalLogin 
{
	//+容器:存放有效的用户名和账户消息
	//initialize()初始化容器中的成员
	
	public void Initalize()
	{
		//执行相关sql语句从表格里获取islive = 1 的账户的信息
	}
	
	public void Update()
	{
		//每隔一段时间更新一次容器
		//或者把这个函数注册到维护的类中
	}
	
	public NormalLogin()
	{
		// TODO Auto-generated constructor stub
	}
	
	public Boolean IsLegal(String user, String password)
	{
		//进行一些列的比较,先从容器中查找,如果找到在比较密码
		return true;
	}

}
