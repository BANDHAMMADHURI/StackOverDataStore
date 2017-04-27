<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="com.google.appengine.labs.repackaged.org.json.JSONException,
com.google.appengine.labs.repackaged.org.json.JSONObject,javax.servlet.RequestDispatcher
 "%>
  <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Answer Process</title>
</head>
<body>

<%! String que_Id;  %>
<%
try {
	JSONObject obj = new JSONObject(request.getParameter("param"));
	//obj=(request.getParameter("param"));
			que_Id=obj.getString("Q_Id");
		
			out.println(que_Id);
			out.println("hi");
			
			/* RequestDispatcher rd = request.getRequestDispatcher("AnswerProcess.jsp");
			rd.forward(request, response); */
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
%>
<!-- <form name="answerForm" action="answerServlet" method="post"><br>
 --><%--  Question Id :<input type="text" name="question_Id" value="<% s %>" id="question_Id"> --%>
Answer : <input type="text" name="answer" id="answer">
<p><% %></p>
<input type="button" name="submit" value="submit" onclick="retrievedata();hide()">
<!-- </form> -->
</body>
 <script>
	function hide() {
			document.getElementById("button").style.visibility = "hidden";
	}
	function retrieveData() {
		console.log(que_Id);
		var answer=document.getElementById("answer").value;
		var q_Id=que_Id;
		var obj={
				"Answer" : answer,
				"Question_Id" : q_Id				
		};
		var json=JSON.stringify(obj);
		String ansQId="";
		xmlHttp = new XMLHttpRequest();
		xmlHttp.open("POST", "/answerServlet?ansQId="+json);
		xmlHttp.send();
		xmlHttp.onreadystatechange = function() {
			if ((xmlHttp.readyState == 4) && (xmlHttp.status == 200)) {
				document.getElementById("show").innerHTML = xmlHttp.responseText;
			}
		};
	}
</script> 
</html>
