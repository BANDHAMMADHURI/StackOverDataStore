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

public class DetailsServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query query = new Query("userDetails");
		PreparedQuery p = datastore.prepare(query);
		String username = req.getParameter("name");
		String pass = req.getParameter("password");

		for (Entity en : p.asIterable()) {
		//Entity en=new Entity("userDetails");
			String name = (String) en.getProperty("name");
			//System.out.println(name);
			String password = (String) en.getProperty("password");
			//System.out.println(password);
			String about=(String) en.getProperty("about");
			if (name.equals(username)) {
				if (password.equals(pass)) {
					out.println("name :" + name);
					//out.println("password : " + password);
					out.println("<br>About  :  "+about);
					//out.println("want to add a question ..? click <a href='questions.html'>here</a>");
					out.println("<br><a href='questions.html'>Add a Question</a>");
					out.println("<br><a href='Main.html'>Home Page</a>");
				} 
			}
		}
	}
}