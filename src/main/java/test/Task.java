package test;

import java.util.TimerTask;

public class Task extends TimerTask
{
	public void run()
	{
		System.out.println("2s定时执行任务一次");
	}
}
