package com.javawide.book.blackbook1.ch08.dbms;

import java.sql.*;
import java.util.Hashtable;

import javax.sql.rowset.CachedRowSet;

import com.javawide.book.blackbook1.ch08.ICloseable;
import com.sun.rowset.CachedRowSetImpl;


public abstract class AbDatabaseFacade implements IDatabaseFacade, ICloseable{
	Hashtable<Integer, Object> parameters = new Hashtable<Integer, Object>();
	protected Connection con;
	private PreparedStatement pstmt;
	String sql;
	private ResultSet rs;
	protected abstract void connect() throws SQLException;
	public final CachedRowSet execute(String sql) {
		CachedRowSet crs = null;
		try {
			connect();
			crs = doExecute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return crs;
	}
	public final void executeUpdate(String sql) {
		try {
			connect();
			doExecuteUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}
	protected CachedRowSet doExecute(String sql) throws SQLException {
		pstmt = con.prepareStatement(sql);
		CachedRowSet crs = new CachedRowSetImpl();
		rs = pstmt.executeQuery();
		crs.populate(rs);
		return crs;
	}
	protected void doExecuteUpdate(String sql) throws SQLException{
		pstmt = con.prepareStatement(sql);
		pstmt.executeUpdate();
	}
	public final void close() {
		try {
			if(null != rs) {
				rs.close();
				rs = null;
			}
			if(null != pstmt) {
				pstmt.close();
				pstmt = null;
			}
			if(null != con) {
				con.close();
				con = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void setSQL(String sql) {
		this.sql = sql;
		this.parameters.clear();
	}
	public void setParameter(int index, Object value) {
		parameters.put(index, value);
	}
	public void executeUpdate() {
		try {
			connect();
			doExecuteUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}
	public CachedRowSet execute() {
		CachedRowSet crs = null;
		try {
			connect();
			crs = doExecute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return crs;
	}
	protected CachedRowSet doExecute() throws SQLException {
		pstmt = con.prepareStatement(sql);
		for(Integer i : parameters.keySet()) {
			pstmt.setObject(i, parameters.get(i));
		}
		CachedRowSet crs = new CachedRowSetImpl();
		rs = pstmt.executeQuery();
		crs.populate(rs);
		return crs;
	}
	protected void doExecuteUpdate() throws SQLException {
		pstmt = con.prepareStatement(sql);
		for(Integer i : parameters.keySet()) {
			pstmt.setObject(i, parameters.get(i));
		}
		pstmt.executeUpdate();
	}
}
