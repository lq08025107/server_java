/**
 * 
 */
package com.sdt.db;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.alibaba.druid.pool.DruidDataSource;
import com.sdt.entity.AlarmEvent;


/**
 * @author liuqiang
 *
 */
public class MybatisTest {
	private AlarmEvent alarmEvent;
	public AlarmEvent test1(){
		AlarmEvent event = new AlarmEvent();
		SqlSessionFactory sqlSessionFactory;
		try {
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader("mybatis.xml"), "userDefine");
			SqlSession session = sqlSessionFactory.openSession();                      
			System.out.println("class="+session.getConfiguration().getEnvironment().getDataSource().getClass().getName());
            DataSource ds = session.getConfiguration().getEnvironment().getDataSource();
            if(ds instanceof DruidDataSource){
                System.out.println("Yes");
            }else{
                System.out.println("No");
            }
            alarmEvent = session.selectOne("com.sdt.entity"+"."+"test1");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return alarmEvent;
	}
	
	public static void main(String[] args) throws IOException{
		String resource = "mybatis.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession session = sqlSessionFactory.openSession();
		AlarmEventMapper mapper = session.getMapper(AlarmEventMapper.class);
		List<AlarmEvent> event = mapper.selectAllEvent();
		System.out.println(event.size());
	}
}
