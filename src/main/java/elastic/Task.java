package elastic;

import java.util.Date;
import java.util.List;
import java.util.TimerTask;

import elastic.bean.CpuObject;
import elastic.bean.ElasticQuery;
import elastic.manage.alarm.AlarmService;
import elastic.manage.alarm.impl.AlarmServiceImpl;
import elastic.manage.percolator.PercolateCpuObjectImpl;

public class Task extends TimerTask
{
	private ElasticQuery object;
	private boolean isFirstQuery = true;
	private Date date;
	
	/*构造函数设置查询基本条件*/
	public Task()
	{
		date = new Date();
		object = new ElasticQuery();
		object.setHostname("hca1");
		object.setIndex("cpu_all_index-2015.05");
		object.setType("CPU_ALL");
		object.setEndTime(date.getTime());
		object.setStartTime(date.getTime() - 10*6000);
	}
	public void run()
	{
		AlarmService alarmService = new AlarmServiceImpl();  //用于获取和存储数据
		PercolateCpuObjectImpl percolateCpuObject = new PercolateCpuObjectImpl(); //用于解析过滤数据
		
		/*第一次查询不用设置时间*/
		if(!isFirstQuery)
		{
			object.setStartTime(object.getEndTime()); //当前查询开始时间为  为上一次查询的时间
			object.setEndTime(date.getTime());   //查询结束时间为当前时间
		}
		isFirstQuery = false;
		List<CpuObject> cpuObjects;
		try
		{
			cpuObjects = alarmService.getDataFromElatic(object);
			if(null != cpuObjects && !cpuObjects.isEmpty())
			{
				for (CpuObject cpuObject : cpuObjects)
				{
					//System.out.println(cpuObject.getUsed_PCT());		
					cpuObject.setHitList(percolateCpuObject.doPercolateWithCpuObject(cpuObject));
					System.out.println(cpuObject.getHitList());
					if (!cpuObject.getHitList().isEmpty())
					{
							alarmService.saveAlarmData(cpuObject);
					}
				}
			}
		} 
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
