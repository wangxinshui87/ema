package elastic;

import java.util.Timer;

public class DataPercalator 
{
	public static void main(String[] args)
	{
		Timer timer = new Timer();
		timer.schedule(new Task(), 1, 10*1000);
	}
}
