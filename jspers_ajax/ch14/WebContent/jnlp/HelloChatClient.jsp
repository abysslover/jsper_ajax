<%@ page language="java" contentType="application/x-java-jnlp-file; charset=UTF-8" pageEncoding="UTF-8"%>
<% String userId = request.getParameter("userId"); %>
<?xml version="1.0" encoding="utf-8"?>
<jnlp spec="6.0+" codebase="http://javawide.com:8088/ch14/jnlp/"
	href="HelloChatClient.jsp?userId=<%= userId %>">
	<information>
		<title>Hello Chat Client</title>
		<vendor>Sunchon National University DB &amp; IR Lab</vendor>
		<homepage href="http://javawide.com" />
		<description>Java Web Start 채팅 클라이언트</description>
		<description kind="short">JNLP와 AWT, NIO를 사용한 채팅 클라이언트</description>
		<offline-allowed />
	</information>
	<security>
		<all-permissions />
	</security>
	<update check="timeout" policy="always"/>
	<resources>
		<java version="1.6+" href="http://java.sun.com/products/autodl/j2se" />
		<jar href="SignedHelloApplet.jar" main="true"/>
	</resources>
	<application-desc main-class="com.javawide.book.blackbook1.ch14.jnlp.ChatClientGUI">
		<argument>javawide.com</argument>
		<argument>9090</argument>
		<argument><%= userId %></argument>
	</application-desc>
</jnlp>