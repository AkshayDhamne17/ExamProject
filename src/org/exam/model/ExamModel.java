package org.exam.model;

public class ExamModel {
      
	 private int examid;
	 private String examname;
	 private int totalMarks;
	 private int passingMarks;
	 
	 ScheduleModel ScheduleModel;
	 
	 public ScheduleModel getScheduleModel() {
		return ScheduleModel;
	}

	public void setScheduleModel(ScheduleModel scheduleModel) {
		ScheduleModel = scheduleModel;
	}

	public ExamModel() 
	 {
		 
	 }
	 
	 public ExamModel(String examname,int totalMarks,int passingmarks)
	 {
		 this.examname=examname;
		 this.passingMarks=passingmarks;
		 this.totalMarks= totalMarks;
		 
	 }
	 
	 
	public int getId() {
		return examid;
	}
	public void setId(int examid) {
		this.examid = examid;
	}
	public String getName() {
		return examname;
	}
	public void setName(String examname) {
		this.examname = examname;
	}
	public int getTotalMarks() {
		return totalMarks;
	}
	public void setTotalMarks(int totalMarks) {
		this.totalMarks = totalMarks;
	}
	public int getPassingMarks() {
		return passingMarks;
	}
	public void setPassingMarks(int passingMarks) {
		this.passingMarks = passingMarks;
	}
	 
}
