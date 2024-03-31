package com.javawide.book.blackbook1.ch08.dbms;

import java.sql.SQLException;

import javax.naming.*;
import javax.sql.DataSource;

public class JNDIDatabaseFacade extends AbDatabaseFacade {
	private Context initCtx;
	private String jndiName;
	public JNDIDatabaseFacade(String jndiName) {
		this.jndiName = jndiName;
	}
	protected void connect() throws SQLException {
		try {
			initCtx = new InitialContext();
			Context envCtx = (Context)initCtx.lookup("java:comp/env");
			DataSource ds = (DataSource)envCtx.lookup(jndiName);
			con = ds.getConnection();
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
}