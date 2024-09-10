package org.exam.repository;
import java.util.*;
import org.exam.model.SubjectModel;

public class SubjectRepository extends DBConfig{
   List<String> list= new ArrayList<String>();
	public boolean isAddSubject(SubjectModel model)
	{
		try {
			stmt=conn.prepareStatement("insert into subject values('0',?)");
			stmt.setString(1,model.getName());
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
	
	
	public boolean isSubjectPresent(String subname)
	{
		try {
			stmt=conn.prepareStatement("select *from subject where subname=?");
			stmt.setString(1, subname);
			rs=stmt.executeQuery();
			return rs.next()?true:false;
		}
		catch(Exception ex)
		{
			return false;
		}
	}
	
	public List<String> getAllSubjects()
	{
//to avoid classcast exception at run time we use String generics here
		try {
			stmt=conn.prepareStatement("select subjectname from subject");
			rs=stmt.executeQuery();
			while(rs.next())
			{
				list.add(rs.getString("subjectname"));
			}
			return list.size()>0?list:null;
			}
		catch(Exception ex)
		{
			System.out.println("error is"+ex);
			return null;
		}
	
	}
}
