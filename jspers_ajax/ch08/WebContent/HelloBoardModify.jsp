<%@ page import="com.javawide.book.blackbook1.ch08.dbms.JNDIDatabaseFacade,
com.javawide.book.blackbook1.ch08.board.Board" language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%
	Board board = new Board("BOARD200803");
	board.setFacade(new JNDIDatabaseFacade("jdbc/helloDB"));
	board.modify(request.getParameter("subject"), request
			.getParameter("content"), request.getParameter("id"));
	response.sendRedirect("HelloBoard.jsp");
%>