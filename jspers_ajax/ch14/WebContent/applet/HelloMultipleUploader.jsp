<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Hello Applet</title>
</head>
<body>
	<!--"CONVERTED_APPLET"-->
<!-- HTML CONVERTER -->
<object
    classid = "clsid:8AD9C840-044E-11D1-B3E9-00805F499D93"
    codebase = "http://java.sun.com/update/1.6.0/jinstall-6u40-windows-i586.cab#Version=6,0,0,12"
    WIDTH = "100%" >
    <PARAM NAME = CODE VALUE = "com.javawide.book.blackbook1.ch14.applet.HelloMultipleUploader2.class" >
    <PARAM NAME = ARCHIVE VALUE = "SignedHelloApplet.jar" >
    <param name = "type" value = "application/x-java-applet;version=1.6">
    <param name = "scriptable" value = "false">
    <PARAM NAME = "HOST" VALUE="<%= request.getServerName() %>" />
    <PARAM NAME = "PORT" VALUE="<%= request.getServerPort() %>" />
    <PARAM NAME = "UPLOAD_URL" VALUE="/ch11/HelloUploader.jsp" />
    <PARAM NAME = "LIMIT_FILE_SIZE" VALUE="1048576000" />
    <comment>
	<embed
            type = "application/x-java-applet;version=1.6" \
            CODE = "com.javawide.book.blackbook1.ch14.applet.HelloMultipleUploader2.class" \
            ARCHIVE = "SignedHelloApplet.jar" \
            WIDTH = "100%" \
            HOST ="<%= request.getServerName() %>" / \
            PORT ="<%= request.getServerPort() %>" / \
            UPLOAD_URL ="/ch11/HelloUploader.jsp" / \
            LIMIT_FILE_SIZE ="1048576000" /
	    scriptable = false
	    pluginspage = "http://java.sun.com/products/plugin/index.html#download">
	    <noembed>
            
            </noembed>
	</embed>
    </comment>
</object>

<!--
<APPLET CODE = "com.javawide.book.blackbook1.ch14.applet.HelloMultipleUploader2.class" ARCHIVE = "SignedHelloApplet.jar" WIDTH = "100%">
<PARAM NAME = "HOST" VALUE="<%= request.getServerName() %>" />
<PARAM NAME = "PORT" VALUE="<%= request.getServerPort() %>" />
<PARAM NAME = "UPLOAD_URL" VALUE="/ch11/HelloUploader.jsp" />
<PARAM NAME = "LIMIT_FILE_SIZE" VALUE="1048576000" />


</APPLET>
-->
<!--"END_CONVERTED_APPLET"-->

</body>
</html>