<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%
	String userId = request.getParameter("userId");
	if(null == userId) userId = "Guest";
%>
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Hello Java Web Start</title>
</head>
<body>
	<object codebase="http://java.sun.com/update/1.6.0/jinstall-6-windows-i586.cab#Version=6,0,0,0"
	classid="clsid:5852F5ED-8BF4-11D4-A245-0080C6F74284" height="0" width="0">
		<param name="app" value="http://javawide.com:8088/ch14/jnlp/HelloChatServer.jnlp" />
		<param name="back" value="true" />
	</object>
	<object codebase="http://java.sun.com/update/1.6.0/jinstall-6-windows-i586.cab#Version=6,0,0,0"
	classid="clsid:5852F5ED-8BF4-11D4-A245-0080C6F74284" height="0" width="0">
		<param name="app" value="http://javawide.com:8088/ch14/jnlp/HelloChatClient.jsp?userId=<%= userId %>" />
		<param name="back" value="true" />
		<a href="http://java.sun.com/javase/downloads/ea.jsp">Download Java Web Start</a>
	</object>
</body>
</html>