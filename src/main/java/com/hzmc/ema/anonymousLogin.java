package com.hzmc.ema;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


public class anonymousLogin 
{
	private String username = "anonymous";
	private String password = "123456";
	private String createTime; //匿名账户登录时间
	private Double maxTime = (double) 100;    //匿名用户存在的最长时间
	public anonymousLogin() 
	{
		createTime = new String();
		//或许当前的时间给createTime
	}
	
	public Double getMaxTime()
	{
		return maxTime;
	}
	/*设置匿名用户最长的登录时间*/
	public void setMaxTime(Double maxTime) 
	{
		this.maxTime = maxTime;
	}

	public boolean Islegal()
	{
		//得到当前的时间
		//和最长时间比较
		//小于最长有效时间,登录返回true
		//否则返回 false
		return true;
	}
}
