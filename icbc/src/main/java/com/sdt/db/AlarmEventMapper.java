/**
 * 
 */
package com.sdt.db;



import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.sdt.entity.AlarmEvent;

/**
 * @author liuqiang
 *
 */
public interface AlarmEventMapper {
	@Select("SELECT * FROM AlarmEvent")
	List<AlarmEvent> selectAllEvent();
}
