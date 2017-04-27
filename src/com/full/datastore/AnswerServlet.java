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
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

public class AnswerServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
		try {
			
			JSONObject obj = new JSONObject(req.getParameter("param"));
        	String q_id = obj.getString("Q_Id");
			String answer = obj.getString("Answer");
			DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
			Query queryQue = new Query("Questions");
			PreparedQuery qAns = datastore.prepare(queryQue);
			for (Entity e : qAns.asIterable()) {

				String qid = e.getKey().toString();
				if (qid.equals(q_id)) {

					Entity entityAns = new Entity("Answers");
					entityAns.setProperty("answer", answer);
					entityAns.setProperty("q_id", q_id);

					datastore.put(entityAns);
					out.println("successfully added");
				}
			}
		} catch (JSONException e1) {
			e1.printStackTrace();
		}
		out.println("<br><a href='Main.html'>Home Page</a><br>");
	}
}
