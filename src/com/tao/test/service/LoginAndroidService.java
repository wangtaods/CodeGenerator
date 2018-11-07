package com.tao.test.service;

import com.tao.test.model.LoginAndroidEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author 444683298
 * @email 444683298@qq.com
 * @date 2018-07-15 21:01:42
 */
public interface LoginAndroidService {
	
	LoginAndroidEntity queryObject(String id);
	
	List<LoginAndroidEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(LoginAndroidEntity loginAndroid);
	
	int update(LoginAndroidEntity loginAndroid);
	
	int delete(String id);
	
	int deleteBatch(String[] ids);
}
