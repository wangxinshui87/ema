package elastic.bean;

import org.elasticsearch.common.text.Text;

public class Threshold
{
	private Text index;
	private Text id;
	public Text getIndex()
	{
		return index;
	}
	public void setIndex(Text index)
	{
		this.index = index;
	}
	public Text getId()
	{
		return id;
	}
	public void setId(Text id)
	{
		this.id = id;
	}
	
}
