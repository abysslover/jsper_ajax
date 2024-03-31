<%@ page import="com.javawide.book.blackbook1.ch08.dbms.JNDIDatabaseFacade,
 javax.sql.rowset.CachedRowSet, java.sql.SQLException" 
language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JNDI Test</title>
</head>
<body>
<%
	JNDIDatabaseFacade facade = new JNDIDatabaseFacade("jdbc/helloDB");
	CachedRowSet rs = facade.execute("SELECT NAME FROM TEST");
	try {
		while(rs.next()) {
			out.println(rs.getString("NAME"));
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
%>
</body>
</html>