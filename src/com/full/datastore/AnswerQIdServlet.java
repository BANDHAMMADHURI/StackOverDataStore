package com.full.datastore;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

public class AnswerQIdServlet extends HttpServlet{
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		PrintWriter out = response.getWriter();
		  response.setContentType("text/html"); 
		  
		try {
			JSONObject obj = new JSONObject(request.getParameter("param"));
			//out.println(JSON.stringify(obj));
			out.println("hi");
			String que_Id=obj.getString("Q_Id");
			out.println(que_Id);
			
			RequestDispatcher rd = request.getRequestDispatcher("AnswerProcess.jsp");
			rd.forward(request, response);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
