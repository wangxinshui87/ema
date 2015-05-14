package elastic.manage.percolator.impl;

import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.action.percolate.PercolateResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.ImmutableSettings.Builder;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;

import elastic.bean.CpuObject;
import elastic.bean.ElasticObject;
import elastic.bean.Threshold;
import elastic.manage.percolator.PercolatorService;

public class PercolatorServiceImpl implements PercolatorService
{
	private String clusterName = "alarm"; // 索引名字
	private String ipAddress = "172.16.8.152";
	private Client client; // 节点

	public PercolatorServiceImpl()
	{
		/* 设置以及建立节点 */
		Builder settings = ImmutableSettings.settingsBuilder().put(
				"cluster.name", this.clusterName);
		this.client = new TransportClient(settings)
				.addTransportAddress(new InetSocketTransportAddress(
						this.ipAddress, 9300));
	}
	//Build a document to check against the percolator
	public List<String> doPercolatorService(ElasticObject elasticObject) throws Exception
	{
		CpuObject cpuObject = (CpuObject)elasticObject;
		/*Build a document to check against the percolator*/
		XContentBuilder doBuilder = XContentFactory.jsonBuilder().startObject();
		doBuilder.field("doc").startObject();
		doBuilder.field("Used_PCT",cpuObject.getUsedPct());
		doBuilder.endObject();
	//	System.out.println("IN TEST" + cpuObject.getUsed_PCT() + cpuObject.getIndex());
		/*Percolate*/
		PercolateResponse response = client.preparePercolate()
                .setIndices(/*cpuObject.getIndex()*/"athreshold")
                .setDocumentType("my-type")
                .setSource(doBuilder).execute().actionGet();
		
		/*handle hit*/
		List<String> hitList = new ArrayList<String>();
		
		for(PercolateResponse.Match match : response) 
		{
			Threshold tempThreshold = new Threshold();
			//System.out.println(match.getId() + "  " + match.getIndex());
			tempThreshold.setId(match.getId());
			tempThreshold.setIndex(match.getIndex());
			hitList.add(tempThreshold.getId().toString());
		//	System.out.println("has match");
		}
		return hitList;
	}
	
	public Client getClient()
	{
		return client;
	}
	public void setClient(Client client)
	{
		this.client = client;
	}
}
