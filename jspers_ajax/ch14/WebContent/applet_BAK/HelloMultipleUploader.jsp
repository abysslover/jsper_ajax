<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Hello Applet</title>
</head>
<body>
	<applet code="com.javawide.book.blackbook1.ch14.applet.HelloMultipleUploader2.class"
	archive="SignedHelloApplet.jar" width="100%">
		<param name="HOST" value="<%= request.getLocalAddr() %>" />
		<param name="PORT" value="<%= request.getLocalPort() %>" />
		<param name="UPLOAD_URL" value="/ch11/HelloUploader.jsp" />
		<param name="LIMIT_FILE_SIZE" value="1048576000" />
	</applet>
</body>
</html>