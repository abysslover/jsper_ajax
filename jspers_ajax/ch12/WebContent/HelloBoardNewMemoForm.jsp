<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<form id="HelloBoardNewMemoForm" name="HelloBoardNewForm">
<table>
	<tr>
		<th>구분</th>
		<th>내용</th>
	</tr>
	<tr>
		<td>작성자</td>
		<td><input type="text" id="writer" name="writer" /></td>
	</tr>
	<tr>
		<td>내용</td>
		<td><textarea id="content" name="content"></textarea></td>
	</tr>
</table>
</form>
<input type="button" id="HelloBoardNewMemoWrite" name="HelloBoardNewMemoWrite" value="등록 하기" />
<input type="button" id="HelloBoardNewMemoCancel" name="HelloBoardNewMemoCancel" value="등록 취소" />