package com.full.datastore;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class SeachServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 83L;
	/**
	 * 
	 */
	public static final long serialVersionUID2 =  1L;

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		String question = req.getParameter("question");
	//	System.out.println(question);
		int count=1;
		DatastoreService search = DatastoreServiceFactory.getDatastoreService();
		Query queryQue = new Query("Questions");
		PreparedQuery pqQue = search.prepare(queryQue);

		String questionId = null;
		for (Entity e1 : pqQue.asIterable()) {
			
			String queid = e1.getKey().toString();
			String Question = (String) e1.getProperty("question");
			

		//	try {

				if (Question.contains(question)) {
					out.println("<br>Question  :  " + Question);
					out.println( "<br>"+count+" Question_Id is :  " + queid);
					
					questionId = queid;
					count++;
					
				}
			/*} catch (NullPointerException e) {
				Key key = e1.getKey();
				search.delete(key);

			}*/
				Query queryAns = new Query("Answers");
				PreparedQuery pqAns = search.prepare(queryAns);
				int number = 1;
				for (Entity e : pqAns.asIterable()) {
					
					String qid = (String) e.getProperty("q_id");
					//try {
					if (qid.equalsIgnoreCase(questionId)) {
						String Answer = (String) e.getProperty("answer");
						out.println("<br>Answer "+number +" : " + Answer);
						number++;
					}
					
					/*} catch (NullPointerException e1) {
						Key key = e.getKey();
						search.delete(key);

					}*/
				}
				questionId=null;

		}

		/*Query queryAns = new Query("Answers");
		PreparedQuery pqAns = search.prepare(queryAns);
		int number = 1;
		for (Entity e : pqAns.asIterable()) {
			
			String qid = (String) e.getProperty("q_id");
			//try {
			if (qid.equalsIgnoreCase(questionId)) {
				String Answer = (String) e.getProperty("answer");
				out.println("<br>"+number +"Answer  : " + Answer);
				number++;
			}
			} catch (NullPointerException e1) {
				Key key = e.getKey();
				search.delete(key);

			}
		}*/
		out.println("<br><br>If you want to add an ANSWER , please make a note of <i>Question_Id</i>");
		out.println("<br><a href='answer.html'>Add an Answer</a>");
		out.println("<br><a href='questions.html'>Add a Question</a>");
		out.println("<br><a href='Main.html'>Home Page</a>");
	}
}
