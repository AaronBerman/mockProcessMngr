package main.java;
import java.lang.String;

public class priorityItem {
	
	public priorityItem() {
		processName = "generic";
		priority = 3;
	}
	
	public priorityItem(String name) {
		processName = name;
		priority = 3;
	}
	
	public priorityItem(String name, int x) {
		processName = name;
		priority = x;
	}
	
	private String processName;
	private int priority;
	
	public String getProcessName() {
		return processName;
	}
	
    public void setProcessName(String newName) {
    	processName = newName;
    }
    
    public int getPriority() {
    	return priority;
    }
    
    public void setPriority(int newPriority) {
    	priority = newPriority;
    }
    
}
