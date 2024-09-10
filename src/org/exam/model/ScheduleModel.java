package org.exam.model;

import java.util.Date;

public class ScheduleModel {
	
	public ScheduleModel()
	{
		
	}
	
	public ScheduleModel(Date examdate,String startTime,String endTime,int examid)
	{
		this.examdate=examdate;
		this.startTime=startTime;
		this.endTime=endTime;
		this.examid=examid;
	}
     private int schid;
     private int examid;
     private Date examdate;
     private String startTime,endTime;
	public int getSchid() {
		return schid;
	}
	public void setSchid(int schid) {
		this.schid = schid;
	}
	public int getExamid() {
		return examid;
	}
	public void setExamid(int examid) {
		this.examid = examid;
	}
	public Date getExamdate() {
		return examdate;
	}
	public void setExamdate(Date examdate) {
		this.examdate = examdate;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
}
