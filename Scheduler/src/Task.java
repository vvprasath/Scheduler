
public class Task {
	int taskid;
	String task;
	int duration;
	
	public Task()
	{
		this.task = "";
		this.duration = 0;
		
	}
	
	public Task(int taskid, String task, int duration)
	{
		this.taskid = taskid;
		this.task = task;
		this.duration = duration;
	}
	
	public int gettaskid()
	{
		return taskid;
	}
	
	public String gettask()
	{
		return task;
	}
	
	public int getduration()
	{
		return duration;
	}

}
