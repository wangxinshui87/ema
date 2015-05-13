package elastic;

import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.action.percolate.PercolateResponse;

import elastic.bean.CpuObject;
import elastic.bean.ElasticObject;
import elastic.bean.Threshold;
import elastic.manage.alarm.AlarmService;
import elastic.manage.alarm.impl.AlarmServiceImpl;
import elastic.manage.percolator.PercolateCpuObject;

public class DataPercalator
{
	public static void main(String[] args)
	{
		
		AlarmService alarmService = new AlarmServiceImpl();  //用于获取和存储数据
		PercolateCpuObject percolateCpuObject = new PercolateCpuObject(); //用于解析过滤数据
		
		/*构造获取数据的过滤条件*/
		ElasticObject object = new ElasticObject();
		object.setHostname("hca1");
		object.setIndex("cpu_all_index-2015.05");
		object.setType("CPU_ALL");
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
