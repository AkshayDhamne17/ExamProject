package org.exam.repository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.exam.model.ExamModel;
import org.exam.model.ScheduleModel;
public class ExamRepository extends DBConfig{
 List<ExamModel> listExams=new ArrayList<ExamModel>();
 QuestionRepository qrepo=new QuestionRepository();
	public boolean isExamPresent(String examname)
	{
		try {
			stmt=conn.prepareStatement("select *from exam where examname=?");
			stmt.setString(1, examname);
			rs=stmt.executeQuery();
			return rs.next();
		}
		catch(Exception ex)
		{
			return false;
		}
	}
	
	public boolean isAddExam(ExamModel model)
	{
	 try {
		 stmt=conn.prepareStatement("insert into exam values('0',?,?,?)");
		 stmt.setString(1,model.getName());
		 stmt.setInt(2,model.getTotalMarks());
		 stmt.setInt(3,model.getPassingMarks());
		 int value=stmt.executeUpdate();
		 if(value>0)
		 {
	      return true;
		 }
 else {
	   return false;
	  }
	 }
	 catch(Exception ex)
	 {
		 System.out.println("Error is"+ex);
		 return false;
	 }
	}
	
	public List<ExamModel>getAllExams()
	{
		try {
			stmt=conn.prepareStatement("select * from exam");
			rs=stmt.executeQuery();
			while(rs.next())
			{
				ExamModel model=new ExamModel();
				model.setId(rs.getInt(1));
				model.setName(rs.getString(2));
				model.setTotalMarks(rs.getInt(3));
				model.setPassingMarks(rs.getInt(4));
				listExams.add(model);
			}
			return listExams.size()>0?listExams:null;
		}
		catch(Exception ex)
		{
			System.out.println("Error is"+ex);
			return null;
		}
	}
	
	public ExamModel getExamIdByName(String name)
	{
		try {
			stmt=conn.prepareStatement("select * from exam where examname='"+name+"'");
			rs=stmt.executeQuery();
			ExamModel model=null;
			if(rs.next())
			{
				model=new ExamModel();
				model.setId(rs.getInt(1));
				model.setName(rs.getString(2));
				model.setTotalMarks(rs.getInt(3));
				model.setPassingMarks(rs.getInt(4));
			}
			return model!=null?model:null;
		
		}
		catch(Exception ex)
		{
			System.out.println("Error is"+ex);
			return null;
		}
	}
	
	int getScheduleId()
	{ int count=0;
		try {
			stmt=conn.prepareStatement("select max(schid) from schedule");
			rs=stmt.executeQuery();
			if(rs.next())
			{
				count=rs.getInt(1);
			}
			++count;
			return count;
		}
		catch(Exception ex)
		{
			System.out.println("error is"+ex);
			return 0;
		}
	}
	public boolean isSetSchedule(ExamModel model,String subjename)
	{
		try {
			int schid=this.getScheduleId();
			if(schid!=0)
			{
				int subid=qrepo.getSubjectIdByName(subjename);
				 ScheduleModel smodel=model.getScheduleModel();
				 String examDate=smodel.getExamdate().toLocaleString();
				 String d[]=examDate.split(",");
				 String []dateSplit=d[0].split("-");
				 
				 int months[]=new int[]{0,1,2,3,4,5,6,7,8,9,10,11};
				 int m=0;
				 switch(dateSplit[1])
				 {
				 case "Jan":
					 m=0;
					 break;
					 
				 case "Feb":
					 m=1;
					 break;
					 
				 case "Mar":
					 m=2;
					 break;
					 
				 case "Apr":
					 m=3;
					 break;
					 
				 case "May":
					 m=4;
					 break;
					 
				 case "June":
					 m=5;
					 break;
					 
				 case "July":
					 m=6;
					 break;
					 
				 case "Aug":
					 m=7;
					 break;
					 
				 case "Sep":
					 m=8;
					 break;
					 
				 case "Oct":
					 m=9;
					 break;
					 
				 case "Nov":
					 m=10;
					 break;
					 
				 case "Dec":
					 m=11;
					 break;
				 }
				 
				 String newyear=dateSplit[2].substring(2,dateSplit[2].length());
				 //System.out.println(newyear);
				 Date Sqldate=new Date((Integer.parseInt(newyear)+100),m,Integer.parseInt(dateSplit[0]));
				 //System.out.println(Sqldate);
				 //HERE Date class comes from sql package not from util
				 //so it contain different constructor with 3 int value
				 //so we cannot give Date as in string format
				 
				stmt=conn.prepareStatement("insert into schedule values(?,?,?,?,?,?)");
				stmt.setInt(1,schid);
				stmt.setInt(2,model.getId());
			    stmt.setDate(3, Sqldate);
			    stmt.setString(4,smodel.getStartTime());
			    stmt.setString(5,smodel.getEndTime());
			    stmt.setInt(6,subid);
			    
			    int value=stmt.executeUpdate();
			    return value>0?true:false;
			}
			else {
				System.out.println("some problem is there");
				 return true;
			}
		}
		catch(Exception ex)
		{
			System.out.println("error is"+ex);
			return false;
		}
	}
	
}
