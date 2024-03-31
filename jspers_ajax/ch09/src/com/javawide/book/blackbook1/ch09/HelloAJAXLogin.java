package com.javawide.book.blackbook1.ch09;

import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;

import com.javawide.book.blackbook1.ch08.dbms.IDatabaseFacade;
import com.javawide.book.blackbook1.ch08.dbms.MSSQLDatabaseFacade;

public class HelloAJAXLogin {
	public static void main(String[] args) {
		HelloAJAXLogin login = new HelloAJAXLogin();
		System.out.println(login.login("hello", "world"));
		System.out.println(login.login("hello", "worlderror"));
		System.out.println(login.login("xmen", "magneto"));
		System.out.println(login.login("xmen", "magnetoerror"));
		System.out.println(login.login("abyss", "lover"));
		System.out.println(login.login("abyss", "lovererror"));
		System.out.println(login.login("korean", "ginseng"));
		System.out.println(login.login("korean", "ginsengerror"));
	}

	public String login(String id, String pw) {
		IDatabaseFacade facade = new MSSQLDatabaseFacade("/db.properties");
		facade.setSQL("SELECT COUNT(*) AS matched FROM HELLO_LOGIN WHERE userID=? AND userPW=?");
		facade.setParameter(1, id);
		facade.setParameter(2, pw);
		CachedRowSet rs = facade.execute();
		int matchCount = 0;
		try {
			if(rs.next()) matchCount = rs.getInt("matched");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (1 == matchCount) ? "로그인 성공" : "로그인 실패";
	}
}
