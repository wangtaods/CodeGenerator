package com.tao.test.service;

import com.tao.test.model.LoginIosEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author 444683298
 * @email 444683298@qq.com
 * @date 2018-07-15 21:01:42
 */
public interface LoginIosService {
	
	LoginIosEntity queryObject(String id);
	
	List<LoginIosEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(LoginIosEntity loginIos);
	
	int update(LoginIosEntity loginIos);
	
	int delete(String id);
	
	int deleteBatch(String[] ids);
}
