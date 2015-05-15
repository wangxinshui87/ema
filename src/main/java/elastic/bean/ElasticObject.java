package elastic.bean;

import java.util.Date;

public class ElasticObject
{
	private String hostname;
	private Date timestamp;
	private String type;
	private String index;
	private String ZZZZ;
	
	
	public Date getTimestamp()
	{
		return timestamp;
	}
	public void setTimestamp(Date timestamp)
	{
		this.timestamp = timestamp;
	}
	public String getZZZZ()
	{
		return ZZZZ;
	}
	public void setZZZZ(String zZZZ)
	{
		ZZZZ = zZZZ;
	}
	public String getHostname()
	{
		return hostname;
	}
	public void setHostname(String hostname)
	{
		this.hostname = hostname;
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
