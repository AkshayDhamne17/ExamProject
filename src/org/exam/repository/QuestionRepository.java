package org.exam.repository;

import org.exam.model.QuestionModel;

public class QuestionRepository extends DBConfig {
	private int questionId;

	private int getQuestionId() {
		try {
			stmt = conn.prepareStatement("select max(qid) from question");
			rs = stmt.executeQuery();
			if (rs.next()) {
				questionId = rs.getInt(1);
			}
			++questionId;
//it does not matter if we give ++questionid or not because in the table question id is auto incremented
		} catch (Exception ex) {
			System.out.println("Error is" + ex);
			return 0;
		}
		return questionId;

	}

	public int getSubjectIdByName(String subtname) {
		try {

			stmt = conn.prepareStatement("select sid from subject where subjectname=?");
			stmt.setString(1, subtname);
			rs = stmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			} else {
				return -1;
			}
		} catch (Exception ex) {
			System.out.println("Error is " + ex);
			return 0;
		}

	}

	public boolean isAddQuestion(QuestionModel qmodel, String subtname) {
		try {
			int qid = this.getQuestionId();
			
			if (qid != 0) {
				stmt = conn.prepareStatement("insert into question values(?,?,?,?,?,?,?)");
				stmt.setInt(1, qid);
				stmt.setString(2, qmodel.getName());
				stmt.setString(3, qmodel.getOp1());
				stmt.setString(4, qmodel.getOp2());
				stmt.setString(5, qmodel.getOp3());
				stmt.setString(6, qmodel.getOp4());
				stmt.setInt(7, qmodel.getAnswer());

				int value = stmt.executeUpdate();

				if (value > 0) {
					// System.out.println("Question Added Successfully...........");
					int sid = this.getSubjectIdByName(subtname);
					
					if (sid != -1) {
						
						stmt = conn.prepareStatement("insert into subjectquestionjoin values(?,?)");
						stmt.setInt(1, qid);
						stmt.setInt(2, sid);
						return stmt.executeUpdate()>0?true:false;
					}
		else if (sid == -1) {
				System.out.println("Subject not found");
				return false;
					} 
		else {
		     System.out.println("may be here error");
					return false;
					}

				} else {
					// System.out.println("Some Problem Is There..................");
					System.out.println("error here2");
					return false;
				}
			} else {
				System.out.println("error here3");
				return false;
			}
			// return true;
		} catch (Exception ex) {
			
			ex.printStackTrace();
			
			return false;
		}
	}

	public boolean uploadBulkQuestion(String question[], String subname) {
		try {
			int qid = this.getQuestionId();
			if (qid != 0) {

				stmt = conn.prepareStatement("insert into question values(?,?,?,?,?,?,?)");
				stmt.setInt(1, qid);
				stmt.setString(2, question[0]);
				stmt.setString(3, question[1]);
				stmt.setString(4, question[2]);
				stmt.setString(5, question[3]);
				stmt.setString(6, question[4]);
				stmt.setInt(7, Integer.parseInt(question[5].trim()));
				int value = stmt.executeUpdate();
				if (value > 0) {
					// System.out.println("Question Added Successfully...........");
					int sid = this.getSubjectIdByName(subname);
					if (sid != -1) {

						stmt = conn.prepareStatement("insert into subjectquestionjoin values(?,?)");
						stmt.setInt(1, qid);
						stmt.setInt(2, sid);
						
						return stmt.executeUpdate() > 0 ? true : false;

					} else if (sid == -1) {
						System.out.println("Subject not found");
						return false;
					} else {
						System.out.println("error here");
						return false;
					}
				} else {
					System.out.println("error not found");
					return false;
				}
			} else {
				System.out.println("no error");
				return false;
			}
		} catch (Exception ex) {
			return false;
		}

	}
}
