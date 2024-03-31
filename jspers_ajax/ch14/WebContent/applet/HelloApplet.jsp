<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Hello Applet</title>
</head>
<body>
	<applet code="com.javawide.book.blackbook1.ch14.applet.HelloApplet.class"
	archive="SignedHelloApplet.jar" width="100%">
		<param name="name" value="<%= request.getParameter("name") %>" />
	</applet>
</body>
</html>