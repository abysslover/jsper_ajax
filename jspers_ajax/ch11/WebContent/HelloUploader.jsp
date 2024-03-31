<%@ page import="com.javawide.book.blackbook1.ch11.uploader.HelloUploader" language="java" 
contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="uploadListener" class="com.javawide.book.blackbook1.ch11.uploader.HelloProgressListener" 
scope="session"></jsp:useBean><%
	new HelloUploader(request, response, "/PDS/hello").setListener(uploadListener);
%>