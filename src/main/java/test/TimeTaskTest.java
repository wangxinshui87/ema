package test;

import java.util.Timer;

public class TimeTaskTest
{
	public static void main(String[] args)
	{
		Timer timer = new Timer();
		timer.schedule(new Task(), 1, 2*1000);
		while(true)
		{
		}
	}
}
