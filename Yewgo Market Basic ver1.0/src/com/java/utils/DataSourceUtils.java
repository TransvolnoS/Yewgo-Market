package com.java.utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DataSourceUtils {
	private static DataSource ds=new ComboPooledDataSource();
	private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();
	
	
	//鑾峰彇杩炴帴姹�
	public static DataSource getDataSource(){
		return ds;
	}
	
	/**
	 * 浠庡綋鍓嶇嚎绋嬩腑鑾峰彇杩炴帴
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException{
		Connection conn = tl.get();
		//鑻ユ槸绗竴娆¤幏鍙� 鏄痭ull
		if(conn	== null){
			conn=ds.getConnection();
			
			//灏嗚繖涓繛鎺ュ拰褰撳墠绾跨▼缁戝畾
			tl.set(conn);
		}
		
		return conn;
	}
	
	/**
	 *	寮�鍚簨鍔�
	 * @throws SQLException 
	 */
	public static void beginTransaction() throws SQLException{
		//鑾峰彇杩炴帴
		Connection conn = getConnection();
		
		//寮�鍚簨鍔�
		conn.setAutoCommit(false);
	}
	
	
	/**
	 * 鎻愪氦浜嬪姟涓旈噴鏀捐祫婧�
	 */
	public static void commitAndClose(){
		try {
			//鑾峰彇杩炴帴
			Connection conn = getConnection();
			
			//鎻愪氦浜嬪姟
			if(conn != null){
				conn.commit();
			}
			
			closeConn(conn);
		} catch(Exception e){
			
		}
		
	}
	
	/**
	 * 鍥炴粴浜嬪姟涓旈噴鏀捐祫婧�
	 */
	public static void rollbackAndClose(){
		try {
			//鑾峰彇杩炴帴
			Connection conn = getConnection();
			
			//鍥炴粴浜嬪姟
			if(conn != null){
				conn.rollback();
			}
			
			closeConn(conn);
			
		} catch (SQLException e) {
			//
		}
	}
	
	/**
	 * 閲婃斁璧勬簮 涓斿拰褰撳墠绾跨▼瑙ｇ粦
	 * @param conn
	 */
	private static void  closeConn(Connection conn){
		try {
			//閲婃斁璧勬簮
			if(conn != null){
				conn.close();
			}
			//灏嗙嚎绋嬪拰杩炴帴瑙ｇ粦
			tl.remove();
		} catch (Exception e) {
		}
		
		conn = null;
	}
}
