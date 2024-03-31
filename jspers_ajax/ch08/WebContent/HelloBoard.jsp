<%@ page import="com.javawide.book.blackbook1.ch08.board.Board, 
com.javawide.book.blackbook1.ch08.dbms.JNDIDatabaseFacade, 
javax.sql.rowset.CachedRowSet, java.sql.SQLException" language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"><%
	String currentPage = request.getParameter("page");
	int aPage = 1;
	if(null != currentPage) aPage = Integer.parseInt(currentPage);
	String keytype = request.getParameter("keytype");
	String keyword = request.getParameter("keyword");
	if("null".equals(keytype)) keytype = null;
	if("null".equals(keyword)) keyword = null;
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Hello Board</title>
<script language="Javascript" type="text/javascript">
	function view(id) {
		window.location = "HelloBoardView.jsp?id=" + id + "&page=<%= aPage %>";
	}
	function newPost() {
		window.location = "HelloBoardNewForm.jsp";
	}
</script>
</head>
<body>
	<table>
		<tr>
			<th>번호</th>
			<th>주제</th>
			<th>작성자</th>
			<th>작성 시간</th>
		</tr>
	<%
		Board board = new Board("BOARD200803");
		board.setFacade(new JNDIDatabaseFacade("jdbc/helloDB"));
		try {
			CachedRowSet rs = null;
			if(null == keytype && null == keyword) {
				rs = board.getPagedList(aPage);
			} else {
				rs = board.getPagedList(keytype, keyword, aPage);
			}
			while(rs.next()) {
	%>
		<tr onclick="view(<%= rs.getString("id") %>)">
			<td><%= rs.getString("id") %></td>
			<td><%= rs.getString("subject") %></td>
			<td><%= rs.getString("writer") %></td>
			<td><%= rs.getString("written") %></td>
		</tr>
	<%		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	%>
	</table><%
		String aTag = String.format("<a href=\"HelloBoard.jsp?page=1&keytype=%s&keyword=%s\">[시작]</a>", keytype, keyword);
		out.println(aTag);
		for(Long i : board.getPaging().getPageNumbers()) {
			aTag = String.format("<a href=\"HelloBoard.jsp?page=%d&keytype=%s&keyword=%s\">[%d]</a>", i, keytype, keyword, i);
			out.println(aTag);
		}
		aTag = String.format("<a href=\"HelloBoard.jsp?page=%d&keytype=%s&keyword=%s\">[끝]</a>", board.getPaging().getTotalPage(), keytype, keyword);
		out.println(aTag);
	%>
	<input type="button" value="새 글" onclick="newPost();" />
	<form action="HelloBoard.jsp">
		<input type="hidden" name="page" value="<%= aPage %>" />
		<input type="text" name="keyword" />
		<select name="keytype"">
			<option value="subject">제목</option>
			<option value="content">내용</option>
		</select>
		<input type="submit" value="검색" />
	</form>
</body>
</html>