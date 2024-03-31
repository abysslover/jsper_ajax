<%@ page import="com.javawide.book.blackbook1.ch08.board.Board, 
com.javawide.book.blackbook1.ch08.dbms.JNDIDatabaseFacade" language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"><%
	String currentPage = request.getParameter("page");
	int aPage = 1;
	if(null != currentPage) aPage = Integer.parseInt(currentPage);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Hello Board View</title>
<script language="Javascript" type="text/javascript">
	function modify(id) {
		window.location = "HelloBoardModifyForm.jsp?id=" + id + "&page=<%= aPage %>";
	}
	function deletePost(id) {
		window.location = "HelloBoardDelete.jsp?id=" + id;
	}
</script>
</head>
<body>
	<%
		Board board = new Board("BOARD200803");
		board.setFacade(new JNDIDatabaseFacade("jdbc/helloDB"));
	%><%= board.view(request.getParameter("id")) %>
	<input type="button" value="수정" onclick="modify(<%= aPage %>);" />
	<input type="button" value="삭제" onclick="deletePost(<%= request.getParameter("id") %>);" />
	<input type="button" value="이전 페이지로" onclick="window.location='HelloBoard.jsp?page=<%= aPage %>';" />
</body>
</html>