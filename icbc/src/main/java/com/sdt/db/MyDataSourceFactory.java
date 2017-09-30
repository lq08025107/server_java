/**
 * 
 */
package com.sdt.db;

import javax.sql.DataSource;

import org.apache.ibatis.datasource.unpooled.UnpooledDataSource;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * @author liuqiang
 *
 */
public class MyDataSourceFactory extends UnpooledDataSource{
	private DataSource dataSource = null;
	public MyDataSourceFactory(){
		this.dataSource = new DruidDataSource();
	}
}
