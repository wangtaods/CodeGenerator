package com.tao.test.service;

import com.tao.test.model.LoginDefEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author 444683298
 * @email 444683298@qq.com
 * @date 2018-07-15 21:01:42
 */
public interface LoginDefService {
	
	LoginDefEntity queryObject(String id);
	
	List<LoginDefEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(LoginDefEntity loginDef);
	
	int update(LoginDefEntity loginDef);
	
	int delete(String id);
	
	int deleteBatch(String[] ids);
}
