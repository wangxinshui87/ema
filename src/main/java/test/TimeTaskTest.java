package test;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.ImmutableSettings.Builder;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;

import elastic.bean.CpuObject;

public class TimeTaskTest
{
	private String clusterName = "alarm"; // 索引名字
	private String ipAddress = "172.16.8.152";
	private Client client; // 节点
	public  long from_time = 0;
	public  long to_time = 0;
	public TimeTaskTest()
	{
		/* 设置以及建立节点 */
		Builder settings = ImmutableSettings.settingsBuilder().put(
				"cluster.name", this.clusterName);
		this.client = new TransportClient(settings)
				.addTransportAddress(new InetSocketTransportAddress(
						this.ipAddress, 9300));
	}
	

	public static void main(String[] args)
	{
		TimeTaskTest timeTaskTest =  new TimeTaskTest();
		
		Date date = new Date();
		/*当前时间*/
		timeTaskTest.to_time = date.getTime();
		/*到前一分钟的时间*/
		timeTaskTest.from_time = timeTaskTest.to_time - 5*6000;
	
//		.setQuery(QueryBuilders.boolQuery().must(QueryBuilders.termQuery("hostname","hca1")))
//		.setQuery(QueryBuilders.rangeQuery("@timestamp").from(timeTaskTest.from_time).to(timeTaskTest.to_time))
		
		SearchResponse actionGet = timeTaskTest.client
				.prepareSearch("cpu_all_index-2015.05")
				.setTypes("CPU_ALL")
				.setQuery(
						QueryBuilders
								.boolQuery()
								.must(QueryBuilders.termQuery("hostname",
										"hca1"))
								.must(QueryBuilders.rangeQuery("@timestamp")
										.from(timeTaskTest.from_time)
										.to(timeTaskTest.to_time)))
				//.setQuery(QueryBuilders.rangeQuery("@timestamp").from(timeTaskTest.from_time).to(timeTaskTest.to_time))
				.execute()
				.actionGet();

		SearchHits hits = actionGet.getHits();
		// List<Map<String, Object>> matchRsult = new LinkedList<Map<String,
		// Object>>();
		List<CpuObject> matchRsult = new ArrayList<CpuObject>(); // 存放match消息

		for (SearchHit hit : hits.getHits())
		{
			CpuObject tempCpuObject = new CpuObject();
			String hostname = (String) hit.getSource().get("hostname");
			double wait_PCT = (Double) hit.getSource().get("Wait_PCT");
			double sys_PCT = (Double) hit.getSource().get("Sys_PCT");
			double user_PCT = (Double) hit.getSource().get("User_PCT");
			double used_PCT = wait_PCT + sys_PCT + user_PCT;
			System.out.println("wait_pct" + wait_PCT + "hostname" + hostname);
		}
		System.out.println("end");
//		Timer timer = new Timer();
//		timer.schedule(new Task(), 1, 2*1000);
//		while(true)
//		{
//		}
		
	}
}
