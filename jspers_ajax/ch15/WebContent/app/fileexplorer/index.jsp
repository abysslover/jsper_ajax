<%@page import="java.io.File, com.javawide.book.blackbook1.ch15.fileexplorer.FileExplorer"
 contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%
	String userFolder = request.getParameter("folderName");
	if(null == userFolder) userFolder = "";
 	String pdsFolder = getServletContext().getRealPath("/fileexplorer");
 	for(File f : FileExplorer.getFiles(pdsFolder + "/" + userFolder)) {
		if(f.isDirectory())
			out.print("<div class=\"directoryIcon\">");
		else if(f.isFile())
			out.print("<div class=\"fileIcon\">");
	out.print(f.getName());
	out.println("</div>");
}
%>