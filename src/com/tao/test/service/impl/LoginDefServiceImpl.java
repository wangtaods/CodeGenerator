package com.tao.test.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.tao.test.dao.LoginDefDao;
import com.tao.test.model.LoginDefEntity;
import com.tao.test.service.LoginDefService;



@Service("loginDefService")
public class LoginDefServiceImpl implements LoginDefService {
	@Autowired
	private LoginDefDao loginDefDao;
	
	@Override
	public LoginDefEntity queryObject(String id){
		return loginDefDao.queryObject(id);
	}
	
	@Override
	public List<LoginDefEntity> queryList(Map<String, Object> map){
		return loginDefDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return loginDefDao.queryTotal(map);
	}
	
	@Override
	public void save(LoginDefEntity loginDef){
		loginDefDao.save(loginDef);
	}
	
	@Override
	public int update(LoginDefEntity loginDef){
		return loginDefDao.update(loginDef);
	}
	
	@Override
	public int delete(String id){
		return loginDefDao.delete(id);
	}
	
	@Override
	public int deleteBatch(String[] ids){
		return loginDefDao.deleteBatch(ids);
	}
	
}
