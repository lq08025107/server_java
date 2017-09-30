/**
 * 
 */
package com.sdt.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.pool.DruidPooledConnection;
import com.sdt.config.Config4all;

import ch.qos.logback.core.db.DataSourceConnectionSource;

/**
 * @author liuqiang
 *
 */
public class DBManager {
	private final static String DBCONFIG = "dbconfig.properties";
	private DruidDataSource dds = null;
	public DBManager(){
		try {
			dds = (DruidDataSource)DruidDataSourceFactory.createDataSource(Config4all.getProperties(DBCONFIG));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public DruidDataSource getDDS(){
		return dds;
	}
	public static void main(String[] args) throws SQLException{
		DruidPooledConnection dpc = null;
		Statement stmt = null;
		ResultSet rset = null;
		   
		DBManager dbManager = new DBManager();
		DruidDataSource dds = dbManager.getDDS();
		dpc = dds.getConnection();
        stmt = dpc.createStatement();
        System.out.println("Executing statement.");
        rset = stmt.executeQuery("select * from alarmEvent");//该数据库中有 users 表
        System.out.println("Results:");
        int numcols = rset.getMetaData().getColumnCount();
        while(rset.next()) {
            for(int i=1;i<=numcols;i++) {
                System.out.print("\t" + rset.getString(i));
            }
            System.out.println("");
        }
	}
}
