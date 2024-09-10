package org.exam.client;
import java.io.*;


import java.util.Date;
import java.util.List;
import java.util.Scanner;
import org.exam.model.ExamModel;
import org.exam.model.QuestionModel;
import org.exam.model.ScheduleModel;
import org.exam.model.SubjectModel;
import org.exam.service.ExamService;
import org.exam.service.QuestionService;
import org.exam.service.SubjectService;
public class ExamClientApplication {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SubjectService sv=new SubjectService();
		QuestionService qservice=new QuestionService();
		ExamService examSerivce=new ExamService();
		do {
			System.out.println("1:ADD New Subject");
			System.out.println("2:ADD New Question");
			System.out.println("3:ADD Bulk Question");
			System.out.println("4:ADD Exam");
			System.out.println("5:Create Exam Schedule");
			System.out.println("Enter Your Choice");
			Scanner sc=new Scanner(System.in);
			int choice=sc.nextInt();
			switch(choice)
			{
			case 1:
				sc.nextLine();
				System.out.println("Enter subject name");
				String subname=sc.nextLine();
				SubjectModel model=new SubjectModel();
				model.setName(subname);
				int result=sv.addSubject(model);
				if(result==1)
				{
					System.out.println("Subject Added Success............");
				}
				else if(result==-1)
				{
					System.out.println("Subject is Already Present");
				}
				else {
					System.out.println("Some Problem Is There............");
				}
				break;
				
			case 2:
				sc.nextLine();
				System.out.println("Enter Question");
				String question=sc.nextLine();
				System.out.println("Enter Option1");
				String op1=sc.nextLine();
				System.out.println("Enter Option2");
				String op2=sc.nextLine();
				System.out.println("Enter Option3");
				String op3=sc.nextLine();
				System.out.println("Enter Option4");
				String op4=sc.nextLine();
				System.out.println("Enter Option Number as Answer");
				int ans=sc.nextInt();
				sc.nextLine();
				System.out.println("Enter Subject Name");
				String subtname=sc.nextLine();
			
				QuestionModel qmodel=new QuestionModel(question,op1,op2,op3,op4,ans);
			
				boolean b=qservice.isAddQuestion(qmodel,subtname);
				
				if(b)
				{
					
					System.out.println("Question Added Successfully...........");
				}
				else
				{
					
					System.out.println("Some Problem Is There.................");
				}
				break;
				
			case  3:
				sc.nextLine();
				System.out.println("Enter Subject name for store bulk question");
				String subsname=sc.nextLine();
				qservice.uploadBulkQuestion(subsname);
				System.out.println("sucessful");
				//it uploads the question in file which we give as parameter in it like core java subject question
				break;
				
			case 4:
				sc.nextLine();
				System.out.println("Enter exam name total marks and passing marks");
				String examname=sc.nextLine();
				int totalMarks=sc.nextInt();
				int passMarks=sc.nextInt();
				ExamModel exammodel=new ExamModel(examname,totalMarks,passMarks);
				int results =examSerivce.isAddExam(exammodel);
              if(results==1)
              {
            	  System.out.println("Exam Added Successfuly................");
              }
              else if(results==-1)
              {
            	  System.out.println("Exam Already Present..................");
              }
              else {
            	  System.out.println("some problem is there........");
              }
				break;
				
			case 5:
				sc.nextLine();
				List<ExamModel> list=examSerivce.getAllExams();
				System.out.println("HEY USER you have list of exams and select one for schedule");
				System.out.println("===========================================================");
				for(ExamModel m:list)
				{
					System.out.println(m.getId()+"\t"+m.getName()+"\t"+m.getTotalMarks()+"\t"+m.getPassingMarks());
				}
				System.out.println("===========================================================");
				System.out.println("Enter exam name for schedule");
				examname=sc.nextLine();
				 ExamModel emodel=examSerivce.getExamIdByName(examname);
	                if(emodel!=null)
	                {
	                	System.out.println("Enter exam date Starttime and endtime");
	                	String dateformat=sc.nextLine();
	                    Date d1=new Date(dateformat);
	                    String startTime=sc.nextLine();
	                    String endTime=sc.nextLine();
	                    
	                    ScheduleModel smodel=new  ScheduleModel();
	                    smodel.setExamdate(d1);
	                    smodel.setStartTime(startTime);
	                    smodel.setEndTime(endTime);
	                    smodel.setExamid(emodel.getId());
	                    emodel.setScheduleModel(smodel);
	                    System.out.println("Enter subject name for exam");
	                    String subjename=sc.nextLine();
	                    b= examSerivce.isSetSchedule(emodel,subjename);
	                    if(b)
	                    {
	                    	System.out.println("Exam Schedule Successfully...........");
	                    }
	                    else {
	                    	System.out.println("Exam Not Scheduled........");
	                    }
	                }
				break;
				
				default:
					System.out.println("Wrong Choice");
			}
		}while(true);
	}

}
