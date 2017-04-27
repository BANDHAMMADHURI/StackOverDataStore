<%@ page
	import="com.google.appengine.api.datastore.DatastoreService,javax.servlet.http.HttpServletRequest"%>
<%@ page
	import="com.google.appengine.api.datastore.DatastoreServiceFactory,com.google.appengine.api.datastore.Entity,com.google.appengine.api.datastore.Key,com.google.appengine.api.datastore.PreparedQuery,com.google.appengine.api.datastore.Query"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>QuestionProcess</title>
</head>
<body id="body">
	<center>
		<h2>Questions</h2>
		<table border="1">
			<tr>
				<th>Question Number</th>
				<th>Question</th>
				<th>Answers</th>
			</tr>

			<%!
			Key qid;
	        String que;%>
			<%
				Query query = new Query("Questions");
				DatastoreService data = DatastoreServiceFactory.getDatastoreService();
				PreparedQuery pq = data.prepare(query);
				int count = 1;
				for (Entity e : pq.asIterable()) {
					que = (String) e.getProperty("question");
					qid = e.getKey();
			%>
			<tr id=<%=qid%>>
				<td align="center"><%=count%></td>
				<td><%=que%></td>
				<%-- <td><%=qid%></td> --%>
				<td><input type="button" value="Add Answer"
					onclick="QuestionId(event);show('ansDiv')"></td>
			</tr>

			<%
				count++;
				}
			%>

		</table>

		<div id="ansDiv" style="visibility: hidden">
			Give your Answer : <input type="text" name="answer" id="answer">
			<input type="button" value="submit" onclick="answerData();passData();show('success')">
		</div>
		<div style="visibility:hidden" id="success">
		<p>Successfully Added
		</p>
		<input type="button" name="home" value="back" onclick="goback()"></div>
	</center>
</body>

<script type="text/javascript">
	var questionId, answer;
	function goback()
	{
		window.location.href="Main.html";
	}
	function show(display) {
		document.getElementById(display).style.visibility = "visible";
	}
	function QuestionId(event) {
		questionId = event.target.parentElement.parentElement.getAttribute('id');
	}
	function answerData() {
		answer = document.getElementById("answer").value;
	}

	function passData() {
		var obj = {
			"Q_Id" : questionId,
			"Answer" : answer
		};

		jsonObj = JSON.stringify(obj);
		var xmlHttp = new XMLHttpRequest();
		var param = " ";
		
		xmlHttp.onreadystatechange = function() {
			if ((xmlHttp.readyState == 4) && (xmlHttp.status == 200)) {

				//document.getElementById("body").innerHTML = xmlHttp.responseText;
			}
		};
		xmlHttp.open("GET", "/answerServlet?param=" + jsonObj);
		xmlHttp.send();
	}
</script>
</html>