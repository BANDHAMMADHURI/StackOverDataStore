package com.full.datastore;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;

public class QuestionsServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
			resp.sendRedirect("QuestionProcess.jsp");
	}
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");
		String question=req.getParameter("question");
		DatastoreService data=DatastoreServiceFactory.getDatastoreService();
		Entity ent=new Entity("Questions");
		ent.setProperty("question", question);
		data.put(ent);
		out.println("successfully added");
		out.println("<a href='Main.html'>Home Page</a>");
	}
}