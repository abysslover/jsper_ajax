<%@page import="com.javawide.book.blackbook1.ch11.files.HelloFileReader"
contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%
	String userFile = request.getParameter("userFile");
	String filePath = getServletContext().getRealPath("/fileexplorer");
	if(null != userFile) {
		filePath += "/" + userFile;
	}
	HelloFileReader.read(filePath, out);
%>