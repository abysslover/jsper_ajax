<%@page contentType="text/html;charset=UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<HTML>
<HEAD>
<TITLE>Result</TITLE>
</HEAD>
<BODY>
<H1>Result</H1>

<jsp:useBean id="sampleGreetServiceProxyid" scope="session" class="com.javawide.book.blackbook1.ch04.webservice.GreetServiceProxy" />
<%
if (request.getParameter("endpoint") != null && request.getParameter("endpoint").length() > 0)
sampleGreetServiceProxyid.setEndpoint(request.getParameter("endpoint"));
%>

<%
String method = request.getParameter("method");
int methodID = 0;
if (method == null) methodID = -1;

if(methodID != -1) methodID = Integer.parseInt(method);
boolean gotMethod = false;

try {
switch (methodID){ 
case 2:
        gotMethod = true;
        String op1_0id=  request.getParameter("op15");
        int op1_0idTemp  = Integer.parseInt(op1_0id);
        String op2_1id=  request.getParameter("op27");
        int op2_1idTemp  = Integer.parseInt(op2_1id);
        int add2mtemp = sampleGreetServiceProxyid.add(op1_0idTemp,op2_1idTemp);
        String tempResultreturnp3 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(add2mtemp));
        %>
        <%= tempResultreturnp3 %>
        <%
break;
case 9:
        gotMethod = true;
        String op1_2id=  request.getParameter("op112");
        int op1_2idTemp  = Integer.parseInt(op1_2id);
        String op2_3id=  request.getParameter("op214");
        int op2_3idTemp  = Integer.parseInt(op2_3id);
        double divide9mtemp = sampleGreetServiceProxyid.divide(op1_2idTemp,op2_3idTemp);
        String tempResultreturnp10 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(divide9mtemp));
        %>
        <%= tempResultreturnp10 %>
        <%
break;
case 16:
        gotMethod = true;
        String op1_4id=  request.getParameter("op119");
        int op1_4idTemp  = Integer.parseInt(op1_4id);
        String op2_5id=  request.getParameter("op221");
        int op2_5idTemp  = Integer.parseInt(op2_5id);
        long multiply16mtemp = sampleGreetServiceProxyid.multiply(op1_4idTemp,op2_5idTemp);
        String tempResultreturnp17 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(multiply16mtemp));
        %>
        <%= tempResultreturnp17 %>
        <%
break;
case 23:
        gotMethod = true;
        String op1_6id=  request.getParameter("op126");
        int op1_6idTemp  = Integer.parseInt(op1_6id);
        String op2_7id=  request.getParameter("op228");
        int op2_7idTemp  = Integer.parseInt(op2_7id);
        int minus23mtemp = sampleGreetServiceProxyid.minus(op1_6idTemp,op2_7idTemp);
        String tempResultreturnp24 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(minus23mtemp));
        %>
        <%= tempResultreturnp24 %>
        <%
break;
case 30:
        gotMethod = true;
        String name_8id=  request.getParameter("name33");
            java.lang.String name_8idTemp = null;
        if(!name_8id.equals("")){
         name_8idTemp  = name_8id;
        }
        java.lang.String doGreet30mtemp = sampleGreetServiceProxyid.doGreet(name_8idTemp);
if(doGreet30mtemp == null){
%>
<%=doGreet30mtemp %>
<%
}else{
        String tempResultreturnp31 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(doGreet30mtemp));
        %>
        <%= tempResultreturnp31 %>
        <%
}
break;
}
} catch (Exception e) { 
%>
exception: <%= e %>
<%
return;
}
if(!gotMethod){
%>
result: N/A
<%
}
%>
</BODY>
</HTML>