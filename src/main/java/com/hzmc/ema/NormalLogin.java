package com.hzmc.ema;

import static org.elasticsearch.node.NodeBuilder.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.node.Node;

import com.hzmc.dao.User;
import com.hzmc.dao.UserDao;
import com.hzmc.dao.UserDaoImpl;
import com.hzmc.service.UserService;
import com.hzmc.service.UserServiceImpl;

public class NormalLogin 
{
	//initialize()初始化容器中的成员
	private String m_username;
	private String m_password;
	private List<User>m_users; //存放用户消息的容器
	private UserService userService = new UserServiceImpl();
	/*初始化从数据库中获取IsLive为1的用户消息*/
	public void Initalize()
	{
		//执行相关sql语句从表格里获取islive = 1 的账户的信息
		User user = new User();
//		m_users = userDao.selectUsers(user);
		for (User user1 : m_users)
		{
			System.out.println(user1.getUsername() + ":" + user1.getPassword());
		}
	}
	
	/*增加用户*/
	public void addUser()
	{
		User user = new User();
		user.setUsername("ls");
		user.setPassword("123456");
		user = userService.addUser(user);
		System.out.println(user.getUsername() + ":" + user.getPassword() + ":" + user.getId() + ":" + user.getIslive());
		
	}
	/*构造函数*/
	public NormalLogin()
	{
		// TODO Auto-generated constructor stub
	}
	
	/*登陆用户,判断登陆是否合法*/
	public Boolean IsLegal(String username, String password)
	{
		// TODO 
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		Map<String, Object> result;
		result = userService.login(user);
		if (result.get("isSuccess").equals(true))
		{
			System.out.println("登陆成功");
		}
		else
		{
			System.out.println("登陆失败  : " + result.get("errorMsg").toString());
			return false;
		}
		
		return true;
	}
	
	public Boolean deleteUser(String username, String password)
	{
		/*只有用户名密码都正确的时候才能删除用户,删除用户就是把用户的ISLIVE 字段设置为0*/
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		Map<String, Object>result;
		result = userService.deleteUser(user);
		if (result.get("isSuccess").equals(true))
		{
			System.out.println("删除成功");
		}
		else
		{
			System.out.println("删除失败" + result.get("errorMsg").toString());
		}
		return true;
	}
	
	public static void main(String[] args) throws ElasticsearchException, IOException
	{
	//	NormalLogin login = new NormalLogin();
	//	login.addUser();
	//	login.IsLegal("zs", "123456");
	//	login.deleteUser("zs", "123456");
		Node node = nodeBuilder().clusterName("ema").client(true).node();
		Client client = ((org.elasticsearch.node.Node) node).client();
//		Settings settings = ImmutableSettings.settingsBuilder().put("client.transport.sniff", true).put("ema", "Buzzard").build();
//		Client client = new TransportClient(settings).addTransportAddress(new InetSocketTransportAddress("172.16.10.222", 9300));
		
//		
//		System.out.println(client.toString());
		IndexResponse response = client.prepareIndex("comment_index", "comment_ugc", "comment_123674")
			    .setSource( XContentFactory.jsonBuilder()
			    .startObject()
			      .field("author", "569874")
			      .field("author_name", "riching")
			      .field("mark", 232)
			      .field("body", "北京不错，但是人太多了")
			      .field("createDate", "20130801175520")
			      .field("valid", true)
			    .endObject())
			    .setTTL(8000)
			    .execute().actionGet();

			System.out.println(response.getId());

	}
}
