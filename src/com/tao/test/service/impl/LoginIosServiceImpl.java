package com.tao.test.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.tao.test.dao.LoginIosDao;
import com.tao.test.model.LoginIosEntity;
import com.tao.test.service.LoginIosService;



@Service("loginIosService")
public class LoginIosServiceImpl implements LoginIosService {
	@Autowired
	private LoginIosDao loginIosDao;
	
	@Override
	public LoginIosEntity queryObject(String id){
		return loginIosDao.queryObject(id);
	}
	
	@Override
	public List<LoginIosEntity> queryList(Map<String, Object> map){
		return loginIosDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return loginIosDao.queryTotal(map);
	}
	
	@Override
	public void save(LoginIosEntity loginIos){
		loginIosDao.save(loginIos);
	}
	
	@Override
	public int update(LoginIosEntity loginIos){
		return loginIosDao.update(loginIos);
	}
	
	@Override
	public int delete(String id){
		return loginIosDao.delete(id);
	}
	
	@Override
	public int deleteBatch(String[] ids){
		return loginIosDao.deleteBatch(ids);
	}
	
}
