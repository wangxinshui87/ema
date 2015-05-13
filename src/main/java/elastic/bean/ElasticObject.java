package elastic.bean;

import java.util.Date;

public class ElasticObject
{
	private String hostname;
	private Date createTime;
	private String type;
	private String index;
	
	
	public String getHostname()
	{
		return hostname;
	}
	public void setHostname(String hostname)
	{
		this.hostname = hostname;
	}
	public Date getCreateTime()
	{
		return createTime;
	}
	public void setCreateTime(Date createTime)
	{
		this.createTime = createTime;
	}
	public String getType()
	{
		return type;
	}
	public void setType(String type)
	{
		this.type = type;
	}
	public String getIndex()
	{
		return index;
	}
	public void setIndex(String index)
	{
		this.index = index;
	}
	
}
