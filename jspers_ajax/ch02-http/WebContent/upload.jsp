<%@page import="java.io.InputStream"%><%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%
	InputStream dis = request.getInputStream();
	byte[] buf = new byte[1024];
	int readed = 0;
	while((readed = dis.read(buf)) != -1) {
		out.print(new String(buf, 0, readed));
	}
%>