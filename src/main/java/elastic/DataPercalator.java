package elastic;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import org.codehaus.groovy.tools.groovydoc.Main;
import org.elasticsearch.action.percolate.PercolateResponse;

import elastic.bean.CpuObject;
import elastic.bean.ElasticObject;
import elastic.bean.ElasticQuery;
import elastic.bean.Threshold;
import elastic.manage.alarm.AlarmService;
import elastic.manage.alarm.impl.AlarmServiceImpl;
import elastic.manage.percolator.PercolateCpuObjectImpl;

public class DataPercalator 
{
	public static void main(String[] args)
	{
		Timer timer = new Timer();
		timer.schedule(new Task(), 1, 10*1000);
	}
}
