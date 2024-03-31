<%@page import="java.io.File, com.javawide.book.blackbook1.ch15.fileexplorer.FileExplorer"
 contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Hello File Explorer</title>
<style type="text/css" title="currentStyle" media="screen">
	@import "gallery.css";
</style>
</head>
<body>
	<div id="folderContainer">
<%
	String userFolder = request.getParameter("folderName");
	if(null == userFolder) userFolder = "";
 	String pdsFolder = getServletContext().getRealPath("/fileexplorer");
 	System.out.println(pdsFolder + "/" + userFolder);
 	for(File f : FileExplorer.getFiles(pdsFolder + "/" + userFolder)) {
		if(f.isDirectory())
			out.print("<div class=\"directoryIcon\">");
		else if(f.isFile())
			out.print("<div class=\"fileIcon\">");
	out.print(f.getName());
	out.println("</div>");
}
%>
	</div>
</body>
</html>