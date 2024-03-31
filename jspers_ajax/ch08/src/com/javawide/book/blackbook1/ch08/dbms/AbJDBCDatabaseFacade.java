package com.javawide.book.blackbook1.ch08.dbms;

import java.io.IOException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public abstract class AbJDBCDatabaseFacade extends AbDatabaseFacade {
	protected Properties prop;
	private String jdbcDriver;
	protected String connectionString;
	private String id;
	private String pw;	
	public AbJDBCDatabaseFacade(String propPath) {
		loadProperties(propPath);
		setProperties();
	}
	private void loadProperties(String propPath) {
		InputStream propStream = getClass().getResourceAsStream(propPath);
		if(null == propStream) return;
		prop = new Properties();
		try {
			prop.load(propStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void setProperties() {
		buildConnectionString();
		this.jdbcDriver = prop.getProperty("driver");
		this.id = prop.getProperty("id");
		this.pw = prop.getProperty("pw");
	}
	protected abstract void buildConnectionString();
	protected void connect() throws SQLException {
		try {
			Class.forName(jdbcDriver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		con = DriverManager.getConnection(connectionString, id, pw);
	}
}