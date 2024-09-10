package org.exam.service;
import java.io.*;
import java.util.*;

import org.exam.helper.PathHelper;
import org.exam.model.QuestionModel;
import org.exam.repository.QuestionRepository;

public class QuestionService {
     
	QuestionRepository qRepo= new QuestionRepository();
	SubjectService ss=new SubjectService();
	public boolean isAddQuestion(QuestionModel qmodel,String subtname)
	{
		
		return qRepo.isAddQuestion(qmodel,subtname);
		
	}
	
	public boolean createFiles()
	{
		try {
			String path="D:\\Java Programs\\ExamProject\\questionbank";
			File f=new File(path);
			if(!f.exists())
			{
				f.mkdir();
			}
			List<String> sublist=ss.getAllSubjects();
			  if(sublist!=null)
			  {
				  for(String subname:sublist)
				  {
					  	f=new File(path+"\\"+subname+".csv");
					  	if(!f.exists())
					  	{
					  	  	f.createNewFile();	
					  	}
				  }
			  }
			  else {
				  System.out.println("Subject not found");
			  }
		}
		catch(Exception ex)
		{
			System.out.println("Error is"+ex);
		}
		return true;
	}
	
	public boolean uploadBulkQuestion(String subname)
	{
		boolean b=this.createFiles();
		if(b)
		{
			File f=new File("D:\\Java Programs\\ExamProject\\questionbank");
			File []fileList=f.listFiles();
			boolean flag=false;
			for(File file:fileList)
			{
				if(file.isFile())
				{
					int index=file.toString().indexOf(subname);
				
			    if(index!=-1)
				   {
					flag=true;
					break;
				   }
					
				}
				
			}
			if(flag)
			{
				//System.out.println("Subject File Found");
				try {
					FileReader fr=new FileReader(PathHelper.filepath+"\\"+subname+".csv");
					BufferedReader br=new BufferedReader(fr);
					String question;
					flag=false;
					while((question=br.readLine())!=null)
					{
						//System.out.println(question);
						String qwithop[]=question.split(",");
						flag=qRepo.uploadBulkQuestion(qwithop,subname);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else {
				System.out.println("Subject file not found");
			}
			return true;
		}
		else {
			return false;
		}
	}
	
	
}
