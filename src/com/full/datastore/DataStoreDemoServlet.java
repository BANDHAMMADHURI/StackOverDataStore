package com.full.datastore;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
//import com.google.apphosting.client.datastoreservice.proto.DatastoreService;

@SuppressWarnings("serial")
public class DataStoreDemoServlet extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		PrintWriter out=resp.getWriter();
		resp.setContentType("text/html");
		
		String name=req.getParameter("name");
		String password=req.getParameter("password");
		String about=req.getParameter("about");
		
		DatastoreService datastore=DatastoreServiceFactory.getDatastoreService();
		Entity entity=new Entity("userDetails");
		
		entity.setProperty("name", name);
		entity.setProperty("password", password);
		entity.setProperty("about",about);
		datastore.put(entity);
		//resp.sendRedirect("/detailsServlet");
		
		out.println("registerd successfully");
		out.println("login "+"<a href='login.html'>Here</a>");
		
	}
}
