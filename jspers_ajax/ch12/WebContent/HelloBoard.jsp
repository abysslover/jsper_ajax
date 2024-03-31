<%@ page import="com.javawide.book.blackbook1.ch12.board.behavior.BoardBehaviorFactory,com.javawide.book.blackbook1.ch12.board.IBehavior,org.apache.jasper.runtime.JspRuntimeLibrary"
language="java" contentType="text/xml; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="board" class="com.javawide.book.blackbook1.ch12.board.TSQLBoard" scope="session"></jsp:useBean>
<%
	IBehavior behavior = BoardBehaviorFactory.getInstance(request.getParameter("behavior"));
	JspRuntimeLibrary.introspect(behavior, request);
	board.setBehavior(behavior);
%><%= board %>