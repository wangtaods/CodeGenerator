package com.tao.test.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.tao.test.dao.LoginAndroidDao;
import com.tao.test.model.LoginAndroidEntity;
import com.tao.test.service.LoginAndroidService;



@Service("loginAndroidService")
public class LoginAndroidServiceImpl implements LoginAndroidService {
	@Autowired
	private LoginAndroidDao loginAndroidDao;
	
	@Override
	public LoginAndroidEntity queryObject(String id){
		return loginAndroidDao.queryObject(id);
	}
	
	@Override
	public List<LoginAndroidEntity> queryList(Map<String, Object> map){
		return loginAndroidDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return loginAndroidDao.queryTotal(map);
	}
	
	@Override
	public void save(LoginAndroidEntity loginAndroid){
		loginAndroidDao.save(loginAndroid);
	}
	
	@Override
	public int update(LoginAndroidEntity loginAndroid){
		return loginAndroidDao.update(loginAndroid);
	}
	
	@Override
	public int delete(String id){
		return loginAndroidDao.delete(id);
	}
	
	@Override
	public int deleteBatch(String[] ids){
		return loginAndroidDao.deleteBatch(ids);
	}
	
}
