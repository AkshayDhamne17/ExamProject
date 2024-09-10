package org.exam.service;

import java.util.*;


import org.exam.model.SubjectModel;
import org.exam.repository.SubjectRepository;

public class SubjectService {
	SubjectRepository subrepo=new SubjectRepository();
	
	public int addSubject(SubjectModel model) 
	{
		 if( subrepo.isSubjectPresent(model.getName()))
		 {
			 return -1;
		 }
		 else
	     {
			 return subrepo.isAddSubject(model)?1:0;
	      }
	}
	
	public List<String> getAllSubjects()
	{
		return this.subrepo.getAllSubjects();
	}
}

