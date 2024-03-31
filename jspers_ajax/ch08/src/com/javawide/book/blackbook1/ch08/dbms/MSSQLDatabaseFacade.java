package com.javawide.book.blackbook1.ch08.dbms;

import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;

public class MSSQLDatabaseFacade extends AbJDBCDatabaseFacade {
	public MSSQLDatabaseFacade(String propPath) {
		super(propPath);
	}

	protected void buildConnectionString() {
		StringBuffer conStr = new StringBuffer(20);
		conStr.append("jdbc:microsoft:sqlserver://")
			.append(prop.getProperty("url"))
			.append(":")
			.append(prop.getProperty("port"))
			.append(";DatabaseName=")
			.append(prop.getProperty("database"));
		this.connectionString = conStr.toString();
	}
	public static void main(String[] args) {
		MSSQLDatabaseFacade facade = new MSSQLDatabaseFacade("/db.properties");
		/*facade.executeUpdate("CREATE TABLE TEST(TID INT IDENTITY, NAME VARCHAR(100))");*/
		/*facade.executeUpdate("INSERT TEST(NAME) VALUES('magneto')");*/
		/*facade.setSQL("INSERT TEST(NAME) VALUES(?)");
		facade.setParameter(1, "Juggernaut");
		facade.executeUpdate();*/
		//CachedRowSet rs = facade.execute("SELECT TID FROM TEST");
		facade.setSQL("SELECT TID FROM TEST WHERE TID=?");
		facade.setParameter(1, 2);
		CachedRowSet rs = facade.execute();
		try {
			while(rs.next()) {
				System.out.println(rs.getString("TID"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
