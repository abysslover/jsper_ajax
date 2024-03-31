<%@ page import="com.javawide.book.blackbook1.ch08.board.Board,  
com.javawide.book.blackbook1.ch08.dbms.JNDIDatabaseFacade, java.util.ArrayList" language="java" 
contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Hello Board Modify Form</title>
</head>
<body>
	<%
		Board board = new Board("BOARD200803");
		board.setFacade(new JNDIDatabaseFacade("jdbc/helloDB"));
		String id = request.getParameter("id");
		ArrayList<String> data = board.getModifyData(id);
	%>
	<form action="HelloBoardModify.jsp">
		<input type="text" name="subject" value="<%= data.get(0) %>" />
		<textarea name="content"><%= data.get(1) %></textarea>
		<input type="hidden" name="id" value="<%= id %>" />
		<input type="submit" />
	</form>
	<input type="button" value="이전 페이지로"
	 onclick="window.location='HelloBoardView.jsp?id=<%= id %>&page=<%= request.getParameter("page") %>';" />
</body>
</html>