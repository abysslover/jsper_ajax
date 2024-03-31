<%@ page language="java" contentType="text/xml; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="memo" class="com.javawide.book.blackbook1.ch12.board.TSQLMemo" scope="session"></jsp:useBean>
<jsp:setProperty name="memo" property="*" /><%
	memo.getMemo();
%><%= memo %>